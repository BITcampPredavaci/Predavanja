/**
 * Migracija 03: Kreira nove tabele za kupce i vezu između kupaca i proizvoda
 */

CREATE TABLE buyers (
	id INTEGER PRIMARY KEY,
	name VARCHAR,
	location VARCHAR,
	credit DECIMAL
);

CREATE TABLE buyer_products (
	buyer_id INTEGER,
	product_id INTEGER
);
