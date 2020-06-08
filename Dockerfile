FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY /$PROD_USER/.m2/repository/com/$DOCKER_USERNAME/people/people/0.0.1-SNAPSHOT/people-0.0.1-SNAPSHOT.jar people.jar
ENTRYPOINT ["java", "-jar", "/people.jar"]