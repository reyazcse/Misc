//ctci: 17.19 Shortest supersequence
//Given a small[] array of distinct elements and a []big array which can contain duplicates. Find the shortest interval in []big which
//contains all the elements of []small
//using min heap
package ctci;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
class Range {
	public int start;
	public int end;
	public Range(int s, int e) {
		start = s;
		end = e;
	}
	public boolean isShorter(Range other) {
		return ((this.end - this.start) < (other.end - other.start));
	}
}
 
 * */
//HeapNode
class PQNode{
	public int index;     
	public int indexPosInList;   //index of value in the list
	public int listPos;   //index of the list
	public PQNode(int v, int vPos, int aPos) {
		index = v;
		indexPosInList = vPos;
		listPos = aPos;
	}
}

//HeapNode comparator
class PQNodeComp implements Comparator<PQNode>{

	@Override
	public int compare(PQNode n1, PQNode n2) {
		return n1.index - n2.index;
	}
	
}

public class ShortestSupersequence4 {
	public Range go(int[] small, int[] big) {
		if(small == null || big == null || small.length==0 || big.length == 0)    //base case
			return new Range(-1,-1);
		
		//lists of all the locations
		List<ArrayList<Integer>> posMatrix = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<small.length ;i++) {
			populate(posMatrix,big,small[i]);
		}
		if(posMatrix.size() != small.length)  //some element of small[] is not there in big[]
			return new Range(-1,-1);
		
		int maxIndex = getMaxIndex(posMatrix);  //get max index of the head values of the lists in posMatrix
		
		PriorityQueue<PQNode> pq = new PriorityQueue<>(small.length, new PQNodeComp());
		
		//init
		for(int i=0; i<small.length; i++) {
			PQNode node = new PQNode(posMatrix.get(i).get(0), 0, i);
			pq.add(node);
		}
		
		int bestStart = -1;
		int bestEnd = -1;
		while(true) {
			PQNode node = pq.remove();
			
			if(bestStart == -1 || maxIndex-node.index < bestEnd - bestStart) {
				bestStart = node.index;
				bestEnd = maxIndex;
			}
			//we reach end of a list
			if(node.indexPosInList == posMatrix.get(node.listPos).size()-1)
				break;
			//new node to be added from the same list
			PQNode newNode = new PQNode(posMatrix.get(node.listPos).get(node.indexPosInList+1), node.indexPosInList+1, node.listPos);
			
			//update maxIndex
			maxIndex = Math.max(maxIndex, newNode.index);
			pq.add(newNode);
		}
		return new Range(bestStart, bestEnd);
	}
	
	

	private void populate(List<ArrayList<Integer>> posMatrix, int[]big, int value) {
		ArrayList<Integer> array = new ArrayList<>();
		for(int i=0; i<big.length; i++) {
			if(big[i] == value)
				array.add(i);
		}
		if(array.size() != 0)        //if value  is not found in []big, then we do not add the list
			posMatrix.add(array);
		
	}
	
	private int getMaxIndex(List<ArrayList<Integer>> posMatrix) {
		int max = -1;
		for(ArrayList<Integer> list : posMatrix) {
			max = Math.max(list.get(0), max);
		}
		return max;
	}
	public static void main(String[] args) {
		int []small = {1,5,9};
		//int []big = {0,1,5,0,0,1,5,0,0,0,5,0};   //to check when one the numbers of small[] do not exist, for e.g. here 9 does not exist.
		//int []big = {0,1,5,0,0,1,5,9,0,0,5,9};
		int[]big = {7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7};
		ShortestSupersequence4 obj = new ShortestSupersequence4();
		Range result = obj.go(small, big);
		System.out.println("start " + result.start + "end " + result.end);

	}

}
