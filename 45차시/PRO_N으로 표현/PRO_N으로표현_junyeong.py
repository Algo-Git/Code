def solution(N, number):
    dp = [{}]
    for i in range(1, 9):
        numbers = {int(str(N) * i)}

        for j in range(1, i):
            for one in dp[j]:
                for two in dp[i - j]:
                    numbers.add(one + two)
                    numbers.add(one - two)
                    numbers.add(one * two)
                    if two:
                        numbers.add(one // two)
        if number in numbers:
            return i
        dp.append(numbers)
    return -1