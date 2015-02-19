INSERT INTO items (id, name, price, stock_count) VALUES (1, 'Mlijeko', 1.5, 1000);
INSERT INTO items (id, name, price, stock_count) VALUES (2, 'Sir', 2.3, 300);
INSERT INTO items (id, name, desc, price, stock_count) VALUES (3, 'Hljeb', 'Razev', 0.7, 100);

INSERT INTO customers (id, name, address) VALUES (1, 'Niko Nikic', 'Bezimena bb');
INSERT INTO customers (id, name, address) VALUES (2, 'Komsija', 'Poprecna do bb');


INSERT INTO orders (id, customer_id, order_date) VALUES (1, 1, strftime('%s', 'now', '-1 day'));
INSERT INTO orders (id, customer_id, order_date) VALUES (2, 1, strftime('%s', 'now'));
INSERT INTO orders (id, customer_id, order_date) VALUES (3, 2, strftime('%s', 'now', 'start of week'));

INSERT INTO order_items (id, order_id, item_id, quantity, unit_price) VALUES (1, 1, 2, 1, 2.3);
INSERT INTO order_items (id, order_id, item_id, quantity, unit_price) VALUES (2, 1, 3, 2, 0.7);
INSERT INTO order_items (id, order_id, item_id, quantity, unit_price) VALUES (3, 1, 1, 5, 1.4);

INSERT INTO order_items (id, order_id, item_id, quantity, unit_price) VALUES (4, 2, 3, 2, 0.8);

INSERT INTO order_items (id, order_id, item_id, quantity, unit_price) VALUES (5, 3, 1, 1, 1.5);
INSERT INTO order_items (id, order_id, item_id, quantity, unit_price) VALUES (6, 3, 3, 1, 0.8);