const { AsyncParallelHook, AsyncSeriesHook, SyncHook, SyncBailHook } = require("tapable");




class Info {
	constructor() {
		this.hooks = {
			sync: new SyncHook(["user"]),
			syncBail: new SyncBailHook(["user"]),
			asyncParallel: new AsyncParallelHook(['name', 'age']),
			asyncSeries: new AsyncSeriesHook(['name', 'age'])
		}
	}
}

// const first_infoInstance = new Info()
// first_infoInstance.hooks.sync.tap("a-info", (user) => {
// 	console.log("a-info", user);
// 	return user;
// })
// first_infoInstance.hooks.sync.tap("b-info", (user) => {
// 	console.log("b-info", user);
// })
// first_infoInstance.hooks.sync.call("user1")

// console.log('------------');

// // syncBail 顺序同参数调用，有返回值就结束，终止剩下的调用
// const s_infoInstance = new Info()
// s_infoInstance.hooks.syncBail.tap("a-info", (user) => {
// 	console.log("a-info", user);
// 	return user;
// })
// s_infoInstance.hooks.syncBail.tap("b-info", (user) => {
// 	console.log("b-info", user);
// })
// s_infoInstance.hooks.syncBail.call("user1")


// console.log('------------');

// tapAsync 有个 cb 参数，cb 不调用就不会结束。
const t_infoInstance = new Info()
t_infoInstance.hooks.asyncParallel.tapAsync("a-info", (name, age, cb) => {
	setTimeout(() => {
		console.log("a-info", name, age);
		cb()
	}, 3000);
})
t_infoInstance.hooks.asyncParallel.tapPromise("b-info", (name, age) => {
	return new Promise((resolve) => {
		setTimeout(() => {
			console.log("b-info", name, age);
			console.log('------------');
			resolve();	
		}, 1000);
	})

})
t_infoInstance.hooks.asyncParallel.callAsync("user1", 8999, () => {
	console.log("tapAsync finished!");
})

console.log('------------');

// AsyncParallelHook 与 AsyncSeriesHook 的区别就在于，
// AsyncParallelHook 是并行关系，而 AsyncSeriesHook 如果任务一阻塞该队列的剩下都会堵塞，不过会让出 cpu 资源。
// AsyncSeriesHook 用于前后有关联关系的任务。
// const f_infoInstance = new Info()
// f_infoInstance.hooks.asyncSeries.tapAsync("a-info", (name, age, cb) => {
// 	setTimeout(() => {
// 		console.log("a-info", name, age);
// 		cb()
// 	}, 3000);
// })
// f_infoInstance.hooks.asyncSeries.tapPromise("b-info", (name, age) => {
// 	return new Promise((resolve) => {
// 		setTimeout(() => {
// 			console.log("b-info", name, age);
// 			resolve();	
// 		}, 1000);
// 	})

// })
// // callback 只能调用一次，调用了就算完成这系列异步任务了，剩下的也不再执行，允许在执行系列中灵活的中断退出
// f_infoInstance.hooks.asyncSeries.callAsync("user1", 8999, function () {
// 	console.log("all mission finished!");
// })
