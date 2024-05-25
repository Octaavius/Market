# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8082 available to the world outside this container
EXPOSE 8082

# The application's JAR file (adjust this name if necessary)
ARG JAR_FILE=build/libs/market-0.0.1-SNAPSHOT.jar

# Add the application's JAR file to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
