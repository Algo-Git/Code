def solution(s):
    answer = 1000
    N = len(s)
    if N == 1:
        return 1
    for i in range(1, N // 2 + 1):
        val = ''
        temp = s[:i]
        cnt = 1

        for j in range(i, N, i):
            if temp == s[j:j + i]:
                cnt += 1
            else:
                if cnt == 1:
                    val += temp
                else:
                    val += str(cnt) + temp
                cnt = 1
                temp = s[j:j + i]

        if cnt == 1:
            val += temp
        else:
            val += str(cnt) + temp
        answer = min(answer, len(val))

    return answer