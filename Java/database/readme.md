SQL 是一门语言，其中根据其功能可以划分为五大类：
DDL（data definition language）：create、drop、alter
DML（data manipulation language）：insert、update、delete
DQL（data query language）：select
DCL（data control language）：grant、revoke
TCL（transaction control language）：commit、rollback、savepoint



TCL 具有 ACID 的特征：atomicity、consistency、isolation、durability
atomicity：一个事务是不可分割的工作单位，事务中的操作要么都做，要么都不做。
consistency：数据库有一致性状态，事务必须使得数据库从一个一致性状态到另一个一致性状态。
isolation：一个事务不能被其他事务干扰。
durability：一个事务被提交修改，对数据库中数据的操作就是永久性的。

理解 consistency，一致性有数据库一致性，也有分布式一致性，不可混为一谈。
> CAP 理论中的 C（consistency）跟数据库的 consistency 不是一个东西。

数据库一致性主要有 
修改丢失：事务 T1、T2 同时对一个数据进行修改，T1、T2 的更新操作可能会相互覆盖 
脏读（针对未提交数据）：事务 T1 读取了 T2 修改的数据，但是 T2 后续回滚了，也将那个数据回滚了，此时 T1 所读到的就是错误数据，也就是脏读。
幻读（提交前后的数据条数不一致）：事务 T1 读取条数之后处于执行期间时，T2 又添加了一条，这就导致 T1 执行前跟执行后的实际条数不同了，这就是幻读。T1 第一次查询是 n 条，结果执行后在查询变成非 n 条，让人以为第一次查询出现了幻觉。
不可重复读（执行中有其他提交，执行前后数据不一致）：事务 T1 在执行开始时读取了数据，事务 T2 在 T1 执行期间修改了该数据，T1 在执行的最后再次读取了该数据，发现与开始时读取的数据不一致，即无法确保前后能重复读出同样数据。
https://www.cnblogs.com/Hakuna-Matata/p/7772794.html


发展历史，使用指南
jdbc connection(DriverManager.getConnection()) -> datasource(connection pool javax) -> 
PooledConnectuon/ConnectionPoolDataSource/ConnectionEventListener -> 
spring jdbcTemplate/NamedTemplate -> orm -> spring boot + orm

javax.sql.DataSource interface, new in the JDBC 2.0 API, provides another way to connect to a data source. The use of a DataSource object is the preferred means of connecting to a data source.

