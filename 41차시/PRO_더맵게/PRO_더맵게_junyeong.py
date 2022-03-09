import heapq


def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)

    while len(scoville) >= 2 and scoville[0] < K:
        fir = heapq.heappop(scoville)
        sec = heapq.heappop(scoville)
        heapq.heappush(scoville, fir + 2 * sec)
        answer += 1

    if scoville[0] < K:
        answer = -1
    return answer