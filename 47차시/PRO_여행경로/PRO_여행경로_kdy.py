def solution(tickets):
    tickets.sort(reverse = True)
    tck = {}
    for f, t in tickets:
        tck[f] = tck.get(f, []) + [t]
    
    stack = ['ICN']
    answer = []
    while stack:
        f = stack[-1]
        if f in tck and tck[f] != []:
            stack.append(tck[f].pop())
        else:
            answer.append(stack.pop())

    return answer[::-1]