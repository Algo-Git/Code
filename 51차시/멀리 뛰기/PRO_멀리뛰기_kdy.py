def solution(n):
    dp = [1, 2] + [0 for _ in range(n-2)]
    for i in range(2, n):
        dp[i] = (dp[i-1]+dp[i-2])%1234567
    return dp[n-1]