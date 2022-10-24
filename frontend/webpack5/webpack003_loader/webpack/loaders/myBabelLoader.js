const babelSchema = require("./schema/babelOptionsSchema.json")
const babel = require('@babel/core');
const util = require('util');
const { validate } = require('schema-utils')

const transform = util.promisify(babel.transform)

module.exports = function (content, map, meta) {

	// 获取配置信息
	const options = this.getOptions(babelSchema)

	// validate(babelSchema, options, {
	// 	name: "My babel"
	// })

	// 创建异步
	const asyncCallback = this.async()

	// babel 转码
	transform(content, options)
		.then(({ code, map }) => asyncCallback(null, code, map, meta))
		.catch(e => asyncCallback(e))
}