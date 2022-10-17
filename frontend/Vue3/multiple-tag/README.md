# 多标签页设计 Vue 3 + Typescript + Vite



首先，我们有一个基本页面



然后多标签页就是要适配这个页面上面的路由，提供一个便捷跳转的功能

所以，多标签页本质上多标签页是一个**附加增强功能**。

既然是增强功能，那就有要被增强的对象，因此被增强的对象如下：





我们期望实现的功能是，类似 chrome 浏览器多标签页的功能：

首先是可以在标签页的范围内随意拖放：

![image-20220121110703818](README.assets/image-20220121110703818.png)

然后是可以右击展示附加功能：

![image-20220121110357745](README.assets/image-20220121110357745.png)



多页签编码：
首先，他的行为其实很简单，就是增删，因此可以简化为两个问题去编码
什么时候增加？
什么时候删除？

首先应当维护一个 tabs，其应当为一个有序列表，内容类似:
{
    "title",
    "path"
}
增加应该是点击侧边栏的菜单时，将跳转的页面添加到 tabs 中，而且不应该重复添加。


删除仅在右击出选项菜单，并点击清除时进行，只需要将 tabs 相应的数据清空即可完成效果，修改后的 tabs 将会自动触发单向数据流来更新视图。



增加这里，有两个方案：
方法一：是在路由守卫哪里在每次点击时手动的添加到 tabs
方法二：发布订阅模式，子组件内部订阅路由的变化，只要路由发生跳转就进行对比添加



右击弹出菜单：
关于这个，其实就是 ui>li 的简单组合，至于出现在鼠标的位置则是需要动态 style，比如
`:style="{ left: left + 'px', top: top + 'px' }` 即可实现出现在鼠标的位置。


