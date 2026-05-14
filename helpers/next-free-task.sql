-- next free task number in a sprint
SELECT COALESCE(MAX(task_number), 0) + 1 AS next_task_number
FROM task
WHERE project_id = 20
  AND sprint_number = 1;