# https://programmers.co.kr/learn/courses/30/parts/12081
def solution(prices):
    # 1. 스택으로 풀이
    answer = [x for x in range(len(prices)-1, -1, -1)]
    stack = []
    for i, price in enumerate(prices):
        # 스택에 있고, 현재 가격이 가장 상위값보다 작으면 빼?
        while stack and price < prices[stack[-1]]:
            j = stack.pop() # 현재가격보다 낮은 값의 인덱스
            answer[j] = i-j # 현재 인덱스와 차이는 얼마나 오래 있었는가.
        stack.append(i)

    # 2. 반복문으로 풀이
    # answer = []
    # N = len(prices)
    # for i, price in enumerate(prices):
    #     for j in range(i+1, N):
    #         if price >prices[j]:
    #             answer.append(j-i)
    #             break
    #     else:
    #         answer.append(N-1-i)
    return answer