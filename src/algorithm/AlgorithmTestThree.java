package algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;
/**
 * https://www.coursera.org/specializations/algorithms
 * @author wwn
 * @date 2019年9月25日
 *
 */
public class AlgorithmTestThree {

	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmTestThree.class);
	}

	/**
	 * #319 灯泡开关 初始有n个关闭的灯泡，第一轮打开所有的灯泡，第二轮，没两个关闭一次，第三轮每3个切换一次灯泡（开的关，关的开），找出n
	 * 轮后还有多少个亮的灯泡
	 * 基本想法是：对于每个质数来说肯定是关的，因为只在i轮关闭，比如7;对于合数来说比如8，除去本身他在2,4的时候被操作，那么正好是一关一开，加上本身的8，正好是关的。
	 * 所以对于n如果有偶数个不同的因数，那么就是关的，如果由奇数个因数就是开的，比如9，可以在3,9操作；总结就是2*2,3*3,4*4,5*5的那种数就是开的
	 * 
	 * @return
	 */
	@Solve(title = "319")
	public int bulbSwitch() {
		int n = 89;
		return (int) Math.floor(Math.sqrt(n));// 向下取整
	}

	/**
	 * #289生命游戏
	 * 
	 * m*n的格子板，在八个方向上（水平，垂直和对角线）上有如下规则： 1、活细胞如果周围活细胞小于2个或者大于三个，该系统就死亡；反之则存活
	 * 2、如果死细胞周围正好有三个活细胞，则复活 3、死亡和复活同一时间发生，也就是细胞发生后的新状态不影响旁边的
	 * 
	 * 我的思路基本就是遍历 ，不是原地算法； 别人的解题思路：先标记再还原 比如 1->1 标记为1 1->0 标记为-1 0->0标记为0 0->1标记为2
	 * 标记完一遍后，再将-1和2分别转化为0和1
	 * 
	 */
	@Solve(title="289",content = "97%,没有用到原地算法")
	public void gameOfLife() {
		int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		int[][] result = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				int count = 0;
				if (i < board.length - 1) {
					count += board[i + 1][j];
					if (j > 0) {
						count += board[i + 1][j - 1];
					}
					if (j < board[i].length - 1) {
						count += board[i + 1][j + 1];
					}
				}
				if (i > 0) {
					count += board[i - 1][j];
					if (j > 0) {
						count += board[i - 1][j - 1];
					}
					if (j < board[i].length - 1) {
						count += board[i - 1][j + 1];
					}
				}
				if (j > 0) {
					count += board[i][j - 1];
				}
				if (j < board[i].length - 1) {
					count += board[i][j + 1];
				}
				if ((board[i][j] == 1 && count >= 2 && count <= 3) || (board[i][j] == 0 && count == 3)) {
					result[i][j] = 1;
				} else {
					result[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < result.length; i++) {
			System.arraycopy(result[i], 0, board[i], 0, result[i].length);
		}
	}

	/**
	 * #781 森林中的兔子 给一个数组代表每个兔子回答森林中还有多少只兔子和他颜色一样，返回森林中最少的兔子数量
	 * 
	 * 思路：回答一样的可以看作为一种颜色，且回答的兔子数量<=答案+1，首先排个序，然后一样值且符合条件的可以看做是一种颜色 其他人思路，用哈希表
	 * 
	 * @return
	 */
	@Solve(title="781",content = "4ms/35.7;80.18%/85.54%", keywords = "哈希表")
	public int numRabbits() {
		int[] answers = { 2, 2, 2, 1, 2 };
		Arrays.sort(answers);
		int totalCount = 0;
		int kindCount = 0;
		for (int i = 0; i < answers.length; i++) {
			if (i > 0 && answers[i] == answers[i - 1] && kindCount < answers[i] + 1) {
				kindCount++;
			} else {
				kindCount = 1;
				totalCount += answers[i] + 1;
			}
		}
		return totalCount;
	}

	/**
	 * #789. 逃脱阻碍者 <tbody> 你在进行一个简化版的吃豆人游戏。你从 (0, 0) 点开始出发，你的目的地是 (target[0],
	 * target[1]) 。地图上有一些阻碍者，第 i 个阻碍者从 (ghosts[i][0], ghosts[i][1]) 出发。
	 * 每一回合，你和阻碍者们*可以*同时向东，西，南，北四个方向移动，每次可以移动到距离原位置1个单位的新位置。
	 * 如果你可以在任何阻碍者抓住你之前到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
	 * 当且仅当你有可能成功逃脱时，输出 True。 </tbody>
	 * 
	 * @return
	 */
	@Solve(title="789")
	public boolean escapeGhosts() {
		int[][] ghosts = { { 1, 9 }, { 2, -5 }, { 3, 8 }, { 9, 8 }, { -1, 3 } };
		int[] target = { 8, -10 };
		int total = Math.abs(target[0]) + Math.abs(target[1]);
		for (int[] g : ghosts) {
			if (total - (Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1])) >= 0) {
				return false;
			}
		}
		return true;
	}
   /**
    * #1189 气球的最大数量
    * @return
    */
	@Solve(title="1189")
	public int maxNumberOfBalloons() {
		String text = "nlaebolko";
		int totalCount=-1;
		for(int i=0;i<"balon".length();i++) {
			char c="balon".charAt(i);
			int index=-1;
			int currentCount=0;
			do {
				index=text.indexOf(c,index+1);
				if(index>-1) {
					currentCount++;
				}
			}while(index > -1);
			if(c=='l') {
				currentCount=currentCount/2;
			}
			totalCount=(totalCount>=0?Math.min(totalCount, currentCount):currentCount);
		}
          return totalCount;
	}
	
	
	/**
	 * #452. 用最少数量的箭引爆气球
	 * 移除重叠部分，最后留下互不重叠部分的区域个数就是气球个数
	 * 注意比如 [1,6],[2,8],[7,10] 去掉的应该是[2,8]
	 * @return
	 */
	@Solve(title="452")
	public int findMinArrowShots() {
		int[][] points= {{1,2},{2,3},{3,4},{4,5}};
	    //对左端的点进行排序
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0]-o1[0];
			}
		});
		int count=1;
		int begin=points[0][0];//从10开始射
		for(int i=1;i<points.length;i++) {
			if(points[i][1]<begin) {
				begin=points[i][0];
				count++;
			}
		}
		return count;	
	}
	
	
	/**
	 * #1089. 复写零
	 * 对原数组中的0进行复写，按原地算法，不要超过数组的长度
	 */
	@Solve(title="1089",content = "24|33.96%;37.7|100.00%,有人用ArrayList做的")
	 public void duplicateZeros() {
		 int[] arr=new int[] {1,2,0,0,3,4,0,4,5,6};
		 int needDuplicate=0;
		 for(int i=0;i<arr.length;i++) {
			 if(arr[i]==0) {
				 needDuplicate++;
			 }else if(needDuplicate>0){
				 for(int k=arr.length-1;k>=i;k--) {
					 if(k>=i+needDuplicate) {
						 arr[k]=arr[k-needDuplicate]; 
					 }else {
						 arr[k]=0;
					 }	 
				 }
				 i=i+needDuplicate-1;
				 needDuplicate=0;
			 }
		 }
		 for(int j=0;j<arr.length;j++) {
			 System.out.print(arr[j]+" ");
		 }
	 }
	
	/**
	 * #1184. 公交站间的距离
	 * 思路：整个可以分成两块，合起来就是总距离之和 
	 * @return
	 */
	@Solve(title="1184")
	public int distanceBetweenBusStops() {
		int[] distance= {1,2,3,4}; int start=3; int destination=0;
		int partOneDistance=0;
		int partTwoDistance=0;
		for(int i=0;i<distance.length;i++) {
			if(i>=Math.min(start, destination)&&i<Math.max(start, destination)) {
				partTwoDistance+=distance[i];
			}else {
				partOneDistance	+=distance[i];
			}
		}
		return Math.min(partOneDistance, partTwoDistance);
	}
	
	  /**
	   * 1160. 拼写单词
	   * @return
	   */
	  @Solve(title="1160",keywords = "哈希",content = "对字符进行统计保存至哈希表")
	  public int countCharacters() {
		  String[] words= {""};String chars="";
		  int length=0;
		  Map<Character,Integer> map=new HashMap<>();
		  for(char c:chars.toCharArray()) {
			  map.put(c,map.getOrDefault(c, 0)+1);
		  }
		  for(String word:words) {
			  if(word.length()<=chars.length()) {
				  char[] wordChar=word.toCharArray();
				  Arrays.sort(wordChar);
				  int count = 0;
				  for(int i=0;i<wordChar.length;i++) {
			           if(i==0) {
			        	   if(!map.containsKey(wordChar[i])) {
			        		   break;
			        	   }
			        	   count=1;
			           }else {
			        	   if(wordChar[i]==wordChar[i-1]) {
			        		   count++;
			        	   }else {
			        		   if(map.get(wordChar[i-1])<count||!map.containsKey(wordChar[i])) {
			        			   break;
			        		   }
			        		   count=1;	   
			        	   }
			           }
			           if(i==wordChar.length-1&&map.get(wordChar[i])>=count) {	
			        	   length+=word.length();
			           }
				  }
				  
			  }
		  }
		  return length;
	  }
	  
	  /**
	   * 
	   * @param A
	   * @return
	   */
	  @Solve
	  public boolean canThreePartsEqualSum() {
		  int[] A= {18,12,-18,18,-19,-1,10,10};
		  //先算出总共多少
		  int sum=0;
		  for(int i=0;i<A.length;i++) {
			  sum+=A[i];
		  }
		  
		  if(sum%3!=0) {
			  return false;
		  }
		  int partSum=sum/3;
		  int currentSum=0;
		  int partCount=0;
		  for(int i=0;i<A.length;i++) {
			  currentSum+=A[i];
			  if(currentSum==partSum) {
				  currentSum=0;
				  partCount++;
			  }
			  if(partCount==2) {
				  return true;
			  }
		  } 
		  return false;
	  }
	  /**
	   * 机器人
	   * @return
	   */
	  @Solve(title="LCP3")
	  public boolean robot() {
		  String command="URRURRR"; 
		  int[][] obstacles= {{7, 7}, {0, 5}, {2, 7}, {8, 6}, {8, 7}, {6, 5}, {4, 4}, {0, 3}, {3, 6}}; 
		  int x=4915; int y=1966;
		  char[] commands=command.toCharArray();
		  int onceXCount=0;
		  int onceYCount=0;
		  for(char c:commands) {
			  if(c=='U') {
				  onceYCount++;
			  }else {
				  onceXCount++;
			  }			  
		  }
		  if(!isReach(x, y, onceXCount, onceYCount, commands)) {
			  return false;
		  }
		  for(int[] o:obstacles) {
			  if(o[0]<=x&&o[1]<=y) {
			     if(isReach(o[0], o[1], onceXCount, onceYCount, commands)) {
			    	 return false;
			     }
			  }
		  }
		  return true;       
	   }
	  /**
	   * 是否可达
	   * @param x
	   * @param y
	   * @param onceXCount
	   * @param onceYCount
	   * @param commands
	   * @return
	   */
	  private boolean  isReach(int x,int y,int onceXCount,int onceYCount,char[] commands) {
		  int rounds=Math.min(x/onceXCount,y/onceYCount);
		  int preXCount=rounds*onceXCount;
		  int preYCount=rounds*onceYCount;
		  for(char c:commands) {
			  if(preXCount==x&&preYCount==y) {
				  return true;
			  } 
			  if(c=='U') {
				  preYCount++;
			  }else {
				  preXCount++;
			  }
			 
		  } 
		  return false;
	  }
}
