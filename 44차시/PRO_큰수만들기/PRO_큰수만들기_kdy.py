def solution(number, k):
    answer = ''
    n = len(number)
    leng = n-k

    if number.count(number[0]) == n:
        return number[0]*leng

    for i, nn in enumerate(number):
        if leng == 0:
            break
        if k == 0:
            answer += nn
            continue
        if nn == '9':
            answer+=nn
            leng-=1
            continue

        tmp = nn
        for j in range(i+1, n-leng+1):
            if number[j] == '9' or tmp<number[j]:
                k-=1
                break
        else:
            answer+=nn
            leng-=1
        
    return answer