CREATE TABLE IF NOT EXISTS engine (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS drivetrain (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS body (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS model (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    brand_id INT NOT NULL REFERENCES brand(id)
);
CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    brand_id INT NOT NULL REFERENCES brand(id),
    model_id INT NOT NULL REFERENCES model(id),
    body_id INT NOT NULL REFERENCES body(id),
    drivetrain_id INT NOT NULL REFERENCES drivetrain(id),
    engine_id INT NOT NULL REFERENCES engine(id)
);
CREATE TABLE IF NOT EXISTS driver (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VRACHAR(50),
    phone VARCHAR(50),
    password VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS history_owner (
    id SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES driver(id),
    car_id INT NOT NULL REFERENCES car(id)
);
CREATE TABLE IF NOT EXISTS advertisement (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    description VARCHAR(255),
    sold BOOLEAN NOT NULL,
    price INI NOT NULL,
    car_id INT NOT NULL REFERENCES car(id),
    driver_id INT NOT NULL REFERENCES driver(id)
);