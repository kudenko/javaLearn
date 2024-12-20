CREATE SCHEMA IF NOT EXISTS library;

SET SCHEMA 'library';

CREATE USER librarian WITH encrypted password 'libra';

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA library TO librarian;

GRANT ALL ON SCHEMA library TO librarian;

GRANT ALL PRIVILEGES ON TABLE author TO librarian;

GRANT ALL PRIVILEGES ON TABLE book TO librarian;

GRANT ALL PRIVILEGES ON TABLE journal TO librarian;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA library to librarian;

SET SCHEMA 'library';

CREATE  TABLE author (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(500),
    last_name VARCHAR(500),
    email VARCHAR(500) NOT NULL
);

CREATE TABLE book(
    id SERIAL PRIMARY KEY,
    name VARCHAR(500),
    count_pages INT,
    author_id INT,
    FOREIGN KEY(author_id)
        REFERENCES author
);

CREATE TABLE journal(
    id SERIAL PRIMARY KEY,
    name VARCHAR(500),
    count_pages INT,
    number INT,
    publication_year INT
);
