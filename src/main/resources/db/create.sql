SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS animals (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  gender VARCHAR,
  dateOfAdmission VARCHAR,
  type VARCHAR,
  breed VARCHAR
);

CREATE TABLE IF NOT EXISTS customers (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    phone VARCHAR,
    typePreference VARCHAR,
    breedPreference VARCHAR
);
