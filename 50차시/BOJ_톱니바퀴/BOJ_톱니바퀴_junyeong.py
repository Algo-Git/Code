# 극이 다르면 반대방향으로 회전
# 극이 같으면 회전 X
from collections import deque


def BFS(idx, direct):
    visited[idx] = 1
    # 오른쪽 방문
    if idx < 3 and not visited[idx+1]:
        if gear[idx][2] != gear[idx+1][6]:
            BFS(idx+1, -direct)
    # 왼쪽 방문
    if idx > 0 and not visited[idx-1]:
        if gear[idx][6] != gear[idx-1][2]:
            BFS(idx-1, -direct)

    # 회전 처리
    if direct == 1:
        gear[idx].appendleft(gear[idx].pop())
    else:
        gear[idx].append(gear[idx].popleft())

gear = [deque(input()) for _ in range(4)]
K = int(input())
answer = 0
for _ in range(K):
    idx, direct = map(int, input().split())
    visited = [0] * 4
    BFS(idx-1, direct)
for i in range(4):
    if gear[i][0] == '1':
        answer += 1 << i
print(answer)