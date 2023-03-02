
public enum SaleType {
	
	NORMAL("NORM"),  PRESALE("PSLE"), SALE("SALE"), TERMINATE("TERM");
	
	private String abbreviation;
	
	private SaleType(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	@Override
	public String toString() {
		return "Sale Type = " + this.getAbbreviation() + " ";
	}
}

