# https://www.acmicpc.net/problem/8911
def move(type, idx, x, y):
    global l, r, u, d
    if type == 'F':
        # 앞으로 가는 경우
        x, y = x + dirt[idx][0], y + dirt[idx][1]
        # +쪽 이동
        if idx == 0 or idx == 3:
            u = max(u, y)
            r = max(r, x)
        # -쪽 이동
        else:
            d = min(d, y)
            l = min(l, x)
    else:
        # 뒤로가는 경우
        x, y = x - dirt[idx][0], y - dirt[idx][1]
        # 아랫쪽, 왼쪽 이동
        if idx == 0 or idx == 3:
            d = min(d, y)
            l = min(l, x)
        # 윗쪽, 오른쪽 이동
        else:
            r = max(r, x)
            u = max(u, y)
    return x, y

T = int(input())
for _ in range(T):
    order = input()
    dirt = [[0, 1], [-1, 0], [0, -1], [1, 0]]
    idx = 0
    l, r = 0, 0
    u, d = 0, 0
    x, y = 0, 0
    for o in order:
        if o == 'F' or o == 'B':
            x, y = move(o, idx, x, y)
        elif o == 'L':
            idx = (idx+1) % 4
        elif o == 'R':
            idx = (idx-1) % 4
    answer = abs((l-r)*(u-d))
    print(answer)