package Requetes;

import java.util.*;
import Divers.StagesKey;
import ErreursEstimation.*;
import Modeles.ModeleTempsReponse;
import Divers.*;
import CollectionStatistiques.*;
import ParametresGlobeaux.*;

public class RequeteTez {
	public String name="default";
	public int index;
	public ArrayList<StageTez> listeStages;
	public StageTez stageFinal;
	public double poids;
	public double importanceDateLimite;
	public int dateLimite;
	public int dateFinReelle;
	public int tempsGMPT=0;
	public ArrayList<CollecteurPotentiel> listeEndroitCollectStatTrie;
	Map<StagesKey, Long> quantiteTransfertStages;
	public Map<StagesKey, Integer> typeLien;
	public double tempstCollectStatistiques=0;
	//public double quantiteStockeApresStage;
	
	public RequeteTez(Client client,String name){
		this.name=name;
		this.poids=client.poidsPenalites;
		this.importanceDateLimite=client.importanceDateLimites;
		this.dateLimite=0;
		this.dateFinReelle=-1;
		listeStages=new ArrayList<StageTez>();
		listeEndroitCollectStatTrie=new ArrayList<CollecteurPotentiel>();
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
		System.out.println("Query : "+this.name+" "+this.tempstCollectStatistiques);
		System.out.println(this.tempstCollectStatistiques+" "+this.tailleRequete());
		for(StageTez stage: this.listeStages)
		{
			stage.descriptionStage();
			System.out.println("----------------------------------------");
			//System.out.println("temps "+stage.name+" = "+stage.dureeTacheTezEnMs+" "+ModeleTempsReponse.msToFenetre(stage.dureeTacheTezEnMs));
			//System.out.println("temps en fenetres ="+stage.nombreTachesTez);
		}
		System.out.println("--------------------------------------------------------------------------");
	}
	
	public void majDureeStages() {
		for(StageTez stage : this.listeStages) {
			stage.calculDureeTacheTez();
		}
	}
	
	public double tailleRequete() {
		double taille=0;
		for(StageTez stage : this.listeStages) {
			for(GroupeTachesTez tache : stage.groupesTezTaches) {
				taille+=ModeleTempsReponse.msToFenetre(stage.dureeTacheTezEnMs+tache.dureeCommunicationEnMs);
			}
		}
		return VariablesGlobales.tailleFenetreTemps*taille;
	}
	
	public double tauxCollecteur() {
		int cpt1=0,cpt2=0;
		for(StageTez stage: this.listeStages)
		{
			for(Operation op : stage.listeOperation) {
				if(op.mettreCollecteur) {
					cpt1++;
				}
				if(op.isInteressantMettreCollecteur()) {
					cpt2++;
				}
			}
		}
		return ((double)cpt1)/cpt2;
	}
}
