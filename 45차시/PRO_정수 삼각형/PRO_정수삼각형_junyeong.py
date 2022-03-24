# https://programmers.co.kr/learn/courses/30/parts/12263
def solution(triangle):
    answer = 0
    N = len(triangle)
    dp = [[0] * n for n in range(1, N + 1)]
    # [7]
    # [3, 8]
    # [8, 1, 0]
    # [2, 7, 4, 4]
    # [4, 5, 2, 6, 5]
    for i in range(N - 1):
        M = len(triangle[i])
        for j in range(M):
            # 바로 아래 값
            dp[i + 1][j] = max(triangle[i][j] + triangle[i + 1][j], dp[i + 1][j])
            # 아래 오른쪽 값
            dp[i + 1][j + 1] = max(triangle[i][j] + triangle[i + 1][j + 1], dp[i + 1][j + 1])

        for k in range(M + 1):
            triangle[i + 1][k] = dp[i + 1][k]
    answer = max(triangle[-1])
    return answer