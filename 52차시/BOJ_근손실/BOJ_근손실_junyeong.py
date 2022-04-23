# https://www.acmicpc.net/problem/18429

def solve(weight, idx):
    global answer
    weight -= K
    if weight < 500:
        return

    if idx >= N:
        answer += 1
        return

    for i in range(N):
        if not used[i]:
            used[i] = 1
            solve(weight+kit[i], idx+1)
            used[i] = 0


N, K = map(int, input().split())
kit = list(map(int, input().split()))

answer = 0
used = [0] * N

for i in range(N):
    used[i] = 1
    solve(500+kit[i], 1)
    used[i] = 0

print(answer)