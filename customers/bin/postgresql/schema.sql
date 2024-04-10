CREATE SCHEMA customerdb;

CREATE TABLE customerdb.customers(
  ID BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(64) NOT NULL,
  second_name VARCHAR(64) NOT NULL
);
