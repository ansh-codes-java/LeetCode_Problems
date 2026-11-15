# Write your MySQL query statement below
SELECT v.customer_id as customer_id,count(v.customer_id) as count_no_trans
FROM Visits v
LEFT JOIN Transactions t
ON v.visit_id = t.visit_id
where t.amount is null
Group by v.customer_id;