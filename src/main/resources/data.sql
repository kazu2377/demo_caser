-- 初期ユーザーデータ消去（必要に応じて）
DELETE FROM todos WHERE user_id IN (SELECT id FROM users WHERE username IN ('admin', 'user'));
DELETE FROM users WHERE username IN ('admin', 'user');

-- 初期ユーザーデータ（パスワードは 'password'）
INSERT INTO users (username, password, role, enabled)
VALUES ('admin', '$2a$10$rAnd6pVkFYNlpJ6u1IXnB.4xbSEDFQx3hXR9.Xxe7JqTnK6jjJUi2', 'ADMIN', true);

INSERT INTO users (username, password, role, enabled)
VALUES ('user', '$2a$10$9EyKIXbC5Y.pryrP0EQQ.eZs/5YwhvQXsqoAIimxjKl7V.HllZzhi', 'USER', true);

-- 初期Todoデータ
INSERT INTO todos (title, completed, created_at, user_id)
VALUES ('管理者のタスク1', false, CURRENT_TIMESTAMP(), 
        (SELECT id FROM users WHERE username = 'admin'));

INSERT INTO todos (title, completed, created_at, user_id)
VALUES ('通常ユーザーのタスク1', false, CURRENT_TIMESTAMP(), 
        (SELECT id FROM users WHERE username = 'user')); 