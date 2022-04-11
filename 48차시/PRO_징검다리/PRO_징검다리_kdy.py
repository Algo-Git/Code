def solution(n, times):
    if n == 1:
        return n*times[0]
    l = times[0]
    r = (int)(n/len(times)*1.5)*times[-1]
    while l < r:
        mid = (l+r)//2
        cnt = 0
        for t in times:
            cnt += mid//t
        if cnt >= n:
            r = mid
        else:
            l = mid+1
            
    return l