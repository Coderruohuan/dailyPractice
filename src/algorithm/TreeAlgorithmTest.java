package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

import algorithm.model.TreeNode;
import algorithm.util.RunUtil;
import algorithm.util.Solve;
import algorithm.util.TreeUtil;

/**
 * 相关树的算法 <tbody>相关知识点
 * <li>节点的度是指该节点拥有子节点的数目
 * <li>树的深度是指树的高度，有几层
 * <li>二叉树的第i层最多有2^(i-1)个节点
 * 
 * @author wwn
 *
 */
public class TreeAlgorithmTest {
	@Test
	public void mainTest() throws Exception {
		RunUtil.runMethod(TreeAlgorithmTest.class);
	}

	/**
	 * #98判断是否是有效的二叉搜索树 二叉搜索树左子节点小于当前节点，右子节点大于当前节点，他的子树也满足
	 * 思路：按左根右输出数组，看是否按序排列====》我的思路都是保存到一组数组中然后遍历的，其实可以在遍历的过程中比较
	 */
	@Solve(keywords = "深度优先搜索")
	public void isValidBST() {
		TreeNode tree = TreeUtil.createTree(new Integer[] { 1, 1 }, 1);
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int currentValue = Integer.MIN_VALUE;
		// 深度遍历,即中序遍历，左--中--右
		while (tree != null || stack.size() > 0) { // 遍历方法1，用栈
			if (tree != null) {
				stack.add(tree);
				tree = tree.left;
			} else {
				tree = stack.pop();
				if (tree.val <= currentValue) { // 验证方法1：在中序遍历的过程中比较
					// return false;s
				}
				list.add(tree.val);// 验证方法2：保存在list里，然后遍历比较
				tree = tree.right;
			}
		}
		// TreeUtil.treeToList(tree, list); 遍历方法2，递归方法

//    	   for(int i=0;i<list.size()-1;i++) {   验证方法2
//    		   if(list.get(i+1)<=list.get(i)) {
//    			   System.out.print(false);
//    			   System.exit(0);
//    		   }
//    	   }
		System.out.print(true);
	}

	/**
	 * #938 二叉树的范围和<br>
	 * 就是给出左右范围L和R，求在这个范围内的节点和
	 * 
	 * @return
	 */
	@Solve(content = "4ms/43.7MB;26.06%/92.68")
	public int rangeSumBST() {
		TreeNode root = TreeUtil.createTree(new Integer[] { 10, 5, 15, 3, 7, 13, 18, 1, null, 6 }, 1);
		int L = 6;
		int R = 10;
		Stack<TreeNode> stack = new Stack<>();
		int sum = 0;
		while (root != null) {
			if (root.val < L) {
				if (root.right != null) {
					stack.add(root.right);
				}
			} else if (root.val > R) {
				if (root.left != null) {
					stack.add(root.left);
				}
			} else {
				if (root.right != null) {
					stack.add(root.right);
				}
				if (root.left != null) {
					stack.add(root.left);
				}
				sum += root.val;
			}
			root = stack.size() > 0 ? stack.pop() : null;
		}
		return sum;
	}

	/**
	 * #100 相似的树
	 * 
	 * @return
	 */
	@Solve(title = "100", content = "1ms/32.04%;34.4MB;82.66%")
	public boolean isSameTree() {
		TreeNode p = TreeUtil.createTree(new Integer[] {}, 1);
		TreeNode q = TreeUtil.createTree(new Integer[] {}, 1);
		Stack<TreeNode> pStack = new Stack<TreeNode>();
		Stack<TreeNode> qStack = new Stack<TreeNode>();
		pStack.add(p);
		qStack.add(q);
		while (pStack.size() > 0 && qStack.size() > 0) {
			TreeNode ptreeNode = pStack.pop();
			TreeNode qtreeNode = qStack.pop();
			if (ptreeNode.val != qtreeNode.val) {
				return false;
			}
			if (ptreeNode.left != null && qtreeNode.left != null) {
				pStack.push(ptreeNode.left);
				qStack.push(qtreeNode.left);
			} else if (ptreeNode.left == null ^ qtreeNode.left == null) {
				return false;
			}

			if (ptreeNode.right != null && qtreeNode.right != null) {
				pStack.push(ptreeNode.right);
				qStack.push(qtreeNode.right);
			} else if (ptreeNode.right == null ^ qtreeNode.right == null) {
				return false;
			}
		}

		if (pStack.size() + qStack.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 别人的思路，递归调用
	 * 
	 * @return
	 */
	@Solve
	public boolean isSameTreeTwo() {
		TreeNode p = TreeUtil.createTree(new Integer[] { 1, 2 }, 1);
		TreeNode q = TreeUtil.createTree(new Integer[] { 1, 2, 3 }, 1);
		return isSameTree(p, q);
	}

	private boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null ^ q == null) {
			return false;
		}
		if (p == null) {
			return true;
		}
		if (p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	/**
	 * #404所有左叶子之和（注意不是左节点）
	 * 
	 * @return
	 */
	@Solve(title = "404", content = "0ms/100%;36.1;79.17%")
	public int sumOfLeftLeaves() {
		TreeNode root = TreeUtil.createTree(new Integer[] { 3, 9, 20, 12, null, 15, 7, null, 4, 23, null, 9 }, 1);
		return sumLeft(root);
	}

	private int sumLeft(TreeNode node) {
		int sum = 0;
		if (node != null) {
			if (node.left != null && node.left.left == null && node.left.right == null) {
				sum += node.left.val;
			} else {
				sum += sumLeft(node.left);
			}
			if (node.right != null) {
				sum += sumLeft(node.right);
			}
		}
		return sum;
	}

	/**
	 * #104 二叉树的最大深度
	 * 
	 * @return
	 */
	@Solve(title = "104", content = "0ms/100%;37.4MB,46,86%")
	public int maxDepth() {
		TreeNode root = TreeUtil.createTree(new Integer[] {}, 1);
		return getTreeDepth(root);
	}

	private int getTreeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right));
	}

	/**
	 * #94 二叉树的中序遍历 递归的中序遍历
	 * 
	 * @return
	 */
	@Solve(title = "94", content = "1ms/85.05%;34.7MB/39.44%")
	public List<Integer> inorderTraversal() {
		TreeNode root = TreeUtil.createTree(new Integer[] { 1, null, 2, null, null, 3 }, 1);
		List<Integer> list = new ArrayList<>();
		getTraversal(root, list);
		return list;
	}

	private void getTraversal(TreeNode root, List<Integer> list) {
		if (root != null) {
			if (root.left != null) {
				getTraversal(root.left, list);
			}
			list.add(root.val);
			if (root.right != null) {
				getTraversal(root.right, list);
			}
		}
	}

	/**
	 * #1161 最大层内元素和 层次遍历，求最大和的那层层数
	 * 
	 * @return
	 */
	@Solve(title = "1161", content = "18ms/49.21%;42.4MB/100%")
	public int maxLevelSum() {
		TreeNode root = TreeUtil.createTree(new Integer[] { 1, 7, 0, 7, -8, null, null, 1, 2, 3, 4, null, 5 }, 1);
		if (root == null) {
			return -1;
		}
		int maxSum = root.val;
		int maxIndex = 1;
		int level = 1;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (queue.size() > 0) {
			int sum = 0;
			List<TreeNode> list = new ArrayList<>();
			// 遍历完一层
			while (queue.size() > 0) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					list.add(node.left);
				}
				if (node.right != null) {
					list.add(node.right);
				}

				sum += node.val;
			}

			if (sum > maxSum) {
				maxSum = sum;
				maxIndex = level;
			}
			level++;
			// 然后再推入一层
			queue.addAll(list);
		}
		return maxIndex;
	}

	/**
	 * #814 二叉树剪枝
	 * 别人比我简洁，直接返回null或者node，不用返回true，false
	 * @return
	 */
	@Solve(title="814",content="0ms/100%;34.2/83.22%",keywords = "递归")
	public TreeNode pruneTree() {
		TreeNode root = TreeUtil.createTree(new Integer[] {}, 1);
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		if (root != null&&!isSatisfied(root)) {
			list.add(root); 
		}else {
			return null;
		}
		while (list.size() > 0) {
			TreeNode node=list.poll();
			if (node.left != null && node.left.val == 0 && isSatisfied(node.left)) {
				node.left = null;
			} else if(node.left != null) {
				list.push(node.left );
			}
			if (node.right != null && node.right.val == 0 && isSatisfied(node.right)) {
				node.right = null;
			} else if(node.right!=null){
				list.push(node.right );
			}
		}
		return root;

	}

	private boolean isSatisfied(TreeNode node) {
		if (node == null) {
			return true;
		} else if (node.val == 1) {
			return false;
		} else if (node.left == null && node.right == null) {
			return true;
		} else {
			return isSatisfied(node.left) & isSatisfied(node.right);
		}
	}
	
	/**
	 * #965 单值二叉树
	 * @return
	 */
	 @Solve(title="965",content="1ms/43.08%;34.4MB;79.81%")
	  public boolean isUnivalTree() {
		  TreeNode root=TreeUtil.createTree(new Integer[] {1,1,1,1,1,null,1}, 1);
		  //广度遍历
		  Queue<TreeNode> queue=new LinkedList<>();
		  if(root==null) {
			  return true;
		  }
		  int val=root.val;
		  queue.add(root);
		  while (queue.size() > 0) {
			int size=queue.size();
			for(int i=0;i<size;i++) {
				TreeNode node=queue.poll();
				if(node.val!=val) {
					return false;
				}
				if(node.left!=null) {
					queue.add(node.left);
				}
				if(node.right!=null) {
					queue.add(node.right);
				}		
			}
		  }
		  return true;
	  }
 
}
