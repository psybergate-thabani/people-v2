package com.psybergate.people.module.messaging;

import com.google.gson.Gson;
import com.psybergate.people.module.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Component
@Slf4j
public class PeopleMessageResourceImpl implements PeopleMessageResource {

    private final RabbitTemplate rabbitTemplate;

    private final String peopleExchangeName;

    private final String peopleRoute;

    private final Gson gson;

    @Autowired
    public PeopleMessageResourceImpl(final RabbitTemplate rabbitTemplate,
                                     @Value("${queue.people.exchange}") final String peopleExchangeName,
                                     @Value("${queue.people.change.route}") final String peopleRoute,
                                     final Gson gson) {
        this.rabbitTemplate = rabbitTemplate;
        this.peopleExchangeName = peopleExchangeName;
        this.peopleRoute = peopleRoute;
        this.gson = gson;
    }

    @Override
    @Transactional
    public void broadcastTerminateEmployee(Employee employee) {
        EmployeeMessage envelope = new EmployeeMessage(EventType.TERMINATED, employee);
        log.info("Sending a message for employee to be terminates. Employee {}", employee);
        send(peopleExchangeName, peopleRoute, gson.toJson(envelope));
    }

    private void send(@NotNull final String exchangeName, @NotNull final String route, @NotNull final String message) {
        rabbitTemplate.convertAndSend(exchangeName, route, message);
    }
}

