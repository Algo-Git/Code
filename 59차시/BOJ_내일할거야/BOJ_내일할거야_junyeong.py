import sys
N = int(input())
task = []
for _ in range(N):
    time, deadline = map(int, sys.stdin.readline().rstrip().split())
    task.append((time, deadline))

task.sort(key=lambda x: -x[1])
cur_day = task[0][1]

for time, dl in task:
    if cur_day > dl:
        cur_day = dl
    cur_day -= time
print(cur_day)