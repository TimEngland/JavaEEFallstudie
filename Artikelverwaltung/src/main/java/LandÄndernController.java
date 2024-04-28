import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@RequestScoped
public class LandÄndernController implements Serializable{
	//Dies ist ein Test
	@Inject
	EmmisionenTabelleController EM ;

	Land land;
	
	Configuration con = new Configuration().configure().addAnnotatedClass(LandÄnderung.class).addAnnotatedClass(LandEntfernung.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	Session session = sf.openSession();

	

	public LandÄndernController() {
	  System.out.println("Hallo");
	}


	public Land getLand() {
		land = EM.getLandÄndern();
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public void änderungVorschlagen() {
		LandÄnderung landÄnderung = new LandÄnderung(this.land, EM.getUserBean().getUserName(), EM.getUserBean().getiD());
		Transaction tx = session.beginTransaction();
		session.save(landÄnderung);
		tx.commit();
	}
	
	public void änderungAusgeben() {
		LandÄnderung landÄnderung = new LandÄnderung(this.land, EM.getUserBean().getUserName(), EM.getUserBean().getiD());
		System.out.println(landÄnderung);
	}
	
	public void landEntfernen() {
		LandEntfernung landEntfernung = new LandEntfernung(this.land.getID(),this.land.getCountryName(), EM.getUserBean().getUserName(), EM.getUserBean().getiD());
		Transaction tx = session.beginTransaction();
		session.save(landEntfernung);
		tx.commit();
	}
	
}
