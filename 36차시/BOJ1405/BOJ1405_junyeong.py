# https://www.acmicpc.net/problem/1405
def DFS(sr, sc, dis, per):
    global result
    if dis > N:
        result += per
        return
    if per == 0:
        return

    for idx in range(4):
        cr, cc = sr + dr[idx], sc + dc[idx]
        if not visited[cr][cc]:
            visited[cr][cc] = 1
            DFS(cr, cc, dis+1, per*poten[idx])
            visited[cr][cc] = 0

dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]
N, es, we, so, no = list(map(int, input().split()))
poten = [es/100, we/100, so/100, no/100]
path = []
visited = [[0] * 30 for _ in range(30)]
result = 0
DFS(14, 14, 0, 1.0)
print(result)
