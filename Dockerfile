FROM openjdk:11
WORKDIR /usr/src/app/py-test
# not sure
COPY . .
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]