import java.util.*;
// 판매 기록 하나씩 부모를 따라가면서 더해줌
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
 int memberNum = enroll.length;

		// 부모 저장하기
		int[] parents = new int[memberNum];


		for (int i = 0; i < memberNum; i++) {
			if (referral[i].equals("-")) {
				parents[i] = -1;
			} else {
				for (int j = 0; j < memberNum; j++) {
					if (enroll[j].equals(referral[i])) {
						parents[i] = j;
						break;
					}
				}
			}
		}
		
		int[] sellerIdx = new int[seller.length];
		for(int i = 0; i<seller.length; i++) {
			for (int j = 0; j < memberNum; j++) {
				if (enroll[j].equals(seller[i])) {
					sellerIdx[i] = j;
					break;
				}
			}
		}
		int[] moneys = new int[memberNum];
		
		for(int i = 0; i<seller.length; i++) {
			int money = amount[i]*100;
			int cur = sellerIdx[i];
			while(true) {
				// 자기 부모가 없으면
				if(parents[cur] == -1) {
					money -= (int)(money/10);
					moneys[cur] += money;
					break;
				}
				
				// 부모가 있다면?
				int nextMoney =(int)(money/10);
				moneys[cur] += money-nextMoney;
				
				cur = parents[cur];
				money = nextMoney;
			}
		}
		return moneys;
	}
}
