

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private int iD;
	private String userName;
	private String passWort;
	
	
	public User() {
		
	}
	public User(String userName, String passWort) {
		this.userName = userName;
		this.passWort = passWort;
	}
	
	
	public int getiD() {
		return iD;
	}
	
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWort() {
		return passWort;
	}
	public void setPassWort(String passWort) {
		this.passWort = passWort;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User b =(User) obj;
			if( b.getUserName().equals(this.getUserName())&&
					 b.getPassWort().equals(this.getPassWort()))
			{ return true;}
		}
		return false;
	}
	@Override
	public String toString() {
		return "User [iD=" + iD + ", userName=" + userName + ", passWort=" + passWort + "]";
	}

	
}
