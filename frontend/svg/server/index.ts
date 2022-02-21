import http from "http"
import fs from "fs/promises"
import path from "path";


const prjPath = path.resolve("./")
const assertPath = path.resolve(prjPath + "/src/assert/")
const server = http.createServer(async (req, res) => {

    if (req.url.includes("favicon.ico")) {
        return;
    }

    console.log("req.url", req.url);
    console.log("req.headers", req.headers);

    const targetServerFilePath = path.resolve(assertPath + req.url);
    console.log("targetServerFilePath:", targetServerFilePath);

    
    const fileData = await fs.readFile(targetServerFilePath)
    res.setHeader("Content-Type", "image/svg+xml")
    res.setHeader("Vary", "Accept-Encoding")
    if (req.url.includes("-gz")) {
        res.setHeader("Content-Encoding", "gzip")
    }
    res.end(fileData);

}).listen(8999)







