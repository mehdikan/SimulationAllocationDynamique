package AllocationStatique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import Infrastructure.*;
import Requetes.*;
import PlanAllocation.*;
import ParametresGlobeaux.*;

public class GMPM extends GenericGreedy {
		
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
		for(GroupeTachesTez t:readyTasks){
			if(candidat==null || t.quantiteTotalOutput(cloud)>candidat.quantiteTotalOutput(cloud)){
				candidat=t;
			}
		}
		return candidat;
	}
	

	
	
	public GroupeRessources affecter(GroupeTachesTez n,ArrayList<GroupeRessources> candidates){
		GroupeRessources ressChoisie=null;
		double meilleurCout=-1;
		//System.out.println("=============== Type"+n.type+" "+" R"+n.job.requete.index+" J"+n.job.indexJob+" T"+n.index+"Duree"+n.duree);
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

				Cout cout=ordonnancer(true);
				//System.out.println("============> "+ress.index+" "+temps);
				if(meilleurCout==-1 || cout.coutTotal()<meilleurCout){
					meilleurCout=cout.coutTotal();
					ressChoisie=ress;	
				}
			}
		}
		n.fini=0;
		n.ressource=null;
		//System.out.println("???> "+ressChoisie.index);
		return ressChoisie;
	}
}
