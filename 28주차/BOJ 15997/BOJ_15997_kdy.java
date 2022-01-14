import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N15997 {

	static play[] P;
	static int[] sco;
	static double[] per;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<String> nat = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			nat.add(st.nextToken());
		}
		P = new play[6];
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			double W = Double.parseDouble(st.nextToken());
			double D = Double.parseDouble(st.nextToken());
			double L = Double.parseDouble(st.nextToken());
			P[i] = new play(nat.indexOf(A), nat.indexOf(B), W, D, L);
		}
		per = new double[4];
		sco = new int[4];
		dfs(0, 1f);
		for (int i = 0; i < 4; i++) {
			System.out.printf("%.10f\n", per[i]);
		}
	}

	private static void dfs(int cnt, double pct) {
		if (cnt == 6) {
			Score[] s = new Score[4];
			for (int i = 0; i < 4; i++) {
				s[i] = new Score(i, sco[i]);
			}
			Arrays.sort(s);

			if (s[1].score != s[2].score) {
				per[s[0].n] += pct;
				per[s[1].n] += pct;
			} else if (s[0].score == s[3].score) {
				per[s[0].n] += pct / 2;
				per[s[1].n] += pct / 2;
				per[s[2].n] += pct / 2;
				per[s[3].n] += pct / 2;
			} else if (s[0].score == s[1].score) {
				per[s[0].n] += pct * 2 / 3;
				per[s[1].n] += pct * 2 / 3;
				per[s[2].n] += pct * 2 / 3;
			} else if (s[2].score == s[3].score) {
				per[s[0].n] += pct;
				per[s[1].n] += pct / 3;
				per[s[2].n] += pct / 3;
				per[s[3].n] += pct / 3;
			} else {
				per[s[0].n] += pct;
				per[s[1].n] += pct / 2;
				per[s[2].n] += pct / 2;
			}

			return;
		}

		sco[P[cnt].A] += 3;
		dfs(cnt + 1, pct * P[cnt].W);
		sco[P[cnt].A] -= 3;

		sco[P[cnt].A] += 1;
		sco[P[cnt].B] += 1;
		dfs(cnt + 1, pct * P[cnt].D);
		sco[P[cnt].A] -= 1;
		sco[P[cnt].B] -= 1;

		sco[P[cnt].B] += 3;
		dfs(cnt + 1, pct * P[cnt].L);
		sco[P[cnt].B] -= 3;
	}

	static class play {
		int A, B;
		double W, D, L;

		public play(int a, int b, double w, double d, double l) {
			A = a;
			B = b;
			W = w;
			D = d;
			L = l;
		}
	}

	static class Score implements Comparable<Score> {
		int n;
		int score;

		public Score(int n, int score) {
			this.n = n;
			this.score = score;
		}

		@Override
		public int compareTo(Score o) {
			return o.score - score;
		}
	}
}
