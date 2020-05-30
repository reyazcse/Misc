package ctci;

import java.util.ArrayList;
//Qs 17.4: optimal solution: O(n)
import java.util.List;

class BitInteger {
	private static int INTEGER_SIZE = Integer.SIZE;
	private boolean[]bits;
	public BitInteger () {
		bits = new boolean[INTEGER_SIZE];
		
	}
	public BitInteger(int number) {
		bits = new boolean[INTEGER_SIZE];
		for(int j=INTEGER_SIZE-1; j>=0; j--) {
			int x = number & 1;
			if (x == 1) {
				bits[j] = true;
			} else
				bits[j] = false;
			number = number >>1;
		}
		printBits(bits);
	}
	public void printBits(boolean[] bits) {
		if (bits == null || bits.length == 0) {
			System.out.println("empty");
			return;
		}
		System.out.println();
		for(int i=0; i<INTEGER_SIZE; i++) {
			if(bits[i])
				System.out.print("1");
			else System.out.print("0");
		}
	}
	int fetch(int pos) {
		if (pos <0 || pos >=INTEGER_SIZE)
			return 0;
		if(bits[pos])
			return 1;
		return 0;
		
	}
	

}


public class MissingNumber2 {
	int findMissingNumber(List<BitInteger> arr, int n ) {
		return utl(arr, n, Integer.SIZE-1);
		
	}
	int utl(List<BitInteger> arr, int n, int column) {
		int v = 0;
		if(column < 0)
			return v;
		List<BitInteger> zeroes = new ArrayList<>();
		List<BitInteger> ones = new ArrayList<>();
		for(BitInteger val : arr) {
			int bit = val.fetch(column);
			if(bit == 1)
				ones.add(val);
			else zeroes.add(val);
		}
		if(zeroes.size() <= ones.size()) {
			v = utl(zeroes, n, column-1);
			v = v << 1;
			v = v | 0;
			return v;
		} else {
			v = utl(ones, n, column-1);
			v = v << 1;
			v = v | 1;
			return v;
		}
	}
	
	public static void main(String [] args) {
		List<BitInteger> arr = new ArrayList<>();
		arr.add(new BitInteger(0));
		arr.add(new BitInteger(1));
		//arr.add(new BitInteger(2));
		arr.add(new BitInteger(3));
		arr.add(new BitInteger(4));
		arr.add(new BitInteger(5));
		arr.add(new BitInteger(6));
		arr.add(new BitInteger(7));
		arr.add(new BitInteger(8));
		arr.add(new BitInteger(9));
		arr.add(new BitInteger(10));
		arr.add(new BitInteger(11));
		arr.add(new BitInteger(12));
		arr.add(new BitInteger(13));
		
		MissingNumber2 obj = new MissingNumber2();
		System.out.println();
		System.out.println(obj.findMissingNumber(arr, arr.size()+1));
	}
	
	
}





