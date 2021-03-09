//https://www.geeksforgeeks.org/the-celebrity-problem/
/*
Input:
N = 3
M[][] = {{0 1 0},
         {0 0 0}, 
         {0 1 0}}
Output: 1
Explanation: 0th and 2nd person both
know 1. Therefore, 1 is the celebrity. 
 * */
package leetcode;

import java.util.Stack;

public class FindCelebrity {
	/*
	 * Think of each person as a vertex.
	 * If i knows j, then we draw a directed edge from vertex i to vertex j
	 * O(n^2) time . O(n) space
	 * */
	public int findCelebrity(int[][]M, int n) {
		if(n <= 1) {
			return -1;						//no celebrity
		}
		int[] inDegree = new int [n];
		int[] outDegree = new int [n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i != j && knows(M, i,j)) {
					outDegree[i]++;
					inDegree[j]++;
				}
			}
		}
		//inDegree of celebrity vertex should be n-1 and
		//outDegree of celebrity vertex should be 0
		for(int i=0; i<n; i++) {
			if(inDegree[i] == n-1 && outDegree[i] == 0) {
				return i;
			}
		}
		return -1;												//no celebrity
	}
	
	
	//O(n) time and O(n) space 
	int celebrity_Optimized(int M[][], int n){
        if(n <= 1) {
            return -1;
        }
    	Stack<Integer> stack = new Stack<>();
    	for(int i=0; i<n; i++) {
			stack.add(i);									//put all members in stack
		}
		while(stack.size() >= 2) {							//take two members out of stack and push only one of them
		    int b = stack.pop();
		    int a = stack.pop();
		    if(M[a][b] == 1) {              //a knows b
		        stack.add(b);
		    }else {
		        stack.add(a);
		    }
		}
		
		int c = stack.pop();		//candidate for celebrity remain in stack. 
		
		//check if it's truly celebrity
		for(int i=0; i<n; i++) {
		    if(i != c && (M[c][i] == 1 || M[i][c] != 1)) {       //if c knows i or i does not know c, then c is not celebrity
		        return -1;
		    }
		}
    	return c;
    	
    }
	
	
	/*
	 * Take two pointers one at start and the other at the end: a and b
	 * If a knows b, then a cannot be celebrity else b cannot be celebrity. Move a or b respectively till a<b
	 * Finally check if a is indeed celebrity or not
	 * 
	 * O(n) time and O(1) space
	 * */
	public int celebrity_MostOptimal2(int M[][], int n){
		if(n <= 1) {
			return -1;
		}
		
		int a = 0, b = n-1;
		while(a < b) {
			if(knows(M, a, b)) {		//a knows b then a cannot be celebrity
				a++;
			}else {
				b--;					//b cannot be celebrity
			}
		}
		
		//finally check if a is indeed celebrity
		for(int i=0; i<n; i++) {
			if(i != a && (knows(M, a, i) || !knows(M, i, a))) {	//if celebrity knows i OR i does not know celebrity
				return -1;													//then chosen candidate is not celebrity
			}
		}
		return a;
	}
	
	public int celebrity_MostOptimal(int M[][], int n){
		if(n <= 1) {
			return -1;
		}
		int celeb = 0;						//assume celebrity
		for(int i=1; i<n; i++) {
			if(knows(M, celeb, i)) {		
				celeb = i;					//new celebrity candidate is i
			}
		}
		
		//now check if the celebrity chosen is indeed celebrity
		for(int i=0; i<n; i++) {
			if(i != celeb && (knows(M, celeb, i) || !knows(M, i, celeb))) {	//if celebrity knows i OR i does not know celebrity
				return -1;													//then chosen candidate is not celebrity
			}
		}
		return celeb;
	}
	
	
	
	//helper method: returns true if i knows j
	private boolean knows(int[][]M, int i, int j) {
		if(M[i][j] == 1) {
			return true;
		}else 
			return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
