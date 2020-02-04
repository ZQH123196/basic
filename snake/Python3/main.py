# # snack : <===, < header, === body
# # f -> food
# # 请确认控制台使用的是等宽字体

import sys
import time

surroundChar = '-'

def printCanvas(canvas):
    for _str in canvas:
        print(_str)
    

def buildMap(mapSize: tuple, ceilSize: int, gap: int = 0):
    print('build func!')
    row = mapSize[0]
    col = mapSize[1]
    canvas = []
    # + 2 是因为需要绘制边框，上下两条，左右两条
    for _row in range(row + 2):
        if _row > 0 and _row < (row+2-1):
            canvas.append(surroundChar + (' ' * col) + surroundChar)
        else: # 边框绘制
           canvas.append(surroundChar * (col + 2))
    pass
    return canvas


def refreshMap():
    pass

def snakeRun(canvas):
    header = ">"
    body = "="*3
    snake = body + header
    # 由于蛇的身子非常有规律，蛇走的时候，其身子必定会顺着头走过的地方一一走过
    # 或者说除头部外某个身体的下一个移动位置必定是前一个身段所处的位置，而头部的下一个位置为玩家控制
    # snake 设计一个与头身一一对应的队列，当头移动时，数组最前面进一个头部运动的新坐标，头部所对应的位置就自动更新，且后续所有的身子都会更新
    canvas[1] = canvas[1][:1] + snake + canvas[1][len(snake)+1:]

    # for i in range(0, 5):
    #     print(" "*i + snake, end='\r', flush=True)
    #     time.sleep(0.1)

def controle():
    pass

if __name__ == '__main__':
    row = 15
    col = 15
    mapSize = (row, col)
    
    ceilSize = 10

    canvas = buildMap(mapSize, ceilSize)
    # printCanvas(canvas)
    snakeRun(canvas)
    printCanvas(canvas)
    
    print(canvas[1][:])
    # print('snake main file is running!')






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

# import threading

# def input_func( context ):
#     context[ 'data' ] = input( 'input:' )

# context = { 'data' : 'default' }
# t = threading.Thread( target = input_func, args = ( context , ), daemon=True)
# t.start( )
# t._stop()
# t.join( 3 ) #等待 5 秒
# print( context ) 

# import os
# i=os.system("cls")