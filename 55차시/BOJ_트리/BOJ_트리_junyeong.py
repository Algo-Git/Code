N = int(input())
tree = list(map(int, input().split()))
K = int(input())

answer = 0
root = tree.index(-1)
tree[K] = -2

if root != K:
    stack = [root]
    while stack:
        cur = stack.pop()
        before = len(stack)
        for i in range(N):
            if cur == tree[i]:
                stack.append(i)
        after = len(stack)
        if before == after:
            answer += 1
print(answer)