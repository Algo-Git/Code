from collections import deque
T = int(input())
for _ in range(T):
    p_list = input()
    n = int(input())
    x_input = input()
    if n == 0:
        x = []
    else:
        x = deque(list(x_input[1:-1].split(',')))
    # 앞에꺼, 뒤에꺼
    dr = [1, 0]
    r_cnt = 0
    for p in p_list:
        if p == 'R':
            r_cnt += 1
        else:
            try:
                if dr[r_cnt%2]:
                    x.popleft()
                else:
                    x.pop()
            except:
                print('error')
                break
    else:
        print('[', end='')
        if r_cnt % 2:
            print(*list(x)[::-1], sep=',', end='')
        else:
            print(*list(x), sep=',', end='')
        print(']')
