# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the build artifact from the target directory to the working directory in the container
COPY build/libs/vk-quote-bot-1.0.0.jar app.jar

# Copy the .env file to the working directory in the container
COPY .env .env

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-Dspring.profiles.active=default", "-jar", "app.jar"]