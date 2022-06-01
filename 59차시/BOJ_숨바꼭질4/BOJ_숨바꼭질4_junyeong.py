# https://www.acmicpc.net/problem/13913
from collections import deque
N, K = map(int, input().split())
MAX = 100001
move = [-1] * MAX
move[N] = N
answer = []
q = deque([N])

while q:
    cur = q.popleft()
    if cur == K:
        while cur != N:
            answer.append(cur)
            cur = move[cur]
        answer.append(N)
        break
    for m in (cur-1, cur+1, cur << 1):
        if 0 <= m < MAX and move[m] == -1:
            move[m] = cur
            q.append(m)

print(len(answer)-1)
print(*answer[::-1])