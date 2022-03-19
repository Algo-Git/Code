def solution(number, k):
    answer = ''
    original = k
    number = list(number)
    stack = []
    N = len(number)
    for i in range(N):
        while stack and k:
            if number[i] > stack[-1]:
                stack.pop()
                k -= 1
            else:
                break
        stack.append(number[i])

    if k != 0:
        answer = ''.join(stack[:-k])
    else:
        answer = ''.join(stack)
    return answer