from collections import Counter

def solution(clothes):
    answer = 1
    c = Counter(x[1] for x in clothes)
    for i in c:
        answer*=c[i]+1
    return answer-1