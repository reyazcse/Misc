/* 17.16 : The massuese

	Solution:
		Better solution given in the book where space complexity is O(1)
		At each element, we can either pick or not pick
		Complexity: Time O(n) || Space O(n) 
 * */
package ctci;

import java.util.HashMap;

public class Masseuse {
	public int go(int []arr) {
		//int []cache = new int[arr.length];
		HashMap<String,Integer> cache = new HashMap<>(); 
		return utl(arr, 0, false, cache);
	}
	
	public int utl(int[]arr, int index, boolean prevTaken, HashMap<String,Integer> cache) {
		if (index == arr.length)
			return 0;
		//key is index + boolean value for e.g. "0true", "0false" etc
		String key = String.valueOf(index) + String.valueOf(prevTaken);
		//check if already calculated
		if(cache.containsKey(key))
			return cache.get(key);
		
		int res1=1;
		if(!prevTaken) {
			res1 = arr[index] + utl(arr, index+1, true, cache);
		}
		int res2 = utl(arr, index+1, false, cache);
		
		int res = Math.max(res1, res2);
		cache.put(key, res);
		return res;
	}

	public static void main(String[] args) {
		//int [] arr = {30,15,60,75,45,15,15,45};
		int []arr1 = {75,105,120,75,90,135};
		Masseuse obj = new Masseuse();
		System.out.println(obj.go(arr1));

	}

}
