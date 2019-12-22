package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

/**
 * 
 * @author wwn
 * @date 2019年10月11日
 *
 */
public class AlgorithmTestFive {

	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmTestFive.class);
	}

	/**
	 * #575 分糖果<br>
	 * 
	 * 给一个偶数数组表示一组糖果，不同的数字代表不同的糖果，返回妹妹能获得的最大糖果种数（在最不理想的情况下） 思路要获得最大种数，那么尽可能选一颗的
	 * 
	 * @return
	 */
	@Solve(title = "575", content = "73ms/70.34%;45.8/89.09%", keywords = "哈希")
	public int distributeCandies() {
		int[] candies = { 1, 1, 2, 3, 4, 5 };
		Set<Integer> set = new HashSet<>();
		for (int candy : candies) {
			set.add(candy);
		}
		return set.size() > candies.length / 2 ? candies.length / 2 : set.size();
	}

	/**
	 * #5222分隔平衡符 一个字符串s中含有相同数量的'L'和'R',求可以分割的最大字符串的数量
	 * 我的思路是：记录平衡子串的开头字符，如果是一样的就加，不一样的就减；到0的时候说明一个子串结束，总的结果加1
	 * 看了其他的，和我的比for循环的if-else条件换一下，这样，否管什么开头只要到0的时候加一下就好了;相比少了一些变量空间
	 * 
	 * @return
	 */
	@Solve(title = "5222", content = "0ms/100%;33.9MB/100%")
	public int balancedStringSplit() {
		String s = "RLLLLRRRLR";
		int count = 0;
		char beginChar = 0;
		int sum = 0;
		for (char c : s.toCharArray()) {
			if (count == 0) {
				beginChar = c;
				count = 1;
				sum++;
			} else {
				if (c == beginChar) {
					count++;
				} else {
					count--;
				}
			}
		}
		return sum;
	}

	/**
	 * #5223 可以攻击国王的皇后 在一个8*8
	 * 「黑皇后」的行棋规定是：横、直、斜都可以走，步数不受限制，但是，不能越子行棋。返回可以攻击白国王的所有黑皇后的坐标
	 * 
	 * 我的思路是 ：首先不在横竖对角线上的排除，然后分别统计各个方向上距离最近的（总共8个方向吧）
	 * 
	 * @return
	 */
	@Solve(title = "5223", content = "1ms/100%;38.5/100%")
	public List<List<Integer>> queensAttacktheKing() {
		int[][] queens = {};
		int[] king = {};
		// 分别代表上，下，左，右，左上，右上，左下，右下
		int[] position = new int[] { -1, -1, -1, -1, -1, -1, -1, -1 };
		for (int i = 0; i < queens.length; i++) {
			// 对角线
			if (Math.abs(queens[i][0] - king[0]) == Math.abs(queens[i][1] - king[1])) {
				if (queens[i][0] < king[0] && queens[i][1] < king[1]) {
					// 左上角
					if (position[4] == -1
							|| Math.abs(queens[i][0] - king[0]) < Math.abs(queens[position[4]][0] - king[0])) {
						position[4] = i;
					}
				} else if (queens[i][0] < king[0] && queens[i][1] > king[1]) {
					if (position[5] == -1
							|| Math.abs(queens[i][0] - king[0]) < Math.abs(queens[position[5]][0] - king[0])) {
						position[5] = i;
					}
					// 右上角
				} else if (queens[i][0] > king[0] && queens[i][1] < king[1]) {
					// 左下角
					if (position[6] == -1
							|| Math.abs(queens[i][0] - king[0]) < Math.abs(queens[position[6]][0] - king[0])) {
						position[6] = i;
					}
				} else {
					// 右下角
					if (position[7] == -1
							|| Math.abs(queens[i][0] - king[0]) < Math.abs(queens[position[7]][0] - king[0])) {
						position[7] = i;
					}
				}
			} else if (queens[i][0] == king[0] || queens[i][1] == king[1]) {
				// 水平方向或者垂直方向
				if (queens[i][1] < king[1] && (position[0] == -1
						|| Math.abs(queens[i][1] - king[1]) < Math.abs(queens[position[0]][1] - king[1]))) {
					// 上
					position[0] = i;
				}
				if (queens[i][1] > king[1] && (position[1] == -1
						|| Math.abs(queens[i][1] - king[1]) < Math.abs(queens[position[1]][1] - king[1]))) {
					// 下
					position[1] = i;
				}
				if (queens[i][0] < king[0] && (position[2] == -1
						|| Math.abs(queens[i][0] - king[0]) < Math.abs(queens[position[2]][0] - king[0]))) {
					// 左
					position[2] = i;
				}
				if (queens[i][0] > king[0] && (position[3] == -1
						|| Math.abs(queens[i][0] - king[0]) < Math.abs(queens[position[3]][0] - king[0]))) {
					// 右
					position[3] = i;
				}
			}
		}
		List<List<Integer>> result = new ArrayList<>();
		for (int i : position) {
			if (i != -1) {
				List<Integer> list = new ArrayList<>();
				list.add(queens[i][0]);
				list.add(queens[i][1]);
				result.add(list);
			}
		}
		return result;
	}

	/**
	 * #1170-比较字符串最小字母出现频次
	 * 
	 * @return
	 */
	@Solve(title = "1170", content = "52 ms/47.68%;37.7/100%")
	public int[] numSmallerByFrequency() {
		String[] queries = { "bbb", "cc" };
		String[] words = { "a", "aa", "aaa", "aaaa" };
		int[] counts = new int[words.length];
		for (int k = 0; k < words.length; k++) {
			counts[k] = minLetterCount(words[k]);
		}
		Arrays.sort(counts);
		int[] result = new int[queries.length];
		for (int j = 0; j < queries.length; j++) {
			int count = minLetterCount(queries[j]);
			for (int k = 0; k < counts.length; k++) {
				if (counts[k] > count) {
					result[j] = counts.length - k;
					break;
				}
				result[j] = 0;
			}
		}
		return result;

	}

	private int minLetterCount(String word) {
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		if (chars.length < 2) {
			return chars.length;
		}
		int count = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] != chars[i - 1]) {
				break;
			}
			count++;
		}
		return count;
	}

	/**
	 * #5230 缀点成线 思路：第一想到的是算系数，但是系数除可能算不尽，所以只能算一个比例 y=kx+b==>(y1-y2)/(x1-x2)=k
	 * 
	 * @return
	 */
	@Solve(title = "5230", content = "0ms/100%;40.7mb;100%")
	public boolean checkStraightLine() {
		int[][] coordinates = { { -3, -2 }, { -1, -2 }, { 2, -2 }, { -2, -2 }, { 0, -2 } };
		if (coordinates.length < 2) {
			return false;
		}
		int diffX = coordinates[0][0] - coordinates[1][0];
		int diffY = coordinates[0][1] - coordinates[1][1];
		for (int i = 2; i < coordinates.length; i++) {
			if ((coordinates[0][0] - coordinates[i][0]) * diffY != (coordinates[0][1] - coordinates[i][1]) * diffX) {
				return false;
			}
		}
		return true;
	}

	/**
	 * #1078. Bigram 分词
	 * 
	 * @return
	 */
	@Solve(title = "1078")
	public String[] findOcurrences() {
		String text = "we will we will rock you";
		String first = "we";
		String second = "will";
		int begin = 0;
		int length = 0;
		List<String> list = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) != 32) {
				length++;
			} else {
				if (length > 0) {
					list.add(text.substring(begin, begin + length));
					begin = begin + length;
					length = 0;
				}
				begin++;
			}
			if (i == text.length() - 1 && length > 0) {
				list.add(text.substring(begin, begin + length));
			}
		}
		List<String> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(first) && i < list.size() - 2) {
				if (list.get(i + 1).equals(second)) {
					result.add(list.get(i + 2));
				}
			}
		}
		String[] resultArray = new String[result.size()];
		return result.toArray(resultArray);

	}

	/**
	 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符
	 * “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
	 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，
	 * 直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
	 * 返回车能够在一次移动中捕获到的卒的数量。
	 * 
	 * 有点读不懂题目，看评论说就是一个车往上下左右方向移动，一个方向至多只能捕获一个卒
	 * 
	 * #999 车的可用捕获量
	 * 
	 * @return
	 */
	@Solve(title = "999")
	public int numRookCaptures() {
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', 'p', '.', '.', '.', '.' },
				{ '.', '.', '.', 'R', '.', '.', '.', 'p' }, { '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', 'p', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.' } };
		int rookX = -1;
		int rookY = -1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'R') {
					rookX = i;
					rookY = j;
					break;
				}
			}
		}
		int count = 0;
		if (rookX >= 0 && rookY >= 0) {
			int u = rookX - 1;
			while (u >= 0) {
				if (board[u][rookY] == 'p') {
					count++;
					break;
				} else if (board[u][rookY] == 'B') {
					break;
				}
				u--;
			}
			int d = rookX + 1;
			while (d < board.length) {
				if (board[d][rookY] == 'p') {
					count++;
					break;
				} else if (board[d][rookY] == 'B') {
					break;
				}
				d++;
			}
			int l = rookY - 1;
			while (l >= 0) {
				if (board[rookX][l] == 'p') {
					count++;
					break;
				} else if (board[rookX][l] == 'B') {
					break;
				}
				l--;
			}
			int r = rookY + 1;
			while (r < board[0].length) {
				if (board[rookX][r] == 'p') {
					count++;
					break;
				} else if (board[rookX][r] == 'B') {
					break;
				}
				r++;
			}
		}
		return count;
	}

	/**
	 * 
	 * 
	 * #1025除数博弈 <br>
	 * 奇数*奇数=奇数 -----奇数*偶数=偶数 -----偶数*偶数=偶数----- 奇数-奇数=偶数----- 奇数-偶数=奇数---- 偶数-偶数=偶数
	 * 
	 * 由此可得 一个奇数的约数一定是奇数。在用这个奇数-约数=偶数 所以只要让对方一直处于奇数就好了，拿不到2
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	@Solve(title = "1025")
	public boolean divisorGame() {
		int N = 3;
		return N % 2 == 0 ? true : false;
	}

	/**
	 * #824 山羊拉丁文
	 * 
	 * @return
	 */
	@Solve(title = "824")
	public String toGoatLatin() {
		String S = "The quick brown fox jumped over the lazy dog";
		String[] strList = S.split("\\s+"); // 按空格切分，\s匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
		String aStr = "";
		for (int i = 0; i < strList.length; i++) {
			char startChar = strList[i].charAt(0);
			if (startChar == 'a' || startChar == 'e' || startChar == 'i' || startChar == 'o' || startChar == 'u'
					|| startChar == 'A' || startChar == 'E' || startChar == 'I' || startChar == 'O' || startChar == 'U'
					|| strList[i].length() < 2) {
				strList[i] = strList[i] + "ma";
			} else {
				strList[i] = strList[i].substring(1, strList[i].length()) + strList[i].charAt(0) + "ma";
			}

//			 for(int k=0;k<i+1;k++) {
//				 strList[i]=strList[i]+"a";
//			 }
			aStr += "a";
			strList[i] = strList[i] + aStr;// 看别人思路的，上面循环是我写的，执行时间比这个长
		}
		String result = String.join(" ", strList);
		return result;

	}

	/**
	 * #852 山脉数组的峰顶索引-- 其实应该就是求数组最大值得索引
	 * 
	 * @return
	 */
	@Solve(title = "852")
	public int peakIndexInMountainArray() {
		int[] A = { 1, 5, 6, 7 };
		int maxIndex = 0;
		for (int i = 1; i < A.length; i++) {
			if (A[i] > A[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	/**
	 * #841 钥匙和房间 深度优先搜索一般可以用方法的递归调用或栈来实现
	 * 
	 * @return
	 */
	@Solve(title = "841", content = "4ms/55.77%;40.7/58.43%", keywords = "图，深度优先搜索")
	public boolean canVisitAllRooms() {
		List<List<Integer>> rooms = new ArrayList<List<Integer>>();
		for (int i = 0; i < 3; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(i + 1);
			rooms.add(list);
		}
		rooms.add(new ArrayList<Integer>());
		int[] opened = new int[rooms.size()];// 访问过的标记为1，就不要在访问了
		Stack<Integer> stack = new Stack<>();
		if (rooms.size() < 1) {
			return true;
		}
		opened[0] = 1;
		stack.push(0);
		while (!stack.isEmpty()) {
			Integer key = stack.pop();
			if (opened[key] != 1) {
				for (Integer k : rooms.get(key)) {
					stack.add(k);
				}
				opened[key] = 1;
			}
		}
		for (int i : opened) {
			if (i != 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * #1137 第 N 个泰波那契数
	 * 
	 * @return
	 */
	@Solve(title = "1137")
	public int getTribonacci() {
		int n = 30;
		int current = 3;
		int first = 0;
		int second = 1;
		int third = 1;
		int r = 0;
		if (n < 3) {
			return n == 0 ? 0 : 1;
		} else {
			while (current <= n) {
				r = first + second + third;
				first = second;
				second = third;
				third = r;
				current++;
			}
		}
		return r;
	}

	/**
	 * #1252. 奇数值单元格的数目 我的思路是分别统计行和列添加的次数，然后每个点增加的次数是行+列总的添加次数。 奇数+偶数=奇数 统计行列的个数
	 * 
	 * @return
	 */
	@Solve(title = "1252", content = "1m/94.69%;35.7 MB/100.00%")
	public int oddCells() {
		int n = 2;
		int m = 2;
		int[][] indices = { { 0, 0 }, { 1, 1 } };
		int[] row = new int[n];
		int[] col = new int[m];
		for (int[] indice : indices) {
			row[indice[0]]++;
			col[indice[1]]++;
		}
		int totalCount = 0;
		for (int r : row) {
			for (int c : col) {
				// 判断奇偶性还可以用（r+c）&1==1 说明是奇数
				if ((r + c) % 2 != 0) {
					totalCount++;
				}
			}
		}
		return totalCount;
	}

	/**
	 * 383 赎金信 我用了哈希表，有人用128的数组好像更快一点
	 * 
	 * @return
	 */
	@Solve(title = "383", content = "29ms/31.05%;36.6/98.87%")
	public boolean canConstruct() {
		String ransomNote = "fffbfg";
		String magazine = "effjfggbffjdgbjjhhdegh";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : magazine.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (char c : ransomNote.toCharArray()) {
			if (!map.containsKey(c) || map.get(c) < 1) {
				return false;
			}
			map.put(c, map.get(c) - 1);
		}
		return true;
	}

	@Solve
	public List<List<Integer>> subsets() {
		int[] nums = {2,4,5,6,7};
		List<List<Integer>> result = new ArrayList<>();
		//长度                                                                                                                         
		for (int i = 1; i <=nums.length; i++) {
			subset(new ArrayList<Integer>(), 0, nums, i,result);		
		}
		result.add(new ArrayList<>());
		return result;
	}
	
	private void  subset(List<Integer> list,int formIndex,int[] nums,int length,List<List<Integer>> result) {
		if(list.size()==length) {
			return ;
		}
		for(int i=formIndex;i<nums.length;i++) {
			List<Integer> tempList=new ArrayList<Integer>();
			tempList.addAll(list);
			tempList.add(nums[i]);
			subset(tempList, i+1, nums, length,result);
			if(tempList.size()==length) {
				result.add(tempList);
			}	
		}
	}
	
	/**
	 * #1260 二维网络迁移(假设为n*m的二维)
	 * 对y进行k次移动得到 [i,y+k],如果y+k满一行，则x增加
	 * 所以x=x+(y+k)/m，另外x>n则从表头开始
	 * [(x+(y+k)/m)%n,(y+k)%m]
	 * @return
	 */
	@Solve(title="1260",content = "7ms/75.17%;45.4/100%")
     public List<List<Integer>> shiftGrid() {
    	 int[][] grid= {{1,2,3},{4,5,6},{7,8,9}}; int k=1;
    	 //前面完整n圈不管，只移动一圈的
    	 k=k%(grid.length*grid[0].length);
    	 int[][] result=new int[grid.length][grid[0].length];
    	 for(int i=0;i<grid.length;i++) {
    		 for(int j=0;j<grid[0].length;j++){
    			 int x=((j+k)/grid[0].length+i)%grid.length;//x增加的数
    	    	 int y=(j+k)%grid[0].length;//y上增加的数
                 result[x][y]=grid[i][j];
    		 }
    	 }
    	 List<List<Integer>> resultList=new ArrayList<>();
    	 for(int i=0;i<result.length;i++) {
    		  List<Integer> list=new ArrayList<>();
     		 for(int j=0;j<result[i].length;j++) {
     			list.add(result[i][j]);
    		 }
     		resultList.add(list);
    	 }
    	 return resultList;		 
	 }
	
	  /**
	   * #784 字母大小写全排列
	   * 
	   * 看别人的思路，大小写切换s[i]^=(1<<5)
	   * @param S
	   * @return
	   */
	@Solve(title="784",content="11ms/27.56%;36.9;98.11%")
	  public List<String> letterCasePermutation() {
		  String S="a1b2";
		  LinkedList<String> queue=new LinkedList<>();
		  for(int i=0;i<S.length();i++) {
			  String c=String.valueOf(S.charAt(i));
			  if((S.charAt(i)>='a'&&S.charAt(i)<='z')||(S.charAt(i)>='A'&&S.charAt(i)<='Z')) {
				  if(queue.size()==0) {
					  queue.add(c.toUpperCase());
					  queue.add(c.toLowerCase());
				  }else {
					  while(queue.size()>0&&queue.peek().length()<=i) {
						     String s=queue.poll();
							 queue.add(s+c.toUpperCase());
							 queue.add(s+c.toLowerCase());
					  }  
				  }  
			  }else {
				  if(queue.size()==0) {
					  queue.add(c);  
				  }else {
					  while(queue.size()>0&&queue.peek().length()<=i) {
						  queue.add(queue.poll()+c);
					  }   
				  }
			  }
		  }
		  return queue; 
	  }


}
