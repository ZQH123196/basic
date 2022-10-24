-- 本文件适用于 sqlite3
-- 事务的回滚功能
-- 嵌套事务的整体回滚功能，savepoint/release
-- 多种事务类型 begin [ deferred | immediate | exclusive ] transaction;
-- 事务 https://www.sqlite.org/lang_transaction.html
-- 事务保存点 https://www.sqlite.org/lang_savepoint.html

INSERT INTO user (id, name, age) VALUES (1, 'zqh', 8999);
INSERT INTO user (id, name, age) VALUES (2, 'zzr', 123);
INSERT INTO user (id, name, age) VALUES (3, 'qyc', 233);


-- https://sqlite.org/pragma.html#pragma_journal_mode
-- PRAGMA schema.journal_mode = DELETE | TRUNCATE | PERSIST | MEMORY | WAL | OFF
-- 设置日志级别，默认值为 DELETE
PRAGMA main.journal_mode = PERSIST;


-- 1. 下面运行了不生效，因为 rollback 了
SELECT * FROM user;
BEGIN TRANSACTION;
INSERT INTO user (id, name, age) VALUES (4, 'oywj', 110);
ROLLBACK TRANSACTION;
SELECT * FROM user;

-- 2. 下面运行之后生效，成功插入，因为 commit 了
SELECT * FROM user;
BEGIN TRANSACTION;
INSERT INTO user (id, name, age) VALUES (4, 'oywj', 110);
COMMIT TRANSACTION;
SELECT * FROM user;
-- 移除影响数据
DELETE FROM user WHERE id = 4;


--
SELECT * FROM user;
SAVEPOINT test;
BEGIN TRANSACTION;
INSERT INTO user (id, name, age) VALUES (4, 'oywj', 110);
ROLLBACK TRANSACTION;
ROLLBACK TRANSACTION TO SAVEPOINT test;
SELECT * FROM user;


-- 使用保存点，下面会失败
SELECT * FROM user;
SAVEPOINT test;
INSERT INTO user (id, name, age) VALUES (5, 'savepoint', 5);
ROLLBACK TRANSACTION TO SAVEPOINT test;
SELECT * FROM user;

-- 使用保存点，下面会成功
SELECT * FROM user;
SAVEPOINT first;
INSERT INTO user (id, name, age) VALUES (5, 'savepoint', 5);
RELEASE SAVEPOINT first;
SELECT * FROM user;
-- 移除影响数据
DELETE FROM user WHERE id = 5;

-- 保存点给予了一种组合、嵌套事务的可能，极大的增加了灵活性、易用性和~复杂度......、
-- 详情看 https://www.sqlite.org/lang_savepoint.html 的 3. Transaction Nesting Rules
-- 全部成功
SELECT * FROM user;
SAVEPOINT first;
SAVEPOINT second;
INSERT INTO user (id, name, age) VALUES (4, 'oywj', 110);
RELEASE SAVEPOINT second;
INSERT INTO user (id, name, age) VALUES (5, 'savepoint', 5);
RELEASE SAVEPOINT first;
SELECT * FROM user;
-- 移除影响数据
DELETE FROM user WHERE id = 4;
DELETE FROM user WHERE id = 5;

-- 里面成功，外面失败，全部回滚，相当于 nested 类型
SELECT * FROM user;
SAVEPOINT first;
SAVEPOINT second;
INSERT INTO user (id, name, age) VALUES (4, 'oywj', 110);
RELEASE SAVEPOINT second;
INSERT INTO user (id, name, age) VALUES (5, 'savepoint', 5);
ROLLBACK TRANSACTION TO SAVEPOINT first;
SELECT * FROM user;
-- 移除影响数据
DELETE FROM user WHERE id = 4;
DELETE FROM user WHERE id = 5;


-- 下面是 REQUIRES_NEW 的情况，父子都是独立的事务，因此子事务与父事务无瓜
-- 里外是独立事务，外部回滚也不影响已经成功的内部事务
-- 重点是利用 ROLLBACK 不指定名称时的就近
SELECT * FROM user;
SAVEPOINT first;
SAVEPOINT second;
INSERT INTO user (id, name, age) VALUES (4, 'oywj', 110);
RELEASE SAVEPOINT second;
INSERT INTO user (id, name, age) VALUES (5, 'savepoint', 5);
ROLLBACK TRANSACTION;
SELECT * FROM user;
-- 移除影响数据
DELETE FROM user WHERE id = 4;
DELETE FROM user WHERE id = 5;




