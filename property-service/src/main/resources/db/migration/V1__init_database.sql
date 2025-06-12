CREATE TABLE property_type (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      description VARCHAR(255),
      UNIQUE (name)
);

CREATE TABLE room_type (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100),
      UNIQUE (name)
);

CREATE TABLE property (
      id INT PRIMARY KEY AUTO_INCREMENT,
      title TEXT NOT NULL,
      description TEXT,

     property_type_id INT NOT NULL,
     room_type_id INT NOT NULL,

     address_street VARCHAR(255),
     city_id INT NOT NULL,
     country_id INT NOT NULL,
     ward_id INT,
     district_id INT,
     postal_code VARCHAR(20),

     thumbnail VARCHAR(255),
     images JSON,

     latitude DECIMAL(10, 7),
     longitude DECIMAL(10, 7),

     price_per_night DECIMAL(10, 2) NOT NULL,
     currency_code ENUM('VNĐ', 'USD') DEFAULT 'VNĐ',

     max_guests INT DEFAULT 1,
     num_bedrooms INT DEFAULT 1,
     num_beds INT DEFAULT 1,
     num_bathrooms DECIMAL(2,1) DEFAULT 1.0,

     active BIT DEFAULT b'1',

     FOREIGN KEY (property_type_id) REFERENCES property_type(id),
     FOREIGN KEY (room_type_id) REFERENCES room_type(id),

     INDEX (property_type_id),
     INDEX (room_type_id),
     INDEX (city_id),
     INDEX (district_id),
     INDEX (ward_id),
     INDEX (country_id),
     INDEX (price_per_night),
     INDEX (active),

          -- Composite indexes for common searches
     INDEX idx_property_search (city_id, property_type_id, active, price_per_night),
     INDEX idx_property_location (latitude, longitude),
     INDEX idx_property_capacity (max_guests, num_bedrooms),

    -- JSON validation
     CONSTRAINT chk_images_json CHECK (JSON_VALID(images) OR images IS NULL)
);

CREATE TABLE property_amenity (
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(100) NOT NULL,
     UNIQUE (name)
);

CREATE TABLE property_amenity_link (
     id INT PRIMARY KEY AUTO_INCREMENT,
     property_amenity_id INT NOT NULL,
     property_id INT NOT NULL,
     number INT DEFAULT 0,
     UNIQUE (property_id, property_amenity_id),
     FOREIGN KEY (property_amenity_id) REFERENCES property_amenity(id),
     FOREIGN KEY (property_id) REFERENCES property(id),
     INDEX (property_id),
     INDEX (property_amenity_id)
);
