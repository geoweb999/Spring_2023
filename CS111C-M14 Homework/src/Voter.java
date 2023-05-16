/* 
 * *************************************************************
 * DO NOT MODIFY THIS CLASS!!!
 * ************************************************************* 
 * */
import java.util.Objects;

public class Voter {

	
	private int id;
	private String name;
	
	public Voter(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name + " (" + id + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		Voter v = (Voter) obj;
		return id==v.id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
