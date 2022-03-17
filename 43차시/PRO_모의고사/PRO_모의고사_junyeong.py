def solution(answers):
    answer = []
    arr = [[1, 2, 3, 4, 5],
           [2, 1, 2, 3, 2, 4, 2, 5],
           [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]]
    N = [len(x) for x in arr]
    cnt = [0] * 3
    for i in range(len(answers)):
        if answers[i] == arr[0][i % N[0]]:
            cnt[0] += 1

        if answers[i] == arr[1][i % N[1]]:
            cnt[1] += 1

        if answers[i] == arr[2][i % N[2]]:
            cnt[2] += 1

    for j in range(3):
        if cnt[j] == max(cnt):
            answer.append(j + 1)

    return answer