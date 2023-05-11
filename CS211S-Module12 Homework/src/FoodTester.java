import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FoodTester {

	public static final int SIZE_OF_SERVING_QUEUE = 3;
	
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
		// INITIALIZE AND START YOUR THREADS HERE!
		// convert List to BlockingQueue to allow multiple cook threads
		BlockingQueue<Food> orderQueue = new ArrayBlockingQueue<>(foodList.size());
		for (Food item : foodList) {
			orderQueue.add(item);
		}
		BlockingQueue<Food> serverQueue = new ArrayBlockingQueue<>(SIZE_OF_SERVING_QUEUE);
		AtomicBoolean cooksDone = new AtomicBoolean(false);  // set to true when all cook threads have finished
		AtomicInteger numberOfCooks = new AtomicInteger(0);
		Thread cooker1 = new CookThread(orderQueue, serverQueue, cooksDone, "Cook 1", numberOfCooks);  
		cooker1.start();
		Thread cooker2 = new CookThread(orderQueue, serverQueue, cooksDone, "Cook 2", numberOfCooks);  
		cooker2.start();
		
		Thread server1 = new ServeThread(orderQueue, serverQueue, cooksDone, "Server 1");
		server1.start();
		Thread server2 = new ServeThread(orderQueue, serverQueue, cooksDone, "Server 2");
		server2.start();
		Thread server3 = new ServeThread(orderQueue, serverQueue, cooksDone, "Server 3");
		server3.start();
//		Thread server4 = new ServeThread(orderQueue, serverQueue, cooksDone, "Server 4");
//		server4.start();
		
			
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
		int totalCookTime = foodList.stream()
                .mapToInt(Food::getCookTime)
                .sum();
		int totalServeTime = foodList.stream()
                .mapToInt(Food::getServeTime)
                .sum();
		System.out.println("Total Cook Time = " + totalCookTime);
		System.out.println("Total Serve Time = " + totalServeTime);	
		System.out.println("Program Time = " + programTimeCounter);
		
	}

}
