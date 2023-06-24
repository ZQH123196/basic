






import { Axios, AxiosRequestConfig } from 'axios';
import { log } from 'console';


const axiosConfg: AxiosRequestConfig = {
    baseURL: "http://localhost:8080"
};

const axiosInstance = new Axios(axiosConfg);



axiosInstance.post('/test/helloWorld').then((res) => { console.log(res.data) });


/**
 * sessionstorage 是存活于标签页的，也就说 h5 页面刷新是不会重置的，
 * 因此可以使用其来判断页面是刷新还是被关闭过了
 */
sessionStorage.keepStatus = true;


const pageCloseHandler = async (e) => {
    // 必须要阻止默认行为，否则下面的可能都来不及发送，包括 alert 也会失效
    // 直接点标签关闭的话，alert 会失效，不过刷新的话不会
    e.preventDefault();
    await axiosInstance.post('/test/pageCloseEvent');
    // window.alert("page close event!")    
}


/**
 * https://developer.mozilla.org/zh-CN/docs/Web/API/Window/beforeunload_event
 */
window.addEventListener("beforeunload", pageCloseHandler)






// @ts-ignore
import {sm2, sm4} from 'sm-crypto';

// 可以为 16 进制串或字节数组，要求为解码(这里用 u8)后 128 比特
// 相当于 16 个字符的 ascii，16 * 8 = 128
// 这里已经是 hex 之后的内容了，使用 hex2array 的话就是 16 个字
// groovy: println(HexUtil.decodeHex("0123456789abcdeffedcba9876543210").size())
// const sm4KeyHex = "0123456789abcdeffedcba9876543210"



import CryptoJS from "crypto-js";
let u8WordArray = CryptoJS.enc.Utf8.parse("1008610086100861");
let sm4KeyHex =  CryptoJS.enc.Hex.stringify(u8WordArray);
// 31303038363130303836313030383631
// console.log("sm4KeyHex", sm4KeyHex);



// 公钥
const publicKey = "0430756f05b5e8dea62e044e7b6e61d0de8d16acc97bbc23a12506c3cb28536597f3cc59de599dcc117cf8299955287f79cd09ac60291e070a69e4b271c546817f" 

//sm2的加解密时有两种方式即0——C1C2C3、1——C1C3C2，前后端需要统一
const cipherMode = 1



let TableName = "eor"+new Date().getUTCDay()
// 将自己的 sm4 id 存入 sessionstorage
sessionStorage.setItem(TableName, sm4KeyHex)

// 注意后端 Java 使用 bc 库解密的话，需要在密文串前面加上 04
let EncSm4Key = "04" + sm2.doEncrypt(sm4KeyHex, publicKey, cipherMode)

let registerSm4KeyHeaders = {
    TableName,
    EncSm4Key
}
await axiosInstance.post('/test/registerSm4Key', {}, {headers: registerSm4KeyHeaders});




const data = '郑启华'
let sm4Key = sessionStorage.getItem(TableName)
// 加密，默认输出 16 进制字符串，默认使用 pkcs#7 填充（传 pkcs#5 也会走 pkcs#7 填充）
let EncSm4Data = sm4.encrypt(data, sm4Key) 
console.log("EncSm4Data", EncSm4Data);

let sm4DecryptTestHeaders = {
    TableName,
    EncSm4Data
}
await axiosInstance.post('/test/sm4DecryptTest', {}, {headers: sm4DecryptTestHeaders});



