//Continuous Median
package ctci;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ContinuousMedian {
	public double[] contMedian(int[] array) {
		if(array == null || array.length == 0)
			return null;
		PriorityQueue<Integer> lowers = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return b-a;
			}
		});
		
		PriorityQueue<Integer> highers = new PriorityQueue<>();
		double[] median = new double[array.length];
		for(int i=0; i<array.length; i++) {
			int num = array[i];
			addNumber(num, lowers, highers);
			rebalance(lowers, highers);
			median[i] = getMedian(lowers, highers);
		}
		return median;
	}
	private void addNumber(int num, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
		if(lowers.size() == 0 || num <= lowers.peek()) {
			lowers.add(num);
		}else {
			highers.add(num);
		}
	}
	private void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
		PriorityQueue<Integer> smaller  = lowers.size() <= highers.size() ? lowers:highers;
		PriorityQueue<Integer> bigger  = lowers.size() <= highers.size() ? highers:lowers;
		if(bigger.size() - smaller.size() >=2) {
			smaller.add(bigger.poll());
		}
	}
	private double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
		PriorityQueue<Integer> smaller  = lowers.size() <= highers.size() ? lowers:highers;
		PriorityQueue<Integer> bigger  = lowers.size() <= highers.size() ? highers:lowers;
		if(smaller.size() == bigger.size()) {
			return ((double)smaller.peek()+bigger.peek())/2;
		}
		else return bigger.peek();
	}
	public static void main(String[] args) {
		int []array = {3,1,2,4,6,5};
		ContinuousMedian obj = new ContinuousMedian();
		double[] median = obj.contMedian(array);
		for(double d : median) {
			System.out.println(d + "  ");
		}

	}

}
