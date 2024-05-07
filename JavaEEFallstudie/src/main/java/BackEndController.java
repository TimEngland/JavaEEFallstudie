import java.io.Serializable;
import java.util.ArrayList;

import jakarta.enterprise.context.SessionScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class BackEndController implements Serializable {
	
@Inject
EmissionenTabelleController EMC;

@Inject	
UserBean userBean;

ArrayList<Land> emissionenTabelle;

private ArrayList<Land> filterTabelle;

private String suchEingabe ;

private Land landÄndern;


public BackEndController() {
	

}


public ArrayList<Land> getEmissionenTabelle() {
	this.emissionenTabelle = EMC.getEmissionenTabelle();
	return emissionenTabelle;
}



public void setEmissionenTabelle(ArrayList<Land> emissionenTabelle) {
	this.emissionenTabelle = emissionenTabelle;
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

public ArrayList<Land> getFilterTabelle() {
	filterTabelle = filtern(suchEingabe);
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

public String returnLand(Land land) {
	  System.out.println(land);
	  //Weil sonst Feld Landändern mit land aus emissionentabelle verknüpft ist, Warum auch immer ?
	 this.landÄndern = new Land(land);
	 System.out.println(this.landÄndern);
	  return "landÄndern.xhtml";
	  
}

public ArrayList<Land> filtern(String suchEingabe){
	if(suchEingabe == null || suchEingabe == "") {
		return getEmissionenTabelle();
	}	
	ArrayList<Land> fT = new ArrayList<Land>();
	
	for(Land land : emissionenTabelle ) {
	if(land.getCountryName().toLowerCase().contains(suchEingabe.toLowerCase())) {
		fT.add(land);
		}
	}

		return fT;
}

public void updateFilterTabelle() {
	filterTabelle = filtern(this.suchEingabe);
}

public String berechtigungVorschlägePrüfen() {
	System.out.println(userBean.getiD());
	if(userBean.getiD() == 1) return "vorschläge.xhtml";
	
	else return "emissionenTabelle.xhtml";
	
}
public String berechtigungBackEndPrüfen() {
	if(userBean.getiD()== -99) {
		return "login.xhtml";
	}
	else return "emissionenTablle.xhtml";
}

public String logout() {
	userBean.setiD(-99);
	userBean.setUserName("");
	return "login.xhtml";	
}

public String neuesLandNavigation() {
	return "neuesLand.xhtml";
}

}
