def solution(people, limit):
    answer = 0
    # people 배열, limit 숫자
    people.sort()
    l = 0
    r = len(people) - 1

    # 역전되면 종료
    while l <= r:
        if people[l] + people[r] <= limit:
            l += 1
            r -= 1
        else:
            r -= 1
        answer += 1

    return answer