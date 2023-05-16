import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FoodTester {

	public static final int SIZE_OF_SERVING_QUEUE = 3;
	public static final int NUMBER_OF_COOK_THREADS = 7;
	public static final int NUMBER_OF_SERVER_THREADS = 7;

	public static void main(String[] args) {
		int iDEThreadCount = Thread.activeCount();
		 
		List<Food> foodList = new ArrayList<>();
		foodList.add(new Food("Spinach Dip", 2, 1));
		foodList.add(new Food("Burger", 5, 1));
		foodList.add(new Food("Pasta", 4, 3));
		foodList.add(new Food("Baked Alaska", 6, 20));
		foodList.add(new Food("Salad", 1, 1));
		foodList.add(new Food("Bruchetta", 3, 1));
		foodList.add(new Food("Bread", 1, 1));
		foodList.add(new Food("Fried Green Tomatoes", 2, 1));

		// convert List to BlockingQueue to allow multiple cook threads
		BlockingQueue<Food> orderQueue = new ArrayBlockingQueue<>(foodList.size());
		for (Food item : foodList) {
			orderQueue.add(item);
		}
		BlockingQueue<Food> serverQueue = new ArrayBlockingQueue<>(SIZE_OF_SERVING_QUEUE);
		AtomicBoolean cooksDone = new AtomicBoolean(false);  // set to true when all cook threads have finished
		AtomicInteger numberOfCooks = new AtomicInteger(0);
		AtomicInteger numberOfServers = new AtomicInteger(0);
		
		Thread[] cookThreads = new CookThread[NUMBER_OF_COOK_THREADS];
		Thread[] serverThreads = new ServeThread[NUMBER_OF_SERVER_THREADS];

		// initialize and start cook threads
		for (int i = 0; i < NUMBER_OF_COOK_THREADS; i++) {
			cookThreads[i] = new CookThread(orderQueue, serverQueue, cooksDone, "Cook " + i, numberOfCooks);  
			cookThreads[i].start();
		}
		for (int i = 0; i < NUMBER_OF_SERVER_THREADS; i++) {
			serverThreads[i] = new ServeThread(serverQueue, cooksDone, "Server " + i, numberOfServers);  
			serverThreads[i].start();
		}
		
		int programTimeCounter=0;
		while(Thread.activeCount()>iDEThreadCount) {
			System.out.println("TIME " + programTimeCounter);
			programTimeCounter++;
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				
			}
		}

		
		// USE STREAMS HERE ON THE INITIAL LIST! 
		// NOTE: THIS PART HAS NOTHING TO DO WITH THE THREADS- JUST MORE STREAM PRACTICE! :)
		// USE METHOD REFERENCES!
		int totalCookTime = foodList.stream().mapToInt(Food::getCookTime).sum();
		int totalServeTime = foodList.stream().mapToInt(Food::getServeTime).sum();
		System.out.println("Total Cook Time = " + totalCookTime);
		System.out.println("Total Serve Time = " + totalServeTime);	
		System.out.println("Program Time = " + programTimeCounter);
		
	}

}
