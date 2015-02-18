/**
 * Migracija 04: Mijenja postojeću tabelu buyer_products da doda strane ključeve
 *
 * Koristimo istu proceduru kao u migraciji 02 drop description: kreiramo novu tabelu,
 * kopiramo podatke, bacimo staru tabelu i promijenimo ime nove tabele.
 */
BEGIN;

CREATE TABLE new_buyer_products (
	buyer_id INTEGER,
	product_id INTEGER,
	FOREIGN KEY(buyer_id) REFERENCES buyers,
	FOREIGN KEY(product_id) REFERENCES products
);

/*
 * Ako prethodna šema baze nije imala definisane strane ključeve, onda postoji visoka
 * šansa da u bazi postoje nekonzistentni podaci. Zbog toga je dobra ideja isključiti
 * strane ključeve prije kopiranja podataka.
 */
PRAGMA foreign_keys = OFF;

INSERT INTO new_buyer_products (buyer_id, product_id)
SELECT buyer_id, product_id
FROM buyer_products;

/*
 * Nakon što su podaci kopirani možemo ponovo uključiti strane ključeve i diviti se
 * konzistentnosti naše baze.
 */
PRAGMA foreign_keys = ON;

DROP TABLE buyer_products;

ALTER TABLE new_buyer_products
RENAME TO buyer_products;

COMMIT;
