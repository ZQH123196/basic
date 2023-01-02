

CREATE DATABASE IF NOT EXISTS `tree`;

USE `tree`;

CREATE TABLE tree_node(
  node_name CHAR(10) NOT NULL PRIMARY KEY,
  path_string VARCHAR(500) NOT NULL
);

