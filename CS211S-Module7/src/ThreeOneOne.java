import java.time.DayOfWeek;
import java.time.LocalDate;

public class ThreeOneOne implements Comparable<ThreeOneOne> {
	
	private Integer caseID;
	private LocalDate dateOpened;
	private String responsible, category, street, neighborhood;
	
	
	
	public ThreeOneOne(Integer caseID, LocalDate dateOpened, String responsible, String category, String street,
			String neighborhood) {
		this.caseID = caseID;
		this.dateOpened = dateOpened;
		this.responsible = responsible;
		this.category = category;
		this.street = street;
		this.neighborhood = neighborhood;
	}
	public Integer getCaseID() {
		return caseID;
	}
	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}
	public LocalDate getOpenedDate() {
		return dateOpened;
	}
	public void setDateOpened(LocalDate openedDate) {
		this.dateOpened = openedDate;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public int getMonth() {
		return dateOpened.getMonthValue();
	}
	
	public DayOfWeek getDOW() {
		return dateOpened.getDayOfWeek();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ThreeOneOne other) {
			return this.caseID.equals(other.caseID);
			
		} else {
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		String result = "Case: "+caseID + " Date:" + dateOpened + " Resp Party: " + responsible + 
				" cat: " + category + " street: " + street + " neigh:" + neighborhood;
		return result;
	}
	
	@Override
	public int compareTo(ThreeOneOne obj) {
		return this.caseID.compareTo(obj.caseID);
	}
	
}
