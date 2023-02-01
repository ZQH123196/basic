import { Listener } from "./Listener";

class MyEvent {
    private defaultValue: any;
    private eventValue: any;
    listnerList: Set<Listener> = new Set();

    constructor(eventValue: any) {
        this.defaultValue = eventValue
        this.eventValue = eventValue
    }

    registerListner(listener: Listener) {
        this.listnerList.add(listener)
    }

    notify(newValue: any) {
        this.listnerList.forEach((listner) => {
            listner.updateValue(newValue)
        })
    }

    reset() {
        this.value = this.defaultValue
    }

    set value(newValue) {
        this.eventValue = newValue
        this.notify(newValue)
    }

    get value() {
        return this.eventValue
    }

}

export {
    MyEvent
}