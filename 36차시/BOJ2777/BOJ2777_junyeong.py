# https://www.acmicpc.net/problem/2777
T = int(input())
for _ in range(T):
    N = int(input())
    cnt = 0
    if N == 1:
        print(1)
    else:
        while True:
            if N == 1:
                print(cnt)
                break
            for i in range(9, 1, -1):
                if not N % i:
                    N //= i
                    cnt += 1
                    break
            else:
                print(-1)
                break