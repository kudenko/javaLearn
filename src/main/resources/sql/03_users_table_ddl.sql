SET SCHEMA 'library';

CREATE TABLE IF NOT EXISTS users(
     id SERIAL PRIMARY KEY,
     first_name VARCHAR(1000),
     last_name VARCHAR(1000),
     user_name VARCHAR(1000),
     user_role VARCHAR(1000),
     user_status VARCHAR(1000),
     password VARCHAR(1000),
     UNIQUE(user_name)
 );

INSERT INTO users (first_name, last_name, user_name, user_role, user_status, password)
VALUES ('Test', 'User', 'testuser', 'ROLE_USER', 'ACTIVE', 'password');

GRANT SELECT, INSERT, UPDATE, DELETE ON users TO librarian;

UPDATE users
SET password = '$2a$10$O2TbcbqlYeFOZCNw4.w9aOMA3bylj4NyFMRaOKKOUF4NCJnHO.5.e'
WHERE user_name = 'testuser';
