from collections import deque

T = int(input())

for t in range(T):
    p = input()
    n = int(input())
    arr = input().replace('[', '').replace(']', '')
    if n > 0:
        arr = list(map(int, arr.split(',')))
    else:
        arr = []
    arr = deque(arr)
    flag = True
    for cmd in p:
        if cmd == 'R':
            flag = not flag
        else:
            if len(arr) == 0:
                print('error')
                break
            if flag:
                arr.popleft()
            else:
                arr.pop()
    else:
        if flag:
            print(str(list(arr)).replace(' ', ''))
        else:
            print(str(list(reversed(arr))).replace(' ', ''))