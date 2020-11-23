FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
