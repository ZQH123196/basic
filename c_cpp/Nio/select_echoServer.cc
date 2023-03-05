#include <iostream>
#include <sys/epoll.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
using namespace std;

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

    // 注意，这里只有 readFds 用到，其他的只是表明有这个东西
    fd_set readFds, writeFds, exceptFds;
    fd_set setArray;

    FD_ZERO(&setArray);

    // 注册感兴趣的 file description
    FD_SET(listenFd, &setArray);
    
    // 自己要保存所有监听的 fd
    int allFd[FD_SETSIZE] = {-1};
    int allFdIndex = 0;
    allFd[allFdIndex++] = listenFd;

    // nfds，三个 fds 最大的值，用以 kenel 加速判断，否则 kenenl 要从 0 判断到 FD_SETSIZE，但 FD_SETSIZE 的值可能会很巨大
    // 难以理解可能是因为历史遗留问题
    int maxFd = -1;
    int readyFdNumber = 0;
    // while (true)
    // {
    //     // 注意，这里是因为 select 的调用会改变入参！所以每次都要重新赋值！
    //     // 具体是 select 函数调用完成后，向其传递的 fd_set 变量中将发生变化。原来为 1 的所有位均变为 0，但发生变化的文件描述符对应为除外。
    //     // 因为调用这个，需要把 三种 fd 传递进去，而其返回的其实有两种，一种是响应的数量。另一种就是具体是那种 fd 的那个位置发生了变化，而第二种的返回体现在对入参的修改上
    //     // 也就是，每次调用都必须把三个 fd_set（用户态）都传过去，拷贝到内核态进行处理，之后将更新结果再同步到用户态的 fd_set。调用完成后，还需要遍历 fd_set，才能知道具体哪些套接字发生了改变。
    //     // 所以，三个 fd_set 即是入参，也是返回值。因为这个太早期了，所以才会有这种操作。一般的现代函数要么直接返回复杂结构体或者规定函数是纯函数。
    //     // 可以理解 select 的行为为，kernel 拿三个 fds 的入参，监听这里面
    //     readFds = setArray;
    //     readyFdNumber = select(maxFd + 1, &readFds, &writeFds, &exceptFds, nullptr);
    //     if (readyFdNumber <= 0)
    //     {
    //         printf("系统调用 select 出错！");
    //     }
    //     if (readyFdNumber == 0)
    //     {
    //         printf("系统调用 select 超时！");
    //     }
    //     if (readyFdNumber > 0)
    //     {
    //         printf("处理 fd！");
    //         /**
    //          * FD_ISSET() 判断是否是已经监听的 fd
    //          * connFd = accept() 接收到接入请求，建立 socket 连接
    //          * FD_SET(connFd) 设置对该 socket 连接的监听
    //          */
    //         if (FD_ISSET(listenFd, &readFds))
    //         {
    //             printf("有连接进入！");
    //             sockaddr_in clientAddr;
    //             socklen_t sockLen = sizeof(clientAddr);
    //             int connFd = accept(listenFd, (sockaddr *)&clientAddr, &sockLen);

    //             // 监听 connFd 的读
    //             FD_SET(connFd, &readFds);
    //             allFd[allFdIndex++] = connFd;

    //             char clientIp[INET_ADDRSTRLEN];
    //             inet_ntop(AF_INET, &clientAddr.sin_addr, clientIp, INET_ADDRSTRLEN);

    //             printf("客户端[%s]请求接入！", clientIp);
    //             printf("已有[%d]个连接接入！", allFdIndex);
    //         }

    //     }
    // }

    return 0;
}
