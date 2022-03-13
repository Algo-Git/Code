# https://programmers.co.kr/learn/courses/30/lessons/42862

def solution(n, lost, reserve):
    _reserve = [r for r in reserve if r not in lost]
    _lost = [l for l in lost if l not in reserve]
    _reserve.sort()
    _lost.sort()
    answer = n
    for loss in _lost:
        if loss - 1 in _reserve:
            _reserve.remove(loss - 1)
        elif loss + 1 in _reserve:
            _reserve.remove(loss + 1)
        else:
            answer -= 1

    #     set_lost = set(lost) - set(reserve)
    #     set_reser = set(reserve) - set(lost)

    #     for loss in sorted(set_reser):
    #         if loss-1 in set_lost:
    #             set_lost -= {loss-1}
    #         elif loss+1 in set_lost:
    #             set_lost -= {loss+1}
    #     answer = n -len(set_lost)
    return answer