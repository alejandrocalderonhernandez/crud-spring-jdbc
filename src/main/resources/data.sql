INSERT INTO product (name, price, create_at) VALUES ('BMW', 382000.89, CURRENT_TIMESTAMP());
INSERT INTO product (name, price, create_at) VALUES ('Porsche', 182000.33, CURRENT_TIMESTAMP());
INSERT INTO product (name, price, create_at) VALUES (' Seat', 300000.21, CURRENT_TIMESTAMP());
INSERT INTO product (name, price, create_at) VALUES ('Ford', 192000.29, CURRENT_TIMESTAMP());
INSERT INTO product (name, price, create_at) VALUES ('Susuky', 382000.89, CURRENT_TIMESTAMP());

INSERT INTO review (description, rating, id_product) VALUES ('Its a great car', 5, 1);
INSERT INTO review (description, rating, id_product) VALUES ('Its the best car', 5, 1);
INSERT INTO review (description, rating, id_product) VALUES ('Could be beter', 3, 1);

INSERT INTO review (description, rating, id_product) VALUES ('Its a faster car', 5, 2);
INSERT INTO review (description, rating, id_product) VALUES ('Its the best car in the world', 5, 2);
INSERT INTO review (description, rating, id_product) VALUES ('Its very fast', 4, 2);
INSERT INTO review (description, rating, id_product) VALUES ('its spensive', 2, 2);
