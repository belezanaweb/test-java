FROM maven:3.3-jdk-8

MAINTAINER Jackson Paiva

RUN mkdir -p /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean install -DskipTests

CMD ["java", "-jar", "-Dspring.profiles.active=default" ,"/opt/app/target/test-java-0.0.1-SNAPSHOT.jar"]

EXPOSE 8666
