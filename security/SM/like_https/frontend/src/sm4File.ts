// @ts-ignore
import {sm2, sm4} from 'sm-crypto';

import axiosInstance from './request';






export async function encFileSm4(url: string, file: File, sm4KeyHex: string) {
    
    const text = await file.text();
    
    const hexWordArray = CryptoJS.enc.Utf8.parse(text);
    const hexStr =  CryptoJS.enc.Hex.stringify(hexWordArray);

    const encFile = sm4.encrypt(hexStr, sm4KeyHex) 
    const fileFormData: FormData = new FormData()
    // 使用 append() 方法时，可以通过第三个可选参数设置发送请求的头 Content-Disposition 指定文件名。如果不指定文件名（或者不支持该参数时），将使用名字“blob”。
    fileFormData.append('file', encFile, file.name)
    
    return axiosInstance.postForm(url, fileFormData);
}




