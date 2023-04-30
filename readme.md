
option 1:
This option does not use any build/jar file. 

- Run the backend locally by running the MeMealsApplication.java file
- docker compose -f docker-compose.dev.yml db

```
java -Dspring.profiles.active=local -jar ./target/meMealsApi-0.0.1-SNAPSHOT.jar
```
or run with with Docker Compose using
```
docker compose -f docker-compose.dev.yml up --build