const fs = require("fs")
const path = require("path")

// es module protobuf js 生成器不支持
const Schema = require("./protobuf/user_pb.js")


const userOne = new Schema.User();
userOne.setId(1)
userOne.setName("张三")

const userTwo = new Schema.User();
userTwo.setId(2)
userTwo.setName("李四")


const userTree = new Schema.User();
userTree.setId(3)
userTree.setName("王五")



const userList = new Schema.Users();

userList.addUsers(userOne)
userList.addUsers(userTwo)
userList.addUsers(userTree)


const bytes = userList.serializeBinary()
fs.writeFileSync(path.resolve(__dirname, "./basicData/protoData"), bytes)


// 注意此处要使用类方法，不能使用实例方法，没有智能提示显得有点古怪
const deserializeData = Schema.Users.deserializeBinary(bytes)
console.log(deserializeData);


