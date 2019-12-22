package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

public class AlgorithmTestOne {

	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmTestOne.class);
	}

	/**
	 * <title>#3 给一个字符串返回最大子串的长度，子串中字符不能重复 </title> <br>
	 * topic： two pointers ,sliding window (指针和滑动窗口算法)
	 * 
	 * 
	 * <tbody>思路 ：有一个开始指针 b和结束指针e，开始b指针不变，e指针往后滑，如果e指针所指字符在b,e指针所指的字符串中有相同的字符，记下此时的
	 * 长度e-b,然后b指针指向重复字符所在位置的后一位，e指针不变 ggububgvfk 比如一开始 b=0,e=0;
	 * 当e=1时重复，此时max=1-0=1,b=1,e=1；然后e在向后滑，滑到第五位u时，b=1，e=4 ，记下max=4-1=3 b指向第一个u所在位置
	 * 即b=b+index+1=1+1+1=3(index是u在字符串gub所在的位置) </tbody>
	 * 
	 */
	@Solve(title = "3", keywords = "双指针，滑动窗口")
	public int lengthOfLongestSubstring() {
		String s = "aab";
		int max = 0;
		int beginPoint = 0;
		for (int i = 0; i < s.length(); i++) {
			int index = s.substring(beginPoint, i).indexOf(String.valueOf(s.charAt(i)));
			// 说明此时已经出现重复了，那么应该从index+1开始，beginPoint=index+1，endPoint还是i
			if (index > -1) {
				max = Math.max(max, i - beginPoint);
				beginPoint = beginPoint + index + 1;
			}
			// 如果一直没有重复的，max就是str的长度
			if (i == s.length() - 1) {
				max = Math.max(max, i - beginPoint + 1);
			}
		}
		return max;
	}

	/**
	 * #11 给一个一维数组，index是横坐标，height[index]是纵坐标，求两个点和X轴所组成的最大长方形面积
	 * 我的做法是以某一点为基准，找这个点左右两边中大于这点值得最远点
	 */
	@Solve(title = "11", keywords = "双指针")
	public int maxArea() {
		int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		int max = 0;
		for (int i = 0; i < height.length; i++) {
			int leftArea = 0, rightArea = 0;
			for (int j = 0; j <= i - 1; j++) {
				if (height[j] >= height[i]) {
					leftArea = (i - j) * height[i];
					break;
				}
			}
			for (int j = height.length - 1; j > i; j--) {
				if (height[j] > height[i]) {
					rightArea = (j - i) * height[i];
					break;
				}
			}
			max = Math.max(max, leftArea + rightArea);
		}
		return max;
	}

	/**
	 * #16 给一组数据和一个目标值，找出数组中三个数之和与目标值最接近的 topic：排序，双指针 我的解法：先固定一个数值，然后另外两个用双指针算法
	 * 
	 */
	@Solve(title = "16", keywords = "排序，双指针")
	public int threeSumClosest() {
		int[] nums = { 0, 1, 2 };
		int target = 1;
		if (nums.length < 3) {
			throw new IllegalArgumentException();
		}
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		int minDiff = Math.abs(nums[0] + nums[1] + nums[2] - target);
		for (int i = 0; i < nums.length; i++) {
			int low = i + 1;
			int height = nums.length - 1;
			while (height > low) {
				int sum = nums[i] + nums[low] + nums[height];
				if (Math.abs(target - sum) < minDiff) {
					result = sum;
					minDiff = Math.abs(target - sum);
				}
				if (sum < target) {
					low++;
				} else {
					height--;
				}
			}
		}
		return result;
	}

	/**
	 * <title>#925 验证输入是否正确</title> <tbody>给两个字符串name，typed
	 * typed中对于name的字符可以重复，但是不能少,且多出来的字符必须和左右一样</tbody> topic:双指针 我的思路是：一个指针指向name
	 * 一个指针指向typed；相同一起移，不相同typed移，直到一方移到尾
	 */	
	@Solve(title = "925", keywords = "双指针")
	public boolean isLongPressedName() {
		String name = "ki";
		String typed = "ki";
		int nameIndex = 0;
		int typeIndex = 0;
		while (nameIndex < name.length() && typeIndex < typed.length()) {
			// 第一个字符必须匹配
			if (nameIndex == 0 && name.charAt(nameIndex) != typed.charAt(typeIndex)) {
				return false;
			} else if (name.charAt(nameIndex) == typed.charAt(typeIndex)) {
				// 如果两者匹配一起移动
				nameIndex++;
			} else if (typed.charAt(typeIndex) != typed.charAt(typeIndex - 1)) {
				// name和typed不匹配，则typed需要和前一个字符比较，不匹配false
				return false;
			}
			typeIndex++;
		}

		if (nameIndex < name.length()) {
			// 当typed已到尾部，name没有，则false
			return false;
		}

		if (typeIndex < typed.length()) {
			// 如果name已到尾部，则typed剩余的都得和前一个字符一样
			for (int i = typeIndex; i < typed.length(); i++) {
				if (typed.charAt(typeIndex) != typed.charAt(typeIndex - 1)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * #48 n*n的数组转置，要求不能重新分配一个二维数组 例如{1,2,3}{4,5,6}{7,8,9}==>{7,4,1},{8,5,2},{9,6,3}
	 * 我的解法是，
	 * <li>按照转置规则原来的j对应转置后的i，转置后的j=原来行数-1-i
	 * <li>将二维数组存储在一维数组中，然后再将一维数组按序转二维(二维转一维是 一维index= i*列数+j)
	 * <li>结合以上两点 temp[(j*matrix.length)+(matrix.length-1-i)]=matrix[i][j];
	 * 
	 * @param matrix
	 */
	@Solve(title = "48", keywords = "原地算法")
	public int[][] rotate() {
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[] temp = new int[matrix.length * matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				temp[(j * matrix.length) + (matrix.length - 1 - i)] = matrix[i][j];
			}
		}
		int k = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = temp[k++];
			}
		}
		return matrix;
	}

	/**
	 * <title>#41 第一个缺失的整数 </title> <topic> 哈希表</topic> TODO 解法还没看后续补上看 <tbody>
	 * 查找第一个不在数组中的整数</tbody>
	 */
	@Solve(title = "41", keywords = "哈希表")
	public int firstMissingPositive() {
		int[] nums = {};
		int result = 1;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0 && nums[i] > result) {
				return result;
			}
			if (result == nums[i]) {
				result++;
			}
		}
		return result;
	}

	/**
	 * <title>#22 输出有效的括号组合</title> <tbody>输入n 表示n对括号，对括号进行排列组合，生成有效的组合 比如n=2
	 * ()(),(())</tbody> topic：回溯算法
	 * 回溯算法：类似枚举的搜素尝试过程，回溯法是一种选优搜索法，按选优条件向前搜索，以达到目标，如果当前选择并不满足目标，就返回重新选择\ 解决用的递归
	 */
	@Solve(title = "22", keywords = "回溯算法")
	public List<String> generateParenthesis() {
		int n = 1;
		List<String> list = new ArrayList<String>();
		generateParenthesis(list, "(", ")", 1, n * 2, 0);
		generateParenthesis(list, "(", "(", 1, n * 2, 0);
		return list;

	}

	private void generateParenthesis(List<String> result, String source, String addStr, int leftCount, int totalCount,
			int rightCount) {
		if (addStr.equals("(")) {
			leftCount++;
		} else {
			rightCount++;
		}
		if (leftCount >= rightCount && leftCount <= totalCount / 2) {
			source = source + addStr;
			if (source.length() == totalCount) {
				System.out.println(source);
				result.add(source);
			} else {
				generateParenthesis(result, source, "(", leftCount, totalCount, rightCount);
				generateParenthesis(result, source, ")", leftCount, totalCount, rightCount);
			}
		}
	}

	/**
	 * #46 给一个没有重复数字的序列，返回所有可能的排列组合 topic:回溯算法
	 */
	@Solve(title = "46", keywords = "回溯算法")
	public List<List<Integer>> permute() {
		int[] nums = { 1, 2, 3, 4 };
		List<Integer> source = new ArrayList<Integer>();
		List<Integer> temp = new ArrayList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int num : nums) {
			source.add(num);
		}
		permute(source, temp, result);
		return result;
	}

	private void permute(List<Integer> source, List<Integer> temp, List<List<Integer>> result) {
		if (source == null || source.size() <= 0 || source.get(0) == null) {
			result.add(new ArrayList<Integer>(temp));
			return;
		}
		for (Integer num : source) {
			temp.add(num);
			ArrayList<Integer> list = new ArrayList<>(source);
			list.remove(num);
			permute(list, temp, result);
			temp.remove(num);
		}
	}
	
}
