CREATE TABLE ville (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(100),
);

CREATE TABLE user (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(100),
    year INT,
    idVille INT,
    FOREIGN KEY (idVille) REFERENCES ville(id)
);

INSERT INTO ville VALUES
	(1, 'Londres'),
	(2, 'Ulm'),
	(3, 'Portland'),
	(4, 'Rennes');

INSERT INTO user VALUES
	(1, 'Alan Turing', 1912, 1),
	(2, 'Albert Einstein', 1879, 2),
	(3, 'Douglas Engelbart', 1925, 3);
	
