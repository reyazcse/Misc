/* 17.16 : The massuese

	Solution:
		Better solution given in the book where space complexity is O(1)
		
		Here we use this idea : 
		at each element, we can either pick or not pick
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
	
	
	//Another method where we get rid of storing flag in the cache
	//idea borrowed from box stacking problem of ctci 8.13
	//include or exclude idea used
	public int go_2(int[]arr) {
		int[]cache  = new int[arr.length];
		return utl_2(arr, 0, cache, false);
	}

	private int utl_2(int[]arr, int index, int[] cache, boolean prevTaken) {
		if(index >= arr.length) {
			return 0;
		}
		
		int with = 0;
		if(!prevTaken) {
			if(cache[index] > 0) {
				with = cache[index];
			}else {
				with = arr[index] + utl_2(arr, index+1, cache, true);
				cache[index] = with;
			}
		}
		int without = utl_2(arr, index+1, cache, false);
		
		return Math.max(with, without);
	}
	public static void main(String[] args) {
		//int [] arr = {30,15,60,75,45,15,15,45};
		int []arr1 = {75,105,120,75,90,135};
		Masseuse obj = new Masseuse();
		System.out.println(obj.go(arr1));
		System.out.println(obj.go_2(arr1));

	}

}
