import socket

server_host = '127.0.0.1'
server_port = 10086
server_address = (server_host, server_port)

client_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM)

client_socket.connect(server_address)
client_socket.send("hello. I'm {}".format(__file__).encode('utf8'))

# loop: input data
while True:
    data = input()
    client_socket.send(data.encode('utf-8'))
    if data == "q":
        break

client_socket.close()
print('client has closed!')