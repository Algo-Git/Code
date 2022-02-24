# https://www.acmicpc.net/problem/16234

# 연합의 인구수 // 연합을 이루고 있는 칸의 개수
# 첫째줄 N, L, R
# N줄까지 인구수
# 모든 정점을 방문하고, 넓이, 총합을 계산.
from collections import deque
def BFS(r, c):
    # 1. BFS로 다 방문하면서, 넓이, 총합을 계산하자
    # 2. path를 저장해서 값을 저장해주자
    # 3. path에 값을 저장해주자
    queue = deque()
    queue.append((r, c))
    area = 1
    people = map[r][c]
    path = [(r, c)]
    while queue:
        cr, cc = queue.popleft()
        for idx in range(4):
            nr, nc = cr+dr[idx], cc+dc[idx]
            if nr < 0 or nr >= N or nc < 0 or nc >= N or visited[nr][nc]:
                continue
            # 차이가 L명 이상 OR R명 이하 => 국경 오픈
            if L <= abs(map[nr][nc]- map[cr][cc]) <= R:
                visited[nr][nc] = True
                queue.append((nr, nc))
                path.append((nr, nc))
                area += 1
                people += map[nr][nc]
    avg = people // area
    for i, j in path:
        map[i][j] = avg
    if len(path) > 1:
        return True
    else:
        return False


N, L, R = map(int, input().split())
map = [list(map(int, input().split())) for _ in range(N)]
visited = [[0] * N for _ in range(N)]
day = 0
dr = [0, 0, 1, -1]
dc = [1, -1, 0, 0]

while True:
    flag = False
    visited = [[False] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                visited[i][j] = True
                ismove = BFS(i, j)
                if ismove:
                    flag = True
    if not flag:
        break
    day += 1
print(day)