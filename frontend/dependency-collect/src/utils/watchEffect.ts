
let listenerFn: Function | null = null;
function watchEffect(fn: Function) {
    listenerFn = fn;
    fn();
    listenerFn = null;
}
export {
    listenerFn,
    watchEffect
}