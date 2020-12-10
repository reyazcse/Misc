//https://leetcode.com/problems/count-and-say/

/*
Solution: 
	Using recursion.
	Get the prev_ans.
	Then do counting of digits on prev_ans.
	...
	
	Complexity:	Refer leetcode solution
		O(2^n) time and O(2 ^ (n-1)) space
	
 * */
package leetcode;

public class CountAndSay {
	public String countAndSay(int n) {
		if( n == 1) {
			return "1";
		}
		String prev_ans = countAndSay(n-1);
		StringBuilder curr_ans = new StringBuilder();
		int i=0;
		while(i < prev_ans.length()) {
			char curr = prev_ans.charAt(i);
			int count = 1;
			while(i+1 < prev_ans.length() && prev_ans.charAt(i) == prev_ans.charAt(i+1)) {
				count++;
				i++;
			}
			curr_ans.append(count).append(curr);
			i++;
		}
		return curr_ans.toString();
	}

	public static void main(String[] args) {
		System.out.println(new CountAndSay().countAndSay(4));

	}

}
