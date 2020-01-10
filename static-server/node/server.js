const net = require('net');

const socket_option = {
  allowHalfOpen: false,
  readable: true,
  writable: true
};

const address = {
  host: '127.0.0.1',
  port: 10086
};

let server_socket = new net.Socket(socket_option);

let server = net.createServer(server_socket);

// 所有的 listen() 方法可以传入一个 backlog 参数来指定待连接队列的最大长度。 实际长度将通过 OS 的 sysctl 设置
// 例如 linux 里的 tcp_max_syn_backlog 和 somaxconn。 这个参数的默认值是511 (不是512）
server.listen(address, 1024);

server.on('listening', () => {
  console.log(`listen on ${address.host}:${address.port}`);
});

server.on('connection', function (client_socket) {
  client_socket.on('data', function (data) {
    process.stdout.write(`${data}`);
  });

  // 懒得在客户端做关闭，直接抓错误吧
  client_socket.on('error', () => {
    console.log('client closed~')
  });
  
});

server.on('error', function () {
  console.error('error');
});


