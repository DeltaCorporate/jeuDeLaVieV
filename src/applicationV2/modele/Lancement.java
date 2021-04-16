package applicationV2.modele;

public class Lancement {

	public static void main(String [] args){
		Environnement e = new Environnement(20, 10);
//		e.ajouter(new Mouton(10,10,e));
//		e.ajouter(new Mouton(20,10,e));
//		e.ajouter(new Mouton(30,10,e));
//		e.ajouter(new Mouton(40,10,e));
//		e.ajouter(new Loup(10,15,e));
//		e.ajouter(new Loup(20,15,e));
//		e.ajouter(new Loup(30,15,e));
//		e.ajouter(new Loup(30,20,e));
		VueConsole vue= new VueConsole(e);
		vue.go();
	}
}
