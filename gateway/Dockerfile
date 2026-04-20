# Use lightweight Java runtime
FROM eclipse-temurin:17-jdk-jammy
# Set working directory
WORKDIR /app

# Copy jar file into container
COPY target/*.jar app.jar
# Expose port (Spring Boot default)
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]