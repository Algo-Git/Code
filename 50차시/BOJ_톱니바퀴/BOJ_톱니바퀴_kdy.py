#BOJ 14891. 톱니바퀴

arr = [list(map(int, list(input()))) for _ in range(4)]
K = int(input())
top = [0 for _ in range(4)]
for k in range(K):
    num, dir = map(int, input().split())
    d = [0 for _ in range(4)]
    d[num - 1] = dir
    for i in range(num, 4):
        if arr[i][(top[i] + 6) % 8] != arr[i - 1][(top[i - 1] + 2) % 8]:
            d[i] = d[i - 1] * (-1)
        else:
            break
    for i in range(num - 1, 0, -1):
        if arr[i - 1][(top[i - 1] + 2) % 8] != arr[i][(top[i] + 6) % 8]:
            d[i - 1] = d[i] * (-1)
        else:
            break
    for i, dd in enumerate(d):
        if dd == 0:
            continue
        top[i] = (top[i] - dd + 8) % 8
print(arr[0][top[0]] + arr[1][top[1]] * 2 + arr[2][top[2]] * 4 + arr[3][top[3]] * 8)