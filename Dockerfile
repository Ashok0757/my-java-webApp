FROM tomcat:11.0-jdk17

# Copy the WAR file to the Tomcat webapps directory
COPY target/CandidateInfo-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the new port
EXPOSE 8096

# Update server.xml to change the port to 8096
RUN sed -i 's/port="8080"/port="8096"/g' /usr/local/tomcat/conf/server.xml