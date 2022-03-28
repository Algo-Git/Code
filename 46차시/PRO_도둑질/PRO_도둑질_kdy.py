def solution(money):
    l = len(money)
    if l < 4:
        return max(money)

    dp = [0 for _ in range(l)] #첫번째집O
    dp2 = [0 for _ in range(l)] #첫번째집X
    dp[0] = money[0]
    dp[1] = max([money[0], money[1]])
    dp2[1] = money[1]

    for i in range(2, l):
        dp[i] = max([dp[i-2]+money[i], dp[i-1]])
        dp2[i] = max([dp2[i-2]+money[i], dp2[i-1]])

    return max([dp[-2], dp2[-1]])