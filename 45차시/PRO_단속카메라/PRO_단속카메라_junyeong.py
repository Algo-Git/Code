# https://programmers.co.kr/learn/courses/30/parts/12244
def solution(routes):
    answer = 0
    end_point = -30001
    routes.sort(key=lambda x: x[1])
    print(routes)
    for start, end in routes:
        if start > end_point:
            answer += 1
            end_point = end

    return answer