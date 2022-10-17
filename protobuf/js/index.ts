import fs from "fs"


interface IUser {
    id: number,
    name?: string,
}

const users: IUser[] = []


users.push({
    id: 1001,
    name: "张三"
})


users.push({
    id: 1002,
    name: "李四"
})

users.push({
    id: 1003,
    name: "王五"
})


fs.writeFileSync("jsonData.json", JSON.stringify(users));

