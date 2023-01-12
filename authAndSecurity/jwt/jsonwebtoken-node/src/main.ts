
// import axios from 'axios';
// let url = ""
// axios.post(url);




function sleep(delay: number) {
    let start = +new Date()
    while (+new Date() - start < delay) {}
}


import jwt, { SignOptions } from "jsonwebtoken";

let payload = {

}

let secret = "8999"

let signOptions: SignOptions = {
    expiresIn: "1s",
    subject: "user"
}
const jwtSign = jwt.sign(payload, secret, signOptions)


sleep(2 * 1000)

let res = jwt.verify(jwtSign, secret)
console.log(res);




