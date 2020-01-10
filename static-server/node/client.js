const net = require('net');

const address = {
  host: '127.0.0.1',
  port: 10086
};

const socket_option = {
  allowHalfOpen: false,
  readable: true,
  writable: true
};

let client_socket = new net.Socket(socket_option);

client_socket.connect(address)

client_socket.on('connecting', () => {
  console.log(`connect to ${address.host}:${address.port}`);
});

client_socket.on('error', () => {
  console.error('createConnection error! client exit~');
});


process.stdin.setEncoding('utf-8');
process.stdin.pipe(client_socket);