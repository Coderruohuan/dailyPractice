package algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

public class AlgorithmTestTwo {

	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(AlgorithmTestTwo.class);
	}

	/**
	 * 判断是否是丑数 丑数是只包含质因数2，3，5的正整数
	 * 
	 */
	@Solve
	public boolean isUgly() {
		int num = 1000;
		if (num <= 0) {
			return false;
		}
		while (num % 2 == 0) {
			num = num / 2;
		}
		while (num % 3 == 0) {
			num = num / 3;
		}
		while (num % 5 == 0) {
			num = num / 5;
		}
		if (num == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * #1016 子串能表示从 1 到 N 数字的二进制串 给一个数N，从1到N表示的二进制都是str的子串
	 * 
	 * 解题思路，对于一个n-1 位长度，可以用n位去掉高位后表示，比如
	 * 101是1101的子串，如果4位长度二进制的字符串都符和，那么不用去验证3位，2位，1位的
	 * 现在假设N对应4位长度的字符串，我只要验证所有3位字符串是否符合，然后验证部分4位的可能性==当然也可以想验证所有4位的可能性，但是有些比n打的数的字符串是不用
	 * 包含的，所以往下退一级，验证三位的
	 * 
	 * 所以，循环1111-10000 如果大于n只要验证后三位，如果小于等于n，验证本身。
	 * 
	 */
	@Solve(title="1016",content = "time:4ms/12.61%;memory:34.6MB/63.30%")
	public boolean queryString() {
		int N = 1;
		String S = "0";
		String NStr = Integer.toBinaryString(N);
		if (N == 1 && !S.contains(NStr)) {
			return false;
		}
		// 判断对maxStr所在的长度进行全判断，大于maxStr的截取后四位
		int max = Integer.parseInt(NStr.replaceAll("0", "1"), 2);
		int min = (int) Math.pow(2, NStr.length() - 1);
		for (int i = min; i < max; i++) {
			String tempStr = Integer.toBinaryString(i);
			if (tempStr.length() == 1) {
				continue;
			}
			if (i <= N && !S.contains(tempStr)) {
				return false;
			} else if (i > N && !S.contains(tempStr.substring(1, tempStr.length() - 1))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * #1154 一年中的第几天
	 * 给一个时间判断他是一年中的第几天 主要是闰年判断 能被4整除且不能被100整除，或者被400整除
	 * 
	 * @throws ParseException
	 */
	@Solve(title="1154")
	public void dayOfYear() throws ParseException {
		String date = "2020-06-09";
		int year = Integer.valueOf(date.split("-")[0]);
		int month = Integer.valueOf(date.split("-")[1]);// 03的格式也会转成3
		int day = Integer.valueOf(date.split("-")[2]);
		// 在八月之前，单数31天，双数30天；8月后双数31天，单数30天
		if (month < 8) {
			System.out.println((month - 1) / 2 * 61 + (month - 1) % 2 * 31 + day
					- (month > 2 ? (year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 1 : 2) : 0));
		} else {
			int extendsMonth = month - 7;
			System.out.println((extendsMonth - 1) / 2 * 61 + (extendsMonth - 1) % 2 * 31 + day
					- (month > 2 ? (year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 1 : 2) : 0) + 214);
			;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2000-03-25"));
		System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * #217 存在重复元素 给一个数组，如果数组中的元素出现一次以上，表示有重复，返回true，否则false topic 哈希表
	 * 
	 * 我的代码复杂了，用hashSet，不能set说明重复了
	 */
	@Solve(title="217",keywords = "哈希表")
	public boolean containsDuplicate() {
		int[] nums = { 1, 4, 1, 43, 40 };
		Hashtable<Integer, List<Integer>> table = new Hashtable<Integer, List<Integer>>();
		for (int num : nums) {
			List<Integer> list = table.get(num % nums.length);
			if (list != null && list.contains(Integer.valueOf(num))) {
				return true;
			} else {
				if (list == null) {
					list = new ArrayList<Integer>();
				}
				list.add(Integer.valueOf(num));
				table.put(num % nums.length, list);
			}
		}
		return false;
	}

	/**
	 * #605 种花问题 描述：用一个数组表示一个花坛，1表示种花，0表示没有，相邻两个位置不能种花，现在给一个n表示还要种n种花，看是否能种
	 * 
	 */
	@Solve(title="605")
	public boolean canPlaceFlowers() {
		int[] flowerbed = { 0, 0, 1, 0, 0 };
		int n = 1;

		for (int i = 0; i < flowerbed.length; i++) {
			if (n == 0) {
				break;
			}
			if (flowerbed[i] == 1) {
				continue;
			} else {
				if (i - 1 >= 0 && flowerbed[i - 1] == 1 || (i + 1 < flowerbed.length && flowerbed[i + 1] == 1)) {
					continue;
				}
				flowerbed[i] = 1;
				n--;
			}
		}
		if (n == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * #1033 移动石子直到连续 给坐标 a,b,c 分别a<b<c
	 * 移动a,c，到k坐标，k在a,c区间内，当移动完后，a,b,c连续就不能在移动了，求移动的最大次数和最小次数 topic 脑经急转弯
	 * 注意的是一种间距为2的情况 比如 1,3,7最少移动一次就够了移到2就连续了
	 */
	@Solve(title="1033",keywords = "脑筋急转弯")
	public int[] numMovesStones() {
		int a = 1;
		int b = 3;
		int c = 5;
		int[] result = new int[2];
		int distanceAB = Math.abs(a - b);
		int distanceBC = Math.abs(b - c);
		int distanceAC = Math.abs(a - c);
		if (distanceAB > distanceBC && distanceAB > distanceAC) {
			if (distanceBC + distanceAC <= 2) {
				;
			} else if (distanceBC <= 1 || distanceAC <= 1 || distanceBC == 2
					|| distanceAC == 2 && distanceBC + distanceAC > 2) {
				result[0] = 1;
				result[1] = distanceBC + distanceAC - 2;
			} else {
				result[1] = distanceBC + distanceAC - 2;
			}
		} else if (distanceAC > distanceBC && distanceAC > distanceAB) {
			if (distanceBC + distanceAB <= 2) {
			} else if (distanceBC <= 1 || distanceAB <= 1 || distanceBC == 2
					|| distanceAB == 2 && distanceBC + distanceAB > 2) {
				result[0] = 1;
				result[1] = distanceBC + distanceAB - 2;
			} else {
				result[0] = 2;
				result[1] = distanceBC + distanceAB - 2;
			}
		} else {
			if (distanceAC + distanceAB <= 2) {
			} else if (distanceAC <= 1 || distanceAB <= 1 || distanceAC == 2
					|| distanceAB == 2 && distanceAC + distanceAB > 2) {
				result[0] = 1;
				result[1] = distanceAC + distanceAB - 2;
			} else {
				result[0] = 2;
				result[1] = distanceAC + distanceAB - 2;
			}
		}
		return result;
	}

	/**
	 * #17电话号码的字母组合 给出一串包含2-9数字的字符串，输出对应字母的所有组合 topic 回溯算法
	 * 
	 */
	@Solve(title="17",keywords = "回溯算法")
	public void letterCombinations() {
		String digits = "23";
		List<String> list = new ArrayList<String>();
		getLetterString(digits, list, "");
		System.out.println(list);

	}

	private void getLetterString(String digits, List<String> list, String old) {
		if (digits.length() == old.length()) {
			list.add(old);
			return;
		}
		int num = Integer.parseInt(digits.substring(old.length(), old.length() + 1));
		// 7或9是4位
		for (int i = 0; i < (num == 7 || num == 9 ? 4 : 3); i++) {
			String result = old + new Character((char) ((num - 2) * 3 + 97 + i + (num > 7 ? 1 : 0))).toString();
			getLetterString(digits, list, result);
		}
	}

	/**
	 * 这是别人的做法，用了队列，很巧妙
	 * 将4对应的g,h,i先压入队列，当第二5时，取出g然后和j,k,l组成新的字符串压入队尾，由于先进先出的特性，每次判读队头的字符串长度是否等于已读的长度，比如说，我已经读到5了，就判断队列头部的字符串是否
	 * 等于2，否则拼接字符串并加入到队尾
	 */
	public void letterCombinations2() {
		String digits = "456";
		LinkedList<String> ans = new LinkedList<String>();
		if (digits.isEmpty())
			return;
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while (ans.peek().length() == i) {
				String t = ans.remove();
				for (char s : mapping[x].toCharArray())
					ans.add(t + s);
			}
		}
		System.out.println(ans);
	}

	/**
	 * #89 格雷编码
	 * 
	 * 当n=1时，[0,1] n=2，[00,01,11,10] n=3,[000,001,011,010,110,111,101,100] ..
	 * 每多一位就是在最右边加0或者1，其实就是在原来的基础上乘以2，然后加1或者0，如果原来是偶数的，先加1在加0，原来是基数的先加0后加1
	 * 
	 * 思路是动态规划 时间11.09%,空间26.88%
	 * 
	 * @param n
	 */
	@Solve(title="89",keywords = "动态规划")
	public void grayCode() {
		int n = 3;
		LinkedList<Integer> list = new LinkedList<Integer>();
		if (n == 0) {
			list.add(0);
		} else {
			for (int i = 1; i <= n; i++) {
				if (list.size() < 1) {
					list.add(0);
					list.add(1);
				} else {
					int currentIndex = 0;
					while (currentIndex < Math.pow(2, i - 1)) {
						Integer num = list.pop();
						if (currentIndex % 2 == 0) {
							list.add(num * 2);
							list.add(num * 2 + 1);
						} else {
							list.add(num * 2 + 1);
							list.add(num * 2);
						}
						currentIndex++;
					}
				}
			}

		}
		System.out.println(list);

	}

	/**
	 * #868 二进制间距 给一个正整数n，求他的二进制两个连续1之间最长距离（注意连续），若是没有则返回0
	 * 
	 * 思路 比如说5049对应的二进制是1001110111001 按照整数转二进制是从右到左算出的
	 * 
	 * 当我遇到第一个1的时候我先把当前距离标做1，那么后面遇到的0在知道先前有1的情况下距离+1，然后遇到下一个1时，判断是否大于最大距离，是的话赋值
	 * 无论怎么样遇到1都要重置距离
	 * 
	 */
	@Solve(title="868")
	public void binaryGap() {
		int N = 5049;
		int max = 0;
		System.out.println(Integer.toBinaryString(N));
		int currentGrap = 0;
		while (N > 0) {
			int remainder = N % 2;
			N = N / 2;
			if (remainder == 0 && currentGrap > 0) {
				currentGrap++;
			} else if (remainder == 1) {
				if (currentGrap > max) {
					max = currentGrap;
				}
				currentGrap = 1;
			}

		}
		System.out.print(max);
	}

	/**
	 * #58 最后一个单词的长度 给出一串字符串，包含大小写和空格符，返回最后一个单词的长度，没有，返回0 既然是要算最后一个单词，那么从后面开始计数
	 * 
	 */
	@Solve(title="58")
	public int lengthOfLastWord() {
		String s = "rew";
		int count = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) != ' ') {
				count++;
			} else if (count > 0) {
				return count;
			}
		}
		return count;
	}

	/**
	 * #55 跳跃游戏
	 * 
	 * 给一个非负整数，每个元素的值代表你可以跳跃的最大长度 判断是否可以跳到最后一个位置
	 * 
	 * 
	 * 我的思路：如果数组中没有0，那么肯定能跳过去，所以在遇到0的时候判断是否之前有能调到0之后一位的数，即存在X，使得X>X到0的距离+1——》求0之前最大的跳跃距离
	 * 由于每移动一位，可跳距离会长一位，所以max--
	 * 
	 * 其他人的思路：从倒数第二位开始遍历，初始长度为1，如果当前位置值<长度，说明这个位置跳不过去，他不是跳跃点；那么继续往前移动，如果当前位置值>长度，说明该点可以当做一个基点
	 * 只要判断到是否可以跳到该点，此时长度重置为1
	 * 
	 * 
	 */
	@Solve(title="55")
	public boolean canJump() {
		int[] nums = { 5, 4, 0, 2, 0, 1, 0, 1, 0 };
		int max = 0;// 当前可跳的最大距离
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0 && i != nums.length - 1) {
				if (max < 2) {// 可跳距离小于2，说明跳不了
					return false;
				}
				max--;
			} else {
				if (nums[i] >= max) {// 如果当前值大于最大可跳距离就将此值赋值给max
					max = nums[i];
				} else {// 否则每移动一位可跳距离减1
					max--;
				}
			}
		}
		return true;
	}

	/**
	 * #134 加油站 给两个数组gas和cost gas表示该点可加的油，cost表示该点到下一站点的花费的油量，如果车剩余油量+加的油量<花费的油量
	 * 那么就开不到下一个站点了 求可以行驶完的站点，如果没有返回-1
	 * 我的思路：遇到第一个gas[i]>=cost[i]的然后循环一圈，满足条件就输出，时间复杂度O(n^2)
	 *
	 * 其他人的思路：两个变量记录一个是sum(gas)-sum(cost),如果这个值<0说明到达不了，大于等于0可以到达，还有一个值current=sum(gask...gasi)-sum(costk...costi),如果current<0，说明
	 * k到达不了i，那么k到i之间不存在j使得j可以到达i。 因为k到j的油量>0
	 * current(k,j)+current(j,i)<0,而current(k,j)>0,所以current(j,i)<0所以当current<0时，取i+1为新的起点
	 */
	@Solve(title="134")
	public boolean canCompleteCircuit() {
		int[] gas = { 4, 3, 4 };
		int[] cost = { 3, 5, 3 };
		for (int i = 0; i < gas.length; i++) {
			if (gas[i] >= cost[i]) {
				int available = 0;
				for (int j = i; j < i + gas.length; j++) {
					available = available + gas[j % gas.length] - cost[j % gas.length];
					if (available < 0) {
						break;
					}
					if (j == i + gas.length - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 其他人的解法
	 * 其他人的思路：两个变量记录一个是sum(gas)-sum(cost),如果这个值<0说明到达不了，大于等于0可以到达，还有一个值current=sum(gask...gasi)-sum(costk...costi),如果current<0，说明
	 * k到达不了i，那么k到i之间不存在j使得j可以到达i。 因为k到j的油量>0
	 * current(k,j)+current(j,i)<0,而current(k,j)>0,所以current(j,i)<0所以当current<0时，取i+1为新的起点
	 */
	public void canCompleteCircuit1() {
		int[] gas = { 4, 3, 4 };
		int[] cost = { 3, 5, 3 };
		int sum = 0, current = 0, station = 0;
		for (int i = 0; i < gas.length; i++) {
			sum = gas[i] - cost[i];
			current = gas[i] - cost[i];
			if (current < 0) {
				current = 0;
				station = i + 1;
			}
		}
		System.out.print(sum < 0 ? -1 : station);
	}

	/**
	 * #198 打家劫舍 给一个数组表示一组相邻的房间里面的金额，如果连续偷两个房间，报警器会响，求在不触动报警器的情况下，能偷到的最大金额 思路
	 * prexMax表示之前的最大，这题看别人解的
	 */
	@Solve(title="198")
	public void rob() {
		int[] nums = { 9, 2, 3, 4, 1, 6 };
		int prevMax = 0;
		int currMax = 0;
		for (int x : nums) {
			int temp = currMax;
			currMax = Math.max(prevMax + x, currMax);
			prevMax = temp;
		}
		System.out.println(currMax);
	}

	/**
	 * #453. 最小移动次数使数组元素相等 每次移动都会使n-1个数增加1，求使数组中所有元素都相同所要移动的最小次数 10.78;11.54
	 */
	@Solve(title="453")
	public void minMoves() {
		int[] nums = { 1, 2, 2, 2 };
		// 先排序
		Arrays.sort(nums);
		int sum = 0;
		for (int i = nums.length - 2; i >= 0; i--) {
			sum = (nums[i + 1] - nums[i]) * (nums.length - i - 1) + sum;
		}
		System.out.println(sum);

	}

	/**
	 * #5174 健身计划评估
	 * 
	 */
	@Solve(title="5174")
	public void dietPlanPerformance(int[] calories, int k, int lower, int upper) {
		int begin = 0;
		int sum = 0;
		int result = 0;
		for (int i = 0; i < calories.length; i++) {
			sum = sum + calories[i];
			if (i >= k) {
				if (sum < lower) {
					result += -1;
				} else if (sum > upper) {
					result += 1;
				}
				sum -= calories[begin];
				begin++;
			}
		}
		System.out.print(result);
	}

	/**
	 * #551 学生出勤记录I A-缺勤 L-迟到 P-正常
	 * 
	 * 不超过一个A且不超过连续两个L则可以被奖励（就是连续两个可以，连续3个或者3个以上不可以）99.80%/86.03%
	 * 
	 * 看到其他的方法秀啊：根据 s.indexOf("A")!=s.lastIndexOf("A")
	 * 判断是否有两个A，通过s.indexOf("LLL")!=-1判断是否有两个以上L
	 */
	@Solve(title="551")
	public boolean checkRecord() {
		String s = "LPALPLP";
		int countA = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'A') {
				countA++;
				if (countA > 1) {
					return false;
				}
			} else if (s.charAt(i) == 'L') {
				if (i + 2 < s.length() && s.charAt(i + 2) == 'L' && s.charAt(i + 1) == 'L') {
					return false;
				}
			}
		}
		return true;
	}

}
