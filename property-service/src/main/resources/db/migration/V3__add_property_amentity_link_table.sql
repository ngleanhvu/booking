CREATE TABLE IF NOT EXISTS property_amenity_link (
    id INT PRIMARY KEY AUTO_INCREMENT,
    property_amenity_id INT NOT NULL,
    property_id INT NOT NULL,
    number INT DEFAULT 1,
    FOREIGN KEY (property_id) REFERENCES property(id),
    FOREIGN KEY (property_amenity_id) REFERENCES property_amenity(id)
)