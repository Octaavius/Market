# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's JAR file (adjust this name if necessary)
ARG JAR_FILE=build/libs/MyMarket-0.0.1-SNAPSHOT.jar

# Add the application's JAR file to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
