
// content 其实就是完整的入口解析后的文本内容，也就是 js 代码
module.exports = function (content, map, meta) {
	console.log("------ loader2.js start.");
	// console.log("content = ", content);
	console.log("------ loader2.js finshed.");

	return content
}

module.exports.pitch = function () {
	console.log("------ loader2.js pitch start.");
	console.log("------ loader2.js pitch finshed.");
}
