FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY ./people/people-module/target/people-module-0.0.1-SNAPSHOT.jar people-module.jar
ENTRYPOINT ["java", "-jar", "/people-module.jar"]