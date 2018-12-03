package Requetes;

import java.util.ArrayList;

import CollectionStatistiques.*;
import Modeles.*;
import ParametresGlobeaux.*;

public class Operation {
	public StageTez stage;
	public TypeOperation type;
	public double dureeEnMs;
	public double outputSize;
	public double outputNbTuples;
	public Operation operationSuivante;
	public Operation operationPrec1;
	public Operation operationPrec2;
	public ArrayList<CollecteurPotentiel> collecteursPotentiels;
	
	public Operation(StageTez stage,
			TypeOperation type,
			double outputNbTuples,
			double outputSize
			//,
			//int niveauInexactitude,
			//int nombreOperationsAffectes,
			//long nombreTachesApres
		) {
		this.stage=stage;
		this.type=type;
		this.outputSize=outputSize;
		this.outputNbTuples=outputNbTuples;
		//this.mettreCollecteur=false;
		//this.niveauInexactitude=Math.min(niveauInexactitude,3);
		//this.nombreOperationsAffectes=nombreOperationsAffectes;
		//this.nombreTachesApres=nombreTachesApres;
		this.operationSuivante=null;
		this.collecteursPotentiels=new ArrayList<CollecteurPotentiel>();
	}
	
	public void setOperationSuivantePrec(Operation operationPrec1,Operation operationPrec2,Operation operationSuivante) {
		this.operationPrec1=operationPrec1;
		this.operationPrec2=operationPrec2;
		this.operationSuivante=operationSuivante;
	}	
	
	/*public double prixCollectStat() {
		double t=ModeleTempsReponse.temps_collect_stat(outputNbTuples,stage.nombreTachesElementairesTez);
		double cout=0;
		for(GroupeTachesTez tache:stage.groupesTezTaches) {
			cout+=tache.ressource.vm.memoireTezSlots*t;
		}
		return cout*((double)ModeleCoutEconomique.prixUniteRessources/VariablesGlobales.tailleFenetreTemps);
	}*/
	
	public void connecter(Operation operationPrec1,Operation operationPrec2,Operation operationSuivante) {
		this.operationPrec1=operationPrec1;
		this.operationPrec2=operationPrec2;
		this.operationSuivante=operationSuivante;
	}

	public boolean isInteressantMettreCollecteur() {
		if(this.type==TypeOperation.TABLESCAN
				|| this.type==TypeOperation.FILTER
				|| this.type==TypeOperation.MAPJOIN
				|| this.type==TypeOperation.MERGEJOIN
				|| this.type==TypeOperation.GROUPBY) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
