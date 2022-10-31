SELECT * FROM role_hierarchy;
SELECT * FROM role_hierarchy WHERE parent_role_id IS NULL;

SELECT @parent:=`role`  FROM role_hierarchy WHERE parent_role_id IS NULL;
SELECT @parent;

-- 此处应当是循环所有根主节点依次构建继承树
-- sql 很难表达循环，所以这里只用一个根节点
SELECT @parentL1:=`role` FROM role_hierarchy rh WHERE rh.parent_role_id = @parent;
SELECT @parentL2:=`role` FROM role_hierarchy rh WHERE rh.parent_role_id = @parentL1;
SELECT @parentL3:=`role` FROM role_hierarchy rh WHERE rh.parent_role_id = @parentL2;

INSERT INTO role (role, username) VALUES ('level4_2', 'testUser4');
INSERT INTO role_hierarchy (role, parent_role_id) VALUES ('level4_2', @parentL3);
SELECT @parentL4:=`role` FROM role_hierarchy rh WHERE rh.parent_role_id = @parentL3;

