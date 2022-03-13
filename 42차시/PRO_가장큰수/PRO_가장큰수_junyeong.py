# https://programmers.co.kr/learn/courses/30/parts/12198

def solution(numbers):
    answer = ''
    str_numbers = list(map(str, numbers))
    str_numbers.sort(key=lambda x: x * 3, reverse=True)

    return str(int(''.join(str_numbers)))