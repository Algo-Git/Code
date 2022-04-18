#BOJ 17951. 흩날리는 시험지 속에서 내 평점이 느껴진거야

N, K = map(int, input().split())
X = list(map(int, input().split()))
if K == 1:
    print(sum(X))
elif K == N:
    print(min(X))
else:
    l = 0
    r = sum(X)
    while l < r:
        mid = (l + r) // 2
        cnt = 1
        summ = 0
        for x in X:
            summ += x
            if summ >= mid:
                cnt += 1
                summ = 0
        if cnt > K:
            l = mid + 1
        else:
            r = mid
    print(l - 1)
