CREATE SCHEMA customerdb;
DROP TABLE IF EXISTS customerdb.customers;
CREATE TABLE customerdb.customers(
  ID UUID NOT NULL,
  first_name VARCHAR(64) NOT NULL,
  second_name VARCHAR(64) NOT NULL,
  CONSTRAINT customer_id PRIMARY KEY(ID)
);
