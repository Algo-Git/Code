from collections import deque as dq, defaultdict

def solution(a, edges):    
    if sum(a) != 0:
        return -1
    
    answer = 0
    tree = defaultdict(set)
    for u, v in edges:
        tree[u].add(v)
        tree[v].add(u)
        
    q = dq('')
    for k, v in tree.items():
        if len(v) == 1:
            q.append(k)
            
    while q:
        cur = q.popleft()
        for nxt in tree[cur]:
            a[nxt]+=a[cur]
            answer+=abs(a[cur])
            tree[nxt].remove(cur)
            if len(tree[nxt]) == 1:
                q.append(nxt)
                
    return answer