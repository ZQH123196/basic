USE `rbac0`;

INSERT INTO role (role) VALUES ('admin');
INSERT INTO role (role) VALUES ('test');


INSERT INTO user (username, password) VALUES ('admin', 'admin123');
INSERT INTO user (username, password) VALUES ('testUser1', 'test');
INSERT INTO user (username, password) VALUES ('testUser2', 'test');

INSERT INTO role_user (role, username) VALUES ('admin', 'admin');
INSERT INTO role_user (role, username) VALUES ('admin', 'testUser1');
INSERT INTO role_user (role, username) VALUES ('test', 'testUser2');

INSERT INTO permission (id, expression) VALUES (1, 'system:role:create');








