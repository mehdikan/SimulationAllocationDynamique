package AllocationStatique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import Infrastructure.*;
import Modeles.ModeleCommunication;
import Modeles.*;
import ParametresGlobeaux.*;
import Requetes.*;
import PlanAllocation.*;

public abstract class GenericGreedy {
	Cloud cloud;
	ArrayList<GroupeTachesTez> allGroupsTaches;
	ArrayList<GroupeRessources> allGroupsSlots;
	TreeSet<EvenementFinTachesTez> evenements;
	//double coutComm=-1;
	//double coutProc=-1;
	//double coutMem=-1;
	//double coutStor=-1;
	public int ordre=1;
	
	public GenericGreedy(){}
	
	public GenericGreedy(Cloud cloud){
		this.cloud=cloud;
		allGroupsTaches=new ArrayList<GroupeTachesTez>();
		allGroupsSlots=new ArrayList<GroupeRessources>();
		
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
	
	public ModeleCoutEconomique lancer(){
		placer();
		for(GroupeTachesTez tache:allGroupsTaches){
			tache.fini=0;
		}
		ModeleCommunication.rajouterTempsCommunicationsGreedy(cloud);
		ModeleCoutEconomique cout=ordonnancer(false);
		System.out.println("temps Total : "+cout.tempsExecTotal);
		return cout;
	}
	
	public void placer(){
		HashSet<GroupeTachesTez> readyTasks=new HashSet<GroupeTachesTez>();
		// ajouter les taches sans dependances	
		for(GroupeTachesTez tache : allGroupsTaches){
			if(tache.dependances.size()==0){
				readyTasks.add(tache);
			}
		}
		
		while(readyTasks.size()!=0){
			GroupeTachesTez n=next(readyTasks);
			ArrayList<GroupeRessources> candidates=ressourcesCandidates(n);
			if(candidates.size()==0){
				System.out.println("EEEEEEEEEEEEreur");
				return;
			}
			
			GroupeRessources c=affecter(n,candidates);
			readyTasks.remove(n);
			n.fini=1;
			n.ressource=c;
			n.ordre=this.ordre;
			this.ordre++;
			
			for(GroupeTachesTez tache : allGroupsTaches){
				if(tache.fini==0){
					boolean trouv=false;
					for(GroupeTachesTez depend : tache.dependances){
						if(depend.fini==0){
							trouv=true;
							break;
						}
					}
					if(!trouv){
						readyTasks.add(tache);
					}
				}
			}
		}
	}
	
	public abstract GroupeTachesTez next(HashSet<GroupeTachesTez> readyTasks);
	
	ArrayList<GroupeRessources> ressourcesCandidates(GroupeTachesTez n){
		ArrayList<GroupeRessources> ressCandidates=new ArrayList<GroupeRessources>();
		for(MachinePhysique mp : cloud.listeMachinesPhysique){
			for(VM vm : mp.ListeVMs){
				if(n.stage.processeurTacheTez<=vm.processeurTezSlots
					&& n.stage.memoireTacheTez<=vm.memoireTezSlots){
					for(GroupeRessources g:vm.groupeTezRessources){
						ressCandidates.add(g);
					}
				}
			}
		}
		return ressCandidates;
	}
	
	public abstract GroupeRessources affecter(GroupeTachesTez n,ArrayList<GroupeRessources> candidates);
	
	public ModeleCoutEconomique ordonnancer(boolean partiel){
		EvenementFinTachesTez evCourant;
		int tCourant;
		evenements=new TreeSet<EvenementFinTachesTez>();
		evenements.add(new EvenementFinTachesTez(1,null,null));
		GroupeTachesTez elu;
		int tempsMax=0;
		ModeleCoutEconomique c=new ModeleCoutEconomique();
		
		if(partiel) this.stock();

		while(!evenements.isEmpty() )
		{
			evCourant=evenements.first();
			tCourant=evCourant.instant;
			//if(tCourant>40) break;
			if(!partiel) System.out.println("Instant : "+tCourant);
			for(GroupeRessources gr:evCourant.ressorceALiberer){ 
				gr.setLibre();
				if(!partiel) System.out.println("Groupe de Ressources "+gr.type+"-"+gr.index+" libérée"+"- temps :"+tCourant);
			}
			for(GroupeTachesTez tache:evCourant.tachesFinies){ 
				tache.fini=1;
				tache.dateFin=tCourant;
				tempsMax=Math.max(tempsMax, tCourant);
				tache.stage.requeteTez.tempsGMPT=Math.max(tache.stage.requeteTez.tempsGMPT, tCourant);
				//System.out.println("# "+tempsMax);
				if(!partiel) System.out.println("Groupe de taches "+tache.index+" finies");
			}
			
			evenements.remove(evenements.first());
			
			boolean trouvTache;
			if(partiel){
				trouvTache=false;
				for(GroupeTachesTez task:allGroupsTaches){
					if(task.ressource!=null){
						trouvTache=true; break;
					}
				}

			}
			else trouvTache=true;
			
			while(trouvTache && allGroupsTaches.size()>0){	
				//if(tCourant>40) break;
				
				elu=groupeTachesElu(tCourant,partiel);
				//if(tCourant<15) System.out.println("-- "+tCourant+" "+elu);				
				if(elu!=null){
					elu.tempsDeclanchement=tCourant;
					//GroupeRessources ress=this.placementNaif(cloud,elu,tCourant);
					//if(ress!=null)
					if(!partiel) System.out.println("Groupes de taches "+elu.stage.requeteTez.index+"-"+elu.stage.indexStage+"-"+elu.index+" est elue et placé dans la ressource "+elu.ressource.type+"-"+elu.ressource.index+"- temps: "+tCourant);
				
					int indexRessouces=0;
					boolean trouv=false;
					for(GroupeTachesTez tache : allGroupsTaches){
						for(MachinePhysique mp : cloud.listeMachinesPhysique){
							for(VM vm : mp.ListeVMs){
									indexRessouces=0;
									for(GroupeRessources g:vm.groupeTezRessources){
										if(g==elu.ressource){
											trouv=true; break;
										}
										indexRessouces++;
									}
									if(trouv) break;
							}
							if(trouv) break;
						}
						if(trouv) break;
					};

					elu.ressource.vm.setAlloueTez(indexRessouces,tCourant,(int)Math.ceil((elu.stage.dureeTacheTezEnMs+elu.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps));
					 Iterator<EvenementFinTachesTez> iterator = evenements.iterator(); 
					 trouv=false;
				      while (iterator.hasNext()){
				    	  EvenementFinTachesTez ev=(EvenementFinTachesTez) iterator.next();
				         if(ev.instant==tCourant+(int)Math.ceil((elu.stage.dureeTacheTezEnMs+elu.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps)){
				        	 trouv=true;
				        	 ev.ressorceALiberer.add(elu.ressource);
				        	 ev.tachesFinies.add(elu);
				        	 break;
				         }
				      }
				      if(!trouv){
				    	  evenements.add(new EvenementFinTachesTez(tCourant+(int)Math.ceil((elu.stage.dureeTacheTezEnMs+elu.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps),elu.ressource,elu));
				      }
				      allGroupsTaches.remove(elu);

				}
				else{
					evenements.add(new EvenementFinTachesTez(tCourant+1,null,null));
					break;
				}
			}
		}
		
		c=calculCout(partiel);
		c.tempsExecTotal=tempsMax;
		int cpt=0;
		c.tempsExecMoyenRequete=0;
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				c.tempsExecMoyenRequete+=(double)r.tempsGMPT;
				r.tempsGMPT=0;
				cpt++;
			}
		}
		if(cpt>0) { c.tempsExecMoyenRequete=c.tempsExecMoyenRequete/cpt;}
		if(partiel) this.back();
		return c;
	}
	
	public GroupeTachesTez groupeTachesElu(int instantCourant,boolean partiel){
		GroupeTachesTez elu=null;
		boolean trouv=false;
		int indexRessouces=0;
		
		for(GroupeTachesTez tache : allGroupsTaches){
			trouv=false;
			for(MachinePhysique mp : cloud.listeMachinesPhysique){
				for(VM vm : mp.ListeVMs){
						indexRessouces=0;
						for(GroupeRessources g:vm.groupeTezRessources){
							if(g==tache.ressource){
								trouv=true; break;
							}
							indexRessouces++;
						}
						if(trouv) break;
				}
				if(trouv) break;
			}
			if(tache.ressource!=null && tache.pret(cloud,instantCourant) && tache.ressource.vm.verifierDisponibiliteTez(indexRessouces,instantCourant,(int)Math.ceil((tache.stage.dureeTacheTezEnMs+tache.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps))){
				if(elu==null 
						|| tache.ordre<elu.ordre)
				{
					elu=tache;
					//return elu;
				}
			}
		}
		
		
		return elu;
	}
	
	public double coutOrdonnancementTotal(){
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				for(StageTez stage : r.listeStages){
					for(GroupeTachesTez tache : stage.groupesTezTaches){
						if(tache.fini==1){
							if(tache.dateFin>r.dateFinReelle){
								r.dateFinReelle=tache.dateFin;
							}
						}
						else{
							System.out.println("Job non finie");
						}
					}
				}
			}
		}
		
		double coutTotal=0;
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				if(r.dateFinReelle-r.dateLimite>0){
					coutTotal+=(r.dateFinReelle-r.dateLimite-1)*r.poids;
				}
			}
		}
		
		System.out.println("Cout pénalités - : "+coutTotal);
		return coutTotal;
	}
	
	public ModeleCoutEconomique calculCout(boolean partiel){
		ModeleCoutEconomique modeleCout=new ModeleCoutEconomique();
		ArrayList<GroupeTachesTez> allGroupsTaches;
		ArrayList<GroupeRessources> allGroupsSlots;
		allGroupsTaches=new ArrayList<GroupeTachesTez>();
		allGroupsSlots=new ArrayList<GroupeRessources>();
		
		
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
				for(GroupeRessources tache: vm.groupeTezRessources){
					allGroupsSlots.add(tache);
				}
			}
		}
		
		double W[][]=new double[allGroupsSlots.size()][allGroupsSlots.size()];
		for(int i=0;i<allGroupsSlots.size();i++){
			for(int j=0;j<allGroupsSlots.size();j++){
				W[i][j]=0;
			}
		}
		for(GroupeTachesTez tache1:allGroupsTaches){
			if(tache1.ressource!=null){
				for(GroupeTachesTez tache2:allGroupsTaches){
					if(tache2.ressource!=null && tache1.stage.requeteTez.index==tache2.stage.requeteTez.index){
						if(tache1.stage.requeteTez.getQuantiteTransfertStages(tache1.stage, tache2.stage)>0){
							W[tache1.ressource.index][tache2.ressource.index]+=tache1.stage.requeteTez.getQuantiteTransfertStages(tache1.stage, tache2.stage);
						}
					}
				}
			}
		}

		modeleCout.coutComm=0;
		for(GroupeRessources a:allGroupsSlots){
			for(GroupeRessources b:allGroupsSlots){
				modeleCout.coutComm+=W[a.index][b.index]*ModeleCoutEconomique.distanceToPoidsCommunication(cloud.getDistanceEntreSlots(a.index, b.index));
			}
		}
		modeleCout.coutRessources=0;
		for(GroupeTachesTez tache:allGroupsTaches){
			if(tache.ressource!=null){
				//coutProc+=tache.ressource.vm.processeurTezSlots*(tache.stage.dureeTacheTez+tache.dureeCommunication);
				modeleCout.coutRessources+=tache.ressource.vm.memoireTezSlots*(int)Math.ceil((tache.stage.dureeTacheTezEnMs+tache.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps);
			}
		}

		
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				for(StageTez stage : r.listeStages){
					for(GroupeTachesTez tache : stage.groupesTezTaches){
						if(tache.fini==1){
							if(tache.dateFin>r.dateFinReelle){
								r.dateFinReelle=tache.dateFin;
							}
						}
					}
				}
			}
		}
		modeleCout.coutPenalite=0;
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				if(r.dateFinReelle-r.dateLimite>0){
					modeleCout.coutPenalite+=(r.dateFinReelle-r.dateLimite-1)*r.poids;
				}
			}
		}		
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
					r.dateFinReelle=0;
			}
		}
		
		modeleCout.coutDisque=0;
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage1 : r.listeStages){
					for(StageTez stage2 : r.listeStages){
						if(r.getLien(stage2,stage1)==1) {
							for(GroupeTachesTez tache1: stage1.groupesTezTaches){
								double dureeDisque=0;
								for(GroupeTachesTez tache2: stage2.groupesTezTaches){
									dureeDisque=Math.max(dureeDisque, tache1.tempsDeclanchement+(int)Math.ceil((stage1.dureeTacheTezEnMs+tache1.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps)-(tache2.tempsDeclanchement+stage2.dureeTacheTezEnFenetres));
									//dureeDisque=Math.max(dureeDisque, tache1.tempsDeclanchement-(tache2.tempsDeclanchement+stage2.dureeTacheTez));
								}
								modeleCout.coutDisque+=dureeDisque*stage1.quantiteRecu;
							}
						}
					}
				}
			}
		}
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage : r.listeStages){
					for(GroupeTachesTez tache: stage.groupesTezTaches){
						modeleCout.coutDisque+=stage.donneeInitiale*((int)Math.ceil((stage.dureeTacheTezEnMs+tache.dureeCommunicationEnMs)/VariablesGlobales.tailleFentreTemps));
					}
				}
			}
		}
		
		return modeleCout;
	}
	

	public PlanStatique ecrireResultats(){
		PlanStatique gantt=new PlanStatique();
	    for(ClasseClients cc:this.cloud.listeClassesClient){
	    	for(RequeteTez rq:cc.requeteTezEnAttente){
	    		for(StageTez stage:rq.listeStages){
	    			for(GroupeTachesTez tache: stage.groupesTezTaches){
	    				gantt.ajouterTrancheTemps(new TrancheTempsAlloue(tache.ressource.index, rq.index, stage.indexStage, tache.index, tache.tempsDeclanchement, (tache.dateFin-1)));
	    			}
	    		}
	    	}
	    }

	    return gantt;
	}	
	
	
	public void stock(){
		for(GroupeRessources gr:this.allGroupsSlots){
			gr.dispoStock();
		}
		for(GroupeTachesTez tache:this.allGroupsTaches){
			tache.attributsStock();
		}
		for(MachinePhysique mp : cloud.listeMachinesPhysique){
			for(VM vm : mp.ListeVMs){
				vm.disponibiliteTrancheTempsStock();
			}
		}
	}
	
	public void back(){
		allGroupsTaches=new ArrayList<GroupeTachesTez>();
		for(ClasseClients cc : cloud.listeClassesClient){
			for(RequeteTez r : cc.requeteTezEnAttente){
				for(StageTez stage : r.listeStages){
					for(GroupeTachesTez tache : stage.groupesTezTaches){
						allGroupsTaches.add(tache);
					}
				}
			}
		}
		for(GroupeRessources gr:this.allGroupsSlots){
			gr.dispoback();
		}
		for(GroupeTachesTez tache:this.allGroupsTaches){
			tache.attributsBack();
		}

		for(MachinePhysique mp : cloud.listeMachinesPhysique){
			for(VM vm : mp.ListeVMs){
				vm.disponibiliteTrancheTempsBack();
			}
		}
	}
}
