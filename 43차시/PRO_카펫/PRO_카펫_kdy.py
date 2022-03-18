def solution(brown, yellow):
    by = brown+yellow #카펫 넓이
    for i in range(3, by): #약수 구하기
        if by%i != 0: #나눠떨어지는 수 아니면
            continue #지나가기
        mod = by//i #by = i*mod
        if (i-2)*(mod-2) == yellow: #가장자리 1칸씩을 뺀 변의 길이끼리 곱한 값이 yellow면
            return [mod, i] #가로가 넓기때문에 큰 수부터 반환