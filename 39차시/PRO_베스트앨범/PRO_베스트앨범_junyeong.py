def solution(genres, plays):
    answer = []
    rank_dict = {}
    _dict = {}

    for i in range(len(genres)):
        if rank_dict.get(genres[i], 0) == 0:
            rank_dict[genres[i]] = plays[i]  # 장르별 총 재생횟수를 담는 딕셔너리
            _dict[genres[i]] = [(plays[i], i)]  # 각 장르별 노래를 배열에 담는 딕셔너리
        else:
            rank_dict[genres[i]] += plays[i]
            _dict[genres[i]].append((plays[i], i))

    sorted_rank = sorted(rank_dict.items(), key=lambda x: -x[1])

    for i in range(2):
        best = _dict.get(sorted_rank[i][0])
        best.sort(key=lambda x: (-x[0], x[1]))

        for j in range(len(best)):
            if j == 2:
                break
            answer.append(best[j][1])

    return answer