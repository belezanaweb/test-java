FROM maven:3.6.2-jdk-8

WORKDIR /app
COPY . /app
EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
