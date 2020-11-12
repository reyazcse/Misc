//https://leetcode.com/problems/k-closest-points-to-origin/
/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Note:

1 <= K <= points.length <= 10000
 * */

//Solution: We solve it using quickselect algorithm.
/*
Algorithm:
	Create a distances array whose each element has distance and index in the 'points' array
	Then perform quickselect on this distances array.
	Choose a pivot and place elements <= pivot element on the left and greater elements on the right of the pivot.
	
	Note: We can also do quickselect on the given points array instead of creating a new array.
	The idea is while partitioning we compare distances and swap the points accordingly
 * */
package leetcode;

public class KClosestPoints {
	private class DistanceIndex {
		public int distance;
		public int index;
		public DistanceIndex(int dis, int ind) {
			this.distance = dis;
			this.index = ind;
		}
	}
	public int[][] kClosest(int[][] points, int K) {
		DistanceIndex [] distances = new DistanceIndex [points.length];
		int i=0;
		for(int[] point : points) {
			int distance = point[0]*point[0] + point[1]*point[1];
			distances[i].distance = distance;
			distances[i].index = i;
			i++;
		}
		quickSelect(distances, 0, distances.length-1, K);
		
		int[][]result = new int[K][2];
		for(i=0; i<K; i++) {
			
			int ind = distances[i].index;
			result[i] = points[ind];
		}
		return result;
	}
	private void quickSelect(DistanceIndex[] distances, int l, int r, int k) {
		if(l >= r) {
			return;
		}
		int pivot = randomPartition(distances, l, r);
		int leftLength = pivot - l +1;
		if(leftLength > k) {
			quickSelect(distances, l, pivot-1, k);
		}else if (leftLength < k) {
			quickSelect(distances, pivot+1, r, k-leftLength);
		}
	}
	
	private int randomPartition(DistanceIndex[]nums ,int l, int r) {
		int pivot = l + (int)(Math.random()*(r-l+1));
		int x = nums[pivot].distance;					//pivot element
		swap(nums, pivot, r);
		int curr = l-1;
		for(int i=l; i<=r-1; i++) {
			if(nums[i].distance <= x) {
				curr++;
				swap(nums, curr, i);
			}
		}

		swap(nums, curr+1, r);
		return curr+1;
	}
	
	private void swap(DistanceIndex []nums, int i, int j) {
		DistanceIndex tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
