package algorithm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

import algorithm.model.ListNode;
import algorithm.util.Unsolve;

public class AlgorithmUnresolved {

	@Test
	public void maxSubArray() {

		int nums[] = { -2, 1 };
		int max = nums[0];
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum = sum + nums[j];
				max = Math.max(max, sum);
			}
		}
		System.out.println(max);
	}

	@Test
	public void isAnagram() {
		String s = "rat";
		String t = "cat";
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		Arrays.sort(sChars);
		Arrays.sort(tChars);
		if (sChars.length != tChars.length) {
			// return false;
		} else {
			for (int i = 0; i < sChars.length; i++) {
				if (sChars[i] - tChars[i] != 0) {
					// return false;
				}
			}
		}
	}



	/**
	 * #21 merge two sorted Lists
	 */
	@Test
	public void mergeTwoLists() {
		ListNode l1 = initListNode(new int[] { 1, 2, 4 });
		ListNode l2 = initListNode(new int[] { 1, 3, 4 });
		ListNode result = null;
		ListNode temp = null;
		while (l1 != null || l2 != null) {
			ListNode current = null;
			if (l1 == null) {
				current = l2;
				l2 = null;
			} else if (l2 == null) {
				current = l1;
				l1 = null;
			} else {
				if (l1.val <= l2.val) {
					current = new ListNode(l1.val);
					l1 = l1.next;
				} else {
					current = new ListNode(l2.val);
					l2 = l2.next;
				}
			}
			if (result == null) {
				result = current;
				temp = result;
			} else {

				temp.next = current;
				temp = temp.next;

			}
		}
		System.out.println(result);
	}

	private ListNode initListNode(int[] array) {
		ListNode l1 = null;
		for (int i = array.length - 1; i >= 0; i--) {
			if (i == array.length - 1) {
				l1 = new ListNode(array[i]);
			} else {
				ListNode current = new ListNode(array[i]);
				current.next = l1;
				l1 = current;
			}
		}
		return l1;
	}

	@Test
	public void relativeSortArray() {
		int[] arr1 = { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
		int[] arr2 = { 2, 1, 4, 3, 9, 6 };
		int[] result = new int[arr1.length];
		int k = 0;
		Arrays.sort(arr1);
		List<Integer> list = new ArrayList<>();
		for (int j = 0; j < arr1.length; j++) {
			list.add(arr1[j]);
		}
		for (int i = 0; i < arr2.length; i++) {
			int index = 0;
			while (index != -1) {
				index = list.indexOf(arr2[i]);
				if (index > -1) {
					result[k++] = (int) list.get(index);
					list.remove(index);
				}
			}
		}
		int[] temp = list.stream().mapToInt(Integer::intValue).toArray();
		System.arraycopy(temp, 0, result, result.length - temp.length - 1, temp.length);
		System.out.println(result);
	}

	/**
	 * <title>#5 给一个字符串，输出最长的回文子串</title> <br>
	 * topic:动态规划
	 */
	@Test
	public void longestPalindrome() {
		String s = "ac";
		System.out.println(getPalindrome(s));
	}

	/**
	 * 
	 * 有错误 暴力遍历法 遍历字符串的每个子串，判断子串是否是回文的
	 * 
	 * @return
	 */
	private String getPalindrome(String s) {
		for (int i = s.length(); i > 0; i--) {
			// 从最长开始判断,子串长度为i
			for (int j = 0; j <= s.length() - i; j++) {
				// 从字符串j位开始遍历，截取j，j+1位
				String str = s.substring(j, j + i);
				if (str.length() == 1) {
					return str;
				}
				// 判断子串是否是回文
				for (int k = 0; k < str.length() / 2; k++) {
					if (str.charAt(k) != str.charAt(str.length() - 1 - k)) {
						break;
					} else if (k == str.length() / 2 - 1) {
						return str;
					}
				}

			}
		}
		return "";
	}

	/**
	 * <tbody>动态规划，一个二维数组，d[i][j]表示字符串中第i位到第j位是否是回文串，即i<=j,图是一个正方形对角线的上部分
	 * 如果第d[i+1][j-1]位是true，表示第i+1到j-1是回文，那么如果i=j 第d[i][j]也是回文 TODO
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getPalindromeTwo(String s) {
		boolean[][] d = new boolean[][] {};
		for (int i = s.length() / 2; i > 0; i--) {
			for (int j = s.length() / 2; j < s.length(); j++) {
				d[i][j] = (s.charAt(i + 1) == s.charAt(j - 1) && d[i + 1][j - 1]);
			}
		}
		return "";

	}

	/**
	 * <title>#78 给一组数组（注意给出数组中的值都是不同的），求出数组的子集，子集不能重复</title> <tbody>topic：回溯算法 Bit
	 * Manipulation位运算</tbody>
	 */
	@Test
	@Unsolve
	public void subsets() {
		int[] nums = { 1, 2, 3 };
		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		ans.add(new ArrayList<>(path));
		if (nums == null || nums.length == 0) {

		}
		int start = 0;
		helper(ans, path, start, nums);
		System.out.println(ans);
	}

	private void helper(List<List<Integer>> ans, List<Integer> path, int start, int[] nums) {
		if (start == nums.length) {
			return;
		}

		for (int i = start; i < nums.length; ++i) {
			path.add(nums[i]);
			ans.add(new ArrayList<>(path));
			helper(ans, path, i + 1, nums);
			path.remove(path.size() - 1);
		}

	}

	/**
	 * #204 给一个n，统计小于n的素数
	 */
	@Test
	@Unsolve
	public void countPrimes() {
		int n = 499979;
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (i == 2 || i == 3) {

				count++;

			} else {
				for (int j = 2; j * j < i; j++) {
					if (i % j == 0) {
						break;
					}
					if (Math.pow(j + 1, 2) > i) {
						count++;
					}
				}
			}
		}
		System.out.print(count);
	}

	/**
	 *
	 * <title> #6 描述给一串字符串，从上到下，从左到右Z型排列，然后按序输出</title>
	 * <tbody>例如：LEETCODEISHIRING，排4排 L D R E O E I I E C I H N T S G
	 * 思路：排n排，可以看成一个个n行(n-1)列的矩阵，n+(n-2)*1个为一组 在字符串中的index%n=在矩阵的列数，n-index%n=在矩阵的行数
	 * 
	 * 
	 * 感觉不太好 输出：LDREOEIIECIHNTSG </tbody>
	 */
	@Unsolve(title = "#6 描述给一串字符串，从上到下，从左到右Z型排列，然后按序输出")
	@Test
	public void convert() {
		String str = "LEETCODEISHIRI";
		int n = 4;
		int count = str.length() % (2 * n - 2) == 0 ? str.length() / (2 * n - 2) : str.length() / (2 * n - 2) + 1;
		char[][] array = new char[n][count * (n - 1)];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 2 * n - 2; j++) {
				if ((2 * n - 2) * i + j < str.length()) {
					if (j < n) {
						array[j][i * (n - 1)] = str.charAt((2 * n - 2) * i + j);
					} else {
						array[n - 1 - j % (n - 1)][j % (n - 1) + i * (n - 1)] = str.charAt((2 * n - 2) * i + j);
					}
				}
			}
		}
		char[] result = new char[n * count * (n - 1)];
		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < count * (n - 1); j++) {
				if (array[i][j] > 0) {
					result[k++] = array[i][j];
				}

			}
		}
		System.out.print(new String(result));
	}

	/**
	 * #36 验证有效数独 <tbody> 1个9*9的方格内，横排和竖排的数字不能重复，且范围在0-9;同时满足3*3的小方格也是0-9数字不重复
	 * 验证已填数字是否是一个有效数独。
	 * 
	 * 
	 * 
	 * </tbody>
	 * 
	 */
	@Test
	@Unsolve(content = "九宫格那里有错")
	public void isValidSudoku() {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '4', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };

		for (int i = 0; i < board.length; i++) {
			Integer[] hashTable = new Integer[board[0].length];
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					Integer index = new Integer(String.valueOf(board[i][j])) - 1;
					if (hashTable[index] != null) {
						System.out.println(false);
						System.exit(0);
						// return false;
					} else {
						hashTable[index] = 1;
					}
				}
			}
		}

		for (int i = 0; i < board[0].length; i++) {
			Integer[] hashTable = new Integer[board.length];
			for (int j = 0; j < board.length; j++) {
				if (board[j][i] != '.') {
					Integer index = new Integer(String.valueOf(board[j][i])) - 1;
					if (hashTable[index] != null) {
						System.out.println(false);
						System.exit(0);
						// return false;
					} else {
						hashTable[index] = 1;
					}
				}
			}
		}

		// 总共是9个宫格
		for (int k = 1; k <= 9; k++) {
			Integer[] hashTable = new Integer[9];
			int start = (k % 3 == 0 ? 6 : (k % 3 - 1) * 3);
			int end = (k % 3 == 0 ? 9 : (k % 3) * 3);
			for (int i = (k / 3) * 3; i < (k / 3 + 1) * 3; i++) {
				for (int j = start; j < end; j++) {
					if (board[i][j] != '.') {
						Integer index = new Integer(String.valueOf(board[i][j])) - 1;
						if (hashTable[index] != null) {
							System.out.println(false);
							System.exit(0);
							// return false;
						} else {
							hashTable[index] = 1;
						}
					}
				}
			}
		}
		System.out.println(true);
	}

	/**
	 * #121 最大利润 给一个数组求最大利润 后面的数-前面的数
	 */
	@Test
	public void maxProfit() {
		int[] prices = { 7, 1, 5, 3, 6, 8 };
		int maxProfit = 0;
		// 两个for循环
		for (int i = 0; i < prices.length - 1; i++) {
			for (int k = i + 1; k < prices.length; k++) {
				if (prices[k] - prices[i] > maxProfit) {
					maxProfit = prices[k] - prices[i];
				}
			}
		}
		System.out.println(maxProfit);
	}

	/**
	 * #127 单词接龙 给出首尾两个单词，每次转换单词中的一个字母，使开始的词最后变成结尾的单词 转换中的单词都在词典中，找出最少的，如果不存在则输出0
	 * topic：广度优先算法(BFS)
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void ladderLength() {
//		String  beginWord="hit";
//		String endWord="cog";
//		String[] str= {"hot","cog","dot","dog","hit","lot","log"};
//		List<String> wordList=new ArrayList<>(Arrays.asList(str));
		// System.out.print(Bfs(beginWord,wordList,endWord));

	}

	public int Bfs1(String beginWord, List<String> wordList, String target) {
		Queue<String> queue = new LinkedList<String>();
		queue.add(beginWord);
		int count = 1;
		while (!queue.isEmpty()) {
			String temp = queue.poll();
			wordList.remove(temp);
			if (target.equals(temp)) {
				return count;
			}
			for (String str : wordList) {
				if (compare(temp, str)) {
					queue.add(str);
				}
			}

		}
		return 0;
	}

	@SuppressWarnings("unused")
	private int getCount(List<String> wordList, String target, String compareStr, int count) {
		if (target.equals(compareStr)) {
			return count;
		}
		List<String> list = new ArrayList<>(wordList);
		int index = 0;
		while (index < wordList.size()) {
			String temp = wordList.get(index);
			if (compare(temp, compareStr)) {
				list.remove(compareStr);
				return getCount(list, target, temp, count) + 1;
			}
			index++;
		}
		return 0;
	}

	private boolean compare(String target, String source) {
		int differCount = 0;
		for (int i = 0; i < target.length(); i++) {
			if (target.charAt(i) != source.charAt(i)) {
				if (differCount >= 1) {
					return false;
				}
				differCount++;
			}
		}
		return true;

	}

	/**
	 * #219 存在重复元素II 给一个数组和一个k，使得nums[i]=nums[j] |j-i|=k
	 * 
	 * 题目理解错了
	 */
	@Test
	@Unsolve
	public void containsNearbyDuplicate() {
		int[] nums = { 1, 4, 1, 43, 40 };
		int k = 3;
		if (nums.length - k == 0 && nums[0] == nums[nums.length - 1]) {

		} else {
			for (int i = 0; i < nums.length - k; i++) {
				if (nums[i] == nums[i + k]) {
					System.out.print(true);
					System.exit(0);
				}
			}
		}
		System.out.println(false);
	}

	/**
	 * #189 旋转数组 一个数和一个非负数k，使整个数组都往右移动k个位置 要求空间复杂度为o(1)的 原地算法
	 */
	public void rotate() {
//		int[] nums= {};
//		int k=10;

	}

	/***
	 * #202 快乐数 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为
	 * 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。 topic：哈希表
	 * 
	 * 思路不断平方直至循环
	 */
	@Test
	@Unsolve(content = "超时")
	public void isHappy() {
//		int a = 0,b = 0,c = 0,d = 0;
//		int[] nums= {a,b,c,d};
//		int n=19;
//		Set<Integer> set=new HashSet<Integer>();
//		while(!set.contains(n)) {
//	        String[] nums= String.valueOf(n).split("");
//	          n=0;
//	        for(String i:nums) {
//	        	n=(int) (n+Math.pow(Integer.parseInt(i), 2));
//	        }	
//	        if(n==1) {
//	        	System.out.println(true);
//	        	System.exit(0);
//	        	//return true;
//	        }
//		}
//		System.out.println(false);
	}

	@Test
	@Unsolve(content = "如果存在不重合的情况")
	public void computeArea() {
		int A = -2, B = -2, C = 2, D = 2, E = 3, F = 3, G = 4, H = 4;
		int[] X = { A, C, E, G };
		int[] Y = { B, D, F, H };
		Arrays.sort(X);
		Arrays.sort(Y);
		int totalArea = Math.abs(C - A) * Math.abs(D - B) + Math.abs(G - E) * Math.abs(H - F)
				- Math.abs(X[2] - X[1]) * Math.abs(Y[2] - Y[1]);
		System.out.print(totalArea);
	}

	/**
	 * #5175 构建回文串检测
	 * 
	 * @return
	 */
	@Unsolve(content = "超时")
	@Test
	public void canMakePaliQueries() {
		String s = "abcda";
		int[][] queries = { { 3, 3, 0 }, { 1, 2, 0 }, { 0, 3, 1 }, { 0, 3, 2 }, { 0, 4, 1 } };
		List<Boolean> list = new ArrayList<>();
		for (int i = 0; i < queries.length; i++) {
			String str = s.substring(queries[i][0], queries[i][1] + 1);
			int k = queries[i][2];
			if (str.length() == 1) {
				list.add(true);
				continue;
			}
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			int current = 0;
			int count = 0;
			for (int j = 0; j < chars.length; j++) {
				if (current == 0) {
					current = 1;
				} else {
					if (chars[j] == chars[j - 1]) {
						current = 0;
					} else {
						count++;
					}
				}
			}
			list.add(str.length() % 2 == 0 ? 2 * k >= (count + 1) : 2 * k >= count);
		}
		System.out.println(list);
	}

	/**
	 * #983 最低票价
	 */
	@Test
	@Unsolve(content = "我7天我也可以1天1天买,超过30天我还能有n中方式，动态规划")
	public void mincostTickets() {
//		 //为期1,7,30
//		 int[] days= {1,4,6,7,8,20}; int[] costs= {2,7,15};
//		 int dayCount=0;
//		 for(int i=0;i<days.length;i++) {
//			 //起始天
//			 if(dayCount==0) {
//				 dayCount++;
//			 }else {
//				 //连续的天数就继续加
//				 if(days[i]-days[i-1]==1) {
//					 dayCount++;
//				 }else {
//					
//				 }
//			 }
//			 
//		 }
	}

	/**
	 * #1052 爱生气的书店老板
	 * customers[i]表示每分钟的顾客数，grumpy[i]表示当前时间老板是生气（1）还是不生气（0），X表示连续X分钟老板即使生气也可以忍住 //
	 * 给一个只包含字符a,b,c的字符串S，如果S中不超过 topic：滑动窗口
	 * 
	 * @return
	 */
	@Unsolve
	public int maxSatisfied() {
//       int[] customers= {1,0,1,2,1,1,7,5}; int[] grumpy= {0,1,0,1,0,1,0,1}; int X=3;
		int satisfiedCustomers = 0;
//		 int max=0;
//		 for(int i=0;i<customers.length;i++) {
//			   if(i<X) {
//				   if(grumpy[i]==1) {
//					   max+=customers[i];
//				   }
//			   }else {
//				   if(grumpy[i-X]==1) {
//					   
//				   }
//			   }
//			   if(i>=X&&grumpy[i-X]==1) {
//				   satisfiedCustomers-=customers[i];		   
//			   }
//			   satisfiedCustomers+=customers[i];
//		 }
		return satisfiedCustomers;
	}

	/**
	 * #547 朋友圈 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。 如果M[i][j] = 1，表示已知第 i 个和 j
	 * 个学生互为朋友关系，否则为不知道。 朋友具有传递性，如果A是B的朋友，B是C的朋友，那A就是C的朋友，他们三是一个朋友圈 输出总共有几个朋友圈
	 * 
	 * 思路：这矩阵有对称性，其实只要看对角线一半就好了，另外同一排同一列的1都是同一个朋友圈，而最后一排包含所有和前面关系
	 * 从最后一行遍历，设此时遍历到j，存在M[j][i]=0，然后去遍历i行 1 1 1 0 1 1 0 0 0 1 0 0 1 0 1 0 0 1 0 0 1
	 * 
	 * @return
	 */
	public int findCircleNum() {
		int[][] M = {};

		@SuppressWarnings("unused")
		int count = 1;
		int i = M.length - 1;
		for (int j = 0; j < M.length - 2; j++) {
			if (M[i][j] == 0) {
				count++;
			}
		}

		// 下半部分三角
//		List<Integer> list=new ArrayList<>();
//		for(int i=M.length-1;i>=0;i++) {
//			if(list.size()>0&&list.contains(i)) {
//				continue;
//			}
//			//同一行的1都是朋友
//			for(int j=0;j<i;j++) {
//				if(M[i][j]==0) {
//					list.add(j);
//				}
//			}
//			
//		}
//		int i=M.length-1;
//		List<Integer> list=new ArrayList<>();
//		for(int j=M.length-1;j>=0;j--) {
//			if(M[i][j]==1) {
//				list.add(j);
//			}
//			int k=0;
//			while(k!=list.size()) {
//				for(int m=0;m<list.get(k);m++) {
//					if(M[list.get(k)][m]==1&&list.contains(m)) {
//						list.add(m);
//					}
//				}
//				k++;
//			}
//		}

		return 0;
	}

	/**
	 * #152 乘积最大子序列 意思如图，给一个整数数组，数组中中有正有负，返回最大的乘积
	 * 思路：对于正整数乘积是越多越好，所以节点在负数和0中，遇到0肯定要断，遇到负数则要看是否有下一个负数
	 * 
	 * 
	 * @return
	 */
	@Unsolve
	public int maxProduct() {
		int[] nums = { -1, -2, 0 };
		int max = 1;
		int currentMultiply = 1;
		int negative = 0;
		int temp = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				max = Math.max(max, Math.max(temp, currentMultiply));
				currentMultiply = 1;
				negative = 0;
				temp = 0;
			} else if (nums[i] < 0) {
				if (negative == 0) {
					negative = nums[i];
					temp = currentMultiply;
					currentMultiply = 1;
				} else {
					currentMultiply = currentMultiply * temp * negative * nums[i];
					negative = 0;
					temp = 0;
				}
			} else {
				currentMultiply *= nums[i];
			}
		}
		return max = Math.max(max, Math.max(temp, currentMultiply));
	}

	/**
	 * 成语接龙 1、词典中可能包含begin和end
	 * 
	 * @return
	 */
//	@Solve
//	public int ladderLength() {
//		String beginWord="hit";//开头	
//		String endWord="cog";//结尾
//		String[] arrays=new String[] {"hot","dot","dog","lot","log","cog"};
//		List<String> wordList=Arrays.asList(arrays);
//	    System.out.print(getlength(beginWord, endWord, wordList, 0));
//		return 0;
//		
//	}

//	private int getlength(String beginWord,String endWord,List<String> wordList,int length) {
//		if(compareStr(beginWord,endWord)<2) {
//			return length;
//		}
//		int minlength=0;
//		for(String temp:wordList) {
//			if(compareStr(beginWord, temp)<2) {
//				length++;
//				List<String> list=new ArrayList<>(wordList);
//				list.remove(temp);
//				int len=getlength(temp, endWord, list,length);
//				if(len>0) {
//					minlength=minlength==0?len:Math.min(len, minlength);
//				}
//			}
//		}
//		return minlength;	
//	}
//	
//	
//	
//	/**
//	 * 比较两个字符串
//	 * @param source
//	 * @param target
//	 * @return
//	 */
//	private int compareStr(String source,String target) {
//		int diffCount=0;
//		for(int k=0;k<source.length();k++) {
//			if(source.charAt(k)!=target.charAt(k)) {
//				diffCount++;
//			}
//			if(diffCount>1) {
//				break;
//			}
//		}
//		return diffCount;	 
//	 }
//	

	/**
	 * #448. 找到所有数组中消失的数字 给一个数组1<=a[i]<=n,找出消失的数字 要求不使用额外空间，时间复杂度o(0)
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> findDisappearedNumbers() {
		int[] nums = { 1, 1 };
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}

		for (int j = 1; j <= nums.length; j++) {
			if (!set.contains(j)) {
				list.add(j);
			}
		}
		return list;
	}

	public String removeDuplicates() {
		String s = "nnwssswwnvbnnnbbqhhbbbhmmmlllm";
		int k = 3;
		int count = 1;
		boolean delete = false;
		do {
			delete = false;
			for (int i = 1; i < s.length(); i++) {
				if (s.charAt(i) == s.charAt(i - 1)) {
					count++;
				}
				if (count == k) {
					s = s.substring(0, i - 2).concat(i < s.length() - 1 ? s.substring(i + 1, s.length()) : "");
					count = 0;
					delete = true;
				}
			}
		} while (delete);
		return s;
	}

	/**
	 * 翻煎饼
	 * 目前的思路是：找到最大的数a，它对应的位置是k,则对前K个数进行反转，此时a到第一位，然后对前n个未排序的数据进行翻转，那么a就到了n位，然后再对前n-1位进行如上操作
	 * 存在的难点是每次翻转后的数组位置进行变动了 k序列长度大概在2n
	 * 
	 * @return
	 */
	public List<Integer> pancakeSort() {
		return null;
	}

	/**
	 * #1223 掷骰子模拟
	 * 
	 * 参考别人的思路动态规划
	 * 
	 * @return
	 */
	@Unsolve(content = "不对，不能用这个 因为当3为2的时候也是取5种")
	public int dieSimulatorDepreceted() {
		int n = 6;
		int[] rollMax = { 7, 7, 3, 7, 7, 7 };
		// 总共掷骰子的次数
		int totalCount = (int) Math.pow(6, n);
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : rollMax) {
			if (i < n) {
				if (map.containsKey(i)) {
					totalCount -= map.get(i);
				} else {
					int sum = 0;
					for (int k = i; k < n; k++) {
						sum = (int) (sum + (n - k) * Math.pow(5, n - k - 1));
					}
					map.put(i, sum);
					totalCount -= sum;
				}
			}
		}
		// 多减了共同部分
		return (int) (totalCount % (Math.pow(10, 9) + 7));
	}

	public static void main(String[] args) {
		try {
			System.out.print(byte2Hex(MessageDigest.getInstance("sha-256").digest("张三4566498798498498498498".getBytes("utf-8"))));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

private static String byte2Hex(byte[] bytes){
    StringBuffer stringBuffer = new StringBuffer();
    String temp = null;
    for (int i=0;i<bytes.length;i++){
    	temp = Integer.toHexString(bytes[i] & 0xFF);
    	if (temp.length()==1){
    		stringBuffer.append("0");
    	}
    	stringBuffer.append(temp);
    }
    return stringBuffer.toString();
    }



/**
 * #860 柠檬水找零
 * bills是客户付你钱的顺序（取值只有5,10,20），如果能成功找零就返回true，否则返回false
 * @return
 */

  public boolean lemonadeChange() {
	  int[] bills= {5,5,10,10,20}; 
	  int totalMoney=0;
	  Map<Integer,Integer> haveMoney=new HashMap<Integer,Integer>();
	  for(int bill:bills) {
		  if(bill==5) {
			  haveMoney.put(5, haveMoney.getOrDefault(5, 0)+1);
		  }else if(bill==10) {
			  if(!haveMoney.containsKey(5)||haveMoney.get(5)<1) {
				  return false;
			  }
			  haveMoney.put(5, haveMoney.getOrDefault(5, 0)-1);
			  haveMoney.put(10, haveMoney.getOrDefault(10, 0)+1);
		  }else {
			  
			  if(haveMoney.containsKey(10)) {
				  
			  }
		  }
		  totalMoney+=5;
	  }
	  return true;
    }
  

}
