
根据 index.html 里面的内容，来写出能让其如同 vue 一样的响应式特性代码。

题解：
首先，响应式就是发布订阅模型，发布订阅的核心只有两样东西，event、listener。
我们可以将 值 的改变作为 event。
在将 index.html 里面的 {{}} 都视作 listener 来看待。
这样当 event 发生时，也就值发生改变时，就会去 notify 所有的 listener。

接下来就是要考虑时机了，
何时收集依赖？
    在初次运行时，就分析 app 内所有的 {{}} 收集并运行一次依赖，将默认值设置上去
何时调用依赖？
    初次运行时和每次对值设置时，也就 event 发生时。


具体设计：
每个 listener 都持有自己的 el，listener 的 notify 实现都一模一样，将传入的值赋值给 el 元素
class Listener {
    el;

    constructor(el) {
        this.el = el
    }

    updateValue(newValue) {
        el.textContent = newValue
    }
}

class Event {
    eventValue;
    listnerList: Set<Listener> = new Set()

    constructor(eventValue) {
        this.eventValue = eventValue
    }

    registerListner(listener: Listener) {
        listnerList.add(listener)
    }

    notify(newValue) {
        listnerList.forEach((listner) => {
            listner.updateValue(newValue)
        })
    }
}












