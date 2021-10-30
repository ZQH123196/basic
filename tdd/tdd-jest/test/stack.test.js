/*
1. 先创建 todolist，使用 it.todo 来等待通过这些测试
2. 编写实际测试代码
3. 编写工作代码以此来通过测试代码
*/

class Stack {
	constructor() {
		this.top = -1;
		this.items = {}
	}

	get peek() {
		return this.items[this.top]
	}

	push(item) {
		this.top += 1
		this.items[this.top] = item;
	}

	pop() {
		let _popVal = this.items[this.top]
		delete this.items[this.top]
		this.top -= 1;
		return _popVal;
	}

}


// 使用 it.todo 来等待完成这些测试
describe("My Stack", () => {
	it.todo('it created empty');
	it.todo('can push to the app');
	it.todo('can pop off');
})

describe("My Stack", () => {
	let stack;

	beforeEach(() => {
		stack = new Stack();
	});

	it('it created empty', () => {
		expect(stack.top).toBe(-1);
		expect(stack.items).toEqual({})
	});

	it('can push to the app', () => {
		stack.push('a')
		expect(stack.top).toBe(0)
		expect(stack.peek).toBe('a')

		stack.push('b')
		expect(stack.top).toBe(1)
		expect(stack.peek).toBe('b')
	});


	it('can pop off', () => {
		stack.push('a')
		stack.push('b')
		let _val = stack.pop()
		expect(stack.top).toBe(0)
		expect(_val).toBe('b')

		_val = stack.pop()
		expect(_val).toBe('a')
	});

})