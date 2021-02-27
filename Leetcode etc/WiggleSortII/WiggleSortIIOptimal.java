//https://leetcode.com/problems/wiggle-sort-ii/
/*
	The idea is same mentioned in WiggleSortII.
	But here we do not sort the array. Instead we find the median using quick select in O(n) time.
	And once we find the median, we have small elements in the left half and big elements in other half.
	After this we place small element at alternating positions, and we fill other positions with big elements so 
	that we get a wave: small big small OR big small big 
	
	Here in below solution, we put small first.
	So we can have small, big, small, big  (if even no of elements)  => median should be at 2nd element 
	OR small, big, small, big, small (if 5 elements)				=> median should be at 3rd element
	
	Complexity: O(n) time and O(n) space.
Note: Even though the complexity is less than WiggleSortII solution, but runtime is much more
References: https://www.youtube.com/watch?v=qV-QnOGEq7I
 * */
package leetcode;

public class WiggleSortIIOptimal {
	public void wiggleSort(int[] nums) {
		if(nums == null || nums.length == 0) {
			return;
		}
		
		int n = nums.length;
		int k = (n+1)/2;					//see above explanation to understand why we take n+1.
		
		int[] tmpArray = nums.clone();
		int medianIndex = quickSelect(tmpArray, 0, n-1, k);
		
		//medianIndex divides tmpArray into parts such that elements on the left of tmpArray[medianIndex]
		//are <= tmpArray[medianIndex] and elements on right part are greater
		
		int smallIdx = medianIndex;
		int bigIdx = n-1;
		//put small elements at even positions and big elements at odd positions
		for(int i=0; i<n; i++) {
			if(i % 2 == 0) {
				nums[i] = tmpArray[smallIdx--];
			}else {
				nums[i] = tmpArray[bigIdx--];
			}
		}
		return;
	}
	
	private int quickSelect(int[] nums, int l, int r, int k) {
		int pivot = randomPartition(nums, l, r);
		
		if(pivot - l + 1 == k) {
			return pivot;
		}else if (pivot - l + 1 > k) {
			return quickSelect(nums, l, pivot-1, k);
		}else {
			return quickSelect(nums, pivot+1, r, k - (pivot-l+1));
		}
	}
	
	private int randomPartition(int[] nums, int l, int r) {
		int range = r-l+1;
		int randomIdx = l + (int)(Math.random() * range);
		int pivotElt = nums[randomIdx];
		swap(nums, randomIdx, r);								//move pivot element to the end
		
		int i = l-1;
		for(int j=l; j<r; j++) {
			if(nums[j] <= pivotElt) {
				i++;
				swap(nums, i, j);
			}
		}
		//swap pivotElt with element at i
		i++;
		swap(nums, i, r);
		
		return i;
	}
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	public static void main(String[] args) {
		int [] nums = {2,3,3,2,2,2,1,1};
		WiggleSortIIOptimal ob = new WiggleSortIIOptimal();
		ob.wiggleSort(nums);
	}
}
