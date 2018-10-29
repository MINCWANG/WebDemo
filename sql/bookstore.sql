CREATE TABLE bs_user(
	id INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL UNIQUE,
	PASSWORD VARCHAR(100) NOT NULL,
	email VARCHAR(100)
);
/*
图书表sql语句
 */

CREATE TABLE bs_book(
	id INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
	title	VARCHAR(100) NOT NULL,
	author 	VARCHAR(100) NOT NULL,
	price	DOUBLE(11,2) NOT NULL,
	sales	INTEGER(11),
	stock	INTEGER(11),
	img_path VARCHAR(100)
);
/**
  创建订单表
 */
CREATE TABLE bs_order(
	id VARCHAR(100) PRIMARY KEY,
	totalCount INTEGER(11),
	totalAmount DOUBLE(11,2),
	state INTEGER(2),
	orderTime DATETIME,
	userId INTEGER(11),
	FOREIGN KEY(userId) REFERENCES `bs_user`(id)
);

/**
 创建订单项表
 */
CREATE TABLE bs_orderitem(
	id INTEGER(11) PRIMARY KEY,
	title VARCHAR(100),
	author VARCHAR(100),
	img_path VARCHAR(100),
	price DOUBLE(11,2),
	COUNT INTEGER(11),
	amount DOUBLE(11,2),
	order_id VARCHAR(100),
	FOREIGN KEY (order_id) REFERENCES `bs_order`(id)
);