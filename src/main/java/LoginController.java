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
	
	@Inject
	UserBean userBean;
	
	@Inject
	EmmisionenTabelleController ETC;
	
	Configuration con = new Configuration().configure().addAnnotatedClass(User.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	Session session = sf.openSession();
	
	public LoginController() {
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





	public String login() {
		
		Transaction tx = session.beginTransaction();
		
		List<User> benutzerListe  = (List<User>) session.createSQLQuery("SELECT * FROM umweltdaten.user").addEntity(User.class).getResultList();
		
		tx.commit();
		for(User b : benutzerListe) {
			if(b.equals(this.user)) {
			    userBean.setUserBean(this.user);
				return "emmisionenTablle.xhtml" ;
			}
		}
	
	
		return "login.xhtml";
	}

	public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
	
		UIInput temp = (UIInput)ev.getComponent();
		this.userNameVal =(String)temp.getValue();
		
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	
		Transaction tx = session.beginTransaction();
		
		List<User> benutzerListe  = (List<User>) session.createSQLQuery("SELECT * FROM umweltdaten.user").addEntity(User.class).getResultList();
		
		tx.commit();
		for(User b : benutzerListe) {
			User temp = new User(this.userNameVal, (String) value);
			if(b.equals(temp)) {return;}
		}
		System.out.println("Login falsch");
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login falscho", null));
	}
	
}