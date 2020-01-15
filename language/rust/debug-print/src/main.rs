// https://rustwiki.org/zh-CN//rust-by-example/hello/print/print_display/testcase_list.html
use std::fmt;

// new type mode
struct List(Vec<i32>);

impl fmt::Display for List {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        let vec = &self.0;

        write!(f, "[")?;
        for (count, v) in vec.iter().enumerate() {
            if count != 0 { write!(f, ",")?; }
            write!(f, "{}:{}", count, v)?;
        }
        write!(f, "]")
    }
}

fn main() {
    let v = List(vec![1, 2, 3]);
    println!("{}", v);
}

/*
Formatting traits
nothing ⇒ Display
? ⇒ Debug
x? ⇒ Debug with lower-case hexadecimal integers
X? ⇒ Debug with upper-case hexadecimal integers
o ⇒ Octal
x ⇒ LowerHex
X ⇒ UpperHex
p ⇒ Pointer
b ⇒ Binary
e ⇒ LowerExp
E ⇒ UpperExp

example
{} ⇒ Display
{:?} ⇒ Debug
{:x?} ⇒ Debug with lower-case hexadecimal integers
*/