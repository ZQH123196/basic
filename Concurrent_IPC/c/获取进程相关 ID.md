

## 函数原型和所需头文件

```c
#include <sys/types.h>
#include <unistd.h>

pid_t getpid(void); // 获取调用该函数进程的进程 ID。
pid_t getppid(void); // 获取调用该函数进程的父进程 ID，pp -> parent process。
uid_t getuid(void); // 获取调用该函数进程的用户 ID，uid -> user ID，那个用户运行就是那个 ID，查看 /etc/passwd 第三列就是用户名所对应的 UID。
gid_t getgid(void); // 获取调用该函数进程所属用户组的 ID，gid -> group ID,查看 /etc/passwd 第四列就是用户名所对应的 GID。
```

返回值：返回各种 ID 值，不会调用失败，永远成功。



