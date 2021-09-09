CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id)
);

INSERT INTO users (first_name, last_name)
		VALUES('tuan', 'truong'), ('minh', 'nguyen'), ('thuy linh', 'tran');