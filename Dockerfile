FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY ./target/people.jar people.jar
ENTRYPOINT ["java", "-jar", "/people.jar"]