package Requetes;

import java.util.*;
import Divers.StagesKey;
import Divers.*;
import ParametresGlobeaux.*;

public class RequeteTez {
	public String name="default";
	public int index;
	public ArrayList<StageTez> listeStages;
	public StageTez stageFinal;
	public double poids;
	public int dateLimite;
	public int dateFinReelle;
	public int tempsGMPT=0;
	Map<StagesKey, Long> quantiteTransfertStages;
	public Map<StagesKey, Integer> typeLien;
	//public double quantiteStockeApresStage;
	
	public RequeteTez(double poids,int dateLimite,String name){
		this.name=name;
		this.poids=poids;
		this.dateLimite=dateLimite;
		this.dateFinReelle=-1;
		listeStages=new ArrayList<StageTez>();
		quantiteTransfertStages=new HashMap<StagesKey, Long>();
		typeLien=new HashMap<StagesKey, Integer>();
		this.index=VariablesGlobales.indexrequetes;
		VariablesGlobales.indexrequetes+=1;
	}
	
	public void rajouterStage(StageTez stage){
		listeStages.add(stage);
		stage.requeteTez=this;
	}
	
	public void initLiens() {
	    for(int j=0;j<this.nbStages();j++){
	    	for(int k=0;k<this.nbStages();k++){
	    		this.majQuantiteTransfertStages(this.getStage(j), this.getStage(k), 0);
	    		this.majTypeLien(this.getStage(j), this.getStage(k), 0);
	    	}
	    }
	}
	
	public void majQuantiteTransfertStages(StageTez s1, StageTez s2,long quantite)
	{
		quantiteTransfertStages.put(new StagesKey(s1,s2),quantite);
		if(quantite>0 && s1!=s2){
			for(int i=0;i<s2.groupesTezTaches.size();i++){
				for(int j=0;j<s1.groupesTezTaches.size();j++){
					s2.groupesTezTaches.get(i).dependances.add(s1.groupesTezTaches.get(j));
				}
			}
		}
	}
	
	public void majTypeLien(StageTez s1, StageTez s2,int type)
	{
		typeLien.put(new StagesKey(s1,s2),type);
	}
		
	public int nbStages(){
		return listeStages.size();
	}
	
	public StageTez getStage(int i){
		return this.listeStages.get(i);
	}
	
	public StageTez getStageByGlobalIndex(int i){
		for(StageTez stage:this.listeStages) {
			if(i==stage.indexStage) return stage;
		}
		return null;
	}
	
	public long getQuantiteTransfertStages(StageTez stage1,StageTez stage2){
		if(quantiteTransfertStages.get(new StagesKey(stage1,stage2))!=null){
			return quantiteTransfertStages.get(new StagesKey(stage1,stage2));
		}
		return 0;
	}
	
	public int getDepandance(StageTez stage1,StageTez stage2){
		if(quantiteTransfertStages.get(new StagesKey(stage1,stage2))!=null && quantiteTransfertStages.get(new StagesKey(stage1,stage2))>0){
			return 1;
		}
		return 0;
	}
	
	public int getLien(StageTez stage1,StageTez stage2){
		if(typeLien.get(new StagesKey(stage1,stage2))!=null){
			return typeLien.get(new StagesKey(stage1,stage2));
		}
		return 0;
	}
	
	public void descriptionRequete() {
		System.out.println("--------------------------------------------------------");
		System.out.println("Query : "+this.name);
		for(StageTez stage: this.listeStages)
		{
			System.out.println("memoire ="+stage.memoireTacheTez);
			//System.out.println("temps en fentres ="+stage.nombreTachesTez);
		}
	}
}
