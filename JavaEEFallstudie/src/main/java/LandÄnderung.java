import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class LandÄnderung  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ÄnderungsID;
	


	private int ID;
	private String countryName;
	private String emissionen  ;
	private String countryCode;
	private String benutzerName;
	private int benutzerID;
	
	
	

	public LandÄnderung() {
	
	}







	public LandÄnderung(Land land, String benutzerName,
			int benutzerID) {
		this.ID = land.getID();
		this.countryName = land.getCountryName();
		this.emissionen = land.getEmissionen();
		this.countryCode = land.getCountryCode();
		this.benutzerName = benutzerName;
		this.benutzerID = benutzerID;
	}

	
	
	



	public int getÄnderungsID() {
		return ÄnderungsID;
	}







	public void setÄnderungsID(int änderungsID) {
		ÄnderungsID = änderungsID;
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



	public int getBenutzerID() {
		return benutzerID;
	}

	public void setBenutzerID(int benutzerID) {
		this.benutzerID = benutzerID;
	}

	public String getBenutzerName() {
		return benutzerName;
	}

	public void setBenutzerName(String benutzerName) {
		this.benutzerName = benutzerName;
	}

	
	@Override
	public boolean equals (Object obj) {
			if (this == obj) {return true;}
		
			if(obj instanceof LandÄnderung) {
			LandÄnderung l = (LandÄnderung) obj;
			
						if (this.ID ==  l.getID() &&
						this.countryName.equals(l.countryName) &&
						this.emissionen.equals(l.emissionen) &&
						this.countryCode.equals(l.countryCode) &&
						this.benutzerName.equals(l.benutzerName) &&
						this.benutzerID == l.getBenutzerID()) {
					return true;
			}
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "LandÄnderung [ÄnderungsID=" + ÄnderungsID + ", ID=" + ID + ", countryName=" + countryName
				+ ", emissionen=" + emissionen + ", countryCode=" + countryCode + ", benutzerName=" + benutzerName
				+ ", benutzerID=" + benutzerID + "]";
	}
	

}
