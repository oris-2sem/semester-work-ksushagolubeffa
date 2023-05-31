FROM maven AS build
COPY src /home/application/src
COPY pom.xml /home/application
USER root

FROM openjdk:17
COPY --from=build /home/application/target/app.jar /usr/local/lib/app.jar
EXPOSE 8080