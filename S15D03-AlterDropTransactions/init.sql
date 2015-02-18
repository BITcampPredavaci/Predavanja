/**
 * Skripta za kreiranje baze s početnom šemom. Ovu skriptu izvršiti prije izvršavanja bilo koje migracije.
 *
 * OPREZ! Ako baza već postoji, sve tabele u bazi će biti izbrisane i rekreirane što može dovesti
 * do gubitka podataka. Ako želite promijeniti šemu baze, koristite migracije.
 *
 */

DROP TABLE IF EXISTS products;

CREATE TABLE products (
	id INTEGER PRIMARY KEY,
	name VARCHAR(255),
	type_id INTEGER,
	price DECIMAL,
	quantity INTEGER
);

DROP TABLE IF EXISTS types;

CREATE TABLE types (
	id INTEGER PRIMARY KEY,
	name VARCHAR(255)
);
