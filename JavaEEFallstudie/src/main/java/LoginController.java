
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Named
@ViewScoped
public class LoginController implements Serializable{
	
	private User user;
	
	private String userNameVal;
	
	ArrayList<Land> emissionenTabelle;

	private ArrayList<Land> filterTabelle;

	private String suchEingabe ;
	
	private UserDAO userDAO;
	@Inject
	UserBean userBean;
	
	@Inject
	EmissionenTabelleController ETC;
	
	
	
	public LoginController() {
		this.userDAO = new UserDAO();
		this.user = new User();
		
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	 
	
	
	public String getBenutzerNameVal() {
		return userNameVal;
	}

	public void setBenutzerNameVal(String benutzerNameVal) {
		this.userNameVal = benutzerNameVal;
	}


	public ArrayList<Land> getEmissionenTabelle() {
	this.emissionenTabelle = ETC.getEmissionenTabelle();
	return emissionenTabelle;
}



	public void setEmissionenTabelle(ArrayList<Land> emissionenTabelle) {
	this.emissionenTabelle = emissionenTabelle;
	}

	public String login() {
		
		
		
		List<User> benutzerListe  = userDAO.findAll();
		
		
		for(User b : benutzerListe) {
			if(b.equals(this.user)) {
			    userBean.setUserBean(b);
				return "backEnd.xhtml" ;
			}
		}
	
	
		return "login.xhtml";
	}

	public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
	
		UIInput temp = (UIInput)ev.getComponent();
		this.userNameVal =(String)temp.getValue();
		
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	
		
		
		List<User> benutzerListe  = userDAO.findAll();
		
		
		for(User b : benutzerListe) {
			User temp = new User(this.userNameVal, (String) value);
			if(b.equals(temp)) {return;}
		}
		
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login falsch", null));
	}
	
	

public ArrayList<Land> getFilterTabelle() {
	filterTabelle = filtern(suchEingabe);
	return filterTabelle;
}



public void setFilterTabelle(ArrayList<Land> filterTabelle) {
	this.filterTabelle = filterTabelle;
}




public String getSuchEingabe() {
	return suchEingabe;
}


public void setSuchEingabe(String suchEingabe) {
	this.suchEingabe = suchEingabe;
}

public ArrayList<Land> filtern(String suchEingabe){
	if(suchEingabe == null || suchEingabe == "") {
		return getEmissionenTabelle();
	}	
	ArrayList<Land> fT = new ArrayList<Land>();
	
	for(Land land : emissionenTabelle ) {
	if(land.getCountryName().toLowerCase().contains(suchEingabe.toLowerCase())) {
		fT.add(land);
		}
	}

		return fT;
}

public void updateFilterTabelle() {
	filterTabelle = filtern(this.suchEingabe);
}

	
}