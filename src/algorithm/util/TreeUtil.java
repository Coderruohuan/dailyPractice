package algorithm.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import algorithm.model.TreeNode;

public class TreeUtil {
	/**
	 * 递归生成树 对于一个满二叉树父编号和子编号存在以下关系:<br>
	 * 左子编号=2*父编号 右子编号=2*父编号+1<br>
	 * 证明： <br>
	 * <li>因为对于第i层的树来说（i从1开始），总共有2^i-1个节点
	 * <li>假设现在有父节点k在第i层的第一个，那么他的前面共有2^(i-1)-1个节点，k的编号是2^(i-1)
	 * <li>k的左子节点是下一层的第一个，编号为2^i<br>
	 * 得出：左子节点=父节点*2
	 * 
	 * @param index=1
	 * @param arrays
	 * @return
	 */
	public static TreeNode createTree(Integer[] arrays, int index) {
		TreeNode node = null;
		if (index - 1 < arrays.length) {
			if (arrays[index - 1] != null) {
				node = new TreeNode(arrays[index - 1]);// 树从1开始，数组从0开始
				// 左节点
				node.left = createTree(arrays, 2 * index);
				node.right = createTree(arrays, 2 * index + 1);
			}
		}
		return node;
	}

	/**
	 * 中序遍历，用的递归方法
	 * 
	 * @param node
	 * @param nodeList
	 */
	public static void inorderTraversal(TreeNode node, List<Integer> nodeList) {
		if (node != null) {
			inorderTraversal(node.left, nodeList);
			nodeList.add(node.val);
			inorderTraversal(node.right, nodeList);
		}
	}

	/**
	 * 中序遍历2，用栈
	 */
	public static void inorderTraversal2(TreeNode node, List<Integer> nodeList) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (node != null && stack.size() > 0) {
			while (node != null) {
				stack.add(node);// 把根节点和左节点保存到stack
				node = node.left;
			}
			node = stack.pop();
			nodeList.add(node.val);
			node = node.right;
		}
	}
	/**
	 * TODO  懒得搞，略略略
	 * @param array
	 * @return
	 */
	public  static TreeNode buildCompleteBinaryTree (int[] array) {
		if(array==null||array.length<1) {
			return null;
		}
		TreeNode tree=new TreeNode(array[0]);
		Stack<TreeNode> stack=new Stack<>();
		stack.add(tree);
		for(int i=1;i<array.length;i++) {
			//TreeNode node=stack.pop();
//			if() {
//				
//			}
		}
		return null;
	}
	
	/**
	 * 树的广度优先搜索 breath first search
	 * 用队列，先进先出
	 * 一个while，一个for
	 * @param root
	 * @return
	 */
    public static List<List<Integer>> BSF(TreeNode root){
    	Queue<TreeNode> queue=new LinkedList<>();
    	List<List<Integer>> resultList=new ArrayList<>();
    	if(root!=null) {
    		queue.add(root);
    	}
    	while(queue.size()>0) {
    		int size=queue.size();
    		List<Integer> list=new ArrayList<Integer>();
    		for(int i=0;i<size;i++) {
    			TreeNode node=queue.poll();
    			list.add(node.val);
    			if(node.left!=null) {
    				queue.add(node.left);
    			}
    			if(node.right!=null) {
    				queue.add(node.right);
    			}	
    		}
    		resultList.add(list);
    	}
    	return resultList;  	
    }
	
    
    
    
	public static void main(String[] args) {
		TreeNode root=createTree(new Integer[] {1,2,3,4,5,6,7}, 1);
		List<List<Integer>> list=BSF(root);
		System.out.print(list);
	}
	
	

}
