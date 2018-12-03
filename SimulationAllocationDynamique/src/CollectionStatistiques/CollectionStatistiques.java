package CollectionStatistiques;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Infrastructure.*;
import ParametresGlobeaux.VariablesGlobales;
import Requetes.*;
import Modeles.*;

public class CollectionStatistiques {
	public static double poids_date_limite=1;
	public static double poids_poids_penalities=1;
	public static double tempsCollectStatistiques=0;
	public static double mu=0.05;
	
	public static void placementCollecteurStatistiques(Cloud cloud,boolean cloudMethod) {
		if(cloudMethod) {
			tempsCollectStatistiques=calculTempsTotalCollectStat(cloud);
			System.out.println("tempsCollectStatistiques = "+tempsCollectStatistiques);
		}
		definirRepartitionTempsCollectStat(cloud,cloudMethod);
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				trierEndoitCollect(req,cloudMethod);
				definirEndroitCollect(req);
				req.majDureeStages();
			}
		}
	}
	
	public static double calculTempsTotalCollectStat(Cloud cloud) {
		double tailleTotal=0;
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				tailleTotal+=req.tailleRequete();
			}
		}
		tailleTotal*=mu;
		return tailleTotal;
	}
	
	public static void definirRepartitionTempsCollectStat(Cloud cloud,boolean cloudMethod) {
		double somme=0;
		if(cloudMethod) {
			for(ClasseClients c : cloud.listeClassesClient) {
				for(RequeteTez req : c.requeteTezEnAttente) {
					req.tempstCollectStatistiques=definirPoidsCollectStatRequete(req);
					somme+=req.tempstCollectStatistiques;
				}
			}
		}
		for(ClasseClients c : cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				if(cloudMethod) {
					req.tempstCollectStatistiques=(req.tempstCollectStatistiques/somme)*tempsCollectStatistiques;
				}
				else {
					
					req.tempstCollectStatistiques=mu*req.tailleRequete();
					System.out.println("fffffffffffffffff "+req.tailleRequete()+" "+req.tempstCollectStatistiques);
				}
			}
		}
	}
	
	public static double definirPoidsCollectStatRequete(RequeteTez req) {
		double taille=req.tailleRequete();
		return taille*(poids_date_limite*(1/req.importanceDateLimite)+poids_poids_penalities*req.poids);
	}
	
	public static void definirEndroitCollect(RequeteTez requete) {
		double taille=0;
		for(CollecteurPotentiel cp:requete.listeEndroitCollectStatTrie) {
			if(taille+ModeleTempsReponse.temps_collect_stat(operation.outputNbTuples,operation.stage.nombreTachesElementairesTez)*operation.stage.nombreGroupesTachesTez<requete.tempstCollectStatistiques) {
				cp.mettreCollecteur=true;
			}
			taille+=ModeleTempsReponse.temps_collect_stat(operation.outputNbTuples,operation.stage.nombreTachesElementairesTez)*operation.stage.nombreGroupesTachesTez;
		}
		System.out.println("nnnnnnn "+taille);
	}
	
	public static void trierEndoitCollect(RequeteTez requete,boolean cloudMethod) {
		requete.listeEndroitCollectStatTrie=new ArrayList<CollecteurPotentiel>();
		for(StageTez stage:requete.listeStages) {
			for(Operation operation:stage.listeOperation) {
				if(operation.isInteressantMettreCollecteur()) {
					for(CollecteurPotentiel cp : operation.collecteursPotentiels) {
						requete.listeEndroitCollectStatTrie.add(cp);
					}
				}
			}
		}
		
		Collections.sort(requete.listeEndroitCollectStatTrie, new Comparator<CollecteurPotentiel>() {
	        @Override
	        public int compare(CollecteurPotentiel cp1, CollecteurPotentiel cp2)
	        {
	        	if(cp1.nombreOperationsAffectes==0) {
	        		if(cp2.nombreOperationsAffectes==0) {
	        			return 0;
	        		}
	        		return -1;
	        	}
	        	else if(cp1.nombreOperationsAffectes==0) {
	        		return 1;
	        	}
	        	
	        	if(cp1.niveauInexactitude!=cp2.niveauInexactitude) {
	        		return cp2.niveauInexactitude-cp1.niveauInexactitude;
	        	}
	        	else{
	        		if(cloudMethod) {
	        			return (int)cp2.nombreTachesApres-(int)cp1.nombreTachesApres;
	        		}
	        		else {
	        			return (int)cp2.nombreOperationsAffectes-(int)cp1.nombreOperationsAffectes;
	        		}
	        	}
	        }
	    });
	}
	
	
}
