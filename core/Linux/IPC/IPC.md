
IPC(Interprocess Communication)


## Linux 提供的 IPC
	
> Linux 的父亲是 Unix，所以 Linux 的进程间通信，其实都是继承于 Unix。


一共有以下几种：   
    
1、信号
    信号是非精确通信，所谓精确通信，就是能告诉你详细信息，而信号这种非精确通信，只能通知某件事情发生了，但是无法传递详细信息。


2）本章的进程间通信
    （a）管道
            · 无名管道
            · 有名管道

            OS在进程之间建立一个“管道”，通过这个管道来实现进程间数据的交换。
                
    （b）system V IPC
            · 消息队列：通过消息队列来通信
            · 共享内存：通过共享内存来通信
            · 信号量：借助通信来实现资源的保护（一种消费机制）


3）域套接字
