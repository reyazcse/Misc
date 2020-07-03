//CtCI: 17.14 program to find the k smallest elements in an array
/*
 	Assume elements in the array are NOT UNIQUE
	Input: arr[] = {2,1,1,5,4,8,7,1,3,6} ; k = 4
	Output: {1,1,1,2}
	
	Solution:
		We solve it using selection sort algorithm in expected O(n).

 * */
package ctci;

import java.util.Random;

public class KElementsDuplicate {
	class PartitionResult {
		int leftSize;
		int midSize;
		public PartitionResult (int ls, int ms) {
			this.leftSize = ls;
			this.midSize = ms;
		}
		public PartitionResult() {}
	}
	public int[] findKSmallest(int []arr, int k) {
		if (k <= 0 || k > arr.length)
			return null;
		int threshold = rank(arr,k);
		int[]smallest = new int[k];
		int count=0;
		for(int a : arr) {
			if (a < threshold)
				smallest[count++] = a;
		}

		if (count < k) {
			for(int a : arr) {
				if (a == threshold)
					smallest[count++] = a;
				if (count == k)
					break;
			}
		}
		return smallest;
	}
	
	public int rank(int []arr, int rank) {
		return rank(arr,rank, 0, arr.length-1);
		
	}
	public int rank(int []arr, int rank, int start, int end) {
		int pivot  = arr[getRandom(start, end)];
		PartitionResult partitionRes = partition(arr, pivot, start, end);
		int leftSize = partitionRes.leftSize;
		int midSize = partitionRes.midSize;
		if(rank < leftSize)
			return rank(arr, rank, start, start+leftSize - 1);
		if (rank < (leftSize + midSize))
			return pivot;
		else return rank(arr,rank - (leftSize + midSize), start + leftSize + midSize, end);
	}
	
	//partition in three parts: left portion elements are < then pivot, middle portion elements
	//are equal to the pivot and right portion elements are greater than pivot.
	public PartitionResult partition(int[]arr, int pivot, int start, int end) {
		int lo = start;
		int mid = start;
		int hi = end;
		while (mid <= hi) {
			if(arr[mid] < pivot) {
				swap(arr,lo,mid);
				lo++;
				mid++;
			} else if (arr[mid] == pivot) {
				mid++;
			} else {
				swap(arr,mid, hi);
				hi--;
			}
		}
		
		PartitionResult res = new PartitionResult();
		res.leftSize = lo-start;
		res.midSize = mid-lo;
		return res;
	}
	
	private void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	private int getRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max-min +1)+min;
	}
	public static void main(String[] args) {
		int []arr = {2,1,5,4,8,7,3,6,1,1};
		KElementsDuplicate obj = new KElementsDuplicate();
		int []result = obj.findKSmallest(arr, 4);
		for (int a : result) {
			System.out.print(a + "  ");
		}

	}

}
