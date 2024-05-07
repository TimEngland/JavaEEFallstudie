import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NeuesLand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vorläufigeID;
	private String countryName;
	private String emissionen  ;
	private String countryCode;
	private String benutzerName;
	private int benutzerID;
	
	public NeuesLand() {}

	
	
	public NeuesLand(String countryName, String emissionen, String countryCode, UserBean userBean ) {
		super();
		this.countryName = countryName;
		this.emissionen = emissionen;
		this.countryCode = countryCode;
		this.benutzerID = userBean.getiD();
		this.benutzerName = userBean.getUserName();
	}



	public int getVorläufigeID() {
		return vorläufigeID;
	}

	public void setVorläufigeID(int vorläufigeID) {
		this.vorläufigeID = vorläufigeID;
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
	public String toString() {
		return "NeuesLand [countryName=" + countryName + ", emissionen=" + emissionen + ", countryCode=" + countryCode
				+ ", benutzerName=" + benutzerName + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {return true;}
		
		if(obj instanceof NeuesLand) {
			NeuesLand l = (NeuesLand) obj;
		
					if (this.vorläufigeID ==  l.getVorläufigeID() &&
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
	
	
	
}
