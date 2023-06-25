
import { Axios, AxiosRequestConfig } from 'axios';



const axiosConfg: AxiosRequestConfig = {
    baseURL: "http://localhost:8080"
};

const axiosInstance = new Axios(axiosConfg);


export default axiosInstance;