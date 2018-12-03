package AllocationStatique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import Divers.Statistics;
import Infrastructure.*;
import Modeles.*;
import Requetes.*;
import PlanAllocation.*;
import ParametresGlobeaux.*;

public class GMPM extends GenericGreedy {
	public int affectationStategie=0;
	public int nextStrategie=0;
		
	public GMPM(Cloud cloud){
		this.cloud=cloud;
		allGroupsTaches=new ArrayList<GroupeTachesTez>();
		allGroupsSlots=new ArrayList<GroupeRessources>();
		
		evenements=new TreeSet<EvenementFinTachesTez>();
		
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				for(StageTez stage : r.listeStages){
					for(GroupeTachesTez tache : stage.groupesTezTaches){
						allGroupsTaches.add(tache);
					}
				}
			}
		}
		
		for(MachinePhysique mp:cloud.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				for(GroupeRessources slot: vm.groupeTezRessources){
					allGroupsSlots.add(slot);
				}
			}
		}
	}
	
	
	public GroupeTachesTez next(HashSet<GroupeTachesTez> readyTasks){
		GroupeTachesTez candidat=null;
		if(nextStrategie==0) {
			for(GroupeTachesTez t:readyTasks){
				if(candidat==null || t.stage.quantiteRecu>candidat.stage.quantiteRecu){
					candidat=t;
				}
			}
		}
		else {
			for(GroupeTachesTez t:readyTasks){
				if(candidat==null 
						|| t.stage.requeteTez.dateLimite/t.stage.requeteTez.poids > candidat.stage.requeteTez.dateLimite/candidat.stage.requeteTez.poids){
					candidat=t;
				}
			}
		}
		nextStrategie=nextStrategie%2;
		return candidat;
	}
	
	public GroupeRessources affecter(GroupeTachesTez n,ArrayList<GroupeRessources> candidates){
		GroupeRessources ressChoisie=null;
		if(affectationStategie==0) {
			double meilleurCout=-1;
			for(GroupeRessources ress:candidates){		
				boolean trouv=false;
				for(GroupeTachesTez tache:allGroupsTaches){
					if(tache.stage==n.stage && tache.ressource==ress){
						trouv=true;
					}
				}
				
				if(!trouv){			
					n.fini=1;
					n.ressource=ress;

					ModeleCoutEconomique cout=ordonnancer(true);
					if(meilleurCout==-1 || cout.prixCommunication()+cout.prixRessources()+cout.prixDisque()<meilleurCout){
						meilleurCout= cout.prixCommunication()+cout.prixRessources()+cout.prixDisque();
						ressChoisie=ress;	
					}
				}
			}
			n.fini=0;
			n.ressource=null;
		}
		else {
			HashMap<GroupeRessources,Integer> chargeRessources=new HashMap<GroupeRessources,Integer>();
			for(GroupeRessources ress:allGroupsSlots){
				chargeRessources.put(ress, 0);
			}
			for(GroupeTachesTez tache:allGroupsTaches){
				if(tache.ressource!=null){
					chargeRessources.put(tache.ressource, chargeRessources.get(tache.ressource)+ModeleTempsReponse.msToFenetre(tache.stage.dureeTacheTezEnMs));
				}
			}
			double deviation=-1;
			for(GroupeRessources ress:candidates){
				chargeRessources.put(ress, chargeRessources.get(ress)+ModeleTempsReponse.msToFenetre(n.stage.dureeTacheTezEnMs));
				Statistics stat=new Statistics(chargeRessources);
				double var=stat.getVariance();
				if(deviation==-1 || var<deviation){
					boolean trouv=false;
					for(GroupeTachesTez tache:allGroupsTaches){
						if(tache.stage==n.stage && tache.ressource==ress){
							trouv=true;
						}
					}
					if(!trouv){
						deviation=var;
						ressChoisie=ress;
					}
				}
				chargeRessources.put(ress, chargeRessources.get(ress)-ModeleTempsReponse.msToFenetre(n.stage.dureeTacheTezEnMs));
			}
		}
		
		this.affectationStategie=(this.affectationStategie+1)%2;
		return ressChoisie;
	}
}
