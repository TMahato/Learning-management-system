
---

# Learning Management System (LMS)

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup and Configuration](#setup-and-configuration)
- [Running the Application](#running-the-application)
- [Database Connection](#database-connection)
- [API Endpoints and cURL Commands](#api-endpoints-and-curl-commands)

---

## Prerequisites

Before you begin, ensure you have the following installed on your system:
- **Java JDK 17** or higher
- **Apache Maven**
- **Git**

---

## Setup and Configuration

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/lms.git
cd lms
```

### 2. Configure the Database

The application is configured to use an **H2 in-memory database** by default. If you want to use a persistent database like MySQL, update `src/main/resources/application.properties`:

#### H2 Database (Default)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build the Project

```bash
mvn clean install
```

## Running the Application

To start the application, run:

```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

---

## Database Connection

If you are using the **H2 in-memory database** (default configuration), you can access the H2 console at:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)

---
## Sql Qureies to fetch the Tables

```bash
select * from course;
select * from student;
select * from instructor;
select * from enrollment;
```
---
## API Endpoints and cURL Commands

Below is a list of `cURL` commands to test each endpoint.

### Course API

**1. Create a Course**

```bash
curl -X POST http://localhost:8080/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Computer Science 101",
    "description": "Introduction to Computer Science",
    "difficulty": "MEDIUM"
  }'
```

**2. Get All Courses**

```bash
curl -X GET http://localhost:8080/courses
```

**3. Get Course by ID**

```bash
curl -X GET http://localhost:8080/courses/{courseId}
```

Replace `{courseId}` with the actual course ID.

**4. Update a Course**

```bash
curl -X PUT http://localhost:8080/courses/{courseId} \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Computer Science Advanced",
    "description": "Advanced Topics in Computer Science",
    "difficulty": "HARD"
  }'
```

**5. Delete a Course**

```bash
curl -X DELETE http://localhost:8080/courses/{courseId}
```

---

### Student API

**1. Create a Student**

```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Smith"
  }'
```

**2. Get All Students**

```bash
curl -X GET http://localhost:8080/students
```

**3. Get Student by ID**

```bash
curl -X GET http://localhost:8080/students/{studentId}
```

Replace `{studentId}` with the actual student ID.

**4. Update a Student**

```bash
curl -X PUT http://localhost:8080/students/{studentId} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson"
  }'
```

**5. Delete a Student**

```bash
curl -X DELETE http://localhost:8080/students/{studentId}
```

---

### Instructor API

**1. Create an Instructor**

```bash
curl -X POST http://localhost:8080/instructors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dr. John Doe"
  }'
```

**2. Get All Instructors**

```bash
curl -X GET http://localhost:8080/instructors
```

**3. Get Instructor by ID**

```bash
curl -X GET http://localhost:8080/instructors/{instructorId}
```

Replace `{instructorId}` with the actual instructor ID.

**4. Update an Instructor**

```bash
curl -X PUT http://localhost:8080/instructors/{instructorId} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dr. Jane Doe"
  }'
```

**5. Delete an Instructor**

```bash
curl -X DELETE http://localhost:8080/instructors/{instructorId}
```

---

### Enrollment API

**1. Enroll a Student in a Course**

```bash
curl -X POST http://localhost:8080/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "student": {"id": 1},
    "course": {"id": 1},
    "grade": null
  }'
```

Replace `1` with the actual student and course IDs.

**2. Get All Enrollments for a Student**

```bash
curl -X GET http://localhost:8080/enrollments/student/{studentId}
```

Replace `{studentId}` with the actual student ID.

**3. Get Enrollment by ID**

```bash
curl -X GET http://localhost:8080/enrollments/{enrollmentId}
```

Replace `{enrollmentId}` with the actual enrollment ID.

**4. Update an Enrollment**

```bash
curl -X PUT http://localhost:8080/enrollments/{enrollmentId} \
  -H "Content-Type: application/json" \
  -d '{
    "student": {"id": 1},
    "course": {"id": 1},
    "grade": 90.0
  }'
```

**5. Delete an Enrollment**

```bash
curl -X DELETE http://localhost:8080/enrollments/{enrollmentId}
```

---

### Report API

**1. Get Student Progress Report (Including GPA and Grade Breakdown by Difficulty)**

```bash
curl -X GET http://localhost:8080/reports/student/{studentId}/progress
```

Replace `{studentId}` with the actual student ID to retrieve the progress report.

---

### Example Report API Response

A sample response from the progress report might look like this:

```json
{
    "courseGrades": [
        {
            "courseTitle": "Computer Science 101",
            "grade": 88.5,
            "difficulty": "MEDIUM"
        },
        {
            "courseTitle": "Mathematics 101",
            "grade": 92.0,
            "difficulty": "HARD"
        },
        {
            "courseTitle": "Introduction to Psychology",
            "grade": 85.0,
            "difficulty": "EASY"
        }
    ],
    "gpa": 88.5,
    "gradeBreakdownByDifficulty": {
        "EASY": [85.0],
        "MEDIUM": [88.5],
        "HARD": [92.0]
    }
}
```

This report includes:
- A list of courses with grades and difficulty levels.
- The student’s GPA across all courses.
- A breakdown of grades by difficulty.

---
