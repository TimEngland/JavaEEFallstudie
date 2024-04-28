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
	
	

	private ArrayList<Land> emmisionenTabelle = new ArrayList<Land>();  
	
	private ArrayList<Land> filterTabelle = new ArrayList<Land>();
	
	
	
	private String suchEingabe ;
	
	private Land landÄndern;
	
	@Inject	
	UserBean userBean;
			
			
	@Override
	public String toString() {
		return "EmmisionenTabelleController [suchEingabe=" + suchEingabe + ", landÄndern=" + landÄndern + "]";
	}


   
	



	public EmmisionenTabelleController() {
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Land.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		emmisionenTabelle = (ArrayList<Land>) session.createSQLQuery("SELECT * FROM umweltdaten.land").addEntity(Land.class).getResultList();
		
		tx.commit();
		
		filterTabelle = filtern(suchEingabe);
	}



	public ArrayList<Land> getEmmisionenTabelle() {
		return emmisionenTabelle;
	}



	public void setEmmisionenTabelle(ArrayList<Land> emmisionenTabelle) {
		this.emmisionenTabelle = emmisionenTabelle;
	}



	public ArrayList<Land> getFilterTabelle() {
		return filterTabelle;
	}



	public void setFilterTabelle(ArrayList<Land> filterTabelle) {
		this.filterTabelle = filterTabelle;
	}
	
	
	
	
	
	public String getSuchEingabe() {
		return suchEingabe;
	}



	public void setSuchEingabe(String suchEingabe) {
		this.suchEingabe = suchEingabe;
	}

	
	


	
	public Land getLandÄndern() {
		if(this.landÄndern == null) {
			this.landÄndern = new Land();
		}
		return landÄndern;
	}



	public void setLandÄndern(Land landÄndern) {
		this.landÄndern = landÄndern;
	}

	

	public UserBean getUserBean() {
		return userBean;
	}



	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}





	public ArrayList<Land> filtern(String suchEingabe){
		if(suchEingabe == null || suchEingabe == "") {
			return getEmmisionenTabelle();
		}	
		ArrayList<Land> fT = new ArrayList<Land>();
		
		for(Land land : emmisionenTabelle ) {
		if(land.getCountryName().contains(suchEingabe)) {
			fT.add(land);
			}
		}
	
			return fT;
	}
	
	public void updateFilterTabelle() {
		filterTabelle = filtern(this.suchEingabe);
	}
	
	public void änderungEinreichen( Land landPara)  { 
   	  
		System.out.println("Aufgeruffen");
		for (Land land: filterTabelle) {
   		  if(landPara.getID() == land.getID()) {
   			
   		  }
   	  }
		
    }

	

	 public String returnLand(Land land) {
		  System.out.println(land);
		  //Weil sonst Feld Landändern mit land aus emmisionentabelle verknüpft ist, Warum auch immer ?
		 this.landÄndern = new Land(land);
		 System.out.println(this.landÄndern);
		  return "landÄndern.xhtml";
		  
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
	
	
	public String berechtigungVorschlägePrüfen() {
		if(userBean.getiD() == 0) return "vorschläge.xhtml";
		
		else return "emmisionenTabelle.xhtml";
		
	}
	     
}

 