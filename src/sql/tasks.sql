--  语音备忘录的用任务表
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
		ddl  VARCHAR(100),
		uid INT,
    completed BOOlEAN
);