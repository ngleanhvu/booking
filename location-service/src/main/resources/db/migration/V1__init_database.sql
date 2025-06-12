CREATE TABLE country (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         code CHAR(2) UNIQUE,
                         name VARCHAR(100),
                         INDEX (name)
);

CREATE TABLE city (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      country_id INT NOT NULL,
                      FOREIGN KEY (country_id) REFERENCES country(id),
                      INDEX (country_id),
                      INDEX (name)
);

CREATE TABLE district (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100),
                          city_id INT NOT NULL,
                          FOREIGN KEY (city_id) REFERENCES city(id),
                          INDEX (city_id),
                          INDEX (name)
);

CREATE TABLE ward (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(100),
                      district_id INT NOT NULL,
                      FOREIGN KEY (district_id) REFERENCES district(id),
                      INDEX (district_id),
                      INDEX (name)
);