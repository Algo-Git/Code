v, e = map(int, input().split())
edges = []
INF = 0xFFFFFF
distance = [INF] * (v + 1)
for _ in range(e):
    s, e, c = map(int, input().split())
    edges.append((s, e, c))


def bellman_ford(start):
    distance[start] = 0
    for i in range(v):
        for j in range(e):
            cur_node, next_node, edge_cost = edges[j]
            if distance[cur_node] != INF and distance[next_node] > distance[cur_node] + edge_cost:
                distance[next_node] = distance[cur_node] + edge_cost
                if i == v - 1:
                    return True

    return False

answer = bellman_ford(1)
if answer:
    print('-1')
else:
    for i in range(2, v + 1):
        if distance[i] == INF:
            print('-1')
        else:
            print(distance[i])
