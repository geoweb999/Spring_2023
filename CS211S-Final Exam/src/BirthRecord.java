import java.time.*;

public class BirthRecord {

    private String name;
    private LocalDate birthDate;
    private double weight, height;
    private int apgarScore; // a numeric score that summarizes the health of a newborn
    private SexAtBirth sexAtBirth;
    private boolean multiple; // true if baby was born as part of twins, triplets, etc. and false otherwise
    
    public BirthRecord(String name, LocalDate birthday, double weight, double height, int apgarScore, boolean multiple, SexAtBirth sexAtBirth) {
        this.name = name;
        this.birthDate = birthday;
        this.weight = weight;
        this.height = height;
        this.apgarScore = apgarScore;
        this.multiple = multiple;
        this.sexAtBirth = sexAtBirth;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public SexAtBirth getSexAtBirth() {
        return sexAtBirth;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

   
    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getApgarScore() {
    	return apgarScore;
    }

	@Override
	public String toString() {
		return name + " (" + sexAtBirth + ") born:" + birthDate + " at " + weight + "lbs and " + height + "inches;"
				+ " apgar=" + apgarScore + " multiple=" + multiple;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + apgarScore;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((sexAtBirth == null) ? 0 : sexAtBirth.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (multiple ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BirthRecord other = (BirthRecord) obj;
		if (apgarScore != other.apgarScore)
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (sexAtBirth != other.sexAtBirth)
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (multiple != other.multiple)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
    
  
}