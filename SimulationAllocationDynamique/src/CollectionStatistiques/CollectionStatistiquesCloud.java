package CollectionStatistiques;
import java.util.Collections;
import java.util.Comparator;

import Infrastructure.*;
import ParametresGlobeaux.VariablesGlobales;
import Requetes.*;
import Modeles.*;

public class CollectionStatistiquesCloud {
	public static double poids_date_limite=1;
	public static double poids_poids_penalities=1;
	
	public static void placementCollecteurStatistiques(Cloud cloud) {
		definirBudgetCollectStat(cloud);
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				trierEndoitCollect(req);
				definirEndroitCollect(req);
				mettreAjourDureeTache(req);
			}
		}
	}
	
	public static void definirBudgetCollectStat(Cloud cloud) {
		double somme=0;
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				req.budgetCollectStatistiques=definirPoidsCollectStatRequete(req);
				somme+=req.budgetCollectStatistiques;
			}
		}
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				req.budgetCollectStatistiques=(req.budgetCollectStatistiques/somme)*VariablesGlobales.budgetCollectStatistiques;
			}
		}
	}
	
	public static double definirPoidsCollectStatRequete(RequeteTez req) {
		double taille=tailleRequete(req);
		return taille*(poids_date_limite*(req.dateLimite/taille)+poids_poids_penalities*req.poids);
	}
	
	
	public static double tailleRequete(RequeteTez req) {
		double taille=0;
		for(StageTez stage : req.listeStages) {
			taille+=stage.dureeTacheTezEnFenetres*stage.nombreTachesElementairesTez;
		}
		return taille;
	}
	
	public static void definirEndroitCollect(RequeteTez requete) {
		double budget=0;
		for(Operation operation:requete.listeEndroitCollectStatTrie) {
			double prix=operation.prixCollectStat();
			if(budget+prix<requete.budgetCollectStatistiques) {
				operation.mettreCollecteur=true;
				budget+=prix;
				
			}
		}
	}
	
	public static void trierEndoitCollect(RequeteTez requete) {
		for(StageTez stage:requete.listeStages) {
			for(Operation operation:stage.listeOperation) {
				requete.listeEndroitCollectStatTrie.add(operation);
			}
		}
		
		Collections.sort(requete.listeEndroitCollectStatTrie, new Comparator<Operation>() {
	        @Override
	        public int compare(Operation op1, Operation op2)
	        {
	        	if(op1.nombreOperationsAffectes==0) {
	        		if(op2.nombreOperationsAffectes==0) {
	        			return 0;
	        		}
	        		return -1;
	        	}
	        	else if(op1.nombreOperationsAffectes==0) {
	        		return 1;
	        	}
	        	
	        	if(op1.niveauInexactitude!=op2.niveauInexactitude) {
	        		return op2.niveauInexactitude-op1.niveauInexactitude;
	        	}
	        	else{
	        		return (int)op2.nombreTachesApres-(int)op1.nombreTachesApres;
	        	}
	        }
	    });
	}
	
	public static void mettreAjourDureeTache(RequeteTez req) {
		for(StageTez stage:req.listeStages) {
			for(Operation op:stage.listeOperation) {
				if(op.mettreCollecteur) {
					stage.dureeTacheTezEnMs+=ModeleTempsReponse.temps_collect_stat(op.outputNbTuples, op.stage.nombreGroupesTachesTez);
				}
			}
		}
	}
}
