import { MyEvent } from "./Event";
import { Listener } from "./Listener";


const testNeedCollectReg = /\{\{(?<key>.*)\}\}/

let eventList: {
    [_: string]: MyEvent
} = {}

/**
 * 
 * 此处的 key 值来自 {{}} 中的值
{
    name: new MyEvent(""),
    age: new MyEvent("")
}


 * @param el 
 * @param param1 
 */
export function createApp(el: string, { data }: { data: typeof eventList }) {
    eventList = data;
    const rootEl = document.querySelector(el)

    
    rootEl?.childNodes.forEach((node) => {
        let match = testNeedCollectReg.exec(node.textContent!)
        const key = match?.groups?.key.trim()
        
        if (key) {
            const event = eventList[key]
            event.registerListner(new Listener(node))
        }
    })

    // 第一次创建就运行一次全部依赖
    Object.keys(eventList).forEach((key) =>{
        eventList[key].reset();
    })

    return eventList
}






