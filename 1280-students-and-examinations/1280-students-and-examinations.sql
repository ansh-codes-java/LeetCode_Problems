# Write your MySQL query statement below
SELECT st.student_id as student_id ,st.student_name as student_name, sb.subject_name as subject_name, count(e.student_id) as attended_exams
FROM Students st
CROSS JOIN Subjects sb
LEFT JOIN Examinations e
On sb.subject_name = e.subject_name AND st.student_id = e.student_id
GROUP BY st.student_id, st.student_name, sb.subject_name
Order by st.student_id,sb.subject_name;