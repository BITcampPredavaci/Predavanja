CREATE TABLE IF NOT EXISTS items (
	id INTEGER PRIMARY KEY,
	name TEXT,
	desc TEXT,
    price REAL,
    stock_count INTEGER
);

CREATE TABLE IF NOT EXISTS customers (
	id INTEGER PRIMARY KEY,
	name TEXT,
    address TEXT
);

CREATE TABLE IF NOT EXISTS orders (
	id INTEGER PRIMARY KEY,
	customer_id INTEGER REFERENCES customers(id),
    order_date INTEGER
);

CREATE TABLE IF NOT EXISTS order_items (
	id INTEGER PRIMARY KEY, -- could use complex key, but often practice is to introduce new simple
    order_id INTEGER REFERENCES orders(id),
    item_id INTEGER REFERENCES items(id),
    quantity INTEGER,
    unit_price REAL
);