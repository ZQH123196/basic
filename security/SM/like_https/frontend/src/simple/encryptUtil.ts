import JSEncrypt from "jsencrypt";




let jsEncrypt = new JSEncrypt()


let str = "1".repeat(100)


const encWorker = new Worker(new URL('./encWorker.ts', import.meta.url), {
    type: 'module',
})
encWorker.postMessage(str)

encWorker.onmessage = (e) => {
    console.log("worker:", e.data);
}

// let strEncrypt = jsEncrypt.encrypt(str);

// console.log(`strEncrypt=[${strEncrypt}]`);



