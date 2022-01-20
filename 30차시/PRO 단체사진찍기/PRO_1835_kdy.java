class Solution {
    Cond[] cond;
    int[] P;
    boolean[] v;
    int answer;
    
    public int solution(int n, String[] data) {
        answer = 0;
        String friends = "ACFJMNRT";
        cond = new Cond[n];
        for (int i = 0; i < n; i++) {
            int a = friends.indexOf(data[i].charAt(0));
            int b = friends.indexOf(data[i].charAt(2));
            int op = 0;
            switch (data[i].charAt(3)){
                case '=':
                    break;
                case '<':
                    op = 1;
                    break;
                case '>':
                    op = 2;
                    break;
            }
            int dis = data[i].charAt(4)-'0';
            cond[i] = new Cond(a, b, op, dis);
        }
        P = new int[8];
        v = new boolean[8];
        perm(0);
        return answer;
    }

    private void perm(int cnt) {
        if (cnt == 8){
            if (check())
                answer++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (v[i])
                continue;
            v[i] = true;
            P[i] = cnt;
            perm(cnt+1);
            v[i] = false;
        }
    }

    private boolean check() {
        for (int i = 0; i < cond.length; i++) {
            int dist = Math.abs(P[cond[i].a]-P[cond[i].b])-1;
            if (cond[i].op == 0){
                if (dist != cond[i].dis)
                    return false;
            }else if (cond[i].op == 1){
                if (dist>= cond[i].dis)
                    return false;
            }else{
                if (dist<=cond[i].dis)
                    return false;
            }
        }
        return true;
    }

    static class Cond{
        int a, b, op, dis;
        public Cond(int a, int b, int op, int dis){
            this.a = a;
            this.b = b;
            this.op = op;//=<>
            this.dis = dis;
        }
    }
}