def bfs(depth, value, numbers, target):
    global answer
    if depth >= len(numbers):
        if value == target:
            answer += 1
        return
    bfs(depth + 1, value + numbers[depth], numbers, target)
    bfs(depth + 1, value - numbers[depth], numbers, target)


def solution(numbers, target):
    global answer
    answer = 0
    N = len(numbers)

    bfs(0, 0, numbers, target)
    return answer