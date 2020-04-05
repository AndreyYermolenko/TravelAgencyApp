CREATE TABLE users
(
    id SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    manager_id INT
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

CREATE TABLE travel_tours
(
    id SERIAL NOT NULL PRIMARY KEY,
    destination VARCHAR(50) NOT NULL,
    begin_date DATE NOT NULL,
    end_date DATE NOT NULL,
    cost FLOAT NOT NULL,
    max_count INT NOT NULL,
    current_count INT NOT NULL,
    description VARCHAR
);

CREATE TABLE tour_user
(
    user_id INT NOT NULL,
    tour_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (tour_id) REFERENCES travel_tour(id),

    UNIQUE (user_id, tour_id)
);

INSERT INTO roles VALUES (1, 'manager');
INSERT INTO roles VALUES (2, 'user');

INSERT INTO users VALUES (1, 'admin@mail.com', 'root', 'Andrey', 'Yermolenko', NULL);
INSERT INTO users VALUES (2, 'user@mail.com', 'root', 'Vasya', 'Pupkin', 1);

INSERT INTO role_user VALUES (1, 1);
INSERT INTO role_user VALUES (2, 2);

INSERT INTO travel_tours VALUES (1, 'Egypt', TO_DATE('2020-08-15', 'YYYY-MM-DD'),
                                TO_DATE('2020-08-20', 'YYYY-MM-DD'),
                                2000, 10, 0, 'Egypt tour'
                                );

COMMIT;
