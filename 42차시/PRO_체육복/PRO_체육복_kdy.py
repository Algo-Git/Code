def solution(n, lost, reserve):

    set_r = set(reserve) - set(lost)
    set_l = set(lost) - set(reserve)
            
    for i in set_r:
        if i-1 in set_l:
            set_l.remove(i-1)
        elif i+1 in set_l:
            set_l.remove(i+1)
            
    answer = n - len(set_l)
    
    return answer