public class PG_12980_점프와순간이동 {
    public static void main(String[] args) {

        System.out.print(new PG_12980_점프와순간이동().solution(1));

    }

    public int solution(int n) {
        int ans = 0;
        // 1의 개수 == 건전지 사용량
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1') ans++;
        }

        return ans;
    }
}