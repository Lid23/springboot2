CREATE TABLE `author` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR (150),
	`password` VARCHAR (60),
	`email` VARCHAR (150),
	CONSTRAINT pk_author PRIMARY KEY (id)
);
INSERT INTO `Author` ( `username`, `password`, `email`) VALUES('老毛','111111','laomao@163.com');

CREATE TABLE `book` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (150),
	`author_id` INT,
	CONSTRAINT pk_book PRIMARY KEY (id)
);

CREATE TABLE `book_author` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`book_id` int,
	`author_id` int,
	CONSTRAINT pk_book_authod PRIMARY KEY (id)
);

CREATE TABLE `t_user` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`t_name` VARCHAR (150),
	`t_age` INT,
	t_address VARCHAR(200),
	CONSTRAINT pk_user PRIMARY KEY (id)
);