# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
# copy application jar (with libraries inside)
COPY target/ordermng-api-1.0-SNAPSHOT.jar /ordermng-api-1.0-SNAPSHOT.jar
# specify default command
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=test", "/ordermng-api-1.0-SNAPSHOT.jar"]