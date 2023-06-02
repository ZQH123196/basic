

import JSEncrypt from "jsencrypt";


let jsEncrypt = new JSEncrypt()

self.onmessage = (e) => {
    let strList: Array<string> = e.data

    let strEncryptList = [];

    for (const str of strList) {
        strEncryptList.push(jsEncrypt.encrypt(str));
    }

    postMessage(strEncryptList)
}



