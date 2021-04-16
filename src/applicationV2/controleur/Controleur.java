package applicationV2.controleur;
import java.net.URL;
import java.util.ResourceBundle;
import applicationV2.modele.Acteur;
import applicationV2.modele.Environnement;
import applicationV2.modele.Loup;
import applicationV2.modele.Mouton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controleur implements Initializable{
	private Environnement env;

	@FXML
	private Pane panneauJeu;

	@FXML
	private ToggleGroup groupeRadio;

	@FXML
	private RadioButton ajoutLoup;

	@FXML
	private RadioButton ajoutMouton;

	@FXML
	private TextField nbIndividus;

	@FXML
	private TextField saisieNbTours;

	@FXML
	private Label nbtours;

	@FXML
	private Label nbvivants;

	@FXML
	private Label nbloups;

	@FXML
	private Label nbmoutons;
	@FXML
	void ajouter(ActionEvent event) {
		//System.out.println("clic ajouter");
		RadioButton selectedToggleButton =(RadioButton) groupeRadio.getSelectedToggle();		
		int nb= Integer.parseInt(this.nbIndividus.getText());
		Acteur a;
		if(selectedToggleButton.equals(ajoutLoup)) {
			for(int i=0; i<nb;i++){
				a=new Loup(this.env);
				this.env.ajouter(a);
				creerSprite(a);
			}
		}
		else{
			for(int i=0; i<nb;i++){
				a=new Mouton(this.env);
				this.env.ajouter(a);
				creerSprite(a);
			}
		}

	}

	@FXML
	void faireDesTours(ActionEvent event) {
		//System.out.println("clic Lancer");
		int nt= Integer.parseInt(this.saisieNbTours.getText());
		for(int i=0;i<nt;i++){
			this.env.unTour();
//			this.rafraichirPanneauJeu();
		}
	}

	@FXML
	void unTour(ActionEvent event) {
		//System.out.println("clic unTour");
		this.env.unTour();
//		this.rafraichirPanneauJeu();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.env=new Environnement(300,300);		
		// version avec bind :
			//this.nbtours.textProperty().bind(this.env.NbToursProperty().asString());
		// version avec listener
		//MonObservateurDeTour obs = new MonObservateurDeTour(this.nbtours);
		//this.env.NbToursProperty().addListener(obs);
		// version avec des lambdas :
		this.env.NbToursProperty().addListener((obse,old,nouv)-> this.nbtours.setText(nouv.toString()));

		// ecoute des slider pour mettre a jour les taux de reproduction.
		//this.sliderLoups.valueProperty().addListener((obs,old,nouv)-> Loup.setReproduction((double)nouv));
		//this.sliderMoutons.valueProperty().addListener((obs,old,nouv)-> Mouton.setReproduction((double)nouv));

		// mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...
		this.panneauJeu.setMaxWidth(305); // 5== largeur de l'image ou du rectangle.
		this.panneauJeu.setMaxHeight(305);
		// ecoute de la liste des acteurs pour prendre en compte les morts et les vivants
		//this.env.getActeurs().addListener(new MonObservateurActeurs(this.panneauJeu, this.labelVivants, this.labelnbLoups,this.labelnbMoutons));
		//this.env.init();
	}

	private void creerSprite(Acteur a) {
		//System.out.println("ajouter sprite");
		Circle r;
		if( a instanceof Loup){
			r= new Circle(3);
			r.setFill(Color.RED);
		}
		else{
			r= new Circle(2);
			r.setFill(Color.WHITE);
		}
		// ils ont le meme identifiant
		r.setId(a.getId());
		r.translateXProperty().bind(a.xProperty());
		r.translateYProperty().bind(a.yProperty());
		r.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));
		panneauJeu.getChildren().add(r);
	}

	/*public void rafraichirPanneauJeu(){
		for(Acteur a : this.env.getActeurs()){
			Circle c = (Circle) this.panneauJeu.lookup("#"+a.getId());
			// si c'est un nouveau né
			if(c==null){
				creerSprite(a);
			}
			else {
				c.setTranslateX(a.getX());
				c.setTranslateY(a.getY());
			}
		}
		// pour enlever les morts, il faut parcourir les sprites...
		for (int i=this.panneauJeu.getChildren().size()-1; i>=0;i--){
			Node c=this.panneauJeu.getChildren().get(i) ;
			Acteur a = this.env.getActeur(c.getId());
			if(a==null){
				this.panneauJeu.getChildren().remove(c);
			}		
		}
	}*/

}
