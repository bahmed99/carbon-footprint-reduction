FROM maven AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/server-0.0.1-SNAPSHOT.jar server.jar
EXPOSE 8080
CMD ["java", "-jar", "server.jar"]
