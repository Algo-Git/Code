import java.util.HashMap;
class Solution {
    int answer;
    int N;
    HashMap<Character, Integer> map;
    Want[] wants;
    boolean[] visited;
    int[] nums;
    public int solution(int n, String[] data) {
        N = n;

        map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);


        wants = new Want[n];
        for (int i = 0; i < n; i++) {
            wants[i] = new Want(map.get(data[i].charAt(0)), map.get(data[i].charAt(2)), data[i].charAt(3), data[i].charAt(4) - '0');
        }

        answer = 0;
        visited = new boolean[8];
        nums = new int[8];
        perm(0);
        return answer;
    }
    
    private void perm(int cnt){
        if(cnt == 8){
            boolean flag = true;
            for(int i = 0; i<N; i++){
                if(!check(wants[i])){
                    flag = false;
                    break;
                }
            }
            if(flag) answer ++;
            return;
        }


        for(int i = 0; i<8; i++){
            if(visited[i]) continue;
            visited[i] = true;
            nums[i] = cnt;
            perm(cnt+1);
            visited[i] = false;
        }
    }
    
    private boolean check(Want want) {
        int p1 = want.p1;
        int p2 = want.p2;
        char op = want.op;
        int limit = want.limit;

        int dist = Math.abs(nums[p1]-nums[p2]) -1;

        if(op == '<' && dist<limit || op=='=' && dist == limit || op=='>'&&dist>limit){
            return true;
        }
        return false;
    }
    

    private class Want {
        int p1;
        int p2;
        char op;
        int limit;

        public Want(int p1, int p2, char op, int limit) {
            this.p1 = p1;
            this.p2 = p2;
            this.op = op;
            this.limit = limit;
        }
    }        
}
