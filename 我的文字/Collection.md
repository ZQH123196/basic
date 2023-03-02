Java泛型中的标记符含义：

 E - Element (在集合中使用，因为集合中存放的是元素)

 T - Type（Java 类）

 K - Key（键）

 V - Value（值）

 N - Number（数值类型）

？ -  表示不确定的java类型

synchronized

如何选用 ArrayList、LinkedList、Vector？
1. 需要线程安全时，用 Vector。
2. 不存在线程安全问题时，查询操作较多用 ArrayList。
3. 不存在线程安全问题时，增删较多用 LinkedList。





Java 提供了许多保存对象的方法：数组将数字索引与对象相关联。

它保存类型明确的对象，因此在查找对象时不必对结果做类型转换。它可以是多维的，可以保存基本类型的数据。虽然可以在运行时创建数组，但是一旦创建数组，就无法更改数组的大小。

Collection 保存单一的元素，而 Map 包含相关联的键值对。

使用 Java 泛型，可以指定集合中保存的对象的类型，因此不能将错误类型的对象放入集合中，并且在从集合中获取元素时，不必进行类型转换。

各种 Collection 和各种 Map 都可以在你向其中添加更多的元素时，自动调整其尺寸大小。

集合不能保存基本类型，但自动装箱机制会负责执行基本类型和集合中保存的包装类型之间的双向转换。

像数组一样， List 也将数字索引与对象相关联，因此，数组和 List 都是有序集合。

如果要执行大量的随机访问，则使用 ArrayList ，如果要经常从表中间插入或删除元素，则应该使用 LinkedList 。队列和堆栈的行为是通过 LinkedList 提供的。Map 是一种将对象（而非数字）与对象相关联的设计。 

HashMap 专为快速访问而设计，而 TreeMap 保持键始终处于排序状态，所以没有 HashMap 快。

 LinkedHashMap 按插入顺序保存其元素，但使用散列提供快速访问的能力。

Set 不接受重复元素。 HashSet 提供最快的查询速度，而 TreeSet 保持元素处于排序状态。 LinkedHashSet 按插入顺序保存其元素，但使用散列提供快速访问的能力。

不要在新代码中使用遗留类 Vector ，Hashtable 和 Stack 。

以下是 [OnJava](https://lingcoder.github.io/OnJava8/#/book/12-Collections?id=for-in%e5%92%8c%e8%bf%ad%e4%bb%a3%e5%99%a8) 的译者所绘制的 Java 集合框架简图，黄色为接口，绿色为抽象类，蓝色为具体类。虚线箭头表示实现关系，实线箭头表示继承关系。

![collection](Collection.assets/collection.png)