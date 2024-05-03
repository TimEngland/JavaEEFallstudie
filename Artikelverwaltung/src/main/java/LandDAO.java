import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class LandDAO {
	Configuration con = new Configuration().configure().addAnnotatedClass(Land.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	
	
	public ArrayList findAll() {
	Session session = sf.openSession();
	Transaction tx = session.beginTransaction();
	
	ArrayList<Land> emissionenTabelle = (ArrayList<Land>) session.createSQLQuery("SELECT * FROM umweltdaten.land").addEntity(Land.class).getResultList();
	
	tx.commit();
	
	return emissionenTabelle;
	}
	
	public void saveLand(Land land) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.save(land);
		tx.commit();
	}
	
	public void removeLand(Land land) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.remove(land);
		tx.commit();
		
	}
	
	public void changeLand(Land land) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.merge(land);
		tx.commit();
		
	}
	
}
