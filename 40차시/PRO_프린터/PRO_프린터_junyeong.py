# https://programmers.co.kr/learn/courses/30/parts/12081
from collections import deque
def solution(priorities, location):
    answer = 0
    N = len(priorities)
    idx = [x for x in range(N)]
    queue = deque(list(zip(priorities, idx)))
    rank = 1
    while queue:
        cur_val, cur_idx = queue.popleft()
        for q, i in queue:
            if q > cur_val:
                queue.append((cur_val, cur_idx))
                break
        else:
            if cur_idx == location:
                return rank
            rank += 1