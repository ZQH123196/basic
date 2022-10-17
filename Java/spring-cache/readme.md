

01-basicCache:
- 

02-redisCache：
- 使用了 redis 的缓存，自动切换到 redis


03-mu
- 不同的数据有时候使用不同的缓存
  - CoffeeCache 使用 redis 进行缓存 @RedisHash(value = "springbucks-coffee", timeToLive = 60)
  - CoffeeCacheRepository 使用的是统一的 JPA CrudRepository 接口，这个接口抽象兼容很多
  - BytesToMoneyConverter、MoneyToBytesConverter 用于 redis 的类型转换，RedisCustomConversions 用于注册进去
    