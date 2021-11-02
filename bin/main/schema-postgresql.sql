DROP TABLE IF EXISTS course_students;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student_enrollment;
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

CREATE TABLE students (
                          studentID serial  PRIMARY KEY,
                          usr_id VARCHAR(10),
                          nm VARCHAR(50),
                          email VARCHAR(50),
                          admitted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          major VARCHAR(50),
                          minor VARCHAR(50)
);

-- CREATE TABLE student_enrollment (
--                                     studentID VARCHAR(50),
--                                     courseID VARCHAR(50),
--                                     enrolled_period varchar(50),
--                                     PRIMARY KEY (studentID, courseID)
-- );

-- CREATE TABLE course (
--                         id serial  PRIMARY KEY,
--                         dept char(2),
--                         num int,
--                         reviews json
-- );

-- CREATE TABLE course_students (
--                                  id serial  PRIMARY KEY,
--                                  course_id int,
--                                  students_id int
-- );
