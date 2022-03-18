from itertools import permutations as p
from math import sqrt
def solution(numbers):
    answer = [] #소수 넣을 배열
    com = set() #집합
    for i in range(len(numbers)): #순열로 가능한 수 만들기
				#numbers중에 i개 뽑는 순열을 이어서 만든 수를 int로 만들어서 집합에 넣기
				#=> 0, 1, 7을 '017'로 만들어서 int로 바꿔서 17을 넣음
        com.update(map(int, [''.join(x) for x in p(list(numbers), i+1)]))
    for i in com: #소수 판별
        if i < 2: #2보다 작으면
            continue #소수 아님
        for j in range(2, int(sqrt(i))+1): #2~루트값까지 반복
            if i%j == 0: #약수 존재
                break #소수X
        else: #약수가 없음
            answer.append(i) #소수 목록에 추가
    return len(answer) #소수 개수 출력