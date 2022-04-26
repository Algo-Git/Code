# https://www.acmicpc.net/problem/14501
N = int(input())
plan = []
for _ in range(N):
    time, pay = map(int, input().split())
    plan.append([time, pay])

DP = [0] * (N+1)
for i in range(N):
    time, pay = plan[i]
    DP[i] = max(DP[i], DP[i-1])
    if i+time < N+1:
        DP[i+time] = max(DP[i+time], DP[i] + pay)
print(max(DP))