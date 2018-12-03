package AllocationDynamique;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import AllocationStatique.*;
import Divers.StagesKey;
import Infrastructure.Cloud;
import PlanAllocation.PlanStatique;
import PlanAllocation.TrancheTempsAlloue;
import Requetes.ClasseClients;
import Requetes.GroupeTachesTez;
import Requetes.RequeteTez;
import Requetes.StageTez;

public class ShiftAlgorithm {
	
	public static void shiftRight(Cloud cloud,PlanStatique planStatique,TrancheTempsAlloue tranche,int dacalage,boolean firstCall) {
		//System.out.println(">> "+(tranche.requete.index+1)+" "+(tranche.stage.indexStage+1)+" "+(tranche.tache.index+1)+" "+dacalage);
		planStatique.tab.remove(tranche);
		if(!firstCall) {
			tranche.dateDebut+=dacalage;
			tranche.dateFin+=dacalage;
		}
		
		int tt=-1;
		ArrayList<TrancheTempsAlloue> aShifterTab=new ArrayList<TrancheTempsAlloue>();
		TrancheTempsAlloue aShifter=null;
		for(TrancheTempsAlloue s:planStatique.tab){
		    if(s.indexRessource==tranche.indexRessource && s.dateDebut<tranche.dateFin && s.dateDebut > tranche.dateDebut-dacalage && (tt==-1 || s.dateDebut<tt)) {
		    	aShifter=s;
		    	tt=s.dateDebut;
		    }
		}
		if(aShifter!=null)
			aShifterTab.add(aShifter);
		
	
		RequeteTez r=cloud.getRequete(tranche.requete.index);
		StageTez stage1=r.getStageByGlobalIndex(tranche.stage.indexStage);
		for(StageTez stage2 : r.listeStages){
			if(r.getLien(stage1,stage2)>0) {
				for(GroupeTachesTez tache : stage2.groupesTezTaches) {
					for(TrancheTempsAlloue s:planStatique.tab){
						if(tache.stage.requeteTez.index==s.requete.index && tache.stage.indexStage==s.stage.indexStage && tache.indexDansStage==s.tache.index && tranche.dateFin>s.dateDebut) {
							aShifterTab.add(s);
						}
					}
				}
			}
		}
		
		
		for(TrancheTempsAlloue tta:aShifterTab){
			if(tranche.dateFin+1-tta.dateDebut>0)
				shiftRight(cloud,planStatique,tta,tranche.dateFin+1-tta.dateDebut,false);
		}
		
		planStatique.tab.add(tranche);
	}
}
