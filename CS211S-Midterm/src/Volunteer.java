public record Volunteer(String name, String phoneNumber, boolean isActive) {
	
    public Volunteer(String name, String phoneNumber) {
    
    	this(name, phoneNumber, true);
    
    }
}
