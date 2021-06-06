FROM openjdk:15
WORKDIR /usr/src/app/t-server
COPY . .
COPY ./target/demo-0.jar ./src/main/resources/data/
ENTRYPOINT ["java", "-jar", "./target/demo-0.jar"]