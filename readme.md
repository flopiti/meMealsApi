
option 1:
This option does not use any build/jar file. 

- Run the backend locally by running the MeMealsApplication.java file
- docker compose -f docker-compose.dev.yml db


option 2:
This option takes an already built jar file.

- Run the backend on docker by copying the jar file
- docker compose -f docker-compose.dev.yml db meal-api

