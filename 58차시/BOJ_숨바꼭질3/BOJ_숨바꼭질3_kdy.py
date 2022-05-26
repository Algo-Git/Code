from collections import deque

N, K = map(int, input().split())
dp = [200000 for _ in range(100000 + 1)]
q = deque([N])
dp[N] = 0

while q:
    pos = q.popleft()
    if pos == K:
        print(dp[K])
        break

    if 0 <= pos - 1 and dp[pos - 1] > dp[pos] + 1:
        dp[pos - 1] = dp[pos] + 1
        q.append(pos - 1)
    if pos + 1 <= 100000 and dp[pos + 1] > dp[pos] + 1:
        dp[pos + 1] = dp[pos] + 1
        q.append(pos + 1)
    if 0 < pos * 2 <= 100000 and dp[pos * 2] > dp[pos]:
        dp[pos * 2] = dp[pos]
        q.append(pos * 2)