package p1;

import java.util.ArrayList;

public class Zta {
	
	private int valeur;
	private ArrayList<Zta> l = new ArrayList<Zta> ();
	private int profondeur;
	
	public Zta(int valeur) {
		super();
		this.valeur = valeur;
		this.profondeur = 0;
	}
	
	
	public Zta(int valeur, int profondeur) {
		super();
		this.valeur = valeur;
		this.profondeur = profondeur;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public ArrayList<Zta> getL() {
		return l;
	}
	public void setL(ArrayList<Zta> l) {
		this.l = l;
	}
	public int getProfondeur() {
		return profondeur;
	}
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	

	
}
