FROM openjdk:11
WORKDIR /usr/src/app/py-test
# not sure
COPY . .
ENTRYPOINT ["java", "-jar", "app.jar"]