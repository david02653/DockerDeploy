FROM openjdk:11
WORKDIR /usr/src/app/t-server
# not sure
COPY . .
ENTRYPOINT ["java", "-jar", "demo-0.jar"]