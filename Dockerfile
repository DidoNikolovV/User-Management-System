FROM amazoncorretto:17.0.6

COPY build/libs/user-management-system.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]