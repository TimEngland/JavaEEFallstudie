import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

public class Land {
	
	@Id
	private int ID;
	private String countryName;
	private String emissionen  ;
	private String countryCode;
	
	public Land() {}
	
	public Land(int ID, String countryCode, String countryName, String emmsisionen) {
		this.ID = ID;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.emissionen = emmsisionen;	
	}
	
	public Land(Land land) {
		this.ID = land.ID;
		this.countryCode = land.countryCode;
		this.countryName = land.countryName;
		this.emissionen = land.emissionen;	
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
	public String getEmissionen() {
		return emissionen;
	}
	public void setEmissionen(String emissionen) {
		this.emissionen = emissionen;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Land [ID=" + ID + ", countryName=" + countryName + ", emissionen=" + emissionen + ", countryCode="
				+ countryCode + "]";
	}
	
	@Override
	public boolean equals (Object obj) {
			if (this == obj) {return true;}
		
			if(obj instanceof LandÄnderung) {
			Land l = (Land) obj;
			
						if (this.ID ==  l.getID() &&
						this.countryName.equals(l.countryName) &&
						this.emissionen.equals(l.emissionen) &&
						this.countryCode.equals(l.countryCode) 
						) {
					return true;
			}
		}
		return super.equals(obj);
	}
	

}
