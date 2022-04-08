DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100),
    status VARCHAR(50),
    duedate DATE
);

INSERT INTO tasks (id, name, status, duedate) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'タスク１', 'NOT_COMPLETED', '2010-01-01');
INSERT INTO tasks (id, name, status, duedate) VALUES ('b1cabd78-7e4c-7fb5-ba7a-4ac7ef441c78', 'タスク２', 'DONE', '2010-02-02');
