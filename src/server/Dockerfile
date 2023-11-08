FROM maven:3.6.3-openjdk-17-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -q

CMD mvn spring-boot:run

