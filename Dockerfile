FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY ./target/pipelines-0.0.1-SNAPSHOT.jar pipelines.jar
ENTRYPOINT ["java", "-jar", "/pipelines.jar"]