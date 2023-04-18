import java.util.PriorityQueue;
import java.util.Queue;

public class Homework {

	public static void main(String[] args) {
		
		PriorityQueue<String> priorityQueue = new PriorityQueue<>();
		priorityQueue.add("priority-1, obj-C");
		priorityQueue.add("priority-2, obj-F");
//		priorityQueue.add(priorityQueue.getFront());
		
		while (!priorityQueue.isEmpty()) {
			String pair = priorityQueue.poll();
			System.out.println(pair);
		}
	}
}
