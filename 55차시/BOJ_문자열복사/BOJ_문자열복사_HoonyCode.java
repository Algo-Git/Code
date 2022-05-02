package BOJ;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_문자열복사_HoonyCode {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        Map<Character, List<Integer>> map = new HashMap<>();

        int str1Lens = str1.length;

        for (int i = 0; i < str1Lens; i++) {

            char c = str1[i];

            if (map.containsKey(c)) {
                List<Integer> list = map.get(c);
                list.add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(c, list);
            }
        }

        int str2Len = str2.length;

        int idx = 0;
        int answer = 0;
        while (str2Len > idx) {
            char start = str2[idx];
            List<Integer> list = map.get(start);

            int max = 0;

            for (int cur : list) {

                int i = 1;
                while (true) {
                    if (cur + i >= str1Lens || idx + i >= str2Len) break;
                    if (str1[cur + i] != str2[i + idx]) break;
                    i++;
                }

                max = Math.max(i, max);
            }

            idx += max;
            answer++;
        }

        System.out.println(answer);

    }
}
