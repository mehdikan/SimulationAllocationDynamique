package AllocationDynamique;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import AllocationStatique.*;
import Infrastructure.Cloud;
import PlanAllocation.PlanStatique;
import PlanAllocation.TrancheTempsAlloue;
import Requetes.ClasseClients;
import Requetes.GroupeTachesTez;
import Requetes.RequeteTez;
import Requetes.StageTez;
import Types.StagesKey;

public class ShiftAlgorithm {
	
	public static void shiftRight(Cloud cloud,PlanStatique planStatique,TrancheTempsAlloue tranche,int dacalage) {
		System.out.println(">> "+(tranche.indexRequete+1)+" "+(tranche.indexStage+1)+" "+(tranche.indexTache+1)+" "+dacalage);
		planStatique.tab.remove(tranche);
		tranche.dateDebut+=dacalage;
		tranche.dateFin+=dacalage;
		
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
		
	
		RequeteTez r=cloud.getRequete(tranche.indexRequete);
		StageTez stage1=r.getStageByGlobalIndex(tranche.indexStage);
		for(StageTez stage2 : r.listeStages){
			if(r.getLien(stage1,stage2)>0) {
				for(GroupeTachesTez tache : stage2.groupesTezTaches) {
					for(TrancheTempsAlloue s:planStatique.tab){
						if(tache.stage.requeteTez.index==s.indexRequete && tache.stage.indexStage==s.indexStage && tache.indexDansStage==s.indexTache && tranche.dateFin>s.dateDebut) {
							aShifterTab.add(s);
						}
					}
				}
			}
		}
		
		for(TrancheTempsAlloue tta:aShifterTab){
			//System.out.println("*** "+(tta.indexRequete+1)+" "+(tta.indexStage+1)+" "+(tta.indexTache+1));
		}
		
		for(TrancheTempsAlloue tta:aShifterTab){
			if(tranche.dateFin+1-tta.dateDebut>0)
				shiftRight(cloud,planStatique,tta,tranche.dateFin+1-tta.dateDebut);
		}
		
		planStatique.tab.add(tranche);
	}
}
