//src: https://leetcode.com/problems/plus-one/
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PlusOne {
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		int carry = 0;
		List<Integer> result = new ArrayList<>();
		int sum = digits[n-1] + 1;               
		
		if(sum > 9) {
			carry = sum/10;
			sum = sum%10;
		}
		result.add(sum);
		for(int i = n-2; i>=0; i--) {
			sum = digits[i] + carry;
			result.add(sum % 10);
			carry = sum/10;
		}
		//if carry is there (it will be single digit only if present since all digits are single in the input), we add a extra node
		if(carry > 0) {
			result.add(carry);
		}
		//convert result to array starting from end of 'result' to 'start'
		int[] ans = new int[result.size()];
		int pos = 0;
		for(int i=result.size()-1; i>=0; i--) {
			ans[pos] = result.get(i);
			pos++;
		}
		return ans;
    }
	
	//ANOTHER SOLUTION:
	
	public int[] plusOne2(int[] digits) {
		for (int i=digits.length-1; i>=0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;			//return here only as no further processing needed
			}
			digits[i] = 0;				//since we will have a carry as we get 9
		}
		//since we reach here, it means we have a carry as all digits are 9
		int[]result = new int[digits.length+1];
		result[0] = 1;
		return result;  //all other values except first will be 0
	}
	
	public static void main(String[] args) {
		int [] digits = {9,9,9};
		PlusOne obj = new PlusOne();
		int []ans = obj.plusOne2(digits);

	}

}
