/*
	src: https://leetcode.com/problems/counting-bits/
	Input: 5
	Output: [0,1,1,2,1,2]


 * */
package misc;

public class CountSetBits {

	public int[] countBits(int num) {
		int[]ans = new int[num+1];
		for(int i=0; i<=num; i++) {
			ans[i] = findSetBits(i);
		}
		return ans;
    }
	public int findSetBits(int i) {
		int cnt=0;
		for(int j=0; j<=31; j++) {		//32 bits since integer
			if(((i >> j) & 1) !=0)
				cnt++;
		}
		return cnt;
	}
	public static void main(String[] args) {
		CountSetBits obj = new CountSetBits();
		int []result = obj.countBits(5);
		for(int i=0;i<result.length;i++) {
			System.out.print(result[i] + "   ");
		}

	}

}
