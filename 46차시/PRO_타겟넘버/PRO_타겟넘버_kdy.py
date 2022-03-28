def solution(numbers, target):
    answer = 0
    
    q = [[numbers[0], 0], [-1*numbers[0], 0]]
    
    while q:
        n, idx = q.pop()
        idx +=1
        if idx>=len(numbers):
            if n == target:
                answer += 1
        else:
            q.append([n + numbers[idx], idx])
            q.append([n - numbers[idx], idx])

    return answer