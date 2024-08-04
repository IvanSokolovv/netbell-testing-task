FROM openjdk:17-alpine3.14

LABEL author="Sokolov Ivan"
LABEL email="ivan.sklvvv@yandex.ru"

ENV SERVER_PORT=60
ARG JAR_FILE_NAME=netbell-testing-task-0.0.1-SNAPSHOT.jar

WORKDIR ./app
COPY ./target/$JAR_FILE_NAME ./

EXPOSE 6060

ENTRYPOINT ["java", "-jar", "netbell-testing-task-0.0.1-SNAPSHOT.jar"]
