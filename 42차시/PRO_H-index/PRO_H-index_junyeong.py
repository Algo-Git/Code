# https://programmers.co.kr/learn/courses/30/parts/12198

def solution(citations):
    answer = 0
    citations.sort(reverse=True)
    N = len(citations)
    for i in range(N-1, -1, -1):
        if citations[i] >= i+1:
            return i+1
    else:
        return i