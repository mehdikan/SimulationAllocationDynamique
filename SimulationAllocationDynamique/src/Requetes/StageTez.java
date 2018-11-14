package Requetes;

import java.util.ArrayList;

import ParametresGlobeaux.*;

public class StageTez {
	public RequeteTez requeteTez;
	public long dureeTacheTezEnMs;
	public int dureeTacheTezEnFenetres;
	public int nombreGroupesTachesTez;
	public int nombreTachesElementairesTez;
	public int processeurTacheTez;
	public long memoireTacheTez;
	public int indexStage;
	public int indexDebutTasksTez;
	public ArrayList<GroupeTachesTez> groupesTezTaches;
	public long ordreArrive;
	public double quantiteStockeApresStage;
	public double quantiteRecu=0;
	public double donneeInitiale=0;
	public ArrayList<Operation> listeOperation;
	
	public StageTez(RequeteTez reqTez,long dureeTacheTezEnMs, int nombreGroupesTachesTez,long memoireTacheTez,double quantiteStockeApresStage,double donneeInitiale,int nombreTachesElementairesTez){
		this.requeteTez=reqTez;
		this.dureeTacheTezEnMs=dureeTacheTezEnMs;
		this.dureeTacheTezEnFenetres=(int)Math.ceil(this.dureeTacheTezEnMs/VariablesGlobales.tailleFentreTemps);
		this.nombreGroupesTachesTez=nombreGroupesTachesTez;
		this.processeurTacheTez=VariablesGlobales.nbTacheParGroupe;
		this.memoireTacheTez=memoireTacheTez;
		this.quantiteStockeApresStage=quantiteStockeApresStage;
		this.donneeInitiale=donneeInitiale;
		this.nombreTachesElementairesTez=nombreTachesElementairesTez;
		
		this.ordreArrive=VariablesGlobales.stageOrdreArrive;
		VariablesGlobales.stageOrdreArrive+=1;
		
		this.indexStage=VariablesGlobales.stageIndex;
		VariablesGlobales.stageIndex++;
		this.indexDebutTasksTez=VariablesGlobales.tezTasksIndex;
		VariablesGlobales.tezTasksIndex+=nombreGroupesTachesTez;
		
		this.groupesTezTaches=new ArrayList<GroupeTachesTez>();
		for(int i=0;i<nombreGroupesTachesTez;i++){
			this.groupesTezTaches.add(new GroupeTachesTez(this,this.dureeTacheTezEnFenetres,i));
		}
		listeOperation=new ArrayList<Operation>();
	}
	
	public void ajouterOperation(TypeOperation type,double input1NbTuples,double input2NbTuples,double outputNbTuples,double input1Size,double input2Size,double outputSize,int niveauInexactitude,int nombreOperationsAffectes,long nombreTachesApres) {
		listeOperation.add(new Operation(this,type,input1NbTuples,input2NbTuples,outputNbTuples,input1Size,input2Size,outputSize,niveauInexactitude,nombreOperationsAffectes,nombreTachesApres));
	}
}
