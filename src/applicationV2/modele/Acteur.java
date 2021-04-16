package applicationV2.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;


public abstract class Acteur {

	private IntegerProperty xProperty, yProperty;
	private int v; // vitesse de deplacement
	private int dx,dy ;// direction 
	protected Environnement env;
	public static int compteur=0;
	private String id;
	private int pv;
	public Acteur(int x, int y, int v, Environnement env,int pv) {
		this.pv=pv;
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		compteur++;
		this.tirerDirection();
	}

	public Acteur( int v, Environnement env, int pv) {
		this.pv=pv;
		Random random=new Random();
		int x = random.nextInt(env.getWidth()-1);
		int y=random.nextInt(env.getHeight()-1);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		compteur++;
		this.tirerDirection();
		//System.out.println("y" + y + "x" +x);
	}	

	public final int getX() {
		return xProperty.getValue();
	}

	public final IntegerProperty xProperty(){
		return this.xProperty;
	}

	public  final void setX(int n){
		xProperty.setValue(n);
	}

	public final int getY() {
		return yProperty.getValue();
	}
	public  void setY(int n){
		yProperty.setValue(n);
	}
	public final IntegerProperty yProperty(){
		return this.yProperty;
	}

	public String getId() {
		return id;
	}

	public void decrementerPv(int n) {
		this.pv-=n;	
	}

	public void incrementerPv(int n) {
		this.pv+=n;	
	}




	public boolean estVivant() {
		return this.pv>0;
	}

	public void meurt(){
		this.pv=0;
	}


	private void tirerDirection(){
		Random random=new Random();
		int randomInt = random.nextInt(3);
		dx=randomInt-1;
		if(dx==0){
			randomInt=random.nextInt(2)-1;
			if(randomInt==0){
				dy=-1;
			}
			else{
				dy=1;
			}
		}
		else{
			dy=random.nextInt(3)-1;
		}
	}
	//permet de savoir si une action probabiliste se réalise 
	public static boolean reussitProba(double pourcent){
		double x= Math.random();
		double pp=pourcent/100;
		return (x<=pp);
	}


	public void seDeplace(){
		// 20% de chance de changer de direction
		// if(Math.random()*100< pourentageRepro )
		if(reussitProba(20)) {
			tirerDirection();
		}
		int nposX=this.getX()+(this.v*dx);
		int nposY=this.getY()+(this.v*dy);
		while(!env.dansTerrain(nposX, nposY)){
			tirerDirection();
			nposX=this.getX()+(this.v*dx);
			nposY=this.getY()+(this.v*dy);
		}
		this.xProperty.setValue(nposX);
		this.yProperty.setValue(nposY);
	}


	public abstract void agit();

	@Override
	public String toString() {
		return "x=" + xProperty + ", y=" + yProperty + ", id=" + id ;
	}


}
