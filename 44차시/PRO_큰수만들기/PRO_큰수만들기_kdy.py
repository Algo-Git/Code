def solution(number, k):
    answer = ''
    n = len(number) #숫자의 길이
    if number.count(number[0]) == n: #모두 같은 숫자로 이루어져있으면 ex) 111
        return number[0]*(n-k) #k개 제외하고 반환
    leng = n-k #제거한 후 숫자 길이 => 구하는 숫자의 길이
    for i, nn in enumerate(number): #숫자 앞자리부터 살펴보기
        if leng == 0: #구하는 숫자 길이가 0이면 => 숫자 구함
            break #중단
        if k == 0: #k개를 이미 제거했으면
            answer += nn #남은 수는 그대로 추가
            continue #다음 자리 살펴보기
        if nn == '9': #현재 수가 9면 => 가장 큰 수
            answer+=nn #답에 바로 추가
            leng-=1 #구하는 숫자 길이 -1
            continue #다음 자리 보러가기
        tmp = nn #현재 자리의 숫자
        for j in range(i+1, n-leng+1): #제거해도 되는 숫자 구간 살펴보기
            if number[j] == '9' or tmp<number[j]: #9가 있거나 현재 수보다 큰 수가 있으면
								k-=1 #현재 숫자 제거
                break #그만 살펴보기
        else: #현재 수가 가장 큼
            answer+=nn #숫자에 추가
            leng-=1 #구할 숫자 길이 -1
        
    return answer #number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자