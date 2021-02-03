/*
CtCI 17.8
People standing on top of each other. Person on top should be lighter and smaller than person below. Given heights and weights of 
persons, find the largest possible number of people in such tower.

 * */

/*
Solution: Without cache. O(2 ^ n)
We can use cache to optimize it.
 * */

package ctci;

import java.util.ArrayList;
import java.util.Arrays;

public class CircusTower {
	
	private static class HtWt implements Comparable<HtWt> {
		public int ht;
		public int wt;
		public HtWt(int h, int w) {
			ht= h;
			wt = w;
		}
		@Override
		public int compareTo(HtWt o) {
			if(this.ht == o.ht) {
				return this.wt - o.wt;
			}else {
				return this.ht - o.ht;
			}
		}
		
		public boolean isBefore(HtWt other) {
			return (this.ht < other.ht && this.wt < other.wt);
		
		}
		
	}
	
	ArrayList<HtWt> getLongest(HtWt[] array) {
		Arrays.sort(array);
		ArrayList<HtWt> sequence = new ArrayList<>();
		return getLongestUtl(array, sequence, 0);
	}
	
	private ArrayList<HtWt> getLongestUtl(HtWt[] array, ArrayList<HtWt> sequence, int index) {
		if(index >= array.length) {
			return sequence;
		}
		
		HtWt value = array[index];
		ArrayList<HtWt> with = null;
		
		if(canbePlaced(sequence, value)) {
			ArrayList<HtWt> sequenceWith = (ArrayList<HtWt>)sequence.clone();
			sequenceWith.add(value);
			with = getLongestUtl(array, sequenceWith, index+1);
		}
		ArrayList<HtWt>	withOut = getLongestUtl(array, sequence, index+1);
		
		if(with == null || with.size() < withOut.size()) {
			return withOut;
		}else {
			return with;
		}
		
	}
	
	private boolean canbePlaced(ArrayList<HtWt> sequence, HtWt value) {
		if(sequence.size() == 0) {
			return true;
		}
		HtWt lastValue = sequence.get(sequence.size()-1);
		if(lastValue.isBefore(value)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		CircusTower ob = new CircusTower();
		HtWt[] array = new HtWt[5];
		array[0] = new HtWt(11, 20);
		array[1] = new HtWt(7, 15);
		array[2] = new HtWt(12, 9);
		array[3] = new HtWt(8, 3);
		array[4] = new HtWt(5, 10);
		
		ArrayList<HtWt> result = ob.getLongest(array);
		
		for(HtWt item : result) {
			System.out.println(item.ht + "  " + item.wt);
		}
		
	}

}
