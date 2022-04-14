from collections import defaultdict


def solution(n, results):
    answer = 0
    # dict { key1:set(), key2: set() }
    winner = defaultdict(set)
    loser = defaultdict(set)
    for r, c in results:
        winner[r].add(c)
        loser[c].add(r)

    for i in range(1, n + 1):
        for win in winner[i]:
            loser[win].update(loser[i])
        for loss in loser[i]:
            winner[loss].update(winner[i])

    for i in range(1, n + 1):
        if len(winner[i]) + len(loser[i]) == n - 1:
            answer += 1

    return answer