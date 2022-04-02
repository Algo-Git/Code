def solution(n, times):
    answer = 0
    start = 1
    end = max(times) * n

    while start <= end:
        mid = (start + end) // 2
        people = 0
        for time in times:
            people += (mid // time)
            if people >= n:
                break
        if people >= n:
            answer = mid
            end = mid - 1
        else:
            start = mid + 1

    return answer