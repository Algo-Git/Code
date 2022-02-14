# https://www.acmicpc.net/problem/1198
from itertools import combinations

N = int(input())
edges = [list(map(int, input().split())) for _ in range(N)]

arr = list(combinations(edges, 3))
result = 0

for x, y, z in arr:
    a, d = x
    b, e = y
    c, f = z
    area = abs((a*e+b*f+c*d) - (d*b + e*c + f*a)) / 2
    result = max(area, result)

print(result)
# 삼각형 너비 공식 사선 공식
# (a, b), (c, d), (e, f)
# | a c e a |
# | b d f b |  => 1/2 (a*d + c*f + e*b) - (c*b + e*d + a*f)
