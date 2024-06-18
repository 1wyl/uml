CREATE TABLE User(
	uid int PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(255) UNIQUE,
	password VARCHAR(255),
	role INT,
	name VARCHAR(30),
	gender VARCHAR(3),
	birthDate DATE,
	age INT,
	address VARCHAR(300),
	phone VARCHAR(200),
	description VARCHAR(255),
	baseSalary float(10,2),
	extraSalary float(10,2),
	totalSalary FLOAT(10,2)
);


CREATE TABLE Role(
	rid int,
	menu VARCHAR(30),
	path VARCHAR(100)
);

INSERT INTO Role (rid, menu, path)
VALUES  
    (1, '个人主页', '/profile'),
    (1, '亲人信息', '/relative/info'),
    (1, '申请服务', '/service/food'),
    (1, '活动查询', '/activity'),
		(1, '健康档案', '/file'),
		(1, '紧急情况', '/emergency'),
		(2, '个人信息', '/worker/profile'),
		(2, '服务请求', '/worker/todolist'),
		(2, '紧急事件', '/worker/emer'),
		(2, '活动设置', '/worker/activity'),
		(2, '设置服务', '/worker/food'),
		(2, '健康档案管理', '/worker/file'),
		(3, '用户管理','/admin/user'),
		(3, '工人管理','/admin/worker'),
		(3, '事件处理记录','/admin/records'),
		(3, '角色菜单管理','/admin/menu'),
		(3, '活动管理', '/admin/activity');




CREATE TABLE Activities(
	aid int AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(200),
	location VARCHAR(200),
	uploadTime DATETIME,
	StartTime DATETIME
);


# relative字段
CREATE TABLE relative(
    rid INT PRIMARY KEY AUTO_INCREMENT,
    id INT, #外码
    relation VARCHAR(255),
    name VARCHAR(255),
    age INT,
    gender VARCHAR(4),
    job VARCHAR(255)
);


CREATE TABLE healthrecord (
    uid INT PRIMARY KEY AUTO_INCREMENT,          -- ID
    name VARCHAR(255),                           -- 姓名
    gender ENUM('男', '女'),                      -- 性别
    age INT,                                     -- 年龄
    height FLOAT,                                -- 身高 (米)
    weight FLOAT,                                -- 体重 (千克)
    bloodtype ENUM('A', 'B', 'AB', 'O'),         -- 血型
    allergies VARCHAR(255),                      -- 过敏史
    medicalhistory TEXT,                         -- 病史
    medications VARCHAR(255),                    -- 当前用药
    smokingstatus ENUM('不吸烟', '吸烟', '曾经吸烟'), -- 吸烟状态
    alcoholuse ENUM('不饮酒', '偶尔饮酒', '经常饮酒'), -- 饮酒情况
    physicalactivity ENUM('无', '轻度', '中度', '重度'), -- 体力活动
    diet TEXT,                                   -- 饮食情况
    bloodpressure VARCHAR(10),                   -- 血压情况
    cholesterollevel FLOAT,                      -- 胆固醇水平 (mg/dL)
    glucoselevel FLOAT                           -- 血糖水平 (mg/dL)
);

CREATE TABLE food (
		fid INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
		`date` Date,
    url VARCHAR(255)
);

CREATE TABLE medicine (
		mid INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
		`date` Date,
    url VARCHAR(255)
);
CREATE TABLE request (
    rid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255),
    request_time DATETIME,
    phone VARCHAR(15),
    service_type ENUM('食品', '药品'),
    product_name VARCHAR(50),
    completion_status BOOLEAN
);

ALTER TABLE request AUTO_INCREMENT = 1001; -- 设置自增起始值为 1001

CREATE TABLE event (
		name VARCHAR(30),
		time DATETIME,
		address VARCHAR(300),
		location VARCHAR(300),
		description VARCHAR(300),
		phone VARCHAR(200),
		isSolved BOOLEAN
);
