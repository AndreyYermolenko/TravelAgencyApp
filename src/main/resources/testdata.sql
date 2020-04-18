INSERT INTO users ("email", "password", "first_name", "last_name", "manager_id")
VALUES ('admin@mail.com', 'root', 'Andrey', 'Yermolenko', NULL);
INSERT INTO users ("email", "password", "first_name", "last_name", "manager_id")
VALUES ('user@mail.com', 'root', 'Vasya', 'Pupkin', 1);

INSERT INTO role_user VALUES (1, 1);
INSERT INTO role_user VALUES (1, 2);
INSERT INTO role_user VALUES (2, 2);

INSERT INTO travel_tour (destination, begin_date, end_date, cost, max_count, current_count, description)
VALUES ('Egypt', TO_DATE('2020-08-15', 'YYYY-MM-DD'),
        TO_DATE('2020-08-20', 'YYYY-MM-DD'),
        2000, 10, 0, 'Egypt tour'
       );
INSERT INTO travel_tour (destination, begin_date, end_date, cost, max_count, current_count, description)
VALUES ('Egypt', TO_DATE('2020-08-20', 'YYYY-MM-DD'),
        TO_DATE('2020-08-25', 'YYYY-MM-DD'),
        2100, 12, 0, 'Egypt tour'
       );

COMMIT;
