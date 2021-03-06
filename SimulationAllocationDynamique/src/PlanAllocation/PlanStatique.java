package PlanAllocation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Requetes.*;

public class PlanStatique {
	public ArrayList<TrancheTempsAlloue> tab;
	
	public PlanStatique(){
		tab=new ArrayList<TrancheTempsAlloue>();
	}

	public void ajouterTrancheTemps(TrancheTempsAlloue tranche){
		tab.add(tranche);
	}
	
	public void ecrireDansFichier(){		
		int nbRessourcesTez=0;
		try{
		    PrintWriter writer = new PrintWriter("resultat.txt", "UTF-8"); 
		    for(TrancheTempsAlloue tta:tab){
		    	writer.print("3"+" "+tta.indexRessource+" "+tta.requete.index+" "+tta.stage.indexStage+" "+tta.tache.index+" "+tta.dateDebut+" "+tta.dateFin+" ");
		    	nbRessourcesTez=Math.max(nbRessourcesTez, tta.indexRessource);
		    }
		    for(int i=0;i<nbRessourcesTez;i++){
		    	writer.print(3+" "+i+" "+100+" "+100+" "+100+" "+0+" "+0+" ");
		    }
		    writer.close();
		}
	    catch (IOException e) {
			   // do something
			}
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
	}
	
	public ArrayList<TrancheTempsAlloue> getTrancheByStage(StageTez stage){
		ArrayList<TrancheTempsAlloue> liste=new ArrayList<TrancheTempsAlloue>();
		for(TrancheTempsAlloue tranche : this.tab) {
			if(tranche.stage.indexStage==stage.indexStage) {
				liste.add(tranche);
			}
		}
		return liste;
	}
}
