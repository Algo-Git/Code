import heapq
import sys

def union(u, v):
    u = find_set(u)
    v = find_set(v)
    if u < v:
        p[v] = u
    else:
        p[u] = v

def find_set(u):
    if u == p[u]:
        return u
    else:
        return find_set(p[u])

N, M = map(int, sys.stdin.readline().rstrip().split())
google = []
p = [x for x in range(N)]
result = 0
mst = []
for _ in range(M):
    A, B, C = map(int, sys.stdin.readline().rstrip().split())
    heapq.heappush(google, (C, A-1, B-1))

while google:
    w, s, e = heapq.heappop(google)
    if find_set(s) == find_set(e):
        continue
    union(s, e)
    mst.append(w)
    if len(mst) == N-1:
        break
print(sum(mst[:-1]))