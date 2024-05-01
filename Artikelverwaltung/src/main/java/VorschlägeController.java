import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
	
	private ArrayList<LandEntfernung> entfernteLänder;
	
	@Inject
	EmmisionenTabelleController EM;

	@Inject
	UserBean userBean;
	
	Configuration con = new Configuration().configure().addAnnotatedClass(LandÄnderung.class).addAnnotatedClass(Land.class).addAnnotatedClass(NeuesLand.class).addAnnotatedClass(LandEntfernung.class);
	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	SessionFactory sf = con.buildSessionFactory(reg);
	Session session = sf.openSession();

public VorschlägeController() {
	
	
	
	Transaction tx = session.beginTransaction();
	
	vorschläge = (ArrayList<LandÄnderung>) session.createSQLQuery("SELECT * FROM umweltdaten.landänderung").addEntity(LandÄnderung.class).getResultList();
	neueLänder = (ArrayList<NeuesLand>) session.createSQLQuery("SELECT * FROM umweltdaten.neuesland").addEntity(NeuesLand.class).getResultList();
	entfernteLänder = (ArrayList<LandEntfernung>) session.createSQLQuery("SELECT * FROM umweltdaten.landentfernung").addEntity(LandEntfernung.class).getResultList();
	
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



public ArrayList<LandEntfernung> getEntfernteLänder() {
	return entfernteLänder;
}

public void setEntfernteLänder(ArrayList<LandEntfernung> entfernteLänder) {
	this.entfernteLänder = entfernteLänder;
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
	

}

public void neuesLandAkzeptieren(NeuesLand neuesLand) {
	ArrayList<Land> emmisionenTabelle = EM.getEmmisionenTabelle();
	ArrayList<Integer> IDs = new ArrayList<Integer>();
	for(Land land : emmisionenTabelle) {
		IDs.add(land.getID());
	}
	Collections.sort(IDs);
	int newID = IDs.get(IDs.size()-1)+ 1;
	
	Land akzeptiert = new Land(newID, neuesLand.getCountryCode(), neuesLand.getCountryName(), neuesLand.getEmmisionen());
	
	EM.addLand(akzeptiert);
	neueLänder.remove(neuesLand);
	
	Transaction tx = session.beginTransaction();
	session.remove(neuesLand);
	session.save(akzeptiert);
	tx.commit();
}

public void neuesLandLöschen(NeuesLand neuesLand) {
	neueLänder.remove(neuesLand);
	
	Transaction tx = session.beginTransaction();
		session.remove(neuesLand);
	tx.commit();
}

public void landEntfernen(LandEntfernung landEntfernung) {
	entfernteLänder.remove(landEntfernung);
	Transaction t = session.beginTransaction();
		session.remove(landEntfernung);
	t.commit();
	Land land = null;
	for(Land l : EM.getEmmisionenTabelle()) {
		if(l.getID() == landEntfernung.getID()) {
			land = l;
		}
	}
	if(land != null) {
	EM.removeLand(land);
	Transaction tx = session.beginTransaction();
		
		session.remove(land);
	tx.commit();
	}
}

public void landNichtEntfernen(LandEntfernung landEntfernung) {
	entfernteLänder.remove(landEntfernung);
	
	Transaction tx = session.beginTransaction();
		session.remove(landEntfernung);
	tx.commit();
}

public String berechtigungPrüfen() {
	if(userBean.getiD() != 1) {
		return "emmisionenTablle.xhtml";
	}
	else return "vorschläge.xhtml";
	
}

}