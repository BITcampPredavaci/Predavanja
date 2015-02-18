/**
 * Migracija 02: Briše kolonu description iz tabele products.
 *
 * Brisanje se odvija u više koraka:
 * * pravimo novu tabelu s novom šemom
 * * kopiramo podatke iz stare tabele u novu (INSERT INTO ... SELECT)
 * * brišemo staru tabelu
 * * mijenjamo naziv nove tabele u finalnu

/*
 * Kako klijenti koji pristupaju bazi ne bi dobili priliku da pišu podatke
 * u tabele koje će se obrisati i time naprave nekonzistentnosti, izolovat
 * ćemo skriptu u transakciju (BEGIN ... COMMIT). Transakcija garantuje da
 * će ILI sve naredbe biti izvršene ILI neće nijedna.
 */
BEGIN;

CREATE TABLE new_products (
	id INTEGER PRIMARY KEY,
	name VARCHAR(255),
	type_id INTEGER,
	price DECIMAL,
	quantity INTEGER
);

INSERT INTO new_products (id, name, type_id, price, quantity)
SELECT id, name, type_id, price, quantity
FROM products;

DROP TABLE products;

ALTER TABLE new_products RENAME TO products;

COMMIT;
