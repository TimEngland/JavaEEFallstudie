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
	private String emmisionen  ;
	private String countryCode;
	private String benutzerName;
	private int benutzerID;
	
	public NeuesLand() {}

	
	
	public NeuesLand(String countryName, String emmisionen, String countryCode, UserBean userBean ) {
		super();
		this.countryName = countryName;
		this.emmisionen = emmisionen;
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
		return "NeuesLand [countryName=" + countryName + ", emmisionen=" + emmisionen + ", countryCode=" + countryCode
				+ ", benutzerName=" + benutzerName + "]";
	}
	
	
	
}
