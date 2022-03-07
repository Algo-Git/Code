def solution(priorities, location):
    answer = 0 #location 위치에 있는 문서가 인쇄되는 순서
    while True:
         for i, p in enumerate(priorities):
            if max(priorities) != p: #우선순위가 가장 높은 문서가 아닌 경우
                continue #가장 마지막으로 넣기
            answer+=1 # 순서 +1
            priorities[i] = 0 # 출력된 문서의 우선순위 최하로 수정
            if i == location: #내가 요청한 문서가 인쇄된 경우
                return answer #출력 순서 반환