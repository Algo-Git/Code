# https://programmers.co.kr/learn/courses/30/parts/12081
def solution(progresses, speeds):
    answer = []
    day = 1
    idx = 0
    N = len(progresses)
    while N > idx:
        cnt = 0

        while N > idx and progresses[idx] + (day * speeds[idx]) >= 100:
            idx += 1
            cnt += 1

        if cnt:
            answer.append(cnt)

        day += 1
    return answer