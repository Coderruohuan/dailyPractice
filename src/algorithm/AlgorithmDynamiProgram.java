package algorithm;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

/**
 * 动态规划合集
 * @author wwn
 * @date 2019年9月26日
 *
 */
public class AlgorithmDynamiProgram {
	
	@Test
	public  void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmDynamiProgram.class);
	}
	
	@Solve(title="70",content="0s/100%;33MB/72.47%")
	public int climbStairs() {
		int n=30;
		int[] cache=new int[n];
        return getStairs(cache, n);
	}
    
	//方法一：利用缓存递归
	private int getStairs(int[] cache,int n) {
		if(n<=1) {
			return 1;
		}
		if(cache[n-1]!=0) {
			return cache[n-1];
		}
		int result= getStairs(cache, n-1)+getStairs(cache, n-2);	
		cache[n-1]=result;
		return result;
	}
	
	/**
	 * 因为climbStairs(n)=climbStairs(n-1)+climbStairs(n-2);
	 * climbStairs(3)=climbStairs(2)+climbStairs(1);
	 * climbStairs(4)=climbStairs(3)+climbStairs(2);
	 * @return
	 */
	@Solve(title="70")
	public  int climbStairsTwo() {
		int n=45;
		if(n<=2) {
			return  n;
		}
		int first=1;
		int second=2;
		for(int i=3;i<n;i++) {
			int temp=first+second;
			first=second;
			second=temp;
		}	
		return second;
	}
	

}
