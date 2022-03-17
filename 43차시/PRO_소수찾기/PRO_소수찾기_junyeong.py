from math import sqrt


def solution(numbers):
    answer = 0
    numbers = list(numbers)
    N = len(numbers)
    numberset = set()
    used = [False] * N

    def solve(val):
        if val and int(val) > 1:
            numberset.add(int(val))
        if len(val) >= N:
            return

        for i in range(N):
            if not used[i]:
                used[i] = True
                solve(val + numbers[i])
                used[i] = False

    solve('')
    numbers = list(numberset)

    for num in numbers:
        for i in range(2, int(sqrt(num)) + 1):
            if not num % i:
                break
        else:
            answer += 1

    return answer