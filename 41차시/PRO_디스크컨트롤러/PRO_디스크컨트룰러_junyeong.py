import heapq
def solution(jobs):
    answer = 0
    N = len(jobs)
    start = -1
    now = 0  # 지금 idx 위치
    heap = []
    cnt = 0  # 몇개의 작업을 진행했는지
    while cnt < N:
        for job in jobs:
            if start < job[0] <= now:
                heapq.heappush(heap, (job[1], job[0]))
        if heap:
            cur_t, cur_s = heapq.heappop(heap)
            start = now
            now += cur_t
            answer += now - cur_s
            cnt += 1
        else:
            now += 1
    return int(answer / N)