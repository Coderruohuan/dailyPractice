package collection;

import java.util.ArrayDeque;

public class DequeClass {

	public static void main(String[] args) {
		// 创建队列
		ArrayDeque<String> arrayDeque = new ArrayDeque<String>();
		arrayDeque.add("one");
		// 将指定元素插入队列开头
		arrayDeque.addFirst("two");// arrayDeque.offerFirst(e)功能类似
		arrayDeque.addFirst("two");
		// 将指定元素插入队列尾部
		arrayDeque.addLast("three");// arrayDeque.offerLast(e)功能类似
		System.out.println(arrayDeque);
		// 获取队列第一个元素或者最后一个元素，但不删除（类似功能peekXXXX）
		System.out.println(arrayDeque.getFirst());
		System.out.println(arrayDeque.getLast());
		// 取出栈顶元素并删除，相当于removeFirst；另removeLast取出队列最后一个元素
		System.out.println(arrayDeque.pop());
		System.out.println(arrayDeque);
		// 将元素插入栈顶
		arrayDeque.push("four");
		arrayDeque.offerLast("two");
		System.out.println(arrayDeque);
		// 删除队列第一个出现two的元素; arrayDeque.removeLastOccurrence(o)
		arrayDeque.removeFirstOccurrence("two");
		System.out.println(arrayDeque);

	}

}
