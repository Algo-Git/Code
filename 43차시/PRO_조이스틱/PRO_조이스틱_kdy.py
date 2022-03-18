def solution(name):
    answer = 0 #조이스틱 조작 횟수의 최솟값
    l = len(name) #이름 길이
    m = l-1 #이동횟수 최솟값 => 가장 큰 값으로 초기화(이름 길이-1)
    if name.count('A') == l: #모든 문자가 A로만 이루어진 경우
        return 0 #조작 횟수 0
    for i, n in enumerate(name): #이름 살펴보기
        if n != 'A': #A가 아닐때
            answer+=min([ord(n)-ord('A'), ord('Z')-ord(n)+1]) #▲▼중 최소 조작 횟수
            idx = i+1 #현재 문자 다음 문자
            for j in range(i+1, l): #한 칸 옆부터 끝까지 살펴보기
                if name[j] == 'A': #A이면
                    idx += 1 #계속 이동
                else: #A가 아니면
                    break #그만 살펴보기
						#현재까지 이동횟수 최솟값, A가 연속된 구간을 기준으로 오/왼 영역 중 짧은 부분을 2번 이동하고 나머지를 한 번씩 이동
            m = min([m, i*2+l-idx, (l-idx)*2+i])
    return answer+m #알파벳 조작 횟수 + 이동 횟수