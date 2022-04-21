def solution(n):
    answer = ''
    rule = ['4', '1', '2']

    while n:
        n, nam = divmod(n, 3)
        answer += rule[nam]
        if not nam % 3:
            n -= 1

    return answer[::-1]