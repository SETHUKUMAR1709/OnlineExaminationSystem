# Online Examination System

A Spring Boot REST API for an online examination platform supporting three user roles (Admin, Teacher, Student) with a complete exam lifecycle: create, add questions, publish, attempt, grade, and view results.

## Tech Stack

| Layer     | Technology                     |
|-----------|--------------------------------|
| Language  | Java 17+                      |
| Framework | Spring Boot 3.x               |
| Database  | MySQL 8.x                     |
| ORM       | Spring Data JPA / Hibernate   |
| Build     | Maven                         |
| Testing   | Postman Collection             |

## Entity Relationship

```
┌───────────┐       ┌───────────┐       ┌──────────────┐
│   User    │──1:N──│   Exam    │──1:N──│   Question   │
│ (role)    │       │(published)│       │ (type)       │
└─────┬─────┘       └─────┬─────┘       └──────┬───────┘
      │                   │                     │
      │ 1:N          1:N  │                     │
      ▼                   ▼                     │
┌─────────────┐    ┌─────────────┐              │
│ ExamAttempt  │◄───│   Answer    │◄─────────────┘
│ (graded)     │    │ (score,     │
└──────────────┘    │  gradedBy)  │
                    └─────────────┘
```

### User Roles
- **ADMIN** – manages all users
- **TEACHER** – creates exams, adds questions, publishes, grades
- **STUDENT** – attempts published exams, views results

### Question Types
`MCQ` · `FILL_IN_THE_BLANK` · `SHORT_ANSWER` · `DESCRIPTIVE`

MCQ and FILL_IN_THE_BLANK are auto-graded on submission; SHORT_ANSWER and DESCRIPTIVE require manual teacher grading.

## API Endpoints

### Users (`/api/users`)

| Method | Endpoint            | Description           |
|--------|---------------------|-----------------------|
| POST   | `/api/users`        | Create a user         |
| GET    | `/api/users`        | List all users        |
| GET    | `/api/users/{id}`   | Get user by ID        |
| GET    | `/api/users/role/{role}` | List users by role |
| PUT    | `/api/users/{id}`   | Update a user         |
| DELETE | `/api/users/{id}`   | Delete a user         |

### Exams (`/api/exams`)

| Method | Endpoint                     | Description                          |
|--------|------------------------------|--------------------------------------|
| POST   | `/api/exams`                 | Create an exam                       |
| GET    | `/api/exams`                 | List all exams                       |
| GET    | `/api/exams/{id}`            | Get exam by ID                       |
| PUT    | `/api/exams/{id}`            | Update an exam                       |
| DELETE | `/api/exams/{id}`            | Delete an exam                       |
| PUT    | `/api/exams/{id}/publish`    | Publish an exam                      |
| GET    | `/api/exams/published`       | List published exams                 |
| GET    | `/api/exams/teacher/{id}`    | Get exams by teacher                 |
| GET    | `/api/exams/average/{score}` | Exams with avg score > value         |

### Questions (`/api/questions`)

| Method | Endpoint                                  | Description                   |
|--------|-------------------------------------------|-------------------------------|
| POST   | `/api/questions`                          | Create a question             |
| GET    | `/api/questions/{id}`                     | Get question by ID            |
| GET    | `/api/questions/exam/{examId}`            | List questions for an exam    |
| PUT    | `/api/questions/{id}`                     | Update a question             |
| DELETE | `/api/questions/{id}`                     | Delete a question             |
| GET    | `/api/questions/unanswered/attempt/{id}`  | Unanswered questions          |

### Attempts (`/api/attempts`)

| Method | Endpoint                                | Description                      |
|--------|-----------------------------------------|----------------------------------|
| POST   | `/api/attempts`                         | Create attempt (raw)             |
| POST   | `/api/attempts/start?examId=&studentId=`| Start exam attempt               |
| GET    | `/api/attempts/{id}`                    | Get attempt by ID                |
| GET    | `/api/attempts/student/{studentId}`     | List attempts by student         |
| GET    | `/api/attempts/count/exam/{examId}`     | Count attempts for exam          |
| GET    | `/api/attempts/topper/exam/{examId}`    | Top scorers for exam             |
| GET    | `/api/attempts/failed/exam/{examId}/{mark}` | Failed students             |
| GET    | `/api/attempts/results/exam/{examId}`   | Exam results                     |
| PUT    | `/api/attempts/{id}/grade`              | Mark attempt as graded           |
| GET    | `/api/attempts/ungraded/exam/{examId}`  | Ungraded attempts for exam       |
| DELETE | `/api/attempts/{id}`                    | Delete an attempt                |

### Answers (`/api/answers`)

| Method | Endpoint                     | Description                         |
|--------|------------------------------|-------------------------------------|
| POST   | `/api/answers`               | Submit an answer (auto-grades MCQ)  |
| PUT    | `/api/answers/grade?...`     | Grade an answer manually            |
| GET    | `/api/answers/scores/{id}`   | Scores per question for an attempt  |
| GET    | `/api/answers/difficult`     | Most difficult questions            |

## Setup Instructions

### Prerequisites
- Java 17 or later
- MySQL 8.x running locally
- Maven (or use the included `mvnw` wrapper)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/SETHUKUMAR1709/OnlineExaminationSystem.git
   cd OnlineExaminationSystem
   ```

2. **Create the database and seed data**
   ```bash
   mysql -u root -p < scripts.sql
   ```

3. **Configure the database connection** in `OnlineExaminationSystem/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/onlinees
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   ```

4. **Build and run**
   ```bash
   cd OnlineExaminationSystem
   ./mvnw spring-boot:run
   ```

5. **Test the API** – Import the Postman collection from `postman/collections/` and run requests against `http://localhost:8081`.

## Exam Lifecycle

```
Teacher creates exam  →  Adds questions  →  Publishes exam
                                               ↓
Student views published exams  →  Starts attempt  →  Submits answers
                                                        ↓
MCQ/FITB auto-graded  ←  Teacher grades remaining  →  Marks attempt graded
                                                        ↓
                                              Results & queries available
```

## License

This project is developed for academic purposes.
