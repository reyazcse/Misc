//src: https://leetcode.com/problems/reverse-integer/
package leetcode;

public class ReverseInteger {
	public int reverse(int x) {
        boolean isNegative = x < 0 ? true: false;
        x = Math.abs(x);
        int reversed = 0;
        while( x > 0) {
        	int temp = reversed*10 + x%10;
        	if (temp/10 != reversed) return 0;    //checking overflow of int
        	reversed = temp;
        	x = x/10;
        } 
        if(isNegative)
            return -1 * reversed;
        return reversed;
    }

	public static void main(String[] args) {
		int x = 210;
		ReverseInteger obj = new ReverseInteger();
		System.out.println(obj.reverse(x));

	}

}
