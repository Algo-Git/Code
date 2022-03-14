def solution(citations):
    citations.sort(reverse=True)
    for i, n in enumerate(citations):
        if i >= n:
            return i
    else:
        return i+1