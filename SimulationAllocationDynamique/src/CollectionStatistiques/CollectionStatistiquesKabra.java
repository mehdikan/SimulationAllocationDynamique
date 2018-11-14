package CollectionStatistiques;

import java.util.Collections;
import java.util.Comparator;

import Infrastructure.Cloud;
import Modeles.ModeleTempsReponse;
import Requetes.ClasseClients;
import Requetes.Operation;
import Requetes.RequeteTez;
import Requetes.StageTez;
import ParametresGlobeaux.*;

public class CollectionStatistiquesKabra {
	public static double gamma=1;
	
	public static void placementCollecteurStatistiques(Cloud cloud) {
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				trierEndoitCollect(req);
				definirEndroitCollect(req);
				mettreAjourDureeTache(req);
			}
		}
	}
	
	public static void definirEndroitCollect(RequeteTez requete) {
		double taille=0;
		for(Operation operation:requete.listeEndroitCollectStatTrie) {
			
			if(taille+ModeleTempsReponse.temps_collect_stat(operation.outputNbTuples,operation.stage.nombreTachesElementairesTez)*operation.stage.nombreTachesElementairesTez<definirPoidsCollectStatRequete(requete)) {
				operation.mettreCollecteur=true;
				taille+=operation.stage.dureeTacheTezEnFenetres*operation.stage.nombreTachesElementairesTez;
			}
		}
	}
	
	public static double definirPoidsCollectStatRequete(RequeteTez req) {
		double taille=tailleRequete(req);
		return taille*gamma;
	}
	
	public static double tailleRequete(RequeteTez req) {
		double taille=0;
		for(StageTez stage : req.listeStages) {
			taille+=stage.dureeTacheTezEnFenetres*stage.nombreTachesElementairesTez;
		}
		return VariablesGlobales.tailleFentreTemps*taille;
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
	        	if(op1.niveauInexactitude!=op2.niveauInexactitude) {
	        		return op2.niveauInexactitude-op1.niveauInexactitude;
	        	}
	        	else{
	        		return (int)op2.nombreOperationsAffectes-(int)op1.nombreOperationsAffectes;
	        	}
	        }
	    });
	}
	
	public static void mettreAjourDureeTache(RequeteTez req) {
		for(StageTez stage:req.listeStages) {
			for(Operation op:stage.listeOperation) {
				if(op.mettreCollecteur) {
					stage.dureeTacheTezEnMs+=ModeleTempsReponse.temps_collect_stat(op.outputNbTuples, op.stage.nombreGroupesTachesTez);
					stage.dureeTacheTezEnFenetres+=(int)Math.ceil(stage.dureeTacheTezEnMs/VariablesGlobales.tailleFentreTemps);
				}
			}
		}
	}
}
