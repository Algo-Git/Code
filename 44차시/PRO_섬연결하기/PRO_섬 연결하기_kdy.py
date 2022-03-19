import heapq as hq

def solution(n, costs):
    answer = 0
    g = [[] for _ in range(n)]
    for a, b, c in costs:
        g[a].append((b, c))
        g[b].append((a, c))

    v = []
    nxt = []
    hq.heappush(nxt, (0, 0))
    while True:
        cost, to = hq.heappop(nxt)
        if to in v:
            continue
        v.append(to)
        answer+=cost
        
        if len(v) == n:
            break
        
        for t, c in g[to]:
            if t in v:
                continue
            hq.heappush(nxt, (c, t))

    return answer