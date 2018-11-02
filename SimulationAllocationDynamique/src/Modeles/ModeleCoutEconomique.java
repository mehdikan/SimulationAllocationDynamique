package Modeles;


public class ModeleCoutEconomique {
	public static double prixUnitePenalites=1;
	public static double prixUniteCommunication=0.00005;
	public static double prixUniteRessources=0.005;
	public static double prixUniteDisque=0.0025;
	
	public static double poidsCoutsCommEntreVM=1;
	public static double poidsCoutsCommEntreMPhyique=10;
	
	public double coutRessources=0;
	public double coutDisque=0;
	public double coutComm=0;
	public double coutPenalite=0;

	public double tempsExecTotal=0;
	public double tempsExecMoyenRequete=0;
	
	public static double distanceToPoidsCommunication(double distance) {
		if(distance==1) return poidsCoutsCommEntreVM;
		else if (distance==2) return poidsCoutsCommEntreMPhyique;
		return 0;
	}
	
	public double prixRessources() {return prixUniteRessources*coutRessources;}
	public double prixPenalites() {return prixUnitePenalites*coutPenalite;}
	public double prixCommunication() {return prixUniteCommunication*coutComm;}
	public double prixDisque() {return prixUniteDisque*coutDisque;}
	
	public double prixTotal(){
		return prixUniteCommunication*coutComm+prixUniteRessources*coutRessources+prixUniteDisque*coutDisque+prixUnitePenalites*coutPenalite;
	}
	
	public void print() {
		System.out.println("Prix Ressources : "+prixRessources());
		System.out.println("Prix communication : "+prixCommunication());
		System.out.println("Prix Penalites : "+prixPenalites());
		System.out.println("Prix Disque : "+prixDisque());
		System.out.println("Prix Total : "+prixTotal());
	}
}
