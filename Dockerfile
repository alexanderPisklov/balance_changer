FROM openjdk:17

EXPOSE 8080

ADD build/libs/balance-change-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=compose", "-jar", "/app/app.jar"]
