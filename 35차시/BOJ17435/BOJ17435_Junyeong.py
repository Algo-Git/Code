# https://www.acmicpc.net/problem/17435
M = int(input())
fx = [0] + list(map(int, input().split()))
dp = [[fx[i]] for i in range(M+1)]
log = 18    # 1 < m < 2^18 < 500,000 < 2^19

for j in range(1, log + 1):
    for i in range(1, M+1):
        # 1, 2, 4, 8회... 2배수로 뛸 때 도착하는 위치 저장DP
        dp[i].append(dp[dp[i][j-1]][j-1])
Q = int(input())
for _ in range(Q):
    n, x = map(int, input().split())
    for b in range(log, -1, -1):
        if n >= 1 << b:
            n -= 1 << b
            x = dp[x][b]
    print(x)