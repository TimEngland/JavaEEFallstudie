import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class NeuesLandController implements Serializable{
	
	@Inject
	UserBean userBean;
	
	NeuesLand neuesLand;
	
	private String bestätigung;
	
	private NeuesLandDAO neuesLandDAO;
	
	
	public NeuesLandController() {
		neuesLand = new NeuesLand();
		neuesLandDAO = new NeuesLandDAO();
	}
	
	public void neuesLandVorschlagen() {
		neuesLand.setBenutzerID(userBean.getiD());
		neuesLand.setBenutzerName(userBean.getUserName());
		
		neuesLandDAO.saveNeuesLand(neuesLand);
		
		bestätigung = neuesLand + "wurde als Vorschlag gespechert";
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public NeuesLand getNeuesLand() {
		return neuesLand;
	}

	public void setNeuesLand(NeuesLand neuesLand) {
		this.neuesLand = neuesLand;
	}

	public String getBestätigung() {
		return bestätigung;
	}

	public void setBestätigung(String bestätigung) {
		this.bestätigung = bestätigung;
	}
	
	public String berechtigungPrüfen() {
		if(userBean.getiD()== -99) {
			return "login.xhtml";
		}
		else return "neuesLand.xhtml";
		
	}
	
	

}
