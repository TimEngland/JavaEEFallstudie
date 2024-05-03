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
	
	private LandDAO landDAO;
	private LandÄnderungDAO landÄnderungDAO;
	private NeuesLandDAO neuesLandDAO;
	private LandEntfernungDAO landEntfernungDAO;
	
	
public VorschlägeController() {
	
	landDAO = new LandDAO();
	landÄnderungDAO = new LandÄnderungDAO();
	neuesLandDAO = new NeuesLandDAO();
	landEntfernungDAO = new LandEntfernungDAO();
	
	
	
	
	vorschläge = landÄnderungDAO.findAll();
	neueLänder = neuesLandDAO.findAll();
	entfernteLänder = landEntfernungDAO.findAll();
	
	
	
	
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
	
	landÄnderungDAO.removeLandÄnderung(vorschlag);
}
//vieleicht boolean machen(funktioniert ja nein)
public void vorschlagAkzeptieren(LandÄnderung vorschlag) {

	Land akzeptiert = new Land(vorschlag.getID(), vorschlag.getCountryCode(), vorschlag.getCountryName(), vorschlag.getEmmisionen());
	EM.replaceLand(akzeptiert);
	vorschläge.remove(vorschlag);
	
	
	landÄnderungDAO.removeLandÄnderung(vorschlag);
	landDAO.changeLand(akzeptiert);		

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

	neuesLandDAO.removeNeuesLand(neuesLand);
	landDAO.saveLand(akzeptiert);
}

public void neuesLandLöschen(NeuesLand neuesLand) {
	neueLänder.remove(neuesLand);	
	neuesLandDAO.removeNeuesLand(neuesLand);
}

public void landEntfernen(LandEntfernung landEntfernung) {
	entfernteLänder.remove(landEntfernung);
	
	landEntfernungDAO.removeLandEntfernung(landEntfernung);
	
	Land land = null;
	for(Land l : EM.getEmmisionenTabelle()) {
		if(l.getID() == landEntfernung.getID()) {
			land = l;
		}
	}
	if(land != null) {
	EM.removeLand(land);
	
	landDAO.removeLand(land);
		

	}
}

public void landNichtEntfernen(LandEntfernung landEntfernung) {
	entfernteLänder.remove(landEntfernung);
	
	landEntfernungDAO.removeLandEntfernung(landEntfernung);

}

public String berechtigungPrüfen() {
	if(userBean.getiD() != 1) {
		return "emmisionenTablle.xhtml";
	}
	else return "vorschläge.xhtml";
	
}

}