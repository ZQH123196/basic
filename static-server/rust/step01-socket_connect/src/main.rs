use std::net::TcpListener;
// 令人困惑
fn main() {
    let listener = TcpListener::bind("127.0.0.1:10088").unwrap();
    
    for stream in listener.incoming() {
        let stream = stream.unwrap();
        println!("Connection established!");
    }
}
