import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1068_트리 {

    static int N, removeNode, answer, root;
    static ArrayList<Integer>[] child;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        child = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            child[i] = new ArrayList();
        }

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(s[i]);
            if (parent == -1) {
                root = i;
                continue;
            }
            child[parent].add(i);  // 자식 노드 저장
        }

        removeNode = Integer.parseInt(br.readLine());

        if(removeNode != root) dfs(root);
        System.out.print(answer);
    }

    private static void dfs(int here) {
        if (here == removeNode) return; // 삭제할 노드이면 return
        if ((child[here].size() == 0 && here != root)
        || (child[here].size() == 1 && child[here].get(0) == removeNode)) { // 리프 노드 == 자식이 없을 때
            answer++;
            return;
        }

        for (int i = 0; i < child[here].size(); i++) {
            dfs(child[here].get(i));
        }
    }
}
