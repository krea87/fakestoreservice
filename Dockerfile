FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]


#FROM eclipse-temurin:21-jdk
#WORKDIR /app

#COPY target/fakestoreservice-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]