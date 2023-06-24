

import JSEncrypt from "jsencrypt";


let jsEncrypt = new JSEncrypt()

self.onmessage = (e) => {
    let data: string = e.data

    

    let strEncrypt = jsEncrypt.encrypt(data);
    postMessage(strEncrypt)
}

