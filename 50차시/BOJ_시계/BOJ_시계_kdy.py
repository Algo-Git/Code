#BOJ 8989. 시계

T = int(input())
for t in range(T):
    time = input().split()
    s = []
    for i, tt in enumerate(time):
        h, m = map(int, tt.split(':'))
        h = (h % 12) * 30 + m / 2
        m = m * 6
        angle = abs(h - m)
        if angle > 180:
            angle = 360 - angle
        s.append([angle, tt])
    s.sort()
    print(s[2][1])