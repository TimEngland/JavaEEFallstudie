import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class LandEntfernungDAO {
	Configuration con = new Configuration().configure().addAnnotatedClass(LandEntfernung.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	
	
	public ArrayList findAll() {
	Session session = sf.openSession();
	Transaction tx = session.beginTransaction();
	
	ArrayList<LandEntfernung> emissionenTabelle = (ArrayList<LandEntfernung>) session.createSQLQuery("SELECT * FROM umweltdaten.landentfernung").addEntity(LandEntfernung.class).getResultList();
	
	tx.commit();
	
	return emissionenTabelle;
	}
	
	public void saveLandEntfernung(LandEntfernung landEntfernung) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.save(landEntfernung);
		tx.commit();
	}
	
	public void removeLandEntfernung(LandEntfernung landEntfernung) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.remove(landEntfernung);
		tx.commit();
		
	}
}
