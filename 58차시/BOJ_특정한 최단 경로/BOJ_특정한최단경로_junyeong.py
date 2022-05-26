import sys
from heapq import heappush, heappop
input = sys.stdin.readline

def dijkstra(s, e):
    dist = [INF] * (N+1)
    dist[s] = 0
    queue = [[0, s]]

    while queue:
        k, u = heappop(queue)
        if k > dist[u]:
            continue
        for w, v in adj[u]:
            if dist[v] > dist[u]+w:
                dist[v] = dist[u]+w
                heappush(queue, [dist[v], v])

    return dist[e]


N, E = map(int, input().rstrip().split())
INF = 0xFFFFFF
adj = [[] for _ in range(N+1)]
for i in range(E):
    a, b, c = map(int, input().split())
    adj[a].append([c, b])
    adj[b].append([c, a])

u, v = map(int, input().split())

answer = min(dijkstra(1, u)+dijkstra(u, v)+dijkstra(v, N), dijkstra(1, v)+dijkstra(v, u)+dijkstra(u, N))
if answer >= INF:
    print('-1')
else:
    print(answer)