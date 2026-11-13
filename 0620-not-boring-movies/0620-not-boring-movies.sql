# Write your MySQL query statement below
SELECT *
FROM Cinema
where id%2!=0 And description!="boring"
order by rating DESC;