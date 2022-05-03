import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class BOJ_4358_생태학 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new TreeMap<>(); // TreeMap은 자동 정렬
        String input = "";

        int total = 0;  // 모든 나무의 개수
        while ((input = br.readLine()) != null) {
            total++;
            map.put(input, map.getOrDefault(input, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (String tree : map.keySet()) {
            double per = (double) map.get(tree) / total * 100.0;
            sb.append(tree).append(" ").append(String.format("%.4f", per)).append("\n");
        }

        System.out.print(sb);
    }
}