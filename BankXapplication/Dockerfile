FROM maven:3.8.5-openjdk-17 AS build 
WORKDIR /BankXapplication
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /BankXapplication
COPY --from=build /BankXapplication/target/BankApplication-1.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"] 
