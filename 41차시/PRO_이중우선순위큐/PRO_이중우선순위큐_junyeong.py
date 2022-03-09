import heapq


def solution(operations):
    answer = []
    for oper in operations:
        s, n = oper.split()

        if s == 'I':
            heapq.heappush(answer, int(n))
        else:
            if not answer:  # 비어있으면 패스
                continue

            if n == '-1':  # 최솟값 삭제
                heapq.heappop(answer)
            else:  # 최댓값 삭제
                answer.remove(max(answer))
                heapq.heapify(answer)

    if not answer:
        return [0, 0]
    else:
        return [max(answer), min(answer)]