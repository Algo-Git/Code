N, K = map(int, input().split())
prob = list(map(int, input().split()))

left = 0
right = sum(prob) + 1

while left+1 < right:
    mid = (right+left) // 2
    cur_k = 0
    temp = 0
    for idx in range(N):
        temp += prob[idx]
        if temp >= mid:
            temp = 0
            cur_k += 1

    if cur_k >= K:
        left = mid
    else:
        right = mid
print(left)