# https://www.acmicpc.net/problem/12865

N, K = map(int, input().split())
stuff = [(0, 0)]
for _ in range(N):
    W, V = map(int, input().split())
    stuff.append((W, V))
dp = [[0] * (K+1) for _ in range(N+1)]

for i in range(1, N+1):
    for j in range(1, K+1):
        W = stuff[i][0]
        V = stuff[i][1]
        if j < W:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j-W] + V, dp[i-1][j])
print(max(dp[-1]))


