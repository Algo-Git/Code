import sys
import heapq


def find(x):
    if x == p[x]:
        return x
    else:
        return find(p[x])


def union(x, y):
    a = find(x)
    b = find(y)
    if a != b:
        p[b] = a


N, M = map(int, input().split())
kind = list(input().split())
p = [i for i in range(N)]
arr = []
# mst = []
answer = 0
cnt = 0

for _ in range(M):
    u, v, d = map(int, sys.stdin.readline().rstrip().split())
    if kind[u-1] == kind[v-1]:
        continue
    heapq.heappush(arr, (d, u-1, v-1))

while arr:
    cost, i, j = heapq.heappop(arr)
    if find(i) == find(j):
        continue
    else:
        # mst.append((i, j, cost))
        union(i, j)
        answer += cost
        cnt += 1
        if cnt == N-1:
            break

if cnt == N-1:
    print(answer)
else:
    print('-1')