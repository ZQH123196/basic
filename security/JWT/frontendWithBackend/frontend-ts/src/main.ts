import axios, { AxiosInstance, AxiosRequestConfig, AxiosRequestHeaders, AxiosResponse } from "axios";


const defaultConfig: AxiosRequestConfig = {
  baseURL: "",
  timeout: 5000,
  headers: {
    "Accept": "application/json, text/plain, */*",
    "Content-Type": "application/json",
  },
}

const instance = axios.create(defaultConfig)


instance.interceptors.request.use((config: AxiosRequestConfig) => {
  return config
})
instance.interceptors.response.use((response: any) => {
  return response
})

/**
 * https://blog.csdn.net/Alienvovo/article/details/87933111
 * jwt
 * 1、get 请求后端，后端返回 jwte token 数据，携带用户信息
 * 2、前端解码 token 数据，获取用户信息
 */

const url = ""
let headers = {
  "Content-type": "application/json"
}

// 1、
const res = await axios.get(url, { headers });


// 2、
import jwt, { JwtPayload } from 'jsonwebtoken';
const token = res.data
// get the decoded payload ignoring signature, no secretOrPrivateKey needed
// var decoded = jwt.decode(token);
// get the decoded payload and header
// var decoded = jwt.decode(token, {complete: true});

const data = jwt.decode(token, {complete: true}) as JwtPayload
data.sub
data.aud
data.iss

