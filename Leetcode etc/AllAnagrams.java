/*
Question: 438
	Given a string s and a non-empty string p, 
	find all the start indices of p's anagrams in s.
	Strings consists of lowercase English letters only and the length of both strings s and p 
	will not be larger than 20,100.

Input:
	s: "cbaebabacd" p: "abc"

Output:
	[0, 6]

Solution:
	We use sliding window technique.
	We also uses two int arrays of length 26 each: 0 ->a, 1 ->b, .......z -> 25
	
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllAnagrams {
	public List<Integer> findAnagrams(String s, String p) {
		//handling base cases
        if (s == null || p == null || s.length() ==0 || p.length() == 0 || p.length() > s.length())
        	return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<>();
        int left=0, right = 0;
        int [] sHash = new int[26];
        int [] pHash = new int[26];
        
        for(;right < p.length(); right++) {
        	char c1 = s.charAt(right);
        	char c2 = p.charAt(right);
        	sHash[c1 - 'a'] += 1;
        	pHash[c2 - 'a'] += 1;
        }
        //till here first window is constructed
        right--;
        while(right < s.length()) {
        	if(Arrays.equals(sHash, pHash)) { //check if strings represented by sHash and pHash are anagrams
        		result.add(left);
        	}
        	right++;
        	if (right == s.length()) {
        		break;
        	}
        	char c1 = s.charAt(right);
        	sHash[c1 - 'a'] +=1;        //adding next char
        	char c2 = s.charAt(left);
        	sHash[c2 - 'a'] -= 1;       //removing leftmost char
        	left++;
        }
        return result;
    }
	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		AllAnagrams obj = new AllAnagrams();
		System.out.println(obj.findAnagrams(s, p));

	}

}
