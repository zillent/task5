FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} task5.jar

ENTRYPOINT ["java","-jar","/task5.jar"]
