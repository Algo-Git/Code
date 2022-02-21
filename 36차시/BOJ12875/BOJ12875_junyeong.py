# https://www.acmicpc.net/problem/12875
N = int(input())
d = int(input())
visited = [0] * (N+1)
input_data = [input() for _ in range(N)]
INF = 0xFFFF
arr = [[INF] * N for _ in range(N)]
result = 0

for i in range(N):
    for j in range(i+1, N):
        if input_data[i][j] == 'Y':
            arr[i][j] = 1
            arr[j][i] = 1

for k in range(N):
    for i in range(N):
        for j in range(N):
            if i == j:
                arr[i][j] = 0
            else:
                arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j])

for i in range(N):
    for j in range(N):
        result = max(arr[i][j], result)

if result == 0xFFFF:
    print(-1)
else:
    print(result*d)