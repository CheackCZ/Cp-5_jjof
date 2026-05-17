-- check current task status
SELECT status
FROM task
WHERE project_id = 3
  AND sprint_number = 4
  AND task_number = 4;

-- next free time entry number for this task

SELECT COALESCE(MAX(entry_number), 0) + 1 AS next_entry_number
FROM time_entry
WHERE project_id = 3
  AND sprint_number = 4
  AND task_number = 4;