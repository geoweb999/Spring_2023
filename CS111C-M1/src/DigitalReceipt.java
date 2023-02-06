public class DigitalReceipt extends Receipt {
	private String emailAddress;

	public DigitalReceipt(Store store, int numItems, double total, String receiptID, String email) {
		super(store, numItems, total, receiptID);
		this.emailAddress = email;

	}
	
	public DigitalReceipt(Store store, int numItems, double total, String receiptID) {
		super(store, numItems, total, receiptID);
		this.emailAddress = "";

	}

	public DigitalReceipt(Store store, double total, String receiptID) {
		super(store, total, receiptID);
		this.emailAddress = "";
	}

	public String getEmail() {
		return emailAddress;
	}

	public void setEmail(String email) {
		this.emailAddress = email;
	}

	public static boolean validateEmail(String email) {
		// once in a while, regex is better
		// comment out the println's for cleaner console output
		
		// check to see if there is one and only '@' by checking
		// for the first occurence and '@' and the last
		int idx = email.indexOf('@');
		int idx2 = email.lastIndexOf('@');
		if (idx != idx2) { 
			System.out.print("\n\tmore than 1 '@'");
			return false;
		}
		if (idx == -1) {
			System.out.print("\n\tMissing '@'");
			return false;
		}
		
		// check to make sure there's at least room for 
		// top-level-domain (TLD) after '@'
		// meaning name@.com would *so far* be considered valid
		if (idx >= email.length() - 5) { 
			System.out.print("\n\t'@' does not allow space for TLD");
			return false;
		}
		// split email into it's two components, we won't get 
		// index out of bounds due to previous checks
		String name = email.substring(0, idx);     
		String domain = email.substring(idx + 1);  
		
		// if toUpperCase == toLowerCase then there are no letters in name
		if (name.toUpperCase() == name.toLowerCase()) { 
			System.out.print("\n\tName does not include at least 1 letter");
			return false;
		}
		
		// start analysis of TLD, the period must be at 
		// index len() - 4 to pass
		idx = domain.lastIndexOf('.');
		if (idx != domain.length() - 4) { 
			System.out.print("\n\tTLD incorrect size or missing");
			return false;
		}
		
		// analyse each char in TLD
		char[] TLD = domain.substring(domain.length() - 3).toCharArray();
		for (char c : TLD) {	
			if (!Character.isLetter(c)) {
				System.out.print("\n\tTLD includes non-letter character");
				return false;
			}
		}
		// we passed all of the tests for the question!
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n\tDigital copy sent to: " + this.emailAddress;
	}
	
	@Override
	public boolean equals (Object obj) {
		if(obj instanceof DigitalReceipt) {
			DigitalReceipt other = (DigitalReceipt) obj;
			return super.equals(other) && this.emailAddress.equalsIgnoreCase(other.emailAddress);
		} else {
			return false;
		}
	}
}
