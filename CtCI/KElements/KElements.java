//CtCI: 17.14 program to find the k smallest elements in an array
/*
 	Assume elements in the array are unique
	Input: arr[] = {2,1,5,4,8,7,3,6} ; k = 3
	Output: {2,1,3}
	
	Solution:
		We solve it using selection sort algorithm in expected O(n).
		It can also be solved using a max heap of size k.
 * */
package ctci;

import java.util.Random;

public class KElements {
	int [] findKElements (int[]array, int k) {
		if (k <=0 || k > array.length)
			return null;
		int threshold = getRank(array, k, 0, array.length-1);
		int [ ] smallest = new int[k];
		int count=0;
		for(int a : array) {
			if (a <= threshold) {
				smallest[count++] = a;
			}
		}
		return smallest;
	}
	
	//it returns the (rank)th element of the array
	public int getRank(int []arr, int rank, int start, int end) {
		int pivot = arr[getRandom(start, end)];
		int partitionIndex = partition(arr,pivot, start, end);
		int leftSize = partitionIndex-start +1;
		if(leftSize == rank) {
			return getMax(arr,start,partitionIndex);
		} else if (rank < leftSize)
			return getRank(arr, rank, start, partitionIndex);			 //recurse left	
		else return getRank(arr,rank-leftSize, partitionIndex+1, end);   //recurse right
		
		
	}
	
	//returns max value in arr from start index to end index
	private int getMax(int []arr, int start , int end) {
		int max = Integer.MIN_VALUE;
		for(int i=start; i<=end; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}
	
	//get random int from min to max, both inclusive
	private int getRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max-min+1) + min;
	}
	
	//partition elements such that elt <= pivot are to the left and elt > pivot are to the right side
	//this is done as per DNF algo: lo..hi is the range we do not know; 0..lo-1 contains element <= pivot
	//hi+1....end contains element > pivot
	private int partition(int[] arr, int pivot, int start, int end) {
		int lo = start;
		int hi = end;
		while(lo <= hi) {
			if(arr[lo] <= pivot) {
				lo++;
			}else {
				swap(arr, lo, hi);
				hi--;
			}
		}
		return lo-1;
	}

	private void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	public static void main(String[] args) {
		int []arr = {2,1,5,4,8,7,3,6};
		KElements obj = new KElements();
		int []result = obj.findKElements(arr, 1);
		for (int a : result) {
			System.out.print(a + "  ");
		}

	}

}
