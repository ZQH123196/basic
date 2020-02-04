# # # snack : <===, < header, === body
# # # f -> food
# # # 请确认控制台使用的是等宽字体

# import sys

# surroundChar = '-'

# def buildMap(mapSize: tuple, ceilSize: int, gap: int = 0):
#     print('build func!')
#     row = mapSize[0]
#     col = mapSize[1]
#     _str = ""
#     # + 2 是因为需要绘制边框，上下两条，左右两条
#     for _row in range(row + 2):
#         if _row > 0 and _row < (row+2-1):
#             _str = _str + surroundChar + (' ' * col) + surroundChar
#         else: # 边框绘制
#             _str = _str + (surroundChar * (col + 2))
#         _str = _str + '\n'
#     pass
#     print(_str)

# def refreshMap():
#     pass



# if __name__ == '__main__':
#     row = 15
#     col = 15
#     mapSize = (row, col)
    
#     ceilSize = 10

#     buildMap(mapSize, ceilSize)
#     print('snake main file is running!')

# import time
# for i in range(10):
#     time.sleep(0.2) 
#     print ("Loading... ".format(i)+str(i), end="\r")


# import time
# for i in range(10):
#     string = 'loading... '*1000 + str(i) + '%'
#     print(string, end='')    # 不换行
#     print('\b' * len(string), end='', flush=True)    # 删除前面打印的字符
#     time.sleep(0.2)


# from threading import Timer
# timeout = 5
# t = Timer(timeout, print, ['Sorry, times up'])
# t.start()
# prompt = "You have %d seconds to choose the correct answer...\n" % timeout
# answer = input(prompt)
# t.cancel()

import threading

def input_func( context ):
    context[ 'data' ] = input( 'input:' )

context = { 'data' : 'default' }
t = threading.Thread( target = input_func, args = ( context , ), daemon=True)
t.start( )
t._stop()
t.join( 3 ) #等待 5 秒
print( context ) 

# import os
# i=os.system("cls")