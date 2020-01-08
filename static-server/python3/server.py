import socket

host = '127.0.0.1'
port = 10086
address = (host, port)

# ipv4 TCP 
server_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM)
server_socket.bind(address)
server_socket.listen()
print("server listen on {host}:{port}".format(host=host, port=port))

# accept client_socket
client_connection, client_address = server_socket.accept()

# loop: recv data from client
while True:
    data = client_connection.recv(1024).decode('utf-8')
    if data == "q":
        print("recv:{cmd}, server will be closed".format(cmd=data))
        break
    elif data == '':
        pass
    else:
        print(data)

client_connection.close()
server_socket.close()
print("server has closed!")