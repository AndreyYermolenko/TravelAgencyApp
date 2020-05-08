INSERT INTO travel_tour (destination, begin_date, end_date, cost, max_count, current_count, description, travel_carrier_id, resort_id)
VALUES ('Egypt1', TO_DATE('2020-08-15', 'YYYY-MM-DD'),
        TO_DATE('2020-08-20', 'YYYY-MM-DD'),
        2000, 10, 0, 'Egypt tour', 1, 3
       );
INSERT INTO travel_tour (destination, begin_date, end_date, cost, max_count, current_count, description, travel_carrier_id, resort_id)
VALUES ('Egypt', TO_DATE('2020-08-20', 'YYYY-MM-DD'),
        TO_DATE('2020-08-25', 'YYYY-MM-DD'),
        2100, 12, 0, 'Egypt tour', 2, 3
       );

COMMIT;
