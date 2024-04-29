# task5 example project

Build:
mvn package
docker-compose build

Run:
docker-compose up -d

to connect to Postgres DB:
jdbc:postgresql://localhost:5432/task5
user: task5
password: task5

to check app is up:
http://127.0.0.1:8080/actuator/health


Screenshots in screenshots folder
