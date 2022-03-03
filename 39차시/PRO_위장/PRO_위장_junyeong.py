def solution(clothes):
    answer = 0
    cloth_dict = {}
    for name, category in clothes:
        if cloth_dict.get(category, 0) == 0:
            cloth_dict[category] = 1
        else:
            cloth_dict[category] += 1

    for idx in cloth_dict.values():
        answer += answer * idx + idx

    return answer