def solution(distance, rocks, n):
    answer = 0
    rocks.sort()
    rocks.append(distance)
    left = 1
    right = distance
    while left <= right:
        mid = (left + right) // 2
        remove = 0
        cur = 0
        for rock in rocks:
            gap = rock - cur
            if gap < mid:
                remove += 1
            else:
                cur = rock
        if remove > n:
            right = mid - 1
        else:
            answer = mid
            left = mid + 1

    return answer