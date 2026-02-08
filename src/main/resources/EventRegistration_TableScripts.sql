CREATE TABLE event (
    event_id INT PRIMARY KEY,
    name VARCHAR(100),
    event_date DATE,
    venue VARCHAR(100),
    max_count INT
);

CREATE TABLE participant (
    participant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email_id VARCHAR(100) UNIQUE,
    gender VARCHAR(10),
    registration_date DATE,
    event_id INT,
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);


INSERT INTO event (event_id, name, event_date, venue, max_count)
VALUES (101, 'Tech Conference', '2026-02-15', 'Bhubaneswar', 100);

INSERT INTO participant (name, email_id, gender, registration_date, event_id)
VALUES ('Ritika', 'ritika@example.com', 'Female', '2026-02-10', 101),
       ('Amit', 'amit@example.com', 'Male', '2026-02-10', 101);