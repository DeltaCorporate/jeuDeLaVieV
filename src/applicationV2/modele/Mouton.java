package applicationV2.modele;

public class Mouton extends Acteur{
	public static double pourcentageRepro =6;
	private int tourNaissance;


	public Mouton(int x, int y, Environnement env) {
		super(x, y, 5,env,40);
		this.tourNaissance=env.getNbTours();
	}


	public Mouton(Environnement env) {
		super( 5,env,40);
		this.tourNaissance=env.getNbTours();
	}

	public static void setReproduction(double n){
		pourcentageRepro=n;
	}



	@Override
	public void agit() {
		// ne se deplace que tous les 2 tours car il s'attarde pour manger
		if((this.env.getNbTours()-this.tourNaissance)%2==0){
			int ax=this.getX();
			int ay= this.getY();
			this.seDeplace();
			System.out.println("M " + this.getId()+ " se deplace de " + ax + ","+ ay + "vers " + this.getX()+ ","+ this.getY());
		}
		// reproduction
		if(Acteur.reussitProba(pourcentageRepro)){
			Mouton bebe= new Mouton(this.getX()+4, this.getY()+4,this.env);
			System.out.println("naissance de " + bebe);
			this.env.ajouter(bebe);
		}
		this.decrementerPv(1);
	}

	@Override
	public String toString() {
		return 	 "Mouton " + super.toString() ;

	}
}
