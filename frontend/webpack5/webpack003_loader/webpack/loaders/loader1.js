
// content 其实就是完整的入口解析后的文本内容，也就是 js 代码

// 同步
// module.exports = function (content, map, meta) {
// 	console.log("------ loader1.js start.");
// 	// console.log("content = ", content);
// 	console.log("------ loader1.js finshed.");
// 	return content
// }

// 同步，无需 return
// module.exports = function (content, map, meta) {
// 	console.log("------ loader1.js start.");
// 	// console.log("content = ", content);
// 	console.log("------ loader1.js finshed.");
// 	this.callback(null, content, map, meta)
// }

// 异步,异步会一直等待到 ayncCallback 调用，才会继续走下一个函数
module.exports = function (content, map, meta) {
	console.log("------ loader1.js start.");
	// console.log("content = ", content);
	const options = this.getOptions()
	console.log("options = ", options);


	console.log("------ loader1.js finshed.");
	const asyncCallback = this.async()
	setTimeout(() => {
		asyncCallback(null, content, map, meta);
	}, 1000);
}

module.exports.pitch = function () {
	console.log("------ loader1.js pitch start.");
	console.log("------ loader1.js pitch finshed.");
}

