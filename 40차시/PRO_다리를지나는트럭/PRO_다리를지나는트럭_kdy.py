from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 0 #모든 트럭이 다리를 건너는데 걸리는 시간
    on = deque(0 for _ in range(bridge_length)) #다리 위 상황
    wsum = 0 #다리 위 트럭의 무게 합
    idx = 0 #현재 트럭
    while idx < len(truck_weights): #모든 트럭이 다리에 올라갈때까지 반복
        answer+=1 #시간 +1
        wsum-=on.pop() #다 건넌 트럭의 무게 빼기
        if wsum+truck_weights[idx] > weight: #다리위에 트럭이 올라갈 수 없는 경우
            on.appendleft(0) #트럭들 1칸씩 옮기기
        else: #올라갈 수 있는 경우
            on.appendleft(truck_weights[idx]) #트럭 다리 위로 올리기
            wsum+=truck_weights[idx] #다리 위 트럭의 무게 추가
            idx+=1 #다음 트럭 살펴보기
    #모든 트럭이 다리를 건너는 시간 = 마지막 트럭이 다리 위에 올라간 시간 + 다리 길이
    return answer+bridge_length