def solution(progresses, speeds):
    answer = []
    last = 0 #이전 작업의 배포일
    for p, s in zip(progresses, speeds):
        div, mod = divmod(100-p, s) #몫, 나머지 구하기
        if mod != 0: #나머지가 있으면
            div += 1 #배포일 +1
        if last >= div: #이전 기능보다 먼저 개발된 경우
            answer[-1]+=1 #이전 작업 배포일에 함께 배포
        else: #더 늦게 끝나는 경우
            answer.append(1) #배포
            last = div #배포일 저장
        
    return answer #배포 기록 출력