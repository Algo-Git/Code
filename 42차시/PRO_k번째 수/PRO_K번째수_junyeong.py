# https://programmers.co.kr/learn/courses/30/parts/12198

def solution(array, commands):
    answer = []
    for i, j, k in commands:
        arr = []
        for idx in range(i - 1, j):
            arr.append(array[idx])
        arr.sort()
        answer.append(arr[k - 1])
        # sorted_arr = sorted(array[i-1:j])
        # answer.append(sorted_arr[k-1])

    return answer