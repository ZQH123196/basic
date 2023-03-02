/** man select
 * 这个例子非常好，极其简洁，是取了 stdio in 作为触发，只要运行打字然后回车，就能看到 select 监听的情况
 * 
*/
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>

int main(void)
{
    fd_set rfds;
    struct timeval tv;
    int retval;

    // 先将 rfds 置空，这个就是一个容量为 1024 的结构
    FD_ZERO(&rfds);

    // 给 rfds 加入 fd 的监听，这里可以更多的认为是添加监听的种类,这里加入的 fd 是 0，也就是控制台的标准输入
    /* Watch stdin (fd 0) to see when it has input. */
    FD_SET(0, &rfds);

    /* Wait up to five seconds. */
    tv.tv_sec = 5;
    tv.tv_usec = 0;

    // 在此堵塞！超时时间是 tv.tv_sec，返回响应的 fd，如果超时就返回 0 个
    // 只监听一个 fd。
    retval = select(1, &rfds, NULL, NULL, &tv);
    printf("retval = [%d]", retval);

    /* Don't rely on the value of tv now! */
    if (retval == -1)
        perror("select() error!");
    else if (retval)
        printf("Data is available now.\n");
    /* FD_ISSET(0, &rfds) will be true. */
    else
        printf("No data within five seconds.\n");

    exit(EXIT_SUCCESS);
}