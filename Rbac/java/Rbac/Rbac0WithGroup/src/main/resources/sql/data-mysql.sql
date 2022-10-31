USE `rbac0withgroup`;

INSERT INTO role (role) VALUES ('admin');
INSERT INTO role (role) VALUES ('test1');
INSERT INTO role (role) VALUES ('test2');
INSERT INTO role (role) VALUES ('test3');
INSERT INTO role (role) VALUES ('test4');


INSERT INTO user (username, password) VALUES ('admin', 'admin123');
INSERT INTO user (username, password) VALUES ('testUser1', 'test');
INSERT INTO user (username, password) VALUES ('testUser2', 'test');

INSERT INTO role_user (role, username) VALUES ('admin', 'admin');
INSERT INTO role_user (role, username) VALUES ('admin', 'testUser1');
INSERT INTO role_user (role, username) VALUES ('test1', 'testUser2');

INSERT INTO permission (id, expression) VALUES (1, 'system:role:create');




INSERT INTO role_group_admin (`group`) VALUES ('groupAdminGroup');
UPDATE role_group_admin SET roles='admin', users='testUser1' WHERE `group`='groupAdminGroup';



INSERT INTO user_group_admin (`group`) VALUES ('userAdminGroup');
UPDATE user_group_admin SET roles='admin', users='testUser3!@@!testUser4' WHERE `group`='userAdminGroup';





