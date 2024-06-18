CREATE TABLE chat (
	cid INT AUTO_INCREMENT PRIMARY KEY,
    uid INT,
	sessionid INT,
	`date` Date,
    question VARCHAR(255) NOT NULL,
	answer VARCHAR(255) NOT NULL
);

