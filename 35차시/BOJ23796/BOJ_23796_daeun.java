import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_23796_2147483648게임 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[8][8];
        long[][] res = new long[8][8];
        String[] s;
        for (int i = 0; i < 8; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < 8; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        char c = br.readLine().charAt(0);

        List<Data>[] list = new ArrayList[8];
        for (int i = 0; i < 8; i++) {
            list[i] = new ArrayList<>();
        }

        switch (c) {
            case 'U':
                for (int j = 0; j < 8; j++) {
                    for (int i = 0; i < 8; i++) {
                        if (map[i][j] != 0) { // 0이 아닐 때
                            int size = list[j].size();
                            // 리스트가 비었으면 리스트에 추가
                            if (size == 0) {
                                list[j].add(new Data(map[i][j], false));
                            } else {  // 리스트가 비지 않았을 때
                                // 더해야 하는 수일 때 => 이전에 더하지 않았고 같은 숫자를 만났을 때
                                if (!list[j].get(size - 1).isAdd &&
                                        list[j].get(size - 1).num == map[i][j]) {
                                    list[j].get(size - 1).num += map[i][j];
                                    list[j].get(size - 1).isAdd = true;
                                }
                                // 더할수 없는 수일 때 => 이전에 더했거나, 다른 숫자일 때 리스트에 추가
                                else if (list[j].get(size - 1).isAdd ||
                                        list[j].get(size - 1).num != map[i][j]) {
                                    list[j].add(new Data(map[i][j], false));
                                }
                            }
                        }
                    }
                    for (int i = 0; i < list[j].size(); i++) {
                        res[i][j] = list[j].get(i).num;
                    }
                }
                break;
            case 'D':
                for (int j = 0; j < 8; j++) {
                    for (int i = 7; i >= 0; i--) {
                        if (map[i][j] != 0) { // 0이 아닐 때
                            int size = list[j].size();
                            // 리스트가 비었으면 리스트에 추가
                            if (size == 0) {
                                list[j].add(new Data(map[i][j], false));
                            } else {  // 리스트가 비지 않았을 때
                                // 더해야 하는 수일 때 => 이전에 더하지 않았고 같은 숫자를 만났을 때
                                if (!list[j].get(size - 1).isAdd &&
                                        list[j].get(size - 1).num == map[i][j]) {
                                    list[j].get(size - 1).num += map[i][j];
                                    list[j].get(size - 1).isAdd = true;
                                }
                                // 더할수 없는 수일 때 => 이전에 더했거나, 다른 숫자일 때 리스트에 추가
                                else if (list[j].get(size - 1).isAdd ||
                                        list[j].get(size - 1).num != map[i][j]) {
                                    list[j].add(new Data(map[i][j], false));
                                }
                            }
                        }
                    }
                    for (int i = 7; i >= 8 - list[j].size(); i--) {
                        res[i][j] = list[j].get(7 - i).num;
                    }
                }
                break;
            case 'L':
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (map[i][j] != 0) { // 0이 아닐 때
                            int size = list[i].size();
                            // 리스트가 비었으면 리스트에 추가
                            if (size == 0) {
                                list[i].add(new Data(map[i][j], false));
                            } else {  // 리스트가 비지 않았을 때
                                // 더해야 하는 수일 때 => 이전에 더하지 않았고 같은 숫자를 만났을 때
                                if (!list[i].get(size - 1).isAdd &&
                                        list[i].get(size - 1).num == map[i][j]) {
                                    list[i].get(size - 1).num += map[i][j];
                                    list[i].get(size - 1).isAdd = true;
                                }
                                // 더할수 없는 수일 때 => 이전에 더했거나, 다른 숫자일 때 리스트에 추가
                                else if (list[i].get(size - 1).isAdd ||
                                        list[i].get(size - 1).num != map[i][j]) {
                                    list[i].add(new Data(map[i][j], false));
                                }
                            }
                        }
                    }
                    for (int j = 0; j < list[i].size(); j++) {
                        res[i][j] = list[i].get(j).num;
                    }
                }
                break;
            case 'R':
                for (int i = 0; i < 8; i++) {
                    for (int j = 7; j >= 0; j--) {
                        if (map[i][j] != 0) { // 0이 아닐 때
                            int size = list[i].size();
                            // 리스트가 비었으면 리스트에 추가
                            if (size == 0) {
                                list[i].add(new Data(map[i][j], false));
                            } else {  // 리스트가 비지 않았을 때
                                // 더해야 하는 수일 때 => 이전에 더하지 않았고 같은 숫자를 만났을 때
                                if (!list[i].get(size - 1).isAdd &&
                                        list[i].get(size - 1).num == map[i][j]) {
                                    list[i].get(size - 1).num += map[i][j];
                                    list[i].get(size - 1).isAdd = true;
                                }
                                // 더할수 없는 수일 때 => 이전에 더했거나, 다른 숫자일 때 리스트에 추가
                                else if (list[i].get(size - 1).isAdd ||
                                        list[i].get(size - 1).num != map[i][j]) {
                                    list[i].add(new Data(map[i][j], false));
                                }
                            }
                        }
                    }
                    for (int j = 7; j >= 8 - list[i].size(); j--) {
                        res[i][j] = list[i].get(7 - j).num;
                    }
                }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Data {
        long num;
        boolean isAdd;

        public Data(long num, boolean isAdd) {
            this.num = num;
            this.isAdd = isAdd;
        }
    }
}