from heapq import heappop, heappush
import sys

input = sys.stdin.readline

N, E = map(int, input().split())
g = [[] for _ in range(N + 1)]
for i in range(E):
    a, b, c = map(int, input().split())
    g[a].append((b, c))
    g[b].append((a, c))

v1, v2 = map(int, input().split())
INF = int(1e9)


def dijk(start):
    d = [INF for _ in range(N + 1)]
    d[start] = 0
    q = [(0, start)]
    v = []

    while q:
        dis, cur = heappop(q)
        if cur in v:
            continue
        for nxt, dist in g[cur]:
            if dis + dist < d[nxt] and nxt not in v:
                d[nxt] = dis + dist
                heappush(q, (dis + dist, nxt))
        v.append(cur)

    return d


from1 = dijk(1)
fromv1 = dijk(v1)
fromv2 = dijk(v2)

ans = min(from1[v1] + fromv1[v2] + fromv2[N], from1[v2] + fromv2[v1] + fromv1[N])

if ans >= INF:
    print(-1)
else:
    print(ans)