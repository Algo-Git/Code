def solution(m, n, puddles):
    answer = 0
    dp = [[0] * (m + 1) for _ in range(n + 1)]
    pudd = set()
    for r, c in puddles:
        pudd.add((c, r))
    dp[0][1] = 1

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if (i, j) in pudd:
                continue
            dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007
    return dp[n][m] % 1000000007