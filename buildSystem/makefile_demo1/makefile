objs = main.o a_func.o b_func.o c_func.o

main: $(objs)
	cc -o main \
	main.o a_func.o b_func.o c_func.o
	# or $(objs)

# 这些 .o 也都依赖这个 base.h 头文件，修改将会重新编译全部
$(objs): base.h

main.o: main.c
	cc -c main.c

a_func.o: a_func.c a_func.h
	cc -c a_func.c a_func.h

b_func.o: b_func.c b_func.h
	cc -c b_func.c b_func.h

# make 自动推导
c_func.o: c_func.c c_func.h

.PHONY : clean
clean:
	rm -rf main *.o *.h.gch

.PHONY : cleanByVar
cleanByVar:
	rm -rf main $(objs) *.h.gch