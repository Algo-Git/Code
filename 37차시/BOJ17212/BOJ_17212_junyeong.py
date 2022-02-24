# https://www.acmicpc.net/problem/17212

coins = [1, 2, 5, 7]
pay = int(input())
INF = 0xFFFF
dp = [INF] * (pay + 1)
dp[0] = 0

for coin in coins:
    for j in range(coin, pay+1):
        if dp[j-coin] != 0xFFFF:
            dp[j] = min(dp[j], dp[j-coin]+1)
print(dp)