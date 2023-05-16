import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ServeThread extends Thread {
	
    private BlockingQueue<Food> serverQueue;
    private AtomicBoolean cooksDone;
    private String serverName;
    private AtomicInteger numberOfServers;

    public ServeThread(BlockingQueue<Food> serverQueue, AtomicBoolean cooksDone, String serverName, AtomicInteger numberOfServers) {
    	this.serverQueue = serverQueue;
    	this.cooksDone = cooksDone;
    	this.serverName = serverName;
    	this.numberOfServers = numberOfServers;
    	this.numberOfServers.incrementAndGet();
    }
    
    public void printStatus(String msg) {
    	System.out.println(serverName + msg + "\t*** Server queue: " + serverQueue.size());
    }

    @Override
    public void run() {
    	synchronized(cooksDone) {
			while (!cooksDone.get()) {
				// cookDone is true only when all cook threads are done
				// if the server queue is empty but cooksDone is false, at least one cook is cooking
				while (!serverQueue.isEmpty()) {
		            try {
		            	Food food = serverQueue.take();
		                printStatus(" Now serving " + food.getName());
		                Thread.sleep(1000 * food.getServeTime()); // Cook the food
		                printStatus(" Finished serving " + food.getName());
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		       	}
			}
    	}
		synchronized(numberOfServers) {
			numberOfServers.decrementAndGet();
			printStatus(" DONE: " + serverName + ": ++++++++ Finished serving.  Number of servers left: " + numberOfServers.get());
		}
		
    }
}