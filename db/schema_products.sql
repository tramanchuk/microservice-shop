CREATE SCHEMA product_db;

DROP TABLE IF EXISTS product_db.products;
CREATE TABLE product_db.products(
  ID UUID NOT NULL,
  NAME VARCHAR(64) NOT NULL,
  PRICE NUMERIC NOT NULL,
  LAST_UPDATE_DATE timestamp without time zone DEFAULT now(),
  CREATED_DATE timestamp without time zone DEFAULT now(),
  CONSTRAINT product_id PRIMARY KEY(ID)
);
