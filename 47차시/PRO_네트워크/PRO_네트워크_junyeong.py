def solution(n, computers):
    answer = 0
    for i in range(n):
        for j in range(n):
            if not computers[i][j]:
                continue
            stack = [j]
            while stack:
                v = stack[-1]
                for idx in range(n):
                    if computers[v][idx]:
                        computers[v][idx] = 0
                        computers[idx][v] = 0
                        stack.append(idx)
                        break
                else:
                    stack.pop()
            answer += 1 

    return answer