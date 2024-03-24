FROM amazoncorretto:17.0.7-alpine
ADD target/apirestsqs-0.0.1-SNAPSHOT.jar apirestsqs-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "apirestsqs-0.0.1-SNAPSHOT.jar"]
EXPOSE 80