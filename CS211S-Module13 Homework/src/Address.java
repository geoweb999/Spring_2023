import java.lang.annotation.*;
import java.lang.reflect.*;

public class Address {
	
    @ProperLength(min = 1, max = 255)
    private String street;

    private String street2;

    @ProperLength(min = 1, max = 40)
    private String city;

    @ProperLength(min = 2, max = 2)
    private String state;

    @ProperLength(min = 5, max = 5)
    private String zip;

	public Address(String street, String street2, String city, String state, String zip) throws IllegalArgumentException  {
		this.street = street;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		
		validateLengths();
	}
	
	private void validateLengths() throws IllegalArgumentException {
		// YOUR CODE HERE
		 Field[] fields = this.getClass().getDeclaredFields();

	        for (Field field : fields) {
	            if (field.isAnnotationPresent(ProperLength.class)) {
	                ProperLength annotation = field.getAnnotation(ProperLength.class);
	                int minLength = annotation.min();
	                int maxLength = annotation.max();

	                field.setAccessible(true);

	                try {
	                    String value = (String) field.get(this);
	                    int length = value.length();

	                    if (length < minLength || length > maxLength) {
	                        throw new IllegalArgumentException("Invalid length for field: " + field.getName() + " expected length between " + 
	                        		minLength + " & " + maxLength + " actual length: " + length);
	                    }
	                } catch (IllegalAccessException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	
	@Override
	public String toString() {
		String s = street + (street2.length()>0 ? "\t"+street2 : "") +
				"\t" + city + ", " + state + " " + zip;
		return s;
				
	}

	
	

}
