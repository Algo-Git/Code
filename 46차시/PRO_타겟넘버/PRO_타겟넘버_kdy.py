def solution(numbers, target):
    q = [0]
    for n in numbers:
        tmp = []
        while q:
            num = q.pop()
            tmp.append(num-n)
            tmp.append(num+n)
        q+=tmp
    return q.count(target)