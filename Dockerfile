FROM openjdk:8-jdk-alpine
MAINTAINER "rderociml@gmail.com"
RUN addgroup -S meuproduto && adduser -S meuproduto -G meuproduto
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]