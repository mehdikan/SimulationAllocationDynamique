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
	
	public StageTez(RequeteTez reqTez,int dureeTacheTez, int nombreTachesTez,int processeurTacheTez,int memoireTacheTez,double quantiteStockeApresStage){
		this.requeteTez=reqTez;
		this.dureeTacheTez=dureeTacheTez;
		this.nombreTachesTez=nombreTachesTez;
		this.processeurTacheTez=processeurTacheTez;
		this.memoireTacheTez=memoireTacheTez;
		this.quantiteStockeApresStage=quantiteStockeApresStage;
		
		this.ordreArrive=VariablesGlobales.stageOrdreArrive;
		VariablesGlobales.stageOrdreArrive+=1;
		
		this.indexStage=VariablesGlobales.stageIndex;
		VariablesGlobales.stageIndex++;
		this.indexDebutTasksTez=VariablesGlobales.tezTasksIndex;
		VariablesGlobales.tezTasksIndex+=nombreTachesTez;
		
		this.groupesTezTaches=new ArrayList<GroupeTachesTez>();
		for(int i=0;i<nombreTachesTez;i++){
			this.groupesTezTaches.add(new GroupeTachesTez(this,dureeTacheTez));
		}
	}
}
