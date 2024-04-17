FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} BillingService-0.0.1.jar

ENTRYPOINT ["java", "-jar", "/BillingService-0.0.1.jar"]

EXPOSE 8084

