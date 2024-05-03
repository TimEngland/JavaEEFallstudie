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
public class EmmisionenTabelleController implements Serializable {
	
	private LandDAO landDAO;

	private ArrayList<Land> emmisionenTabelle = new ArrayList<Land>();  
	
	
	
	public EmmisionenTabelleController() {
		
		landDAO = new LandDAO();
		
	}



	public ArrayList<Land> getEmmisionenTabelle() {
		return landDAO.findAll();
	}



	public void setEmmisionenTabelle(ArrayList<Land> emmisionenTabelle) {
		this.emmisionenTabelle = emmisionenTabelle;
	}



	

	
	

	public void addLand(Land landAdd) {
		emmisionenTabelle.add(landAdd);
	}
	
	 
	public void replaceLand(Land landRep) {
		int ID = landRep.getID();
		int index = -1;
		for (Land land : emmisionenTabelle) {
			if(land.getID() == ID) {
				index = emmisionenTabelle.indexOf(land);
			}
		if(index != -1) {
			emmisionenTabelle.set(index, landRep);	
		}
		}
			
	}
	
	public void removeLand(Land landRem) {
		emmisionenTabelle.remove(landRem);
	}
	
	
	
	     
}

 