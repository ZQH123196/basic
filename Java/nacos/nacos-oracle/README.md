

本项目是以下两个项目成果的结合，感谢他们的项目！
* https://github.com/pig-mesh/nacos-datasource-plugin-oracle
* https://github.com/chxlay/iserver-nacos

在查看了源码之后，发现 nacos 的支持是个有趣的玩意，其内部是使用 jdbctemplate 实现的，因此事实上是对 jdbctemplate 的支持。
其实现是通过获取获取对应 mapper 中拼接好的各种数据库方言模板，但是 mapper 不填值，实际值还是 ?，因此只需要返回 string 就行。
nacos 在获取了 mapper 中对应语言的方言实现后，之后在由 nacos 用 jdbctemplate 来填充。
当然，这实际上就要求对应数据库方言的开发者需要深入源码之后才能理解如何去编写。

