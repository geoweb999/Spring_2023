package zzzStuff;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueTester {
	public static void main(String[] args) {
		Queue<PriorityPair<Integer, String>> funque = new LinkedList<>();
		funque.add(new PriorityPair(1,"Process2"));
		funque.add(new PriorityPair(2,"Process1"));
		System.out.println(funque);
	}
}
