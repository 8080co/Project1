FROM maven:3.6.1-jdk-8-alpine
ARG JAR_FILE=target/*.jar
COPY $JAR_FILE /app.jar
CMD ["java","-jar","app.jar"]