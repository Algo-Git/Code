def solution(n):
    answer = 0
    if n < 2:
        return 1

    DP = [0] * n
    DP[0] = 1
    DP[1] = 2
    for i in range(2, n):
        DP[i] = (DP[i - 1] + DP[i - 2]) % 1234567
    else:
        answer = DP[-1]

    return answer