package algorithm;

import java.util.HashMap;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

public class AlgorithmTestSix {
	
	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmTestSix.class);
	}
	
	/**
	 *#1103  分糖果II
	 * @return
	 */
	@Solve(title="1103",content = "1ms/97%;33.6/100%")
	public int[] distributeCandies() {
		int candies=10; int num_people=3;
		int[] distributeCandies=new int[num_people];
		int current=1;
		while(candies>0) {
			for(int i=0;i<num_people;i++) {
				if(current>=candies) {
					current=candies;
				}
				distributeCandies[i]+=current;
				candies-=current;
				if(candies<=0) {
					break;
				}
				current++;
			}
		}
		return distributeCandies;       
	}
	
	/**
	 * #5271访问所有点的最小时间，我的思路是达到最小差坐标后然后加上剩余路程。
	 * 其他人思路直接加上最大坐标差
	 * @return
	 */
	@Solve(title="5271",content = "2ms/100%;40.5MB/100%")
    public int minTimeToVisitAllPoints() {
    	int[][] points= {{}};
    	int resultTime=0;
    	for(int i=0;i<points.length-1;i++) {  		
    		int xDiff=Math.abs(points[i][0]-points[i+1][0]);
    		int yDiff=Math.abs(points[i][1]-points[i+1][1]);
    		resultTime+=Math.abs(points[i][0]-points[i+1][0])+Math.abs(points[i][1]-points[i+1][1]);
    		if(points[i][0]!=points[i+1][0]&&points[i][1]!=points[i+1][1]) {
    			resultTime-=Math.min(xDiff, yDiff);
    	    }
    	}
    	
    	return resultTime;     
    }
	
	/**
	 * #1267，我的思路是如果该点为1，就遍历它的x行和y列，有1总结果数加1，且该对应的标为2，感觉写的for有点多= =
	 * 
	 * @return
	 */
	@Solve(title="1267",content="6ms/100%;54.9MB;100%")
	 public int countServers() {
		 int[][] grid= {{1,0},{1,1}};
		 int count=0;
		 for(int i=0;i<grid.length;i++) {
			 for(int j=0;j<grid[i].length;j++) {
				 if(grid[i][j]==1) {
					 //遍历同行的
					 for(int k=0;k<grid[i].length;k++) {
						 if(grid[i][k]>=1&&k!=j) {
							 if(grid[i][k]==1) {
								 grid[i][k]=2;
								 count++; 
							 }
							 grid[i][j]=2;
						 }
					 }
					 //遍历同列的
					 for(int k=0;k<grid.length;k++) {
						 if(grid[k][j]>=1&&k!=i) {
							 if(grid[k][j]==1) {
								 grid[k][j]=2;
								 count++;
							 }
							 grid[i][j]=2;
						 }
					 }
					 if(grid[i][j]==2) {
						 count++;
					 }
				 }
			 }
		 }
		 return count;
	 }

}
