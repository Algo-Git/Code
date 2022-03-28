def solution(m, n, puddles):
    dp = [[0 for _ in range(m)] for _ in range(n)]
    for i in range(m):
        if [i+1, 1] in puddles:
            break
        dp[0][i] = 1
    for i in range(n):
        if [1, i+1] in puddles:
            break
        dp[i][0] = 1
        
    for i in range(1, n):
        for j in range(1, m):
            if [j+1, i+1] in puddles:
                continue
            dp[i][j] = dp[i-1][j]+dp[i][j-1]
                
    return dp[n-1][m-1]%1_000_000_007