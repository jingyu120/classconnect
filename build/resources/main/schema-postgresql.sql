-- DROP TABLE IF EXISTS users_roles;
-- DROP TABLE IF EXISTS roles;
-- DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
    id serial PRIMARY KEY,
    dept VARCHAR(10),
    num int,
    nm VARCHAR(100),
    descrip VARCHAR(1000)
);