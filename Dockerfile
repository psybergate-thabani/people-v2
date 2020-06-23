FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8083
COPY ./target/people.jar people.jar
ENTRYPOINT ["java", "-jar", "/people.jar"]
