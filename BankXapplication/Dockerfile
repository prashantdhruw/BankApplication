FROM openjdk:11-jre-slim

WORKDIR /BankXapplication

COPY target/BankApplication-1.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]