public class ArrayQueue<T> implements QueueInterface<T> {
	
	protected T[] queue; // Circular array of queue entries and one unused location
	protected int frontIndex; // Index of front entry
	protected int backIndex; // Index of back entry
	private static final int DEFAULT_CAPACITY = 50;

	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	} 

	public ArrayQueue(int initialCapacity) {
		queue = (T[]) new Object[initialCapacity + 1];
		frontIndex = 0;
		backIndex = queue.length-1;
	} 

	public void enqueue(T element) {
		if(isArrayFull()) {
			expandArray();
		}		
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = element;
	} 

	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return queue[frontIndex];
		}
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
	} 

	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}
	

	public void clear() {
		for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length) {
			queue[index] = null;
		} 
		queue[backIndex] = null;

		frontIndex = 0;
		backIndex = queue.length - 1;
	} 

	protected boolean isArrayFull() {
		return frontIndex == ((backIndex + 2) % queue.length);
	}
	
	protected void expandArray() {
		int oldSize = queue.length;
		int newSize = 2 * oldSize;
		
		T[] biggerQueue = (T[]) new Object[newSize];
		
		int currentIndex = frontIndex;
		for (int index = 0; index < oldSize - 1; index++) {
			biggerQueue[index] = queue[currentIndex];
			currentIndex = (currentIndex + 1) % oldSize;
		}
		frontIndex = 0;
		backIndex = oldSize - 2;
		queue = biggerQueue; 
	}

	
   	public void splice(ArrayQueue<T> anotherQueue) {
   		if (anotherQueue.isEmpty()) {
   			return;
   		}
   		int anotherIndex = anotherQueue.frontIndex;
   		while (anotherIndex != (anotherQueue.backIndex + 1 % anotherQueue.queue.length)) {
   			T element = anotherQueue.queue[anotherIndex];
   			this.enqueue(element);
   			anotherIndex = (anotherIndex + 1) % anotherQueue.queue.length;
   		}
   		
   	} 
   	
   	public T getSecond() {
   		this.getFront();  // throws error if queue is empty
   		int nextIndex = (this.frontIndex + 1) % this.queue.length;
   		if (nextIndex == ((backIndex + 1) % queue.length)) {
   			// there is no second element in queue, throw error (exception class exists)
			throw new EmptySecondException();
		} else {
			return queue[nextIndex];
   		}
   	}
} 
