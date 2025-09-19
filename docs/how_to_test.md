Testing the Application

1. Use Postman or cURL to test the endpoints.

2. Verify Oscar winner functionality:

GET http://localhost:8080/movies/oscar?title=Inception


3. Submit movie ratings:

POST http://localhost:8080/movies/rate
Content-Type: application/json
{
"title": "Inception",
"rating": 5
}


4. Retrieve top 10 movies:

GET http://localhost:8080/movies/top


5. Ensure data persists and the top 10 list updates correctly based on ratings and box office value.