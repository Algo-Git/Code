def solution(brown, yellow):
    answer = []
    N = brown // 2 + 2
    for i in range(1, N // 2 + 1):
        x, y = i, N - i
        if (x - 2) * (y - 2) == yellow:
            return [y, x]

    return answer