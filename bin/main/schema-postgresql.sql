DROP TABLE IF EXISTS students;

DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
    id serial PRIMARY KEY,
    dept VARCHAR(10),
    num int,
    nm VARCHAR(100),
    descrip VARCHAR(1000)
);

CREATE TABLE students (
    id serial PRIMARY KEY,
    usr_id VARCHAR(10),
    nm VARCHAR(50),
    email VARCHAR(50),
    admitted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    major VARCHAR(50),
    minor VARCHAR(50),
    course_id int,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);




-- CREATE TABLE course_students (
--     id serial PRIMARY KEY,
--     course_id int,
--     students_id int
-- );

-- CREATE TABLE student_enrollment (
--                                     studentID VARCHAR(50),
--                                     courseID VARCHAR(50),
--                                     enrolled_period varchar(50),
--                                     PRIMARY KEY (studentID, courseID)
-- );