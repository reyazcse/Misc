//src: https://leetcode.com/problems/house-robber/
//Solution : for each item, we can have two choices at max: pick it OR do not pick it. Also we can pick only if prev item hasn't been picked
package misc;

public class HouseOfRobber {
	private int result = 0;
	public int func(int []arr) {
		utl(arr,0,false,0);
		return result;
	}
	public void utl(int []arr, int pos, boolean prevTaken, int val) {
		if (pos == arr.length) {
			result = Math.max(result, val);
			return;
		}
		utl(arr, pos+1, false, val);
		if(!prevTaken)
			utl(arr, pos+1, true, val+arr[pos]);  //take this only if prev item has not been picked
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//another way: very similar as above
	public int func2(int []arr) {
		return utl2(arr,0,false);
		
	}
	public int utl2(int []arr, int pos, boolean prevTaken) {
		if (pos == arr.length) {
			return 0;
		}
		int x=0,y=0,z=0;
		x = utl2(arr, pos+1, false);
		if (!prevTaken)
			y = arr[pos] + utl2(arr, pos+1, true);  //take this only if prev item has not been picked

		return Math.max(x, y);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//accepted solution
	public int rob(int []arr) {
		int n = arr.length;
		int [][]table = new int[2][n+1];
		robUtl(arr,0,0,table);
		return table[0][0];
	}
	public int robUtl(int[] arr, int pos, int prevTaken, int[][] table) {
		if (pos == arr.length)
			return 0;
		if(table[prevTaken][pos] != 0)
			return table[prevTaken][pos];
		int x = robUtl(arr, pos+1, 0, table);
		int y=0;
		if(prevTaken == 0 && arr[pos] != 0)                    //not including the second condition threw TLE on input: nums = {0,0,0,...}
			y = arr[pos] + robUtl(arr, pos+1, 1, table);
		table[prevTaken][pos] = Math.max(x, y);
		return table[prevTaken][pos];
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//below two solutions copied from others
	public int func3(int [] arr) {
		int n = arr.length;
		//dp[i] gives max value of robbery from 1st i houses
		int[] dp = new int [n+2];
		for (int i=0; i<n; i++) {
			dp[i+2] = dp[i+1];
			dp[i+2] = Math.max(dp[i+2], arr[i] + dp[i]);
		}
		return dp[n+1];
	}
	
	//we can get rid of dp array
	public int func4(int [] nums) {
		int n = nums.length;
		// cMax be the max robbery from the 1st i houses
	    // pMax till i-1, ppMax is till i-2
	    int ppMax, pMax, cMax;
	    ppMax = pMax = cMax = 0;

	    for (int i = 0; i < n; i++)
	    {
	        cMax = pMax;
	        cMax = Math.max(cMax, nums[i] + ppMax);

	        ppMax = pMax;
	        pMax = cMax;
	    }

	    return cMax;
	}
	
	public static void main(String [] args) {
		int []arr = {2,7,9,3,1};
		int []arr1 = {1,2,3,1};
		HouseOfRobber obj = new HouseOfRobber();
		System.out.println(obj.func(arr1));
		System.out.println(obj.func2(arr1));
		System.out.println(obj.rob(arr1));
		System.out.println(obj.func3(arr1));
		System.out.println(obj.func4(arr1));
	}

}
