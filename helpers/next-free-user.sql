-- next free user id
SELECT COALESCE(MAX(user_id), 0) + 1 AS next_user_id
FROM users;

-- next free user_detail id
SELECT COALESCE(MAX(id), 0) + 1 AS next_userdetail_id
FROM user_detail;