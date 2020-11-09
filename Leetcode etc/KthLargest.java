//https://leetcode.com/problems/kth-largest-element-in-an-array/
/*
 * 
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 * */

/*
The idea is we divide the array into two parts: part1 and part2
part2 contains elements >= pivot element.
When we find that size of part2 = k, it means the pivot element is the answer.

Worst case is O(n^2) : This happens when the pivot is chosen such that one partition contains 1 element while 
other partition contains rest of the elements.

Expected case: O(n).


 * */
package leetcode;

public class KthLargest {
	public int findKthLargest(int[] nums, int k) {
		return findKthLargest(nums, 0, nums.length-1, k);
	}
	
	private int findKthLargest(int[] nums, int l, int r, int k) {
		//get index of partition which is pivot
		int pivot = randomPartition(nums, l, r, k); 
		if(r-pivot+1 == k) {			// right part has k elements and they are >= nums[pivot]
			return nums[pivot];
		}else if(r-pivot+1 > k) {		// right part has more than k elements
			return findKthLargest(nums, pivot+1, r, k);
		}else {							//recurse in the left part with updated k. 
			return findKthLargest(nums, l, pivot-1, k-(r-pivot+1));
		}
	}
	
	//randomly find an index b/w l and r inclusive
	//then partitions the nums into left and right parts. 
	//right part has elements >= pivot element
	private int randomPartition(int[] nums, int l, int r, int k) {
		int n = r-l+1;
		int random = (int)(Math.random()*n);
		random = l+random;
		int x = nums[random];		//element at pivot
		swap(nums, l, random);		//put element at pivot at the leftmost side
		int curr = r+1;
		for(int i=r; i>=l+1; i--) {
			if(nums[i] >= x) {
				curr--;
				swap(nums, i, curr);
			}
		}
		swap(nums, l, curr-1);	//swap pivot element with an element which is less than pivot element
		return curr-1;			// return index of pivot element. it is also start of the right part of partition
	}
	
	private void swap(int []nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	public static void main(String[] args) {
		int [] nums = {3,4,1,6,5,7,2};
		int k = 2;
		KthLargest ob = new KthLargest();
		System.out.println(ob.findKthLargest(nums, k));

	}

}
