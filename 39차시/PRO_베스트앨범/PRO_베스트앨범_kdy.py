def solution(genres, plays):
    answer = []
    dic = {}
    n = len(genres)
    play = {}
    
    if n == 1:
        return [0]
    
    for i, (g, p) in enumerate(zip(genres, plays)):
        if g in dic:
            dic[g].append([i, p])
            play[g] += p
        else:
            dic[g] = [[i, p]]
            play[g] = p
            
    for (k, v) in sorted(play.items(), key=lambda x:x[1], reverse=True):
        for (i, p) in sorted(dic[k], key=lambda x:x[1], reverse=True)[:2]:
            answer.append(i)
        
    return answer