package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

public class AlgorithmTestFour {
	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmTestFour.class);
	}

	@Solve
	public boolean uniqueOccurrences() {
		int[] arr = { 1, 2, 2, 1, 1, 3 };
		Arrays.sort(arr);
		int count = 1;
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[i - 1]) {
				if (!set.add(count)) {
					return false;
				}
				count = 1;
			} else {
				count++;
			}
			if (i == arr.length - 1) {
				return set.add(count);
			}
		}
		return true;
	}

	@Solve
	public int equalSubstring() {
		String s = "krpgjbjjznpzdfy";
		String t = "nxargkbydxmsgby";
		int maxCost = 14;
		int[] values = new int[s.length()];

		for (int i = 0; i < s.length(); i++) {
			values[i] = Math.abs(s.charAt(i) - t.charAt(i));
		}

		int maxlength = 0;
		int begin = 0;
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > maxCost) {
				maxlength = Math.max(maxlength, i - begin);
				sum = 0;
				begin = i + 1;
			} else {
				sum = sum + values[i];
				if (sum > maxCost) {
					maxlength = Math.max(maxlength, i - begin);
					while (sum > maxCost) {
						sum -= values[begin];
						begin++;
					}
				}
			}
			if (i == values.length - 1) {
				maxlength = Math.max(maxlength, i - begin + 1);
			}
		}
		return maxlength;
	}

	/**
	 * 暴力算法超时了
	 * 
	 * 由A[i]+A[j]+i-j最大，移动得A[i]+i 和A[j]-j要最大 j>i
	 * 
	 * @return
	 */
	@Solve
	public int maxScoreSightseeingPair() {
		int[] A = { 8, 1, 5, 2, 6 };
		int maxScore = 0;
		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				maxScore = Math.max(maxScore, A[i] + A[j] + i - j);

			}
		}
		return maxScore;
	}

	/**
	 * 由A[i]+A[j]+i-j可得，A[i]+i+A[j]-j （i<j） 有上述公式可知总的和要最大，只要保证 A[i]+i 和A[j]-j最大就好
	 * 遍历j 取j之前最大的A[i]+i
	 * 
	 * @return
	 */
	@Solve
	public int maxScoreSightseeingPair2() {
		int[] A = { 8, 1, 5, 2, 6 };
		if (A == null || A.length == 0) {
			return 0;
		}
		int maxScore = 0;
		int maxPre = A[0] + 0;
		for (int j = 1; j < A.length; j++) {
			maxScore = Math.max(maxPre, A[j] - j);
			maxPre = Math.max(maxPre, A[j] + j);
		}
		return maxScore;
	}

	/**
	 * #804 唯一摩尔斯密码
	 * 
	 * @return
	 */
	@Solve
	public int uniqueMorseRepresentations() {
		String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
				"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
		String[] words = { "gin", "zen", "gig", "msg" };
		Set<String> set = new HashSet<String>();
		for (String str : words) {
			char[] chars = str.toCharArray();
			for (char c : chars) {
				str = str.replace(String.valueOf(c), morse[c - 'a']);
			}
			set.add(str);
		}
		return set.size();
	}

	@Solve
	public int minCostToMoveChips() {
		int[] chips = { 1, 2, 3, 4, 5 };
		int total = chips.length;// 总得代价
		int oddCount = 0;
		int evenCount = 0;
		for (int i = 0; i < chips.length; i++) {
			if (chips[i] % 2 == 0) {
				evenCount++;
			} else {
				oddCount++;
			}
		}
		return total - Math.max(oddCount, evenCount);
	}

	@Solve
	public int longestSubsequence() {
		int[] arr = { 4, 12, 10, 0, -2, 7, -8, 9, -9, -12, -12, 8, 8 };
		int different = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i] - different)) {
				map.put(arr[i], map.get(arr[i] - different) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		int max = 1;
		for (Integer count : map.values()) {
			max = Math.max(count, max);
		}
		return max;
	}

	/**
	 * #922 奇偶排序 对于A[i],如果A[i]是偶数，那么i也是偶数；如果A[i]是奇数，那么i也是奇数 我的思路是用奇偶指针分别遍历奇偶位，遇到就停下来
	 * 官方思路：不管原来是不是在正确的位置，凡事遇到偶数就把他放在a[0],a[2],a[4].... 遇到偶数就放在a[1],a[3]...
	 * 
	 * @return
	 */
	@Solve(title="922",content = "3ms/98%,39.7MB/97%")
	public int[] sortArrayByParityII() {
		int[] A = { 4 };
		int even = 0;
		int odd = 1;
		while (even <= A.length && odd <= A.length) {
			while (even < A.length && A[even] % 2 == 0) {
				even += 2;
			}
			while (odd < A.length && A[odd] % 2 != 0) {
				odd += 2;
			}
			if (even < A.length && odd < A.length) {
				int temp = A[even];
				A[even] = A[odd];
				A[odd] = temp;
			}
		}
		return A;
	}

	/**
	 * #447 回旋镖数量 还不如两个for全循环块 = =
	 * 
	 * @return
	 */
	@Solve(title="447")
	public int numberOfBoomerangs() {
		int[][] points = { { 7822, -3128 }, { 8230, -7843 }, { 4481, -8255 }, { 3566, -847 }, { 592, -3459 },
				{ 3828, -1163 }, { 2560, 6480 }, { 2467, -1150 }, { 8966, 5286 }, { 5533, -832 }, { 7991, 3403 },
				{ 5994, -5529 }, { 2404, -6222 }, { 5046, -6022 }, { 4417, 4555 }, { 6371, 9279 }, { 5633, -3132 },
				{ 3493, 799 }, { 584, 4282 }, { 5755, 8319 }, { 6034, 9291 }, { 5388, -1406 }, { 5771, -5793 },
				{ 3797, -8911 }, { 9494, 9330 }, { 258, 3837 }, { 9085, 2604 }, { 4660, -8511 }, { 6382, -3942 },
				{ 1820, 800 }, { 613, -1809 }, { 5721, 9041 }, { 9939, 4551 }, { 5903, -9104 }, { 9609, 1191 },
				{ 8023, -5706 }, { 549, -8122 }, { 4111, -3003 }, { 1759, -8847 } };
		HashMap<Integer, List<String>> map = new HashMap<>();
		long beginTime = System.currentTimeMillis();
		int total = 0;
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int distance = (int) (Math.pow(points[i][0] - points[j][0], 2)
						+ Math.pow(points[i][1] - points[j][1], 2));
				if (map.containsKey(distance)) {
					List<String> list = map.get(distance);
					for (String s : list) {
						List<String> strList = Arrays.asList(s.split(","));
						if (strList.contains(String.valueOf(i)) || strList.contains(String.valueOf(j))) {
							total += 2;
						}
					}
					list.add(i + "," + j);
				} else {
					List<String> list = new ArrayList<>();
					list.add(i + "," + j);
					map.put(distance, list);
				}
			}
		}
		System.out.print(System.currentTimeMillis() - beginTime);
		return total;

	}

	/**
	 * #521最长特殊序列I
	 * 
	 * @return
	 */
	@Solve(title="521")
	public int findLUSlength() {
		String a = "aefawfawfawfaw";
		String b = "aefawfeawfwafwaef";
	   return a.equals(b)?-1:Math.max(a.length(), b.length());
	}
	
	 /**
	  * #442 数组中重复的元素 
	  * 数组中存在一些重复两次的元素还有一些只出现一次的元素,找出这些元素
	  * 对于  1<=nums[i]<=nums.length
	  * 不能使用任何额外空间，时间复杂度o(n)
	  * 当慢指针和快指针相遇后，再次相遇时慢指针走的长度就是环长
	  * 
	  * 
	  * 我是进行了交换，看其他人解题用负数表示已经访问过了
	  * 比如4,2,1,5,2,5
	  * ==>4,2,1,-5,2,5 当值为负数的时候表示已经访问过了
	  * 然后取数的时候取绝对值
	  * @return
	  */
	@Solve(title="442",content = "598ms/5.02%;46.6MB/98.31%")
	 public List<Integer> findDuplicates(){
		 int[] nums= {4,2,1,5,2,5};
		 List<Integer> list=new ArrayList<>();
		 for(int i=0;i<nums.length;i++) {
			 while(nums[i]!=i+1) {
				 if(nums[nums[i]-1]==nums[i]) {
					 if(!list.contains(nums[i])) {
						 list.add(nums[i]); 
					 }
					 break;
				 }else {
					 int temp=nums[nums[i]-1];
					 nums[nums[i]-1]=nums[i];
					 nums[i]=temp; 
				 }
				 
			 }
		 }	 
		 return  list;
	 }
	
	
	
	/**
	 * #27移除给定元素
	 * 原地处理,空间复杂度要是o(1),不考虑元素顺序
	 * @return
	 */
	@Solve(title="27")
    public int removeElement() {
    	int[] nums= {}; int val=4;
    	int begin=0;
        for(int i=0;i<nums.length;i++) {
        	if(nums[i]!=val) {
        		nums[begin++]=nums[i];
        	}
        }
        return begin;
    }

}
