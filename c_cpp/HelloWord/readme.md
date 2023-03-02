
编译
gcc helloword.c
gcc helloword.c -o a.out

gcc 不加 -o 默认生成 a.out 二进制文件
./a.out

输出 Hello World!



gcc -g 添加调试信息
gcc -g helloword.c -o a.out

进入调试
gdb a.out


break b，先打断点
(gdb) b 1
然后 run r
(gdb) r

list l, 从第一行 list
(gdb) l 1