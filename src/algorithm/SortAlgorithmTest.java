package algorithm;

import org.junit.Test;

import algorithm.util.RunUtil;
import algorithm.util.Solve;

/**
 * 排序算法
 * 
 * @author wwn
 * @date 2019年10月6日
 *
 */
public class SortAlgorithmTest {
	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(SortAlgorithmTest.class);
	}

	/**
	 * 冒泡排序：比较连续相邻的两个元素，如果是两个元素是降序关系，则互换它们的值，排序过后，最大的在最底下 时间复杂度 o(n^2)
	 */
	@Solve
	public int[] bubbleSort() {
		int[] arrays = { 2, 9, 5, 4, 8, 1 };
		// 是否需要排序
		boolean needSort = true;
		// 总共要排arrays.length-1遍
		for (int n = 1; n < arrays.length && needSort; n++) {
			needSort = false;
			// 第一遍，全部要比，第二遍只要比前面arrays.length-1个...第n遍，比arrays.length-n
			for (int i = 0; i < arrays.length - n; i++) {
				if (arrays[i] > arrays[i + 1]) {
					int temp = arrays[i];
					arrays[i] = arrays[i + 1];
					arrays[i + 1] = temp;
					needSort = true;
				}
			}
		}
		return arrays;
	}

	/**
	 * 归并排序算法：算法将数组一分为二，递归调用归并排序算法，直至一个元素；然后将元素合并 时间复杂度o(nlogn)
	 * 
	 * @return
	 */
	@Solve
	public int[] mergeSortTest() {
		int[] arrays = { 2, 9, 5, 4, 8, 1 };
		return mergeSort(arrays);
	}

	// 拆分
	private int[] mergeSort(int[] array) {
		if (array.length > 1) {
			// 分两半
			int[] oneArray = new int[array.length / 2];
			System.arraycopy(array, 0, oneArray, 0, array.length / 2);
			int[] result1 = mergeSort(oneArray);// 不断递归
			int twoLength = array.length - array.length / 2;
			int[] twoArray = new int[twoLength];
			System.arraycopy(array, array.length / 2, twoArray, 0, twoLength);
			int[] result2 = mergeSort(twoArray);
			array = merge(result1, result2);
		}
		return array;
	}

	// 归并
	private int[] merge(int[] arr1, int[] arr2) {
		int index1 = 0;
		int index2 = 0;
		int resultIndex = 0;
		int[] resultArray = new int[arr1.length + arr2.length];// 将两个有序的数组合成1个
		while (index1 < arr1.length && index2 < arr2.length) {
			if (arr1[index1] < arr2[index2]) {
				resultArray[resultIndex++] = arr1[index1++];
			} else {
				resultArray[resultIndex++] = arr2[index2++];
			}
		}
		// 把剩下的补全
		while (index1 < arr1.length) {
			resultArray[resultIndex++] = arr1[index1++];
		}
		while (index2 < arr2.length) {
			resultArray[resultIndex++] = arr2[index2++];
		}
		return resultArray;
	}

	/**
	 * 快速排序：选取一个主元，将数组分成两部分，一部分小于主元，一部分大于等于主元，然后子数组不断重复以上过程直至为1
	 * 
	 * @return
	 */
	@Solve
	public int[] quickSortTest() {
		int[] arr = {5, 4, 7, 8, 2, 7, 8, 5, 6, 3};
		if(arr.length>1) {
			quickSort(arr,0, arr.length-1);	
		}
		return arr;
	}
    /**
     * 注意high先走
     * @param arr
     * @param start
     * @param end
     * @return
     */
	private int[] quickSort(int[] arr, int start, int end) {
		int low = start + 1;
		int high = end;
		while (low < high) {
			while (arr[high] > arr[start] && low < high) {
				high--;
			}
			while (arr[low] <= arr[start] && low < high) {
				low++;
			}
			// 说明找到了，否则就是start>=end
			if (low < high) {
				int temp = arr[low];
				arr[low] = arr[high];
				arr[high] = temp;
			}
		}
		// 此时i,j碰面，交换结束；假设存在情景所有的数都比主元大，那么是不用交换的
		if (arr[start] > arr[low]) {
			int temp = arr[start];
			arr[start] = arr[low];
			arr[low] = temp;
		}
		if(low>start+1) {
			quickSort(arr, start, low);
		}
		if (high < end) {
			quickSort(arr, high + 1, end);
		}
		return arr;
	}
	
	
	/**
	 * 堆排序
	 * 堆排序利用的数据结构是完全二叉树，完全二叉树有如下特性：<br>
	 * 1、大顶堆（根节点的值大于左右子节点的值），小顶堆（根节点的值小于左右子节点的值）
	 * 2、每一个根节点都有左右子节点（除了倒数第二层节点），倒数第二层有右节点则必须有左节点。
	 * @return
	 */
	@Solve
	public int[] Heapsort() {
		int[] array= {};
		//将数组转化为完全二叉树
		
		
		
		return array;
		
	}
	
	
	/**
	 * 选择排序：每次选择最大的（或最小的）数放到排序列的起始位置和冒泡排序的算法相比，选择排序只动了最大值，感觉比冒泡的时间复杂度大一点
	 * @return
	 */
	@Solve
	public int[] selectSort() {
		int[] array= {5, 4, 7, 8, 2, 7, 8, 5, 6, 3};
		for(int i=0;i<array.length-1;i++) {
			int max=i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j]>array[max]) {
					max=j;
				}
			}
			if(max!=i) {
				int temp=array[i];
				array[i]=array[max];
				array[max]=temp;
			}
		}
		System.out.println("选择排序的最后结果是：");
		return array;
	}
	
	/**
	 * 插入排序：将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，适用于少量数据的
	 * 插入方式有区别，我是用待排序的数去有序序列中插入，
	 * @return
	 */
    @Solve
	public  int[] insertionSort() {
		int[] array= {5, 4, 7, 8, 2, 7, 8, 5, 6, 3};
		for(int i=1;i<array.length;i++) {
			int j=i-1;
			while(j>=0) {
				if(array[j]<=array[i]) {
					int temp=array[i];
					for(int k=i-1;k>=j+1;k--) {		
						array[k+1]=array[k];
					}
					array[j+1]=temp;
					break;
				}else if(j==0&&array[i]<array[j]) {
					int temp=array[i];
					for(int k=i-1;k>=j;k--) {		
						array[k+1]=array[k];
					}
					array[j]=temp;
					break;
				}
				j--;
			}		
		}
		System.out.println("插入排序的最后结果是：");
		return array;
		
	}
	
	/**
	 * 插入排序：借鉴
	 * @return
	 */
	public  int[] insertionSort2() {
		int[] array= {5, 4, 7, 8, 2, 7, 8, 5, 6, 3};
		for(int i=1;i<array.length;i++) {
			int key=array[i];
			int j=i-1;
			while(j>=0 &&array[j]>key) {
				array[j+1]=array[j];
				j--;
			}
			array[j+1]=key;
		}
		System.out.println("插入排序2的最后结果是：");
		return array;
	}
	
	/**
	 * 希尔排序
	 * @return
	 */
	public  int[] shellSort() {
		int[] array= {};
		return array;
	}
	
	
   

}
