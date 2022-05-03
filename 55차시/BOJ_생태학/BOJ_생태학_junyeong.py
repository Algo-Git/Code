import sys


forest = {}
N = 0
while True:
    tree = sys.stdin.readline().rstrip()
    if not tree:
        break
    if forest.get(tree):
        forest[tree] += 1
    else:
        forest[tree] = 1
    N += 1
answer = sorted(forest)
for item in answer:
    print(f'{item} {forest.get(item)/N*100:.4f}')
