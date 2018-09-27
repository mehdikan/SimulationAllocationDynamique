package AllocationDynamique;

import java.util.ArrayList;
import java.util.Iterator;

import AllocationStatique.*;

public class ShiftAlgorithm {
	
	public static void shiftRight(PlanStatique planStatique,TrancheTempsAlloue tranche,int dacalage) {
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
		
		
		
		for(TrancheTempsAlloue tta:aShifterTab){
			shiftRight(planStatique,tta,tranche.dateFin+1-tta.dateDebut);
		}
		planStatique.tab.add(tranche);
	}
}
