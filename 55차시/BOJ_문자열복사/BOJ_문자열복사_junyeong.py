pattern = input()
text = input()

# 1. 패턴을 다 쪼개버려서 셋에 넣는다
# 2. 큰거부터 찾아준다.
text_set = set()
N = len(pattern)
M = len(text)
for i in range(N):
    for j in range(N-i):
        text_set.add(pattern[j:j+i+1])

l = 0
r = M
answer = 0
while l < M:
    if text[l:r] in text_set:
        answer += 1
        l += r-l
        r = M
    else:
        r -= 1
print(answer)