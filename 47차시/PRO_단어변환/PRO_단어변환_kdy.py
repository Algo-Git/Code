def solution(begin, target, words):
    if target not in words:
        return 0
    
    stack = [(begin, 0, [])]
    answer = 55
    while stack:
        cur, ans, v = stack.pop()

        if ans >= answer:
            continue
            
        if cur == target:
            answer = ans
            continue
            
        for word in set(words)-set(v):
            cnt = 0
            for c, w in zip(cur, word):
                if c!=w:
                    cnt+=1
            if cnt == 1:
                v.append(word)
                stack.append((word, ans+1, v))
                
    return answer