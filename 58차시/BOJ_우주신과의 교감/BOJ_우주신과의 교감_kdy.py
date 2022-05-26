def find(a):
    if par[a] != a:
        par[a] = find(par[a])
    return par[a]


def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        par[b] = a
    else:
        par[a] = b


N, M = map(int, input().split())
pos = [[]]
par = list(range(N + 1))
ans = 0

for i in range(N):
    pos.append(list(map(int, input().split())))

for i in range(M):
    p, q = map(int, input().split())
    union(p, q)

arr = []
for i in range(1, N):
    for j in range(i + 1, N + 1):
        if find(i) == find(j):
            continue
        dis = ((pos[i][0] - pos[j][0]) ** 2 + (pos[i][1] - pos[j][1]) ** 2) ** 0.5
        arr.append((dis, i, j))

arr.sort()
for dist, st, end in arr:
    if find(st) != find(end):
        ans += dist
        union(st, end)

print(format(round(ans, 2), '.2f'))