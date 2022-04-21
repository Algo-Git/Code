def solution(n):
    answer = ''
    while n > 0:
        n, m = divmod(n, 3)
        if m == 0:
            n -= 1
        answer = '412'[m] + answer
            
    return answer