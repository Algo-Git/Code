def solution(N, number):
    c = len(str(number))
    if str(number).count(str(N)) == c:
        return c
    
    dp = [set() for _ in range(9)]
    dp[1].add(N)
    for cnt in range(2, 9):
        n = int(str(N)*cnt)
        dp[cnt].add(n)
        for i in range(1, cnt//2+1):
            for d in dp[i]:
                for p in dp[cnt-i]:
                    dp[cnt].add(d+p)
                    dp[cnt].add(d-p)
                    dp[cnt].add(p-d)
                    dp[cnt].add(d*p)
                    if p != 0:
                        dp[cnt].add(d//p)
                    if d != 0:
                        dp[cnt].add(p//d)
                        
            if number in dp[cnt]:
                return cnt
        
    return -1