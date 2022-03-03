def solution(participant, completion):
    answer = ''
    N = len(completion)
    participant.sort()
    completion.sort()
    for i in range(N):
        if participant[i] != completion[i]:
            answer = participant[i]
            break
    else:
        answer = participant[-1]
    return answer