#![allow(unused)]
#![feature(fn_traits)]
fn main() {
    // 所有的函数都默认实现了 Fn、FnMut、FnOnce 这三个 trait。 
    // 可以将不能运行的代码取消注释，可以看出当前 implement 的是什么 trait


    // 不捕获环境变量，自动实现 Fn，复制语义自动实现 Copy
    // let mut c = ||{};
    // c.call(());
    // c.call_mut(());
    // c.call_once(());
    // c.call_mut(());

    // 捕获复制语义变量，不修改环境变量，自动实现 Fn，复制语义自动实现 Copy，注意 mut
    // let s = "zqh";
    // let c = ||{s};
    // c.call(());
    // // c.call_mut(());
    // c.call_once(());
    // c.call(());


    // 捕获复制语义变量，修改环境变量，自动实现 FnMut
    // 在 call_once 之后会传入 self 导致所有权转移，外部环境失去自由变量的所有权，所以后面不能在使用 call_mut，因为所有权已经由外部移入到闭包内部。
    // let mut s = "zqh";
    // let mut c = ||{ s = "eor" };
    // // c.call(());
    // c.call_mut(());
    // c.call_once(());
    // // c.call_mut(()); // error


    // 捕获移动语义变量，不修改环境变量，自动实现 FnOnce
    // let s = "zqh".to_string();
    // let c = ||{println!("{:?}", s);};
    // //c.call(());
    // //c.call_mut(());
    // c.call_once(());
    

    // 捕获移动语义变量，不修改环境变量，使用了 move 关键字自动实现 Fn
    // let s = "zqh".to_string();
    // let c = move ||{println!("{:?}", s);};
    // c.call(());
    // //c.call_mut(());  // c 没有定义为 mut，这导致 &mut self 是不能被分配的
    // c.call_once(());
    // //c.call(());  // 运行 once 之后所有权已经被移交

    // 捕获移动语义变量，修改环境变量，自动实现 FnMut，注意加 mut 才能实现 FnMut，因为 trait 定义的是 &self(self: &Self)
    // let mut s = "zqh".to_string();
    // let mut c = move ||{ s = "eor".to_string() };
    // //c.call(());
    // c.call_mut(());
    // c.call_once(());


    // 捕获移动语义变量，修改环境变量，自动实现 FnOnce
    // let mut s = "zqh".to_string();
    // let c = move ||{ s = "eor".to_string() };
    // //c.call(());
    // c.call_mut(());
    // c.call_once(());
}
