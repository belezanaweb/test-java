FROM adoptopenjdk/openjdk8
MAINTAINER Andre Barroso
WORKDIR /var/www
COPY . /var/www
ENTRYPOINT java -jar /var/www/test-java-0.0.1-SNAPSHOT.jar
EXPOSE 8080
