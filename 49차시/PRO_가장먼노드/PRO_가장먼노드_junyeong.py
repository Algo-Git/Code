from collections import deque
def solution(n, edge):
    answer = 0
    adj = [[] for _ in range(n + 1)]
    queue = deque([1])
    for i, j in edge:
        adj[i].append(j)
        adj[j].append(i)

    distance = [0] * (n + 1)
    distance[1] = 1
    while queue:
        cur = queue.popleft()

        for next in adj[cur]:
            if not distance[next]:
                distance[next] = distance[cur] + 1
                queue.append(next)
    max_dis = max(distance)
    for val in distance:
        if val == max_dis:
            answer += 1
    return answer