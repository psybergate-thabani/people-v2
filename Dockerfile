FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY ./people/people-module/target/people-0.0.1-SNAPSHOT.jar people.jar
ENTRYPOINT ["java", "-jar", "/people.jar"]