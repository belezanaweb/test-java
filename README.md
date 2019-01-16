# Backend Test

I'm using Spring Boot with Java 8, Redis for cache and MongoDB as Database

# Docker 
To run local just install java 8 and docker

docker run --name some-mongo -d -p 27017:27017 mongo

docker run --name some-redis -d -p 6379:6379 redis

Go to project folder and execute:
mvn spring-boot:run