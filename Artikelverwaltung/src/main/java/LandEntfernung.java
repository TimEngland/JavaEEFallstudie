import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LandEntfernung {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int LandEntfernungsID;
	
	private int ID;
	private String countryName;
	
	private String benutzerName;
	private int benutzerID;
	
	public LandEntfernung() {}

	public LandEntfernung(int ID, String countryName,String benutzerName, int benutzterID) {
		this.ID = ID;
		this.countryName = countryName;
		this.benutzerName = benutzerName;
		this.benutzerID = benutzterID;
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

	public String getBenutzerName() {
		return benutzerName;
	}

	public void setBenutzerName(String benutzerName) {
		this.benutzerName = benutzerName;
	}

	public int getBenutzerID() {
		return benutzerID;
	}

	public void setBenutzerID(int benutzerID) {
		this.benutzerID = benutzerID;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {return true;}
		
		if(obj instanceof LandEntfernung) {
			LandEntfernung l = (LandEntfernung) obj;
		
					if (this.ID ==  l.getID() &&
					this.benutzerName.equals(l.getBenutzerName()) &&
					this.benutzerID == l.getBenutzerID()) {
				return true;
		}
	}
	return super.equals(obj);
	}
	

}
