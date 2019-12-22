package collection;

import java.util.PriorityQueue;

public class QueueClass {
	public static void main(String[] args) {
		PriorityQueue<Object> queue = new PriorityQueue<>();
		queue.add(2);
		queue.add(4);
		queue.add(3);
		System.out.println(queue);

	}

}
