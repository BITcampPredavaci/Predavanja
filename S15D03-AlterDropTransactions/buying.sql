/**
 * Skripta za kupovinu proizvoda: Fata (id=1) kupuje masku za telefon (id=2)
 *
 */

/* Ispis početnog stanja */
SELECT name, quantity
FROM products;

SELECT name, credit
FROM buyers;

/*
 * Počinjemo transakciju: ako skripta ne uspije iz bilo kog razloga
 * (greška u sintaksi, nestanak struje...) sve naredbe unutar transakcije
 * će biti poništene. Također drugi klijenti neće moći čitati parcijalne
 * podatke koje smo upisali sve dok transakcija ne završi u potpunosti.
 */
BEGIN;

/* Skidamo pare s računa */
UPDATE buyers
SET credit = credit - 20.20
WHERE id = 1;

/* Dodjeljujemo Fati masku */
INSERT INTO buyer_products (buyer_id, product_id)
VALUES (1, 2);

/* Smanjujemo količinu dostupnih proizvoda */
UPDATE products
SET quantity = quantity - 1
WHERE id = 2;

/* Završavamo transakciju */
COMMIT;

/* Ispis finalnog stanja */
SELECT products.name, buyers.name, buyers.credit, products.quantity
FROM products, buyers, buyer_products
WHERE buyer_products.buyer_id   = buyers.id
AND   buyer_products.product_id = products.id;
