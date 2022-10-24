const fs = require("fs")
const util = require("util")
const path = require("path")
const webpack = require('webpack');


const { RawSource } = webpack.sources;
const readFile = util.promisify(fs.readFile)

// https://webpack.js.org/contribute/writing-a-plugin/
class plugin4addTxt {
	apply(compiler) {
		// debugger
		// compilation 只有在 tap 中可以取到，是 webpack 在运行时调用该函数传入的
		compiler.hook.tapAsync("plugin4addTxt", async (compilation, cb) => {
			debugger
			const contentAddFromPlugins = "contentAddFromPlugins"

			// 往要输出资源中，添加一个 contentAddFromPlugins.txt
			compilation.assets['contentAddFromPlugins.txt'] = {
				// 文件大小
				size() {
					return contentAddFromPlugins.length;
				},
				// 文件内容
				source() {
					return contentAddFromPlugins;
				}
			}

			const tmpTxt = await readFile(path.resolve(__dirname, "assets/tmp.txt"))
			// https://webpack.js.org/api/compilation-object/#emitasset
			// 使用内置 api 添加
			compilation.emitasset('tmp.txt', new RawSource(tmpTxt));
			cb();
		})


	}
}