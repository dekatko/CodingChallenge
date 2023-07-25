INSERT INTO product (id, name, price, sku) VALUES (1, 'Milch', '1.99', '102');
INSERT INTO product (id, name, price, sku) VALUES (2, 'Brot', '3.99', '2035');
INSERT INTO product (id, name, price, sku) VALUES (3, 'Käse', '2.99', 'S-155');
INSERT INTO product (id, name, price, sku) VALUES (4, 'Wurst', '4.99', '1488');
INSERT INTO product (id, name, price, sku) VALUES (5, 'Couscous', '0.99', 'B001');

INSERT INTO product_eans (product_id, eans) VALUES (1, '12345678');
INSERT INTO product_eans (product_id, eans) VALUES (1, '77777777');
INSERT INTO product_eans (product_id, eans) VALUES (1, '23498128');
INSERT INTO product_eans (product_id, eans) VALUES (2, '34558821');
INSERT INTO product_eans (product_id, eans) VALUES (2, '12323410');
INSERT INTO product_eans (product_id, eans) VALUES (3, '34598146');
INSERT INTO product_eans (product_id, eans) VALUES (3, '43565922');
INSERT INTO product_eans (product_id, eans) VALUES (3, '23454045');
INSERT INTO product_eans (product_id, eans) VALUES (4, '18754629');
INSERT INTO product_eans (product_id, eans) VALUES (4, '46025548');
INSERT INTO product_eans (product_id, eans) VALUES (5, '54342316');

INSERT INTO user (id, username) VALUES (1, 'denis-the-menace');
INSERT INTO cart (id, user_id) VALUES (1, 1);
INSERT INTO cart_product (quantity, cart_id, product_id) VALUES (2, 1, 1);
INSERT INTO cart_product (quantity, cart_id, product_id) VALUES (5, 1, 4);
