//https://leetcode.com/problems/decode-ways/
/*
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "111" can have each of its "1"s be mapped into 'A's to make "AAA", or it could be mapped to "11" and "1" ('K' and 'A' respectively) to make "KA". Note that "06" cannot be mapped into 'F' since "6" is different from "06".

Given a non-empty string num containing only digits, return the number of ways to decode it.

The answer is guaranteed to fit in a 32-bit integer.

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).

 * */


//Solution: O(n)
package leetcode;

public class DecodeWays {
	//recursive: TLE
    public int numDecodings(String s) {
        return utl(s, 0);
    }
    
    private int utl(String s, int i) {
        if(i == s.length()){
            return 1;
        }
        if(s.charAt(i) == '0') {
            return 0;
        }
        
        int x = utl(s, i+1);
        
        int y=0;
        if(i+2 <= s.length() && Integer.parseInt(s.substring(i, i+2)) <= 26) {
            y = utl(s, i+2);
        } 
        
        return x+y;
    }
    
    
    
    //TOP DOWN
    public int numDecodings_TopDown(String s) {
    	int[]dp = new int[s.length()];
        return utl_TopDown(s, 0, dp);
    }
    
    private int utl_TopDown(String s, int i, int[] dp) {
        if(i == s.length()){
            return 1;
        }
        if(s.charAt(i) == '0') {
            return 0;
        }
        if(dp[i] != 0) {
        	return dp[i];
        }
        int x = utl_TopDown(s, i+1, dp);
        
        int y=0;
        if(i+2 <= s.length() && Integer.parseInt(s.substring(i, i+2)) <= 26) {
            y = utl_TopDown(s, i+2, dp);
        } 
        
        return dp[i] = x+y;
    }

}
