FROM maven:3.6.3-amazoncorretto-8 AS builder
WORKDIR backend

COPY pom.xml .
RUN mvn dependency:go-offline --batch-mode

COPY src/ src/
RUN mvn package --batch-mode --offline -DskipTests

FROM amazoncorretto:8
WORKDIR backend
COPY --from=builder backend/target/*.jar .
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=swarm", "backend-1.0.0.jar"]