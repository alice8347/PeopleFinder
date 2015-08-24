CREATE TABLE companies (
  name VARCHAR(50)
);

INSERT INTO companies SELECT DISTINCT company FROM customers;

ALTER TABLE companies
ADD companyId INTEGER;

UPDATE companies SET companyId = rownum;

ALTER TABLE companies
ADD PRIMARY KEY (companyId);

ALTER TABLE customers
ADD companyId INTEGER;

UPDATE customers
SET companyId = (SELECT companyId FROM companies WHERE companies.name = customers.company);

CREATE TABLE cities (
name VARCHAR(50)
);

INSERT INTO cities SELECT DISTINCT city FROM customers;

ALTER TABLE cities
ADD id INTEGER;

UPDATE cities SET id = rownum;

ALTER TABLE cities
ADD PRIMARY KEY (id);

CREATE TABLE states (
name VARCHAR(10)
);

INSERT INTO states SELECT DISTINCT state FROM customers;

ALTER TABLE states
ADD id INTEGER;

UPDATE states SET id = rownum;

ALTER TABLE states
ADD PRIMARY KEY (id);

UPDATE customers
SET city = (SELECT id FROM cities WHERE cities.name = customers.city);

UPDATE customers
SET state = (SELECT id FROM states WHERE states.name = customers.state);