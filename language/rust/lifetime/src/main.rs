// fn certainty(s: &str) -> &str {
//     s
// }

fn certainty<'a>(s: &'a str) -> &'a str {
    s
}

// fn uncertainty(s: &str, ss: &str) -> & str {
//     s
// }

// fn uncertainty<'a>(s: &'a str, ss: &str) -> &'a str {
//     s
// }

// fn uncertainty<'a>(s: &'a str, ss: &str) -> &'a str {
//     if true {
//         s
//     }  else {
//         ss
//     } 
// }

fn uncertainty<'a>(s: &'a str, ss: &'a str) -> &'a str {
    if true {
        s
    }  else {
        ss
    } 
}

fn main() {
    let s = &"zqh";
    println!("{}", certainty(s));
    println!("{}", uncertainty(s, s));
}