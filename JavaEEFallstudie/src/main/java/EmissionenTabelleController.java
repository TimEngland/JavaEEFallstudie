import java.io.Serializable;
import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ApplicationScoped
public class EmissionenTabelleController implements Serializable {
	
	private LandDAO landDAO;

	private ArrayList<Land> emissionenTabelle = new ArrayList<Land>();  
	
	
	
	public EmissionenTabelleController() {
		
		landDAO = new LandDAO();
		
	}



	public ArrayList<Land> getEmissionenTabelle() {
		return landDAO.findAll();
	}



	public void setEmissionenTabelle(ArrayList<Land> emissionenTabelle) {
		this.emissionenTabelle = emissionenTabelle;
	}



	

	
	

	public void addLand(Land landAdd) {
		emissionenTabelle.add(landAdd);
	}
	
	 
	public void replaceLand(Land landRep) {
		int ID = landRep.getID();
		int index = -1;
		for (Land land : emissionenTabelle) {
			if(land.getID() == ID) {
				index = emissionenTabelle.indexOf(land);
			}
		if(index != -1) {
			emissionenTabelle.set(index, landRep);	
		}
		}
			
	}
	
	public void removeLand(Land landRem) {
		emissionenTabelle.remove(landRem);
	}
	
	
	
	     
}

 