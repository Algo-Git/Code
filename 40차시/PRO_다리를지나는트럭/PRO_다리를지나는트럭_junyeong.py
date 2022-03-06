# https://programmers.co.kr/learn/courses/30/parts/12081
from collections import deque
def solution(bridge_length, weight, truck_weights):
    answer = 0
    N = bridge_length
    in_bridge = [0] * N
    truck = deque(truck_weights)
    idx = 0

    while truck:
        answer += 1
        if sum(in_bridge) + truck[0] <= weight:
            in_bridge[(idx % N) - 1] = truck.popleft()

        if in_bridge[idx % N]:
            in_bridge[idx % N] = 0
        idx += 1
    answer += N
    # 들어가서 나올때까지 N초 걸림
    return answer