/**
 * Migracija 01: Dodaje kolonu description tipa TEXT u tabelu products.
 */

ALTER TABLE products
ADD COLUMN description TEXT;
