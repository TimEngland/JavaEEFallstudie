import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable{
	
	private int iD = -99;
	private String userName;
	
	
	
	public UserBean() {
		
	}
	public UserBean(int iD, String userName) {
		this.iD = iD;
		this.userName = userName;
		
	}
	
	public void  setUserBean(User user ) {
		this.iD = user.getiD();
		this.userName = user.getUserName();
	
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

	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserBean) {
			UserBean b =(UserBean) obj;
			if( b.getUserName().equals(this.getUserName())&&
						   b.getiD() == this.getiD())
			{ return true;}
		}
		return false;
	}

	

}
