def solution(answers):
    answer = []
    a = [0, 0, 0]
    s1 = [1, 2, 3, 4, 5]
    s2 = [2, 1, 2, 3, 2, 4, 2, 5]
    s3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    for i, ans in enumerate(answers):
        if ans == s1[i%5]:
            a[0] += 1
        if ans == s2[i%8]:
            a[1] += 1
        if ans == s3[i%10]:
            a[2] += 1
            
    m = max(a)
    
    for i in range(3):
        if a[i] == m:
            answer.append(i+1)
            
    return answer