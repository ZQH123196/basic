import './style.css'
import { setupCounter } from './counter'
import axios from 'axios'
import jwt from "jsonwebtoken"

document.querySelector<HTMLDivElement>('#app')!.innerHTML = `
  <div>

  </div>
`

setupCounter(document.querySelector<HTMLButtonElement>('#counter')!)


// jwt.sign()


const url = ""
let data = {
  
}
axios.post(url, data);

