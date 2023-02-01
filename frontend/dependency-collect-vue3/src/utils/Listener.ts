class Listener {
    el: HTMLElement | Node;

    constructor(el: HTMLElement | Node) {
        document.querySelector
        this.el = el
    }

    updateValue(this: any, newValue: any) {
        this.el.textContent = newValue
    }
}

export { Listener };