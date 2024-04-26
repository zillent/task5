# task5 example project

Init and run Postgres:

1. cd db
2. docker build -t task5-db .
3. docker run --name task5-db -p 5432:5432 -d task5-db

to connect to Postgres DB:
jdbc:postgresql://localhost:5432/task5
user: task5
password: task5

Build:
mvn package

Run:
java -jar target/task5-0.0.1-SNAPSHOT.jar

