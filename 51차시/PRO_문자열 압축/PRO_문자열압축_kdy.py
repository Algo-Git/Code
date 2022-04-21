def solution(s):
    l = len(s)
    answer = l

    for i in range(1, (l//2)+1):
        cnt = 1
        pre = s[:i]
        sen = 0
        for j in range(i*2, l+1, i):
            if pre == s[j-i:j]:
                cnt+=1
            else:
                if cnt > 1:
                    sen+=len(str(cnt))
                sen+=len(pre)
                cnt = 1
                pre = s[j-i:j]
        else:
            if cnt>1:
                sen+=len(str(cnt))
            sen+=len(s[j-i:])

        if sen < answer:
            answer = sen

    return answer