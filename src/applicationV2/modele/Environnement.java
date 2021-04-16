package applicationV2.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private int width,height;	
	private ObservableList<Acteur> acteurs;
	private  IntegerProperty nbTours;
	private StringProperty temps;

	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.nbTours = new SimpleIntegerProperty(0);
		this.acteurs= FXCollections.observableArrayList();
		this.temps = new SimpleStringProperty("JOUR");
	}

	public final  int getNbTours(){
		return this.nbTours.getValue();
	}

	public final void setNbTours(int n){
		this.nbTours.setValue(n);
	}
	public final IntegerProperty NbToursProperty(){
		return this.nbTours;
	}

	public final String getTemps(){
		return this.temps.getValue();
	}
	public final void setTemps(String temps){
		this.temps.setValue(temps);
	}
	public final StringProperty tempsProperty(){
		return this.temps;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ObservableList<Acteur> getActeurs() {
		return acteurs;
	}

	public Acteur getActeur(String id) {
		for(Acteur a:this.acteurs){
			if(a.getId().equals(id)){
				return a;
			}
		}
		return null;
	}


	public void ajouter(Acteur a){
		acteurs.add(a);
	}

	public boolean dansTerrain(int x, int y){
		return (0 <= x && x<this.width && 0<=y && y< this.height);
	}

	public void unTour(){
		// cela ne peut etre un foreach a cause des naissances 
		// modification de acteurs.
		//System.out.println("tour " + this.nbTours);
		for(int i=0;i<acteurs.size(); i++){
			Acteur a = acteurs.get(i);			
			a.agit();
		}
		for(int i=acteurs.size()-1; i>=0;i--){
			Acteur a = acteurs.get(i);
			if(!a.estVivant()){
				System.out.println("mort de : " + a);
				acteurs.remove(i);
			}
		}
		this.setNbTours(this.getNbTours()+1);
		if(this.getNbTours()%12 == 0){
			if(this.getTemps().equals("JOUR")){
				setTemps("NUIT");
			} else{
				setTemps("JOUR");
			}
		}
	}


	public void init(){
		for(int i=0; i<50;i++){
			this.ajouter(new Loup(this));
		}
		for(int i=0; i<100;i++){
			this.ajouter(new Mouton(this));
		}
	}


}
