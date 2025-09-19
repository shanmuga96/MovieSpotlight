Running the Movie Spotlight Application

Clone the repository

git clone https://github.com/<username>/moviespotlight.git
cd moviespotlight


Build the project

mvn clean install


Run the application

mvn spring-boot:run


Access API

The application runs by default on http://localhost:8080/.

Endpoints:

GET /movies/oscar?title=<movie_name> – Check if the movie won Best Picture.

POST /movies/rate – Submit a rating for a movie.

GET /movies/top – Retrieve the top 10 movies based on ratings and box office value.

Dependencies

Java 11 or 17

Maven

Spring Boot

Hibernate/JPA