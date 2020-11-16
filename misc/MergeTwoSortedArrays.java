//Given two sorted arrays A and B, sort them in place without using any extra space

/*
The idea is we keep two pointers: one at A and other at B
Then we compare the elements A[i] and B[0].
Swap if element in B is greater. 
After swap B might become unsorted
We then insertion sort B.

Complexity: 
	Time: O(mn) where m = size of A and n = size of B
	Space: O(1)

Note: There is a better algorithm using gap method. But that is not intuitive. 
References: take u forward https://www.youtube.com/watch?v=hVl2b3bLzBw&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2

 * */
package misc;

public class MergeTwoSortedArrays {
	public void merge(int[]A, int[]B) {
		if(A == null || B == null || A.length == 0 || B.length == 0) {
			return;
		}
		int i=0;
		while(i<A.length) {
			if(A[i] <= B[0]) {
				i++;
			}else {				
				swap(A, B, i, 0); 	//swap element at ith in A to the first element in B.
				insertionSort(B);	//B might become unsorted after the swap, so we use insertion sort to sort it.
			}
		}
	}
	private void swap(int[]A, int[]B, int i, int j) {
		int tmp = A[i];
		A[i] = B[j];
		B[j] = tmp;
	}
	private void insertionSort(int[]arr) {
		int first  = arr[0];
		int k=1;
		for(; k<arr.length && arr[k] < first; k++) {
			arr[k-1] = arr[k];
		} 
		arr[k-1] = first;
	}
	public static void main(String[] args) {
		int [] A = {1, 10};
		int []B = {2,3,9};
		MergeTwoSortedArrays ob = new MergeTwoSortedArrays();
		ob.merge(A, B);
		System.out.println("[]A");
		for(int elt : A) {
			System.out.print(elt + " ");
		}
		System.out.println();
		System.out.println("[]B");
		for(int elt : B) {
			System.out.print(elt + " ");
		}
	}

}
