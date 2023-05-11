import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CookThread extends Thread {
	
    private BlockingQueue<Food> orderQueue;
    private BlockingQueue<Food> serverQueue;
    private AtomicBoolean cooksDone;
    private String cookName;
    private AtomicInteger numberOfCooks;


    public CookThread(BlockingQueue<Food> orderQueue, BlockingQueue<Food> serverQueue, AtomicBoolean cooksDone, String cookName, AtomicInteger numberOfCooks) {
    	this.orderQueue = orderQueue;
    	this.serverQueue = serverQueue;
    	this.cooksDone = cooksDone;
    	this.cookName = cookName;
    	this.numberOfCooks = numberOfCooks;
    	this.numberOfCooks.incrementAndGet();
    }

    @Override
    public void run() {
		while (!orderQueue.isEmpty()) {
            Food food = null;
            try {
            	food = orderQueue.take();
                System.out.println(cookName + ": started " + food.getName() + " *** Order queue: " + orderQueue.size() + " Server queue: " + serverQueue.size());
                Thread.sleep(1000 * food.getCookTime()); // Cook the food
                System.out.println(cookName + ": finished " + food.getName() + " *** Order queue: " + orderQueue.size() + " Server queue: " + serverQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(cookName + ": adding to server queue " + food.getName() + " *** Order queue: " + orderQueue.size() + " Server queue: " + serverQueue.size());
            	serverQueue.put(food); // will wait if queue is full
            	System.out.println(cookName + ": added to server queue " + food.getName() + " *** Order queue: " + orderQueue.size() + " Server queue: " + serverQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		numberOfCooks.decrementAndGet();
		System.out.println(cookName + " finished cooking, " + numberOfCooks.get() + " cooks left.");
		cooksDone.set(numberOfCooks.get() == 0);
	}

}
