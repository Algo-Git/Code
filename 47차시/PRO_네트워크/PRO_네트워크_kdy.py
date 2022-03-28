def solution(n, computers):
    answer = 0
    v = [False for _ in range(n)]
    
    for i in range(n):
        if v[i]:
            continue
            
        answer+=1
        stack = [i]
        while stack:
            c = stack.pop()
            if v[c]:
                continue
            v[c] = True
            for j, co in enumerate(computers[c]):
                if co == 1 and not v[j]:
                    stack.append(j)
    
    return answer