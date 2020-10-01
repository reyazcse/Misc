//https://leetcode.com/problems/the-skyline-problem/

/*
 * Given skyline of a cities, merge the buildings to get the skyline
 * Time complexity: (nlogn) as we are using TreeMap, insert, delete takes O(logn) for each op
 * Space complexity: O(n)
 * 
 * Reference: https://www.youtube.com/watch?v=GSBLe8cKu0s  //Tushar Roy
 *
 * */

/*
Solution:
	We extract the building point from each building. A building point is start/end of a building
	At first we sort the building points. Sorting is done based on start points and other params of buildings
	Then when we encounter a building, we put its height in a treemap.
	If we encounter a building, we remove its height from the treemap.
	In both the steps above, if max height in the map changes, we get a solution point.
	
Note:
	There are many cases to consider while building points sort:
	When two buildings start from same x coordinate, then we need to consider the higher building first
	When two buildings end at the same x coordinate, then we need to consider building with lower height first
	Else we need to consider the start building first if one is starting and other is ending at the same x coordinate
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;


public class SkyLine {
	public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> skyline = new ArrayList<>();
        BuildingPoint[]buildingPoints = getBuildingPoints(buildings);
        Arrays.sort(buildingPoints);
        //map stores the height and the count of buildings having this height
        TreeMap<Integer, Integer> tMap = new TreeMap<>();
        tMap.put(0, 1);
        int prevMax = 0;
        int currentMax = 0;			//initialize
        for(BuildingPoint buildingPoint : buildingPoints) {
        	if(buildingPoint.isStart) {
        		tMap.compute(buildingPoint.height, (key, value) -> {
        			if(value == null) {
        				return 1;
        			}else return value+1;
        		});
        	}else {
        		tMap.compute(buildingPoint.height, (key,value) ->{
        			if(value == null || value == 1)
        				return null;
        			else return value-1;
        		});
        	}
        	//current height after insertion or removal
        	currentMax  = tMap.lastKey();
        	//if current height is different from before(height of building changes on insertion or removal), then this building point is part of solution
        	if(currentMax != prevMax) {
        		skyline.add(Arrays.asList(buildingPoint.x, currentMax));
        		prevMax = currentMax;
        	}
        }
        return skyline;
        
    }
	
	//for each building, we get two BuildingPoint: one for start bar and other for end bar
	private BuildingPoint[] getBuildingPoints(int[][]buildings) {
		BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
		int i=0;
		for(int[] building : buildings) {
			buildingPoints[i] = new BuildingPoint(building[0],building[2],true);
			buildingPoints[i+1] = new BuildingPoint(building[1],building[2],false);
			i +=2;
		}
		return buildingPoints;
	}
	public static void main(String[] args) {
		int [][] buildings = new int[6][3];
		int[]building0 = {1,3,3};
		buildings[0] = building0;
		int[]building1 = {2,4,4}; 
		buildings[1] = building1;
		int[]building2 = {5,8,2}; 
		buildings[2] = building2;
		int[]building3 = {6,7,4}; 
		buildings[3] = building3;
		int[]building4 = {8,10,4}; 
		buildings[4] = building4;
		int[]building5 = {9,10,2}; 
		buildings[5] = building5;
		List<List<Integer>> skyline = new SkyLine().getSkyline(buildings);
		System.out.println(skyline);


	}

}

class BuildingPoint implements Comparable<BuildingPoint>{
	public int x;
	public int height;
	public boolean isStart;
	
	public BuildingPoint(int x, int height, boolean isStart) {
		this.x = x;
		this.height = height;
		this.isStart = isStart;
	}
	@Override
	public int compareTo(BuildingPoint o) {
		if (this.x != o.x) {
			return x - o.x;
		}else {
			/*
			 * if both are start, take building with less height first, i.e. asc order
			 * if both are not start, then take the building with low height first, i.e. desc order
			 * else always take the start first
			 * */
			if(this.isStart && o.isStart) {
				return o.height - this.height;
			}else if(!this.isStart && !o.isStart) {
				return this.height - o.height;
			}else {
				
				int x = this.isStart ? -1 :  1;	//always taking start first
				return x;
			}
		}
	}
	
}

//-----Data to test the sorting of buildings. BuildinPoints are in random order---------//		
//BuildingPoint b1 = new BuildingPoint(7,4,false);
//BuildingPoint b2 = new BuildingPoint(7,3,false);
//BuildingPoint b3 = new BuildingPoint(9,4,true);
//BuildingPoint b4 = new BuildingPoint(1,3,true);
//BuildingPoint b5 = new BuildingPoint(1,4,true);
//BuildingPoint b6 = new BuildingPoint(2,4,false);
//BuildingPoint b7 = new BuildingPoint(3,3,false);
//BuildingPoint b8 = new BuildingPoint(10,4,false);
//BuildingPoint b9 = new BuildingPoint(8,3,true);
//BuildingPoint b10 = new BuildingPoint(5,4,true);
//BuildingPoint b11 = new BuildingPoint(6,3,true);
//BuildingPoint b12 = new BuildingPoint(9,3,false);
//
//BuildingPoint[] buildingPoints = new BuildingPoint[12];
//buildingPoints[0] = b1;
//buildingPoints[1] = b2;
//buildingPoints[2] = b3;
//buildingPoints[3] = b4;
//buildingPoints[4] = b5;
//buildingPoints[5] = b6;
//buildingPoints[6] = b7;
//buildingPoints[7] = b8;
//buildingPoints[8] = b9;
//buildingPoints[9] = b10;
//buildingPoints[10] = b11;
//buildingPoints[11] = b12;
//Arrays.sort(buildingPoints);
//System.out.println(buildingPoints);
