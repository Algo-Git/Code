import sys
S = sys.stdin.readline().rstrip()
rev_S = S[::-1]
N = len(S)

if S == rev_S:
    print(N)
else:
    for i in range(N):
        if S[i:] == rev_S[:N-i]:
            answer = i
            break
    print(answer + N)