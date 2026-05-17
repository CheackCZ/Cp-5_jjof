-- next free sprint number in project
SELECT COALESCE(MAX(sprint_number), 0) + 1 AS next_sprint_number
FROM sprint
WHERE project_id = 20;

-- next free task number in sprint
SELECT COALESCE(MAX(task_number), 0) + 1 AS next_task_number
FROM task
WHERE project_id = 20
  AND sprint_number = 1;