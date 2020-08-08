* [Message Queues](https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_message_queues.htm)



ftok 函数，先不去了解它的作用来先说说为什么要用它，共享内存，消息队列，信号量它们三个都是找一个中间介质，来进行通信的，这种介质多的是。就是怎么区分出来，就像唯一一个身份证来区分人一样。你随便来一个就行，就是因为这。只要唯一就行，就想起来了文件的设备编号和节点，它是唯一的，但是直接用它来作识别好像不太好，不过可以用它来产生一个号

下面这张图很好的诠释了 MQ 的工作方式：
![](messageQueues/assets/images/multiple_message_queue.jpg)