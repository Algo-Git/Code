def solution(prices):
    l = len(prices) #주식가격 수
    answer = [0 for _ in range(l)] #가격이 떨어지지 않은 기간
    for i in range(l-1): #마지막을 제외한 주식가격 살펴보기
        for j in range(i+1, l): #이후 주식가격 살펴보기
            answer[i]+=1 #가격이 떨어지지 않은 기간 +1
            if prices[j] < prices[i]: #가격이 떨어진 경우
                break #중단
    return answer #가격이 떨어지지 않은 기간 반환