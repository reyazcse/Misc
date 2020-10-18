//https://leetcode.com/problems/verifying-an-alien-dictionary/
/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. 
The order of the alphabet is some permutation of lowercase letters.
Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

 * */

package leetcode;

public class VerifyAlienDictionary {
	public boolean isAlienSorted(String[] words, String order) {
		int[] map = new int[26];  				//since we are given at max 26 lowercase english chars
		for(int i=0; i<order.length(); i++) {
			int ch = order.charAt(i)-'a';
			map[ch] = i;						//store position of this char
		}
		boolean mismatchOrdered = false;
		
		//Process all the words by pairing them consecutively: if w1,w2,w3,w4 and w4 is less than w2 then w4 is also less than w3
		//hence we process adjacent words in pair
		for(int i=0; i<words.length-1; i++) {
			int minWordLen = Math.min(words[i].length(), words[i+1].length());
			for(int j=0; j<minWordLen; j++) {
				int ch1 = words[i].charAt(j)-'a';
				int ch2 = words[i+1].charAt(j)-'a';
				
				//if there is a character mismatch b/w the words
				if(ch1 != ch2 ) {
					if(map[ch1] > map[ch2])
						return false;
					//if we find correct order, then do not return instantly as there maybe more words to be processed
					else if (map[ch1] < map[ch2]) {
						mismatchOrdered = true;
						break;
					}
				}
			}
			//We come here if j==minWordLen OR we find a correct order.
			//If correct order has not been found and first word is bigger, we return false. for e.g. [apple,app]
			//for word like [apple, b], we already set the flag and then come to this line. we dont return false in that case
			if(!mismatchOrdered && words[i].length() > words[i+1].length()) {  
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = {"hello","leetcode"};
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		VerifyAlienDictionary obj = new VerifyAlienDictionary();
		System.out.println(obj.isAlienSorted(words, order));

	}

}
