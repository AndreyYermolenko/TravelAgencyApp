CREATE TABLE agency_branch
(
    id SERIAL NOT NULL PRIMARY KEY,
    destination VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL
);

INSERT INTO agency_branch (destination, address) VALUES ('Sumy branch', '2, Rymskogo-Korsakova st., 40007 Sumy, Ukraine');
INSERT INTO agency_branch (destination, address) VALUES ('Kiev branch', '15, Khreschatyk st., 01044 Kiev, Ukraine');
INSERT INTO agency_branch (destination, address) VALUES ('Kharkov branch', '17, Gogol st., 61057 Kharkov, Ukraine');

CREATE TABLE travel_carrier
(
    id SERIAL NOT NULL PRIMARY KEY,
    destination VARCHAR(50) NOT NULL
);

INSERT INTO travel_carrier (destination) VALUES ('ТК "В последний путь"');
INSERT INTO travel_carrier (destination) VALUES ('ОАО "Перевозка дров"');

CREATE TABLE resort
(
    id SERIAL NOT NULL PRIMARY KEY,
    destination VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    description VARCHAR
);

INSERT INTO resort (destination, country, description) VALUES ('Turkish resort', 'Turkey', 'Take a rest for the last time!');
INSERT INTO resort (destination, country, description) VALUES ('German resort', 'Germany', 'Tasty beer!');
INSERT INTO resort (destination, country, description) VALUES ('Egyptian resort', 'Egypt', 'Fry on the sand!');

CREATE TABLE users
(
    id SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    manager_id INT,
    agency_branch_id INT NOT NULL,

    FOREIGN KEY (manager_id) REFERENCES users(id),
    FOREIGN KEY (agency_branch_id) REFERENCES agency_branch(id)
);

CREATE TABLE roles
(
    id SERIAL NOT NULL PRIMARY KEY,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE role_user
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),

    UNIQUE (user_id, role_id)
);

INSERT INTO roles VALUES (1, 'manager');
INSERT INTO roles VALUES (2, 'user');

INSERT INTO users (email, password, first_name, last_name, manager_id, agency_branch_id)
VALUES ('admin1@mail.com', '$2a$10$oNL5kXw.WVqQG9J2qEz31OpnN6KVr3lQ9AawMzEZsfJefzevmdPVq', 'Andrey', 'Yermolenko', NULL, 1);
INSERT INTO users (email, password, first_name, last_name, manager_id, agency_branch_id)
VALUES ('admin2@mail.com', '$2a$10$oNL5kXw.WVqQG9J2qEz31OpnN6KVr3lQ9AawMzEZsfJefzevmdPVq', 'Ivan', 'Sidorov', NULL, 2);
INSERT INTO users (email, password, first_name, last_name, manager_id, agency_branch_id)
VALUES ('admin3@mail.com', '$2a$10$oNL5kXw.WVqQG9J2qEz31OpnN6KVr3lQ9AawMzEZsfJefzevmdPVq', 'Igor', 'Petrov', NULL, 3);

INSERT INTO role_user VALUES (1, 1);
INSERT INTO role_user VALUES (1, 2);
INSERT INTO role_user VALUES (2, 1);
INSERT INTO role_user VALUES (2, 2);
INSERT INTO role_user VALUES (3, 1);
INSERT INTO role_user VALUES (3, 2);

CREATE TABLE travel_tour
(
    id SERIAL NOT NULL PRIMARY KEY,
    destination VARCHAR(50) NOT NULL,
    begin_date DATE NOT NULL,
    end_date DATE NOT NULL,
    cost FLOAT NOT NULL,
    max_count INT NOT NULL,
    current_count INT NOT NULL,
    description VARCHAR,
    travel_carrier_id INT,
    resort_id INT NOT NULL,

    FOREIGN KEY (travel_carrier_id) REFERENCES travel_carrier(id),
    FOREIGN KEY (resort_id) REFERENCES resort(id)
);

CREATE TABLE tour_user
(
    user_id INT NOT NULL,
    tour_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (tour_id) REFERENCES travel_tour(id) ON DELETE CASCADE,

    UNIQUE (user_id, tour_id)
);

CREATE TABLE tokens
(
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE (token)
);

CREATE OR REPLACE FUNCTION increase_quantity() RETURNS TRIGGER AS $$
DECLARE
    count int;
    quantity numeric;
BEGIN
    IF TG_OP = 'INSERT' THEN
        count = (SELECT count(*) FROM tour_user WHERE tour_id = NEW.tour_id);
        quantity = (SELECT max_count FROM travel_tour WHERE id = NEW.tour_id);
        IF count > quantity THEN
            RAISE TRIGGERED_ACTION_EXCEPTION;
        END if;
        UPDATE travel_tour SET current_count = count WHERE id = NEW.tour_id;
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER quantity_user
    AFTER INSERT ON tour_user FOR EACH ROW
    EXECUTE PROCEDURE increase_quantity();

COMMIT;
