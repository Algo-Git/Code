import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<Character, Integer> map = new HashMap<>();
    Condition[] conditions;
    int answer = 0;
    boolean[] v = new boolean[8];
    int[] line = new int[8];


    public int solution(int n, String[] data) {


        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);

        conditions = new Condition[n];

        char[] in;
        for (int i = 0; i < n; i++) {
            in = data[i].toCharArray();
            conditions[i] = new Condition(map.get(in[0]), map.get(in[2]), in[3], in[4] - '0');
        }


        perm(0, n);


        return answer;
    }

    private void perm(int dept, int n) {

        if (dept == 8) {

            //조건 검사
            if (checkCondition(n)) {
                answer++;
                return;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (v[i]) continue;
            v[i] = true;
            line[dept] = i; //dept 사람이 i 번에 서있다!
            perm(dept + 1, n);
            v[i] = false;
        }

    }

    private boolean checkCondition(int n) {

        int A, B, sign, limit, len;
        for (int i = 0; i < n; i++) {
            A = conditions[i].A;
            B = conditions[i].B;
            sign = conditions[i].sign;
            limit = conditions[i].limit;
            len = Math.abs(line[A] - line[B]) - 1;

            if ((sign == '=' && len == limit) ||
                    (sign == '>' && len > limit) ||
                    (sign == '<' && len < limit))
                continue;

            return false;
        }


        return true;
    }

    class Condition {
        int A;
        int B;
        char sign;
        int limit;

        public Condition(int a, int b, char sign, int limit) {
            A = a;
            B = b;
            this.sign = sign;
            this.limit = limit;
        }
    }
}