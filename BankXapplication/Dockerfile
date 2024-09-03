FROM maven:3.9.9-jdk-11 AS build 
WORKDIR /BankXapplication
COPY . .
RUN mvn clean install

FROM openjdk:11-jre-slim
WORKDIR /BankXapplication
COPY --from=build /BankXapplication/target/BankApplication-1.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"] 
