def solution(phone_book):
    answer = True
    hash_map = {}

    for phone in phone_book:  # 폰북을 모두 해시맵에 매핑
        hash_map[phone] = 1

    for phone in phone_book:  # 번호의 앞부터 하나씩 추가하면서 검색
        temp = ''
        for i in phone:
            temp += i
            if temp in hash_map and temp != phone:
                return False

    return answer