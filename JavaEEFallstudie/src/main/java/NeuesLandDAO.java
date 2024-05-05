import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class NeuesLandDAO {
	Configuration con = new Configuration().configure().addAnnotatedClass(NeuesLand.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	
	
	public ArrayList findAll() {
	Session session = sf.openSession();
	Transaction tx = session.beginTransaction();
	
	ArrayList<NeuesLand> emissionenTabelle = (ArrayList<NeuesLand>) session.createSQLQuery("SELECT * FROM umweltdaten.neuesland").addEntity(NeuesLand.class).getResultList();
	
	tx.commit();
	
	return emissionenTabelle;
	}
	
	public void saveNeuesLand(NeuesLand neuesLand) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.save(neuesLand);
		tx.commit();
	}
	
	public void removeNeuesLand(NeuesLand neuesLand) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		  session.remove(neuesLand);
		tx.commit();
		
	}

}
