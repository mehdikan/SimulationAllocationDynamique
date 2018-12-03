package Modeles;

import ParametresGlobeaux.VariablesGlobales;

public class ModeleCoutEconomique {
	public static double prixUnitePenalites=1;
	public static double prixUniteCommunication=0.001/(1024L*1024*1024/VariablesGlobales.tailleBlocDonnees); // 0.001 dollar/Go
	public static double prixUniteRessources=0.3328/((int)(VariablesGlobales.tailleMemoireParGroupeEnGo*1024*1024*1024/VariablesGlobales.tailleBlocDonnees))/(3600000/VariablesGlobales.tailleFenetreTemps); // t3.2xlarge	8cpu	32Gio	0,3328 USD par heure
	public static double prixUniteDisque=0.1/(1024L*1024*1024/VariablesGlobales.tailleBlocDonnees)/((30L*24*60*60*1000)); // 0,1 dollar/Go/mois Volumes SSD à usage général (gp2) Amazon EBS
	
	public static double poidsCoutsCommEntreVM=0;
	public static double poidsCoutsCommEntreMPhyique=1;
	
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
