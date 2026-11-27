SELECT s.user_id as user_id, ROUND(IFNULL(Sum(action="confirmed")/COUNT(*),0),2) as confirmation_rate
FROM signups as s
LEFT JOIN confirmations as c
ON s.user_id = c.user_id
Group by s.user_id;