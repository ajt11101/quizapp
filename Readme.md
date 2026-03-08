# Quiz App (Spring Boot)

A simple **Quiz Application backend** built using **Java and Spring Boot**.
The application allows users to add questions, create quizzes, submit answers, and evaluate results.

## Features

* Add new quiz questions
* Fetch questions by category
* Create quizzes
* Submit answers for a quiz
* Evaluate answers and return the score
* REST APIs for quiz operations

## Tech Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL**
* **Maven**

## API Modules

* **Question Management**

    * Add questions
    * Get all questions
    * Get questions by category

* **Quiz Management**

    * Create quiz
    * Get quiz questions
    * Submit answers
    * Evaluate quiz and return score

## Project Structure

```
src
 ├── controller
 ├── service
 ├── dao / repository
 ├── model
 └── resources
```

## Running the Application

1. Clone the repository
2. Configure database in `application.properties`
3. Run the Spring Boot application

```
mvn spring-boot:run
```

The server will start at:

```
http://localhost:8080
```

## Future Improvements

* Add authentication (JWT / Spring Security)
* Add frontend UI
* Add timer-based quizzes
* Improve question randomization
