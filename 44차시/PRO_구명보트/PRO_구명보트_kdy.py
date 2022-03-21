from collections import deque as dq
def solution(people, limit):
    answer = 0
    people.sort()
    people = dq(people)
    while people:
        M = people.pop()
        if people and M+people[0] <= limit:
            people.popleft()
        answer+=1
    return answer