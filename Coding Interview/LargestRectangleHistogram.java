//Problem: Largest rectangle in a histogram
package misc;

import java.util.Stack;

public class LargestRectangleHistogram {
	/*
		Time complexity : O(n^2)
		Here for each bar, we find out the index of the leftmost bar which is smaller and also
		the index of the rightmost bar which is smaller than current bar.
		Then area = (height of this bar) * (lastHigherRight - lastHigherLeft)
		return the max area found so far.
	 * */
	public int largestRectSlow(int[]a) {
		int maxRectArea = Integer.MIN_VALUE;
		int currentArea = 0;
		for(int i=0; i<a.length; i++) {
			int lastHigherLeft = getLastHigherLeft(a,i);
			int lastHigherRight = getLastHigherRight(a,i);
			currentArea = (lastHigherRight-lastHigherLeft+1)*a[i];
			maxRectArea = Math.max(maxRectArea, currentArea);
		}
		return maxRectArea;
	}
	
	//find index of leftmost bar which is STRICTLY smaller
	private int getLastHigherLeft(int[]a, int index) {
		int x=index;
		while(x-1 >= 0 && a[x-1] >= a[index]) {
			x--;
		}
		return x;
	}
	
	//find index of rightmost bar which is STRICTLY smaller
	private int getLastHigherRight(int[]a, int index) {
		int x = index;
		while(x+1 < a.length && a[x+1] >= a[index] ) {
			x++;
		}
		return x;
	}

	public static void main(String[] args) {
		//int []a = {3,5,7,4,8,2,6,1};
		int []a = {4,5,7,4,8,2,6,1};
		LargestRectangleHistogram obj = new LargestRectangleHistogram();
		System.out.println(obj.largestRectBest(a));
	}
	
	/*
	 Optimized solution. 
	 Time Complexity : O(n)
	 Space Complexity : O(n)
	 
	 Here we use stack and for each element, we find the index of leftmost smaller bar and rightmost
	 smaller bar using the stack.
	 
	 * */
	public int largestRectFast(int[]a) {
		int[]leftPos = new int[a.length];
		int[]rightPos = new int[a.length];
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<a.length; i++) {
			//pop all bars not less in height than current bar
			while(!stack.isEmpty() && a[stack.peek()] >= a[i]) {
				stack.pop();
			}
			//now we got the leftMost index since we are at a bar which is smaller
			leftPos[i] = stack.isEmpty() ? 0 : stack.peek()+1;
			stack.push(i);
			
		}
		//clear the stack
		while (!stack.isEmpty()) {
			stack.pop();
		}
		
		//finding the indices of the rightmost bars
		for(int i=a.length-1; i>=0; i--) {
			while(!stack.isEmpty() &&  a[stack.peek()] >= a[i]) {
				stack.pop();
			}
			rightPos[i] = stack.isEmpty() ? a.length-1 : stack.peek()-1;
			stack.push(i);
		}
		int maxArea = 0;
		for(int i=0; i<a.length; i++) {
			maxArea = Math.max(maxArea, (rightPos[i]-leftPos[i] + 1) * a[i]);
		}
		return maxArea;
		
	}
	
	
	/*
	 More ptimized solution. This is soln is similar to above but here we do not use array to
	 store leftMost indices. We calculate area on the fly. 
	 Time Complexity : O(n)
	 Space Complexity : O(n)
	 
	 * */
	public int largestRectFastOptimized(int[]a) {
		int[]rightPos = new int[a.length];
		Stack<Integer> stack = new Stack<>();
		
		//calculate indices of the rightmost bars for each element 
		for(int i=a.length-1; i>=0; i--) {
			while(!stack.isEmpty() &&  a[stack.peek()] >= a[i]) {
				stack.pop();
			}
			rightPos[i] = stack.isEmpty() ? a.length-1 : stack.peek()-1;
			stack.push(i);
		}
		//clear stack
		while (!stack.isEmpty()) {
			stack.pop();
		}
		int maxArea = 0;
		int leftPos = -1;
		//now when we get the leftmost index for each element, we calculate the area since
		//we alreay have calculated the rightmost indices.
		for(int i=0; i<a.length; i++) {
			while(!stack.isEmpty() && a[stack.peek()] >= a[i]) {
				stack.pop();
			}
			leftPos = stack.isEmpty() ? 0 : stack.peek()+1;
			stack.push(i);
			maxArea = Math.max(maxArea, (rightPos[i]-leftPos + 1) * a[i]);
		}
		return maxArea;
		
	}
	
	
	/*
	 Best solution. 
	 Time Complexity : O(n)
	 Space Complexity : O(n)
	 
	 In our earlier solutions, when we are at a current bar, calculate the area with this bar height.
	 We had the leftmost and rightmost indices of bars smaller than this and we calculated area with those.
	 But in this solution, when we are processing a bar, we DO NOT calculate the area with this bar,
	 rather we calculate the with the bar which is getting popped off the stack.
	 So when we pop a bar, we know the the smaller rightmost bar (which is current bar). We also know 
	 the smaller leftmost bar which is there at the top of stack (0 for empty stack). 
	 So we can easily calculate the area. 
	 
	 * */
	public int largestRectBest(int[]a) {
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<a.length; i++) {
			while(!stack.isEmpty() && a[stack.peek()] >= a[i]) {
				int top = stack.pop();
				int rightPos = i-1;
				int leftPos = stack.isEmpty() ? 0 : stack.peek()+1;
				int currArea = a[top] * (rightPos - leftPos + 1);
				maxArea = Math.max(maxArea, currArea);
			}
			stack.push(i);
		}
		return maxArea;
	}
	
}

//Notes:
//https://kartikkukreja.wordpress.com/2016/10/22/problem-of-the-day-largest-rectangle-in-a-histogram/

