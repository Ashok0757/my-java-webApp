# Use Maven to build the application first
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Use Tomcat to run the built WAR file
FROM tomcat:11.0-jdk17
COPY --from=builder /app/target/CandidateInfo-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the new port
EXPOSE 8096

# Update server.xml to change the port to 8096
RUN sed -i 's/port="8080"/port="8096"/g' /usr/local/tomcat/conf/server.xml
