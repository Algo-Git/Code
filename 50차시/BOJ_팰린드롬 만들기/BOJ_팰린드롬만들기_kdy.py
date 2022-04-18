#BOJ 1254. 팰린드롬 만들기

str = input()
leng = len(str)
for i in range(leng - 1):
    half = (leng + i) // 2
    l = str[:half]
    r = str[leng - half + i:]
    t = str[:i]
    if l == t + r[::-1]:
        print(leng + i)
        break
else:
    print(leng * 2 - 1)