#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int main(void) {

    pid_t  ret = -1;

    // 由于在 fork 前就将缓冲区刷出去了，所以子进程不会打印。
    printf("only parent print\n");

    // 行缓冲，加换行会 flush。
    printf("befor fork ");

    ret = fork();

    if (ret > 0) {
        printf("parent PID = %d\n", getpid());
        printf("parent ret(child pid) = %d\n", ret);
    } else {
        printf("child PID = %d\n", getpid());
        printf("child ret = %d\n", ret);
        printf("child ppid(parent id) = %d\n", getppid());
    }

    printf("after fork\n\n");

    while(1);

    exit(0);
}