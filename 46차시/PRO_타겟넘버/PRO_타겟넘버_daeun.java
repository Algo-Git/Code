public class PRO_43165_타겟넘버 {
    public static void main(String[] args) {

        System.out.print(new PRO_43165_타겟넘버().Solution(new int[]{1, 1, 1, 1, 1}, 3));

    }

    public static class Data implements Comparable<Data>{
        int x, y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Data o) {
            return this.x - o.x;
        }
    }

    public int Solution(int[] numbers, int target) {
        int ans = 0;

        ans = dfs(0, numbers, target);

        return ans;
    }

    private int dfs(int cnt, int[] numbers, int target) {
        if (cnt == numbers.length) {
            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                sum += numbers[i];
            }
            if (sum == target) return 1;
            return 0;
        }

        // 그대로 넣기
        int ans = dfs(cnt + 1, numbers, target);

        // -1 곱한 후 넣기
        numbers[cnt] *= -1;
        ans += dfs(cnt + 1, numbers, target);
        numbers[cnt] *= -1;

        return ans;
    }
}