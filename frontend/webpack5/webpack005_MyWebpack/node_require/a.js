// https://www.ruanyifeng.com/blog/2015/05/require.html





console.log('-'*12);
console.log(`我是 ${__filename}，require 其实就是调用代码.`);
console.log('module.id: ', module.id);
console.log('module.exports: ', module.exports);
console.log('module.parent: ', module.parent);
console.log('module.filename: ', module.filename);
// console.log('module.loaded: ', module.loaded);
// console.log('module.children: ', module.children);
// console.log('module.paths: ', module.paths);