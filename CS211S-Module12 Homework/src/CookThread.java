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
    
    public void printStatus(String msg) {
    	System.out.println(cookName + msg + "\t*** Order queue: " + orderQueue.size() + " Serving Queue: " + serverQueue.size());
    }

    @Override
    public void run() {
		while (!orderQueue.isEmpty()) {
            Food food = null;
            try {
            	food = orderQueue.take();
                printStatus(" started cooking " + food.getName());
                Thread.sleep(1000 * food.getCookTime()); // Cook the food
                printStatus(" finished cooking " + food.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                printStatus(" adding to server queue " + food.getName());
            	serverQueue.put(food); // will wait if queue is full
                printStatus(" finished adding to server queue " + food.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		numberOfCooks.decrementAndGet();
		printStatus(" finished cooking, " + numberOfCooks.get() + " cooks left.");
        try {
           Thread.sleep(100); // pack up and go home (fixes race condition)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		cooksDone.set(numberOfCooks.get() == 0);
	}

}
