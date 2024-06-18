-- 亲人信息表：RID（primary key），ID（foreign key），姓名（required），年龄，性别，职业，照片，视频，与老人关系（required）。
CREATE TABLE relative(
    rid INT PRIMARY KEY AUTO_INCREMENT,
    id INT,
    relation VARCHAR(255),
    name VARCHAR(255),
    age INT,
    gender VARCHAR(4),
    job VARCHAR(255)
);