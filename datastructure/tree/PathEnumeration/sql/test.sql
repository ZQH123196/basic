USE `tree`;

SELECT * FROM tree_node tn;

SET @RootNode = 'A';
SELECT * FROM tree_node
WHERE node_name = @RootNode;



-- create 添加叶子节点
-- 在 根节点 后加入一个 B
SET @ParentNode = @RootNode;
SET @CurNode = 'B';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

INSERT INTO tree_node
(node_name, path_string)
VALUES
(@CurNode, CONCAT(@ParentPath, @CurNode));


-- 在 根节点 后加入一个 C
SET @ParentNode = @RootNode;
SET @CurNode = 'C';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

INSERT INTO tree_node
(node_name, path_string)
VALUES
(@CurNode, CONCAT(@ParentPath, @CurNode));


-- 在 C 节点后加入一个 D
SET @ParentNode = 'C';
SET @CurNode = 'D';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

INSERT INTO tree_node
(node_name, path_string)
VALUES
(@CurNode, CONCAT(@ParentPath, @CurNode));

-- 在 C 节点后加入一个 E
SET @ParentNode = 'C';
SET @CurNode = 'E';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

INSERT INTO tree_node
(node_name, path_string)
VALUES
(@CurNode, CONCAT(@ParentPath, @CurNode));

-- 在 C 节点后加入一个 F
SET @ParentNode = 'C';
SET @CurNode = 'F';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

INSERT INTO tree_node
(node_name, path_string)
VALUES
(@CurNode, CONCAT(@ParentPath, @CurNode));

-- 添加 中间节点（非叶子节点）
-- 需求：在 C 和 D 之间添加一个 T 节点，或者说是在 D 之前插入一个节点
-- 先当做叶子节点添加，然后更新原有的子节点
SET @ParentNode = 'C';
SET @CurNode = 'T';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

INSERT INTO tree_node
(node_name, path_string)
VALUES
(@CurNode, CONCAT(@ParentPath, @CurNode));

SET @ParentNode = 'T';
SET @CurNode = 'D';

SELECT  @ParentPath := `path_string` FROM tree_node WHERE node_name = @ParentNode;

UPDATE tree_node
SET path_string = CONCAT(@ParentPath, @CurNode)
WHERE node_name = @CurNode;


-- query 查询


-- 获取 当前节点的子树
SET @CurNode = 'A';
SELECT * FROM tree_node WHERE path_string LIKE CONCAT('%', @CurNode, '%') ;



-- 获取 当前节点到根节点 的全路径
-- 查找所有 path 是当前节点子集的就行
-- A	A  	A 在 ACF 中第一次出现的位置是 1
-- C	AC 	AC 在 ACF 中第一次出现的位置是 1
-- F	ACF ACF 在 ACF 中第一次出现的位置是 1
SET @CurNode = 'F';
SELECT P2.*
FROM tree_node AS P1,
tree_node AS P2
WHERE P1.node_name = @CurNode
AND POSITION(P2.path_string IN P1.path_string) = 1;


-- 获取 某节点的父节点
-- 先截取到当前节点路径上的父节点然后精准匹配
SET @CurNode = 'F';

SELECT @ParentNode := SUBSTRING(path_string, -2, 1) FROM tree_node WHERE node_name = @CurNode;

SELECT * FROM tree_node
WHERE node_name = @ParentNode



-- update 修改


-- 更新叶子节点
-- 直接修改对应节点就行
SET @CurNode = 'F';
SET @NewNode = 'G';

UPDATE tree_node 
SET node_name = @NewNode, path_string = REPLACE(path_string, @CurNode, @NewNode)
WHERE node_name = @CurNode



-- 更新非叶子节点
-- 需要获取子树，并 replace 子树中当前节点的值就行了，这一步跟 delete 非叶子节点一样的，只是替换子树路径的值不同
SET @CurNode = 'F';
SET @NewNode = 'G';

UPDATE tree_node 
SET node_name = @NewNode, path_string = REPLACE(path_string, @CurNode, @NewNode)
WHERE node_name = @CurNode

SELECT node_name FROM tree_node WHERE path_string LIKE CONCAT('%', @CurNode, '%') AND node_name != @CurNode

UPDATE tree_node 
SET path_string = REPLACE(path_string, @CurNode, @NewNode)
WHERE node_name IN ( SELECT node_name FROM 
(SELECT node_name FROM tree_node WHERE path_string LIKE CONCAT('%', @CurNode, '%') AND node_name != @CurNode)
AS t_table
)




-- delete 删除



-- 删除 叶子节点
-- 很简单，因为父节点不记录子节点的任何信息，直接删除那个节点就行
-- 从 C 节点删除 F
SET @CurNode = 'F';

DELETE FROM tree_node 
WHERE node_name = @CurNode



-- 删除 中间节点
-- 先获取要删节点的子树，直接将子树路径中的当前节点删掉即可（因为存的本来就是从根路径到自身的路径），然后在删除当前节点
-- 删除 C 节点，父级为 A，子树为 ACTD、ACE、ACF、ACT
SET @CurNode = 'C';

SELECT node_name FROM tree_node WHERE path_string LIKE CONCAT('%', @CurNode, '%') AND node_name != @CurNode



UPDATE tree_node 
SET path_string = REPLACE(path_string, @CurNode, '')
WHERE node_name IN ( SELECT node_name FROM 
(SELECT node_name FROM tree_node WHERE path_string LIKE CONCAT('%', @CurNode, '%') AND node_name != @CurNode)
AS t_table
)














