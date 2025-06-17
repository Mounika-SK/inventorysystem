# Use the official OpenJDK image as the base
FROM openjdk:17-jdk-slim
 
# Set the working directory inside the container
WORKDIR /app
 
# Copy the application JAR file to the container
COPY target/inventorysystem-0.0.1-SNAPSHOT.war /app/inventorysystem-0.0.1-SNAPSHOT.war
 
# Expose the port your application runs on
EXPOSE 8090
 
# Define the entry point for running the application
CMD ["java", "-jar", "inventorysystem-0.0.1-SNAPSHOT.war"]