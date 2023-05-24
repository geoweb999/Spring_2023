public class MessageThread extends Thread {
	
    private String message;
    private int printNumber;

    public MessageThread(String message, int printNumber) {
        this.message = message;
        this.printNumber = printNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < printNumber; i++) {
            System.out.println(message);
        }
    }
}
