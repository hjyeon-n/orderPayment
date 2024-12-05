INSERT INTO products (name, description, price) VALUES ('Product A', 'Description A', 100.00);
INSERT INTO products (name, description, price) VALUES ('Product B', 'Description B', 200.00);
INSERT INTO products (name, description, price) VALUES ('Product C', 'Description C', 300.00);

INSERT INTO orders (quantity, status, order_date) VALUES (3, 'PENDING', '2024-11-02');
INSERT INTO orders (quantity, status, order_date) VALUES (5, 'COMPLETED', '2024-11-01');

INSERT INTO order_items (order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO order_items (order_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO order_items (order_id, product_id, quantity) VALUES (2, 3, 5);