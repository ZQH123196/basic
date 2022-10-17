
依赖链
A -> B -> C


场景一：
A B 都有 @SpringApplication
C 作为公共子依赖

A、B、C 各自都有 application.yaml


此时 B @Value 报找不到值


