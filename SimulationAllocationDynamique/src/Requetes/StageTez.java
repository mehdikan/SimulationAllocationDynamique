package Requetes;

import java.util.ArrayList;

import ParametresGlobeaux.*;

public class StageTez {
	public RequeteTez requeteTez;
	public int dureeTacheTez;
	public int nombreTachesTez;
	public int processeurTacheTez;
	public int memoireTacheTez;
	public int indexStage;
	public int indexDebutTasksTez;
	public ArrayList<GroupeTachesTez> groupesTezTaches;
	public long ordreArrive;
	public double quantiteStockeApresStage;
	public double quantiteRecu=0;
	public double donneeInitiale=0;
	
	public StageTez(RequeteTez reqTez,int dureeTacheTez, int nombreTachesTez,int memoireTacheTez,double quantiteStockeApresStage,double donneeInitiale){
		this.requeteTez=reqTez;
		this.dureeTacheTez=dureeTacheTez;
		this.nombreTachesTez=nombreTachesTez;
		this.processeurTacheTez=VariablesGlobales.nbTacheParGroupe;
		this.memoireTacheTez=memoireTacheTez;
		this.quantiteStockeApresStage=quantiteStockeApresStage;
		this.donneeInitiale=donneeInitiale;
		
		this.ordreArrive=VariablesGlobales.stageOrdreArrive;
		VariablesGlobales.stageOrdreArrive+=1;
		
		this.indexStage=VariablesGlobales.stageIndex;
		VariablesGlobales.stageIndex++;
		this.indexDebutTasksTez=VariablesGlobales.tezTasksIndex;
		VariablesGlobales.tezTasksIndex+=nombreTachesTez;
		
		this.groupesTezTaches=new ArrayList<GroupeTachesTez>();
		for(int i=0;i<nombreTachesTez;i++){
			this.groupesTezTaches.add(new GroupeTachesTez(this,dureeTacheTez,i));
		}
	}
}
