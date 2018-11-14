package Requetes;

import CollectionStatistiques.*;
import Modeles.*;
import ParametresGlobeaux.*;

public class Operation {
	public StageTez stage;
	public TypeOperation type;  
	public double input1Size;
	public double input2Size;
	public double outputSize;
	public double input1NbTuples;
	public double input2NbTuples;
	public double outputNbTuples;
	public boolean mettreCollecteur;
	public int niveauInexactitude;
	public int nombreOperationsAffectes;
	public long nombreTachesApres;
	
	public Operation(StageTez stage,TypeOperation type,double input1NbTuples,double input2NbTuples,double outputNbTuples,double input1Size,double input2Size,double outputSize,int niveauInexactitude,int nombreOperationsAffectes,long nombreTachesApres) {
		this.stage=stage;
		this.type=type;
		this.input1Size=input1Size;
		this.input2Size=input2Size;
		this.outputSize=outputSize;
		this.input1NbTuples=input1NbTuples;
		this.input2NbTuples=input2NbTuples;
		this.outputNbTuples=outputNbTuples;
		this.mettreCollecteur=false;
		this.niveauInexactitude=niveauInexactitude;
		this.nombreOperationsAffectes=nombreOperationsAffectes;
		this.nombreTachesApres=nombreTachesApres;
	}
	
	public double prixCollectStat() {
		long t=ModeleTempsReponse.temps_collect_stat(outputNbTuples,stage.nombreTachesElementairesTez);
		double cout=0;
		for(GroupeTachesTez tache:stage.groupesTezTaches) {
			cout+=tache.ressource.vm.memoireTezSlots*t;
		}
		return cout*((double)ModeleCoutEconomique.prixUniteRessources/VariablesGlobales.tailleFentreTemps);
	}
}
