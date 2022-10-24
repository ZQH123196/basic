const path = require('path')



module.exports = {
	entry: './src/index.js',
	module: {
		rules: [
			{
				test: /\.js$/,
				use: [
					// {
					// 	// loader: path.resolve(__dirname, "./webpack/loaders/loader1")
					// 	loader: 'loader1',
					// 	options: {
					// 		name: "loader1 option!"
					// 	}
					// },
					// {
					// 	loader: 'loader2',
					// },
					// {
					// 	loader: 'loader3',
					// },
					{
						loader: "myBabelLoader.js",
						options: {
							presets: ['@babel/preset-env']
						}
					}
				]
			}
		]
	},
	// 配置 loader 解析路径，可以不在使用绝对路径，其会自动查找文件夹中的 loader
	resolveLoader: {
		modules: [
			"node_modules",
			path.resolve(__dirname, "webpack/loaders/")
		]
	}
}