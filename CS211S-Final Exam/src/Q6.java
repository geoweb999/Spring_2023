
public class Q6 {

	public static void main(String[] args) {
        MessageThread thread1 = new MessageThread("Geoffrey", 10);
        MessageThread thread2 = new MessageThread("winter!", 100);
        thread1.start();
        thread2.start();
    }
}
