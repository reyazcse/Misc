/*

Square root decomposition.
Complexity of update: O(1)
Complexity of query: O(sqrt(N)), where N is the size of array. Without sqrt decomposition, this is O(N).

 * */
package misc;

public class SqrtDecomposition {
	private int [] a;
	private int[] blockSums;
	private int blockSize;
	
	//complexity: O(N)
	public void init(int[] input) {
		int N = input.length;
		a = new int[input.length];
		for(int i=0; i<N; i++)
			a[i] = input[i];			//just copying the input
		
		blockSize = (int)Math.ceil(Math.sqrt(N));
		//since blockSize is sqrt, no of blocks = N/sqrt(N) = sqrt(N) = blockSize
		blockSums = new int[blockSize];
		
		//initialize blockSums. each block element is the sum of elements in the block.
		for(int i=0; i<N; i++) {
			blockSums[i/blockSize] += a[i]; 
		}
	}
	
	//O(1)
	public void update(int pos, int newVal) {
		int oldVal = a[pos];
		a[pos] = newVal;
		blockSums[pos/blockSize] += newVal - oldVal;
	}
	
	//O(sqrt(N))
	public int query(int left, int right) {
		int sum = 0;
		int startBlockIndex = left/blockSize;
		int endBlockIndex = right/blockSize;
		//get sum from the inner blocks
		for (int i=startBlockIndex+1; i< endBlockIndex; i++) {
			sum += blockSums[i];
		}
		int startIndex = left % blockSize;
		int endIndex = right % blockSize;
		//get sum from leftmost block
		for(int i=startIndex; i<blockSize; i++) {
			sum += a[startBlockIndex*blockSize + i];
		}
		//get sum from rightmost block
		for(int i=0; i<=endIndex; i++ ) {
			sum += a[endBlockIndex * blockSize + i];
		}
		
		return sum;
		
	}
	
	public static void main(String[] args) {
		int [] input = {3,2,8,5,4,3,0,6};
		SqrtDecomposition obj = new SqrtDecomposition();
		obj.init(input);
		System.out.println(obj.query(1, 6));
		obj.update(3, 10);
		System.out.println(obj.query(1, 6));

	}

}
