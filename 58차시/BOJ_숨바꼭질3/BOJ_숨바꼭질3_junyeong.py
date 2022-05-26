from collections import deque


N, K = map(int, input().split())
INF = 100001
queue = deque([N])
dist = [-1] * INF
dist[N] = 0
while queue:
    cur_x = queue.popleft()
    for next_x in (cur_x<<1, cur_x-1, cur_x+1):
        if 0<=next_x<INF and dist[next_x] == -1:
            if next_x == cur_x<<1:
                queue.appendleft(cur_x<<1)
                dist[cur_x<<1] = dist[cur_x]
            else:
                queue.append(next_x)
                dist[next_x] = dist[cur_x]+1

print(dist[K])
