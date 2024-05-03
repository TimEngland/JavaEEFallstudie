import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserDAO {
	Configuration con = new Configuration().configure().addAnnotatedClass(User.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	
	
	public ArrayList findAll() {
	Session session = sf.openSession();
	Transaction tx = session.beginTransaction();
	
	ArrayList<User> userListe = (ArrayList<User>) session.createSQLQuery("SELECT * FROM umweltdaten.user").addEntity(User.class).getResultList();
	
	tx.commit();
	
	return userListe;
	}

}
