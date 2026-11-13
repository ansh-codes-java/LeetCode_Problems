# Write your MySQL query statement below
SELECT 
    IFNULL(
        (SELECT num
         FROM MyNumbers
         Group by num
         having count(*)=1
         order by num DESC
          LIMIT 1),
     NULL
)as num ;