package zzzStuff;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueTester {
	public static void main(String[] args) {
		Queue<Pair<Integer, String>> funque = new LinkedList<>();
		funque.add(new Pair(1,"One"));
		funque.add(new Pair(2,"Two"));
		System.out.println(funque);
	}
}
