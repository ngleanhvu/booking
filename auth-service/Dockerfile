# ----- Build Stage -----
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy toàn bộ project (quan trọng vì parent POM nằm ở trên)
COPY ../ /app

# Build riêng module discovery-server
RUN mvn -B clean package -pl auth-service -am -DskipTests

# ----- Runtime Stage -----
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/auth-service/target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
