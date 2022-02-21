# https://www.acmicpc.net/problem/1072
X, Y = map(int, input().split())
Z = Y*100//X
ans = 0

if Z > 98:
    print(-1)
else:
    left = 1
    right = X
    while right >= left:
        half = (right + left) // 2
        if (Y + half) * 100 // (X + half) > Z:
            right = half-1
            ans = half
        else:
            left = half+1
    print(ans)