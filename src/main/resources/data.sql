INSERT INTO product (id, name, price, sku, available) VALUES (1, 'Milch', '1.99', '102', true);
INSERT INTO product (id, name, price, sku, available) VALUES (2, 'Brot', '3.99', '2035', true);
INSERT INTO product (id, name, price, sku, available) VALUES (3, 'Käse', '2.99', 'S-155', true);
INSERT INTO product (id, name, price, sku, available) VALUES (4, 'Wurst', '4.99', '1488', true);
INSERT INTO product (id, name, price, sku, available) VALUES (5, 'Couscous', '0.99', 'B001', true);

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
INSERT INTO cart (id, user_id, checked_out) VALUES (1, 1, false);
INSERT INTO cart_product (quantity, cart_id, product_id) VALUES (2, 1, 1);
INSERT INTO cart_product (quantity, cart_id, product_id) VALUES (5, 1, 4);
