-- during the delay another transaction may insert:
SELECT COALESCE(MAX(employee_id), 0) + 1 AS next_employee_id
FROM employee;