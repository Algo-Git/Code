def solution(n, costs):
    answer = 0
    INF = 0xFFFFFF
    mst = [0] * n
    weigh = [INF] * n
    weigh[0] = 0
    adj = [[INF] * n for _ in range(n)]
    # mst에 없고, 인접한 모든 루트를 방문해야함.
    for i, j, w in costs:
        adj[i][j] = w
        adj[j][i] = w

    for _ in range(n):
        min_val = INF
        min_idx = -1
        for j in range(n):
            # j에서 방문할 수 있는 노드이면서, mst에 속하지 않는 j
            if weigh[j] < min_val and not mst[j]:
                min_idx = j
                min_val = weigh[j]
        mst[min_idx] = 1

        for i in range(n):
            if weigh[i] > adj[min_idx][i] and not mst[i]:
                weigh[i] = adj[min_idx][i]

    answer = sum(weigh)
    return answer