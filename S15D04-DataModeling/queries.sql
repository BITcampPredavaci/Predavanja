-- number of sold items for each item
SELECT i.name, sum(oi.quantity) 
FROM items i INNER JOIN 
	order_items oi ON i.id = oi.item_id 
GROUP BY i.name;

-- total number of orders for each customer
SELECT c.name, count(o.id) 
FROM customers c INNER JOIN 
	orders o ON c.id = o.customer_id 
GROUP BY c.name;


-- total number of order items (every item is counted as 1 regardles on quantity) bought by each customer
SELECT c.name, count(oi.item_id) 
FROM customers c INNER JOIN 
	orders o ON c.id = o.customer_id INNER JOIN 
	order_items oi ON o.id = oi.order_id 
GROUP BY c.name;

-- total number of items bought by each customer
SELECT c.name, sum(oi.quantity) 
FROM customers c INNER JOIN 
	orders o ON c.id = o.customer_id INNER JOIN 
	order_items oi ON o.id = oi.order_id 
GROUP BY c.name;

-- total price for each order
SELECT order_id, sum(quantity * unit_price) 
FROM order_items 
GROUP BY order_id; 

-- order with max total price
SELECT order_id, sum(quantity * unit_price) total_price 
FROM order_items 
GROUP BY order_id 
ORDER BY total_price DESC 
LIMIT 1;

-- second biggest order
SELECT order_id, sum(quantity * unit_price) total_price 
FROM order_items 
GROUP BY order_id 
ORDER BY total_price DESC 
LIMIT 1 OFFSET 1;

-- all orders whose total price was greater than 2, ordered by totla price descending
SELECT order_id, sum(quantity * unit_price) total_price 
FROM order_items 
GROUP BY order_id 
HAVING total_price > 2 
ORDER BY total_price DESC;

-- all orders whose total price was greater than 2 if items cheaper than 1 are ignored, ordered by total price descending
SELECT order_id, sum(quantity * unit_price) total_price 
FROM order_items 
WHERE unit_price >=1 
GROUP BY order_id 
HAVING total_price > 2 
ORDER BY total_price DESC;

-- string concatination
SELECT name || ': ' || address as data FROM customers;

-- = operator is case sensitive
SELECT * FROM items WHERE upper(name) = upper("mlijeko");

-- LIKE operator is case insensitive
SELECT * FROM items WHERE name LIKE "mli%"; -- starts with 'mli'
SELECT * FROM items WHERE name LIKE "mlijek_"; -- single char

-- GLOB operator is case sensitive
SELECT * FROM items WHERE name GLOB "Mli*"; -- starts with 'Mli', case senitive
SELECT * FROM items WHERE lower(name) GLOB "mli*"; -- starts with 'mli', case insenitive
SELECT * FROM items WHERE name GLOB "Mlijek?"; -- single char

