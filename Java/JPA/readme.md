

01 是最基本的使用：在 application-dev.yaml 配置使用了 hibernate 的初始化功能。
- @EnableJpaRepositories 这个 @Import({JpaRepositoriesRegistrar.class}) 启用 jpa 的自动配置
- @GeneratedValue 默认的生成策略是自动，可能是 oracle 用 sequence，mysql 用自增。
- @Type 可以指定类型处理器，处理复杂类型，比如金钱。type 属性用于指定处理的类，而 parameters 可以传入一个注解作为配置类的参数！因为其 @Type 就是配置 parameters 为注解类型的。
- 多对多这里还没写完，https://blog.csdn.net/sinat_34003720/article/details/116760340
    * @ManyToMany - 表示生成多对多关系的table
    * @JoinTable - 用来对多对多关系table进行定制

02 是使用了高级一点的接口，展示了一个复杂度较高的模型
- 自定义了很多语义化的方法，这些方法会被语法解析器解析并自动根据语义生成代码
  - List<CoffeeOrder> findByCustomerOrderById(String customer); 因为其返回带顺序的 list，所以意思是根据传入的 customer 字符串来查找，名字要一致，否则反射识别不了
  - List<CoffeeOrder> findByItems_Name(String name); 因为其返回带顺序的 list，所以是查询 Items.name 与传入的 name 相等的值 
  - findTop3ByOrderByUpdateTimeDescIdAsc，OrderBy 启用排序，然后按照更新时间的逆序来排列，如果更新时间相同则按照 id 升序排列的前三个数据，其 UpdateTime 和 Id 都是列名
- @MappedSuperclass 抽离共同父类属性，用来描述继承上的关联关系
  - 相当于一个抽象概念，是编程上的抽象，所以不会映射到数据库，其实是为了适配 oop 中父类出现的，为了复用代码而已。
  - 标注为 @MappedSuperclass 的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
  - 标注为 @MappedSuperclass 的类不能再标注 @Entity 或 @Table 注解，也无需实现序列化接口。
- @ToString(callSuper = true) 这个是 lombok 的注解，有了 callSuper = true 属性的配置就可以自动实现将父类属性一并打印的 toString 方法，主要是配合继承关系来使用
- @PagingAndSortingRepository
- @Enumerated 用来表达枚举类型
- @OrderBy("id") 表达查出来的 list 将按照 id 排序，默认 ASC 升序。这个次序是为了 list 这有顺序类型而出现的。
- @EnableTransactionManagement，然后在所有区域加上 @Transactional 使用事务管理器，不开启事务的话有些地方会因为没 Session 而报 LazyInitializationException，比如 foreach，其会传入一个 lambda 表达式，不加事务的话无法吧 session 传播进去。


03 是使用了更高一层的抽象 
- 将 table 的名称改的正常一些 T_MENU -> T_COFFEE
- JpaRepository 接口是 JPA 层的抽象，算是应用级的抽象，建议使用这个
- @Type 使用了新的金额处理类 PersistentMoneyMinorAmount
- spring.jpa.hibernate.ddl-auto 设置为 none，使用 spring-boot 的数据库初始化功能



01 引入 joda-money 用来处理金钱类型，引入 usertype.core 来作为 joda 与 hibernate 之间的类型桥接




