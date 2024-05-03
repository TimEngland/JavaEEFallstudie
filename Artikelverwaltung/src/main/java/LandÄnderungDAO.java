import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class LandÄnderungDAO {
	Configuration con = new Configuration().configure().addAnnotatedClass(LandÄnderung.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	
	
	public ArrayList findAll() {
	Session session = sf.openSession();
	Transaction tx = session.beginTransaction();
	
	ArrayList<LandÄnderung> emissionenTabelle = (ArrayList<LandÄnderung>) session.createSQLQuery("SELECT * FROM umweltdaten.landänderung").addEntity(LandÄnderung.class).getResultList();
	
	tx.commit();
	
	return emissionenTabelle;
	}
	
	public void saveLandÄnderung(LandÄnderung landÄnderung) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.save(landÄnderung);
		tx.commit();
	}
	
	public void removeLandÄnderung(LandÄnderung landÄnderung) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.remove(landÄnderung);
		tx.commit();
		
	}
	
	
}
