
根据 index.html 里面的内容，来写出能让其如同 vue 一样的响应式特性代码。

题解：
首先，响应式就是发布订阅模型，发布订阅的核心只有两样东西，event、listener。
我们可以将 值 的改变作为 event。
在将 index.html 里面的 {{}} 都视作 listener 来看待。
这样当 event 发生时，也就值发生改变时，就会去 notify 所有的 listener。



具体设计：
每个 listener 都持有自己的 el，listener 的 notify 实现都一模一样，将传入的值赋值给 el 元素
class Listener {
    const el;

    Listener(el) {
        this.el = el
    }

    function updateValue(newValue) {
        el.textContent = newValue
    }
}

class Event {
    const listnerList: Listener = []

    function registerListner(listener: Listener) {
        listnerList.add(listener)
    }

    function notify(newValue) {
        listnerList.foreach((listner) => {
            listner.updateValue(newValue)
        })
    }
}












