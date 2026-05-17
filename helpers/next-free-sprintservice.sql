-- next free sprint number in project
SELECT COALESCE(MAX(sprint_number), 0) + 1 AS next_sprint_number
FROM sprint
WHERE project_id = 20;

-- list incomplete tasks which will be moved
SELECT *
FROM task
WHERE project_id = 20
  AND sprint_number = 1
  AND status <> 'done';