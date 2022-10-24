
console.log("hello webpack entry file.");


class TmpClass {
	constructor(name) {
		this.name = name
	}

	get name() {
		return this.name
	}

	set name(newVal) {
		this.name = newVal
	}
}

let tmpClass = new TmpClass("init.")
console.log(tmpClass.name);

tmpClass.name = 123;
console.log(tmpClass.name);
