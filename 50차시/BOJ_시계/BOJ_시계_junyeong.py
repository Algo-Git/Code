T = int(input())
for tc in range(1, T+1):
    data = list(input().split())
    times = []
    for idx, time in enumerate(data):
        h, m = map(int, time.split(':'))
        hour_pos = h % 12 * 30 + m / 2
        min_pos = m * 6
        val = abs(hour_pos-min_pos)
        deg = min(val, 360-val)
        times.append([h, m, deg, idx])
    times.sort(key=lambda x: (x[2], x[0], x[1]))
    print(data[times[2][3]])