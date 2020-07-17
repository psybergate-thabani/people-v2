package com.psybergate.people.module.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class QueueConfig {

    @Bean
    public TopicExchange peopleExchange(@Value("${queue.people.exchange}") String peopleExchangeName) {
        return new TopicExchange(peopleExchangeName);
    }

    @Bean
    public Queue peopleChange(@Value("${queue.people.change.name}") String queueName, @Value("${queue.people.durable}") String durableStr) {
        boolean durable = Boolean.valueOf(durableStr);
        return new Queue(queueName, durable);
    }

    @Bean
    public Binding peopleChangeBinding(Queue peopleChange, TopicExchange peopleExchange, @Value("${queue.people.change.route}") String route) {
        return BindingBuilder.bind(peopleChange).to(peopleExchange).with(route);
    }
}

