# Write your MySQL query statement below
SELECT sell_date,
COUNT(DISTINCT product) as num_sold,
Group_CONCAT(
        DISTINCT product
        ORDER BY product ASC
        ) as products 
FROM Activities 
GROUP BY sell_date;