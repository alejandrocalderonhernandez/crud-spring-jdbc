DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS review;

CREATE TABLE product (
	  id BIGINT auto_increment PRIMARY KEY,
	  name VARCHAR(10) NOT NULL,
	  price DECIMAL  NOT NULL,
	  create_at timestamp NOT NULL
);
CREATE TABLE review (
	  id BIGINT auto_increment PRIMARY KEY,
	  description VARCHAR(255) NOT NULL,
	  rating SMALLINT  NOT NULL,
	  id_product BIGINT,
	  FOREIGN KEY(id_product) REFERENCES product(id) ON DELETE CASCADE
);

