# https://www.acmicpc.net/problem/23796
from pprint import pprint
def left():
    for i in range(N):
        j = 0
        k = 0
        prev = -1
        while j < N:
            if arr[i][j]:
                if prev == -1:
                    prev = arr[i][j]
                elif prev == arr[i][j]:
                    result[i][k] = prev * 2
                    prev = -1
                    k += 1
                else:
                    result[i][k] = prev
                    prev = arr[i][j]
                    k += 1
            j += 1
        if j == N:
            if prev != -1:
                result[i][k] = prev
    return result


def right():
    for i in range(N):
        j = N-1
        k = N-1
        prev = -1
        while j > -1:
            if arr[i][j]:
                if prev == -1:
                    prev = arr[i][j]
                elif prev == arr[i][j]:
                    result[i][k] = prev * 2
                    prev = -1
                    k -= 1
                else:
                    result[i][k] = prev
                    prev = arr[i][j]
                    k -= 1
            j -= 1
        if j == -1:
            if prev != -1:
                result[i][k] = prev
    return result

def up():
    for j in range(N):
        i = 0
        k = 0
        prev = -1
        while i < N:
            if arr[i][j]:
                if prev == -1:
                    prev = arr[i][j]
                elif prev == arr[i][j]:
                    result[k][j] = prev * 2
                    prev = -1
                    k += 1
                else:
                    result[k][j] = prev
                    prev = arr[i][j]
                    k += 1
            i += 1
        if i == N:
            if prev != -1:
                result[k][j] = prev
    return result


def down():
    for j in range(N):
        i = N-1
        k = N-1
        prev = -1
        while i > -1:
            if arr[i][j]:
                if prev == -1:
                    prev = arr[i][j]
                elif prev == arr[i][j]:
                    result[k][j] = prev * 2
                    prev = -1
                    k -= 1
                else:
                    result[k][j] = prev
                    prev = arr[i][j]
                    k -= 1
            i -= 1
        if i == -1:
            if prev != -1:
                result[k][j] = prev
    return result


N = 8
arr = [list(map(int, input().split())) for _ in range(N)]
direction = input()

result = [[0] * N for _ in range(N)]
if direction == 'L':
    ans = left()
elif direction == 'U':
    ans = up()
elif direction == 'D':
    ans = down()
elif direction == 'R':
    ans = right()

for arr in ans:
    print(*arr)
