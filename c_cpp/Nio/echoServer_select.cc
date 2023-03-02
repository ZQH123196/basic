#include <sys/epoll.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>

/** man select
 * select 可以理解为一个 set 结构。
 * Four macros are provided to manipulate the sets.
 *
 * select(int nfds, fd_set *readfds, fd_set *writefds,
        fd_set *exceptfds, struct timeval *timeout);
        nfds should be set to the highest-numbered file descriptor in any of the three sets, plus 1.  The indicated file descriptors in each set are checked, up
         to this limit (but see BUGS).
        select 堵塞直到有监听的 fd 事件发生，nfds 应该被设定为三个集合中的最大描述符的值+1，
        nfds 被用来循环检查这些文件描述符，所以要传入当前三个描述符（readfds、writefds、exceptfds）中最大的监听数 +1；
        1.timeout=NULL（阻塞：select将一直被阻塞，直到某个文件描述符上发生了事件）
        2.timeout所指向的结构设为非零时间（等待固定时间：如果在指定的时间段里有事件发生或者时间耗尽，函数均返回）
        3.timeout所指向的结构，时间设为0（非阻塞：仅检测描述符集合的状态，然后立即返回，并不等待外部事件的发生）
        
        
 * FD_ZERO() clears a set. 将指定的文件描述符集清空，在对文件描述符集合进行设置前，必须对其进行初始化，如果不清空，由于在系统分配内存空间后，通常并不作清空处理，所以结果是不可知的。
 * FD_SET() and FD_CLR() add and remove a given  file  descriptor  from  a  set.
 * FD_ISSET() tests to see if a file descriptor is part of the set; this is useful after select() returns.
 *
 *
 */

int main(int argc, char const *argv[])
{

    /**
     * socket()
     * bind()
     * listen()
     */
    const char *serverIp = "127.0.0.1";
    const int serverPort = 9000;
    sockaddr_in serverAddr;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(9000);
    inet_pton(AF_INET, serverIp, &serverAddr.sin_addr);

    int listenFd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    bind(listenFd, (sockaddr *)&serverAddr, sizeof(serverAddr));
    listen(listenFd, FD_SETSIZE);




    fd_set readfds, writefds, exceptfds;


    while (true)
    {
        select()
    }

    return 0;
}
