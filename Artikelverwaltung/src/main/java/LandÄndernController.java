import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@RequestScoped
public class LandÄndernController implements Serializable{
	
	@Inject
	BackEndController BEC ;
	
	@Inject
	UserBean userBean;

	Land land;
	
	Configuration con = new Configuration().configure().addAnnotatedClass(LandÄnderung.class).addAnnotatedClass(LandEntfernung.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	Session session = sf.openSession();

	

	public LandÄndernController() {
	  System.out.println("Hallo");
	}


	public Land getLand() {
		land = BEC.getLandÄndern();
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public void änderungVorschlagen() {
		LandÄnderung landÄnderung = new LandÄnderung(this.land, userBean.getUserName(), userBean.getiD());
		Transaction tx = session.beginTransaction();
		session.save(landÄnderung);
		tx.commit();
	}
	
	public void änderungAusgeben() {
		LandÄnderung landÄnderung = new LandÄnderung(this.land, userBean.getUserName(), userBean.getiD());
		System.out.println(landÄnderung);
	}
	
	public void landEntfernen() {
		LandEntfernung landEntfernung = new LandEntfernung(this.land.getID(),this.land.getCountryName(), userBean.getUserName(), userBean.getiD());
		Transaction tx = session.beginTransaction();
		session.save(landEntfernung);
		tx.commit();
	}
	

	public String berechtigungPrüfen() {
		if(userBean.getiD()== -99) {
			return "login.xhtml";
		}
		else return "landÄndern.xhtml";
		
	}
}
