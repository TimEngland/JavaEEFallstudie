import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

public class Land {
	
	@Id
	private int ID;
	private String countryName;
	private String emmisionen  ;
	private String countryCode;
	
	public Land() {}
	
	public Land(int ID, String countryCode, String countryName, String emmsisionen) {
		this.ID = ID;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.emmisionen = emmsisionen;	
	}
	
	public Land(Land land) {
		this.ID = land.ID;
		this.countryCode = land.countryCode;
		this.countryName = land.countryName;
		this.emmisionen = land.emmisionen;	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getEmmisionen() {
		return emmisionen;
	}
	public void setEmmisionen(String emmisionen) {
		this.emmisionen = emmisionen;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Land [ID=" + ID + ", countryName=" + countryName + ", emmisionen=" + emmisionen + ", countryCode="
				+ countryCode + "]";
	}
	
	@Override
	public boolean equals (Object obj) {
			if (this == obj) {return true;}
		
			if(obj instanceof LandÄnderung) {
			Land l = (Land) obj;
			
						if (this.ID ==  l.getID() &&
						this.countryName.equals(l.countryName) &&
						this.emmisionen.equals(l.emmisionen) &&
						this.countryCode.equals(l.countryCode) 
						) {
					return true;
			}
		}
		return super.equals(obj);
	}
	

}
