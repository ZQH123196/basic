import intro from "./assert/txt/intro.txt"

console.log(`intro = ${intro}`)


const bodyDom = document.getElementsByTagName("body")[0]

const divDom = document.createElement("div")
divDom.innerText = intro

bodyDom.append(divDom)
