-- 注册页面使用全部字段，登录页面使用username和password
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE not NULL,
    password VARCHAR(50),
    name VARCHAR(100),
    gender VARCHAR(10),
    birthDate DATE,
	description VARCHAR(255)
);