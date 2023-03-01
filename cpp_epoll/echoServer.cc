#include <sys/epoll.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    /** 常规 监听
     * socket()
     * bind()
     * listen()
     */

    const char *ip = "127.0.0.1";
    const int port = 8999;

    sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = ::htons(port);
    ::inet_pton(AF_INET, ip, &addr.sin_addr);

    // socket SOCK_NONBLOCK 非堵塞读写
    int listenFd = ::socket(AF_INET, SOCK_STREAM | SOCK_NONBLOCK | SOCK_CLOEXEC, 0);

    int ret = ::bind(listenFd, (sockaddr *)&addr, sizeof addr);
    if (ret != 0)
        printf("端口被占用！");

    ret = ::listen(listenFd, 128);
    if (ret != 0)
        printf("listen 失败！");

    /** IO 多路复用
     * man epoll
     * epoll_create();
     * epoll_ctl();
     * while(true) {
     *   epoll_await();
     * }
     */
    // man 2 epoll_create
    // epoll_create() creates a new epoll(7) instance.  Since Linux 2.6.8, the size argument is ignored, but must be greater than zero;
    // epoll_create() 的入参 size 已经被废弃，随便传入一个大于 0 的数字就成
    int epollFd = ::epoll_create(1);

    /**
     * level-trigger 调用 epoll_wait 时，fd 只要处于可读或可写状态，就会通知用户。
     * edge-trigger 调用 epoll_wait 是，只有不可读变为可读，或不可写变为可写之时，才会通知用户。
     * ET 的话，是高速模式，读/写 fd 的时候，必须连续读/写完（直到返回 EAGAIN 错误）。否则如果未读/写完，系统会认为状态没有变化，就不会再重复通知，这样这个 fd 就死掉了
     * 所以 ET 要一次性搞定，最好是立即委托一个新线程去处理。
    */
    // 当 listenFd 上发生 EPOLLIN 事件时触发，默认水平触发
    epoll_event event;
    event.events = EPOLLIN;
    event.data.fd = listenFd;
    printf("event.events = [%d]", event.events);
    printf("asdasd");
    // epoll_ctl - control interface for an epoll file descriptor
    // EPOLL_CTL_ADD：Add fd to the interest list and associate the settings specified in event with the internal file linked to fd.
    epoll_ctl(epollFd, EPOLL_CTL_ADD, listenFd, &event);

    const int eventQueueSize = 1024;
    const int timeOut = -1; // -1 == infinite，一直卡着
    while (true)
    {
        epoll_event eventQueue[eventQueueSize] = {0};
        int nums = epoll_wait(epollFd, eventQueue, eventQueueSize, timeOut);

        // listenFd 可以 read() 了，nums 是有多少事件
        for (int i = 0; i < nums; i++)
        {
            // 监听的是 listenFd EPOLLIN 事件，有绑了两种 fd
            int curFd = eventQueue[i].data.fd;
            
            // listenFd 有可读的事件，也就是 client socket 接入了
            if (eventQueue[i].events == EPOLLIN && curFd == listenFd)
            {
                sockaddr_in clientAddr;
                clientAddr.sin_family = AF_INET;
                socklen_t clientLen = sizeof(clientAddr);

                // 创建该条连接的 fd
                int connFd = ::accept(listenFd, (sockaddr*)&clientAddr, &clientLen);

                epoll_event event;
                event.data.fd = connFd;
                event.events = EPOLLIN | EPOLLET; // EPOLLET 边缘触发，就是只触发一次就移除

                epoll_ctl(epollFd, EPOLL_CTL_ADD, connFd, &event);
            }

            // 不是 listenFd 说不是连接事件，那就是有数据要读
            // 对于 echoServer 而言，就是返回相同的数据就搞定
            if (eventQueue[i].events == EPOLLIN && curFd != listenFd) {
                char buf[1024] = {0};
                ::recv(curFd, buf, 1024, 0);
                ::send(curFd, buf, 1024, 0);
            }
            
            

        }
        

    }
    

    return 0;
}
