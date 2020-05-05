//src: https://www.spoj.com/problems/AGGRCOW/
//explanation: https://www.youtube.com/watch?v=SiE1XFhYoaA
/*
	Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls. The stalls are located along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).

	His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, FJ wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?
	Input
		t – the number of test cases, then t test cases follows.
 		* Line 1: Two space-separated integers: N and C
 		* Lines 2..N+1: Line i+1 contains an integer stall location, xi
	Output
	For each test case output one integer: the largest minimum distance.
	
	Input:
		1
		5 3
		1
		2
		8
		4
		9
	Output:
		3
 * */

package misc;

import java.util.Arrays;
//O(N * (high - low)) where low is lowest value in arr.
public class AggresiveCows {
	int arrangeCows(int [] stalls, int cows) {
		int n = stalls.length;
		Arrays.sort(stalls);
		int maxGap = stalls[n-1] - stalls[0];
		int minGap = stalls[1] - stalls[0];
		
		//for each gap, see if u can place the cows.  TO return the max gap, we go in desc order
		for(int gap = maxGap; gap>= minGap; gap--) {
			//place the first cow at pos 0 always
			int prevStall = 0;
			int cowsPlaced = 1;
			
			//now find a stall such that gap is greater than or equal to current gap
			for(int j=prevStall+1; j < n; j++) {
				if(stalls[j] - stalls[prevStall] >= gap) {
					prevStall = j;				//put cow in stall
					cowsPlaced++;
					if (cowsPlaced == cows)    //if total cows are placed, we get an answer
						return gap;
				}
			}
		}
		return -1;		//since num of cows <= N, we wont reach here.
	}
	
	//optimal solution: O (N * log (high-lo))
	
	public int arrangeCowsOptimal (int [] stalls, int cows) {
		Arrays.sort(stalls);
		int n = stalls.length;
		int lo = 1;				//min gap  = 1
		int hi = stalls[n-1];	//max gap = greatest element
		while(lo < hi) {
			int mid = lo + (hi-lo)/2;
			int prev = 0; int cnt = 1;
			for(int i=prev; i<n; i++) {
				if (stalls[i] - stalls[prev] >= mid) {
					prev = i;
					cnt++;
				}
			}
			if(cnt < cows) {		//if we cant put cows for this gap=mid, we cant put for gap > mid, hence we do mid-1
				hi = mid - 1;
			}else {					//if we are able to put cows for gap=mid, let's maximise gap. So we go onwards mid and inclding mid
				lo = mid;
			}
		}
		return lo;     //lo == hi
	}

	public static void main(String[] args) {
		AggresiveCows obj = new AggresiveCows();
		//int []stalls = {1,2,8,4,9,12,16};
		int []stalls = {1,2,4,8,9,12,16};
		//int cows = 4;
		int cows = 4;
		System.out.println(obj.arrangeCows(stalls, cows));
		System.out.println(obj.arrangeCowsOptimal(stalls, cows));

	}

}
