#ci/cd
FROM openjdk:11-alpine
COPY ./build/libs/server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]