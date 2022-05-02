package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_생태학_HoonyCodde {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        Map<String, Integer> map = new HashMap<>();

        String tree;
        while (true) {
            tree = br.readLine();
            if (tree == null || tree.equals("")) break;

            if (map.containsKey(tree)) {
                map.replace(tree, map.get(tree) + 1);
            } else {
                map.put(tree, 1);
            }
            total++;
        }

        String[] treeNameArr = new String[map.size()];

        int idx = 0;
        for (String name : map.keySet()) {
            treeNameArr[idx++] = name;
        }

        Arrays.sort(treeNameArr);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < treeNameArr.length; i++) {
            sb.append(treeNameArr[i]).append(' ').append(String.format("%.4f", (double) map.get(treeNameArr[i]) * 100 / total)).append('\n');
        }

        System.out.print(sb.toString());

    }
}
