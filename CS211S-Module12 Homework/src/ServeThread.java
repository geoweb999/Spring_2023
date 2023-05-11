import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServeThread extends Thread {
	
    private BlockingQueue<Food> orderQueue;    // this Queue is present only to show the size in print statements
    private BlockingQueue<Food> serverQueue;
    private AtomicBoolean cooksDone;
    private String serverName;

    public ServeThread(BlockingQueue<Food> orderQueue, BlockingQueue<Food> serverQueue, AtomicBoolean cooksDone, String serverName) {
    	this.orderQueue = orderQueue;
    	this.serverQueue = serverQueue;
    	this.cooksDone = cooksDone;
    	this.serverName = serverName;
    }

    @Override
    public void run() {
		while (!cooksDone.get()) {
			// cookDone is true only when all cook threads are done
			// if the server queue is empty but cooksDone is false, at least one cook is cooking
			while (!serverQueue.isEmpty()) {
	            try {
	            	Food food = serverQueue.take();
	                System.out.println(serverName + ": started " + food.getName() + " *** Order queue: " + orderQueue.size() + " Server queue: " + serverQueue.size());
	                Thread.sleep(1000 * food.getServeTime()); // Cook the food
	                System.out.println(serverName + ": finished " + food.getName() + " *** Order queue: " + orderQueue.size() + " Server queue: " + serverQueue.size());
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	       	}
		}
		System.out.println(serverName + ": Finished serving");
    }
}