//Qs 17.4:  basic solution
package ctci;

public class MissingNumber {
	
	public int find (int []arr, int n) {
		int size = arr.length;
		for(int i=0 ; i<=n; i++) {
			int pos = 0;
			for(; pos<size; pos++) {
				if(isFound(i,pos, arr)) {
					break;
				}
			}
			if(pos == size)
				return i;
		}
		return -1;
	}
	
	private boolean isFound(int num, int pos, int[]arr) {
		for(int i=0; i<32; i++) {
			if((getBitAt(arr[pos], i) ^ getBitAt(num,i)) == 1) {
				return false;
			}
		}
		return true;
	}
	private int getBitAt(int num, int pos) {
		return 1 & (num >> pos);
	}

	public static void main(String[] args) {
		int []arr = {3,2,0,4,1};
		int n = 5;
		MissingNumber obj = new MissingNumber();
		System.out.println(obj.find(arr, n));

	}

}
