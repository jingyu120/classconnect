drop table if exists student_course;
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
    admitted_date VARCHAR(50),
    major VARCHAR(50),
    minor VARCHAR(50)
);