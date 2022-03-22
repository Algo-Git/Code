def solution(routes):
    answer = 0
    routes = sorted(routes, key=lambda x: x[1])
    pre = -30000
    for inn, out in routes:
        if pre < inn:
            answer += 1
            pre = out
    return answer