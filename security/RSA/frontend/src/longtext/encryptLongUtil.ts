import JSEncrypt from "jsencrypt";

/**
 * 对于长文本的加解密，我期望自行分割
 * 传入的是一个已被分割的 list，返回的也是对应的 list
 * 而不是使用 竖线 分割符的方式来做长文本的加解密，这样前后端就不需要 split 了，而是直接接受一个 list
 * 
 * 
 * 前端 将所有 post 请求的 body 都分割加密成 list
 * 后端 则是通用都接收 list，并将其解密并转换为真正的 body
 */


let jsEncrypt = new JSEncrypt()


let str = "1".repeat(1001)

let chunkSize = 100
// ceil 向上取整
let lenList = Math.ceil(str.length / 100)

let strList = []
for(let i = 0; i < lenList; i++) {
    strList[i] = str.substring(i * chunkSize, (i+1) * chunkSize)
}



const encWorker = new Worker(new URL('./encLongWorker.ts', import.meta.url), {
    type: 'module',
})
encWorker.postMessage(strList)

encWorker.onmessage = (e) => {
    console.log(e.data);
}



// let strEncrypt = jsEncrypt.encrypt(str);

// console.log(`strEncrypt=[${strEncrypt}]`);



