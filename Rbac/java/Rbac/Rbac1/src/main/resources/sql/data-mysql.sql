USE `rbac0WithHierarchy`;

INSERT INTO role (role) VALUES ('admin');
INSERT INTO role (role, username) VALUES ('testRole1', 'testUser1');
INSERT INTO role (role, username) VALUES ('testRole2', 'testUser2');
INSERT INTO role (role, username) VALUES ('testRole3', 'testUser3');
INSERT INTO role (role, username) VALUES ('testRole4', 'testUser4');

INSERT INTO role (role, username) VALUES ('level1', 'testUser1');
INSERT INTO role (role, username) VALUES ('level2', 'testUser2');
INSERT INTO role (role, username) VALUES ('level3', 'testUser3');
INSERT INTO role (role, username) VALUES ('level4', 'testUser4');




INSERT INTO user (username, password) VALUES ('admin', 'admin123');
INSERT INTO user (username, password) VALUES ('testUser1', 'test');
INSERT INTO user (username, password) VALUES ('testUser2', 'test');

INSERT INTO role_user (role, username) VALUES ('admin', 'admin');
INSERT INTO role_user (role, username) VALUES ('admin', 'testUser1');
INSERT INTO role_user (role, username) VALUES ('test', 'testUser2');

INSERT INTO permission (id, expression) VALUES (1, 'system:role:create');







INSERT INTO role_hierarchy (role, parent_role_id) VALUES ('admin', null);
INSERT INTO role_hierarchy (role, parent_role_id) VALUES ('level1', 'admin');
INSERT INTO role_hierarchy (role, parent_role_id) VALUES ('level2', 'level1');
INSERT INTO role_hierarchy (role, parent_role_id) VALUES ('level3', 'level2');
INSERT INTO role_hierarchy (role, parent_role_id) VALUES ('level4', 'level3');
