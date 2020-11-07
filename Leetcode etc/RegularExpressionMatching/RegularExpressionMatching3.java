//https://leetcode.com/problems/regular-expression-matching/
//below are leetcode solutions. Look at bottom up solution comments to get the idea
package leetcode;

public class RegularExpressionMatching3 {
	
	
	/*******************RCURSEIVE SOLUTION************************************************************************************/
	public boolean isMatch(String s, String p) {
		if(p.isEmpty()) {
			return s.isEmpty();
		}
		boolean first_match = !s.isEmpty() && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
		if(p.length() >=2 && p.charAt(1) == '*') {
			if(first_match) {
				return isMatch(s, p.substring(2)) || isMatch(s.substring(1), p);
			}else {
				return isMatch(s, p.substring(2));
			}
		}else {
			if(first_match) {
				return isMatch(s.substring(1), p.substring(1));
			}
		}
		return false;
	}
	
	/*************************************TOP down dp*************************************************************************/
	public boolean isMatchDP(String s, String p) {
		Result[][] dp = new Result[s.length()+1][p.length()+1];
		return isMatchDPUtl(s, p, 0, 0, dp);
	}
	
	private boolean isMatchDPUtl(String s, String p, int i, int j, Result[][] dp) {
		if(j == p.length()) {
			return i == s.length();
		}
		if(dp[i][j] != null) {
			return dp[i][j]== Result.TRUE;
		}
		boolean ans = false;
		boolean first_match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
		if(j+1 < p.length() && p.charAt(j+1) == '*') {
			if(first_match) {
				ans =  isMatchDPUtl(s, p, i, j+2, dp) || isMatchDPUtl(s, p, i+1, j, dp);
			}else {
				ans = isMatchDPUtl(s, p, i, j+2, dp);
			}
		}else {
			ans = first_match && isMatchDPUtl(s, p, i+1, j+1, dp);
		}
		dp[i][j] = ans==true? Result.TRUE : Result.FALSE;
		return ans;
	}
	
	
	
	/*************************************Bottom up dp*************************************************************************/
	 public boolean isMatchBottomUpDP(String text, String pattern) {
	        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
	        dp[text.length()][pattern.length()] = true;								//we reach end of text and pattern, hence true

	        //Note that we start from text.length() and not from text.length()-1
	        //Reason is even though we might be at the end of text, we can still have pattern left to process and result maybe true
	        //But we start j from  pattern.length() - 1, since if we are pattern.length() and there is some text left then it means dp[i][j] will be false
	        //So we already have the rightmost column cells as false (except dp[text.length()][pattern.length()])
	        for (int i = text.length(); i >= 0; i--){			
	            for (int j = pattern.length() - 1; j >= 0; j--){
	                boolean first_match = (i < text.length() &&
	                                       (pattern.charAt(j) == text.charAt(i) ||
	                                        pattern.charAt(j) == '.'));
	                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
	                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
	                } else {
	                    dp[i][j] = first_match && dp[i+1][j+1];
	                }
	            }
	        }
	        printTable(dp);
	        return dp[0][0];
	    }
	 
	 private void printTable(boolean [][] dp) {
		 for(int i=0; i<dp.length; i++) {
			 System.out.println();
			 for(int j=0; j<dp[0].length; j++) {
				 System.out.print(dp[i][j] + "   ");
			 }
		 }
	 }
	public static void main(String[] args) {
		RegularExpressionMatching3 obj = new RegularExpressionMatching3();
		obj.isMatchBottomUpDP("aa", "a*");
				
		

	}

	
	enum Result {
	    TRUE, FALSE
	}
}



