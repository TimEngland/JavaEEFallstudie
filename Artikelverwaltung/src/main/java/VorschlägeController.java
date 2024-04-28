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
public class VorschlägeController implements Serializable{
	
	private ArrayList<LandÄnderung> vorschläge;
	
	private ArrayList<NeuesLand> neueLänder;
	
	@Inject
	EmmisionenTabelleController EM;

	Configuration con = new Configuration().configure().addAnnotatedClass(LandÄnderung.class).addAnnotatedClass(Land.class).addAnnotatedClass(NeuesLand.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	Session session = sf.openSession();

public VorschlägeController() {
	
	
	
	Transaction tx = session.beginTransaction();
	
	vorschläge = (ArrayList<LandÄnderung>) session.createSQLQuery("SELECT * FROM umweltdaten.landänderung").addEntity(LandÄnderung.class).getResultList();
	neueLänder = (ArrayList<NeuesLand>) session.createSQLQuery("SELECT * FROM umweltdaten.neuesland").addEntity(NeuesLand.class).getResultList();
	
	tx.commit();
	
	
}

public ArrayList<LandÄnderung> getVorschläge() {
	return vorschläge;
}

public void setVorschläge(ArrayList<LandÄnderung> vorschläge) {
	this.vorschläge = vorschläge;
}



public ArrayList<NeuesLand> getNeueLänder() {
	return neueLänder;
}

public void setNeueLänder(ArrayList<NeuesLand> neueLänder) {
	this.neueLänder = neueLänder;
}

//vieleicht boolean machen(funktioniert ja nein)
public void vorschlagLöschen(LandÄnderung vorschlag) {
	vorschläge.remove(vorschlag);
	
	Transaction tx = session.beginTransaction();
		session.remove(vorschlag);
	tx.commit();
}
//vieleicht boolean machen(funktioniert ja nein)
public void vorschlagAkzeptieren(LandÄnderung vorschlag) {
	

	Land akzeptiert = new Land(vorschlag.getID(), vorschlag.getCountryCode(), vorschlag.getCountryName(), vorschlag.getEmmisionen());
	EM.replaceLand(akzeptiert);
	vorschläge.remove(vorschlag);
	
	
	Transaction tx = session.beginTransaction();
			session.remove(vorschlag);
			session.merge(akzeptiert);
	tx.commit();
	
	System.out.println(akzeptiert);
}

}