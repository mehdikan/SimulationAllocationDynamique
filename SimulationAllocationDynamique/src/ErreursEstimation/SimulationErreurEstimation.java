package ErreursEstimation;

import AllocationDynamique.ShiftAlgorithm;
import Infrastructure.*;
import Modeles.ModeleTempsReponse;
import Requetes.*;
import ParametresGlobeaux.*;
import PlanAllocation.*;

public class SimulationErreurEstimation {
	public static int poids_proba_level_low=1;
	public static int poids_proba_level_medium=5;
	public static int poids_proba_level_high=10;
	
	public static void simulerErreur(Cloud cloud,PlanStatique plan,double maxPlus) {
		RequeteTez requete=choisirUneRequeteAleatoirement(cloud);
		Operation operationBase=choisirUneOperationAleatoirement(requete);
		double plus=VariablesGlobales.random.nextDouble()*maxPlus;
		Operation operation=operationBase;
		while(operation!=null) {
			operation.outputNbTuples+=operation.outputNbTuples*plus;
			operation.outputSize+=operation.outputSize*plus;
			operation=operation.operationSuivante;
		}
		requete.majDureeStages();
		
		if(operationBase.mettreCollecteur) {
			System.out.println("Erreur estimation detectée");
		}
		else {
			for(TrancheTempsAlloue tranche : plan.tab) {
				int diff=ModeleTempsReponse.msToFenetre(tranche.stage.dureeTacheTezEnMs+tranche.tache.dureeCommunicationEnMs)-(tranche.dateFin-tranche.dateDebut);
				if(diff>0) {
					tranche.dateFin+=diff;
					ShiftAlgorithm.shiftRight(cloud,plan,tranche,diff,true);
				}
			}
		}
	}
	
	public static RequeteTez choisirUneRequeteAleatoirement(Cloud cloud) {
		System.out.println(VariablesGlobales.random);
		ClasseClients classe=cloud.listeClassesClient.get(VariablesGlobales.random.nextInt(cloud.listeClassesClient.size()));
		return classe.requeteTezEnAttente.get(VariablesGlobales.random.nextInt(classe.requeteTezEnAttente.size()));
	}

	public static Operation choisirUneOperationAleatoirement(RequeteTez requete) {
		int somme=0;
		for(StageTez stage:requete.listeStages) {
			for(Operation operation:stage.listeOperation) {
				if(operation.isInteressantMettreCollecteur()) {
					somme+=getPoids(operation.niveauInexactitude);
				}
			}
		}
		int rand=VariablesGlobales.random.nextInt(somme);
		somme=0;
		for(StageTez stage:requete.listeStages) {
			for(Operation operation:stage.listeOperation) {
				if(operation.isInteressantMettreCollecteur()) {
					if(somme+getPoids(operation.niveauInexactitude)>rand) {
						return operation;
					}
					somme+=getPoids(operation.niveauInexactitude);
				}
			}
		}
		return null;
	}
	
	public static int getPoids(int niveauInexactitude) {
		if(niveauInexactitude==1) return poids_proba_level_low;
		else if(niveauInexactitude==2) return poids_proba_level_medium;
		return poids_proba_level_high;
	}
	
}
