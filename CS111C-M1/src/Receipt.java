import java.text.NumberFormat;

public class Receipt {
	private Store store;			// where items were purchased
	private int numberOfItems;		// number of items purchased
	private double totalMoneySpent;	// total money spent
	private String receiptID;		// receipt ID 
	

	public Receipt(Store store, int numItems, double total, String receiptID) {
		this.store = store;
		this.numberOfItems = numItems;
		this.totalMoneySpent = total;
		this.receiptID = receiptID;
	}

	public Receipt(Store store, double total, String receiptID) {
		this.store = store;
		this.numberOfItems = 1;
		this.totalMoneySpent = total;
		this.receiptID = receiptID;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumItems(int numItems) {
		this.numberOfItems = numItems;
	}

	public double getTotal() {
		return totalMoneySpent;
	}

	public void setTotal(double total) {
		this.totalMoneySpent = total;
	}

	public String getReceiptID() {
		return receiptID;
	}

	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}

	public String toString() {
		// convert numberOfItems to string and add "s" if quantity == 1
		String numItems = String.valueOf(this.getNumberOfItems()) +  " item";
		numItems += (this.getNumberOfItems() == 1) ? "" : "s";
		
		String moneyString = NumberFormat.getCurrencyInstance().format(totalMoneySpent);
		return "ID: " + this.receiptID + " Store: " + this.store + 
				 " Amount: " + moneyString + " for " + numItems;
	}
	
	public static boolean idMeetsCriteria(String receiptID, char firstTarget, char secondTarget, int countOfSecondAfterFirst) {
		// check for existence of firstTarget
		int idx = receiptID.indexOf(firstTarget);
		if (idx == -1) {
			return false;
		}
		// count occurences of secondTarget after index
		int count = 0;
		for (int i = idx + 1; i < receiptID.length(); i++) {
		    if (receiptID.charAt(i) == secondTarget) {
		        count++;
		    }
		}
		// valid if count matches parameter
		return (count == countOfSecondAfterFirst);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Receipt) {
			Receipt other = (Receipt) obj;
			return ( this.numberOfItems == other.numberOfItems && 
					this.receiptID.equalsIgnoreCase(other.receiptID) && 
					this.store.equals(other.store) && 
					this.totalMoneySpent == other.totalMoneySpent );
		} else {
			return false;
		}
	}
}
