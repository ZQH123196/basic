import time
import sys
from collections import deque

# # base
# grid = [
# "+++++++++++++++",
# "+             +",
# "+             +",
# "+             +",
# "+     e       +",
# "+             +",
# "+             +",
# "+             +",
# "+ s           +",
# "+++++++++++++++",
# ]

# test1
# grid = [
#     "+++++++++++++++",
#     "+             +",
#     "+             +",
#     "+             +",
#     "+   + e +     +",
#     "+    ++++     +",
#     "+ s           +",
#     "+             +",
#     "+             +",
#     "+++++++++++++++",
# ]

grid = [
    "OOOOOXOOXXOXOOOOOOXO",
    "OOOOOOOXOOOOXOOOXXOO",
    "OOOOOOOXOOOOOOOOOOXO",
    "OOXXMOOOOOOOXOOOOOOX",
    "OO*XXOOOOOOOOXOOOOOO",
    "OOOOOXOOXXOOOOOOOOOO",
    "OXOXOOOOOOOOOOOOOOOX",
    "OOOOOOXXOOOOOOOOXOXO",
    "OOOXOOOOOOOOOOOOOOXO",
    "OOOXXOOOOOOXOOOOOOXO",
    "OOOOOOOOOOOOOOXOOOXO",
    "OXOOOOOOXOOOOOOOOOOO",
    "OOOOOOXOXOOOOXOOXOOO",
    "OOOXOOOOOOOOOOOOXOOO",
    "OOXOOOOOOOOOOOOOOOOO",
    "OOOOOOOOOOXOXOOOXOOO",
    "OXOOOOXOOXXOOXXOOOOO",
    "OOOOXOOOOOOOOOOOOOOO",
    "OXOOOOOOXOOOOOOOOOOO",
    "XOOOXOXOOOOOXOOOXOOO",
]


def search(col, row):
    frontier.append((col, row))
    solution[col, row] = col, row

    while len(frontier) > 0:
        col, row = frontier.popleft()

        cell = (col - 1, row - 1)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 左上
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col - 1, row)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 上
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col - 1, row + 1)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 右上
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col, row + 1)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:   # 右
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col + 1, row + 1)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 右下
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col + 1, row)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 下
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col + 1, row - 1)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 左下
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)

        cell = (col, row - 1)
        if cell in path and grid[cell[0]][cell[1]] != wall and cell not in visited:  # 左
            solution[cell] = col, row
            frontier.append(cell)
            visited.add(cell)


def backRoute(x, y):
    print(x, y)
    while (x, y) != (startCol, startRow):
        x, y = solution[x, y]
        print(x, y)


global startCol, startRow, endCol, endRow, wall, answerPath


wall = 'X'
startCol = 0
startRow = 0
endCol = 0
endRow = 0
path = []  # 用于越界判定


for countCol, col in enumerate(grid):
    for countRow, row in enumerate(col):
        path.append((countCol, countRow))
        if row == '*': # 起点
            startCol = countCol
            startRow = countRow
        if row == 'M': # 终点
            endCol = countCol
            endRow = countRow


# python 双引号，单引号都是一样的，不会区分是 char 还是 String，统统都是 String

visited = set()
frontier = deque() # 双端队列
solution = {}  # 关系矩阵
pass  # pass 只是占位符号，除了分隔跟好看，没有什么用处
print("start:", startCol, startRow)
print("end:", endCol, endRow)
print("---------------以下是从终点到起点的回溯----------------")
search(startCol, startRow) # BFS 并记录父子关系进
backRoute(endCol, endRow) # 回溯函数
