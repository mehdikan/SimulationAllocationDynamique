package Infrastructure;

import java.util.*;

import Divers.IntKey;
import Modeles.ModeleTempsReponse;

//import com.sun.javafx.collections.MapAdapterChange;

import Divers.*;
import Requetes.*;
import ParametresGlobeaux.*;
import PlanAllocation.PlanStatique;
import PlanAllocation.TrancheTempsAlloue;

public class Cloud {
	public ArrayList<ClasseClients> listeClassesClient;
	public ArrayList<MachinePhysique> listeMachinesPhysique;
	public Map<IntKey, Integer> candidatsMap;
	public Map<IntKey, Integer> candidatsReduce;
	public Map<IntKey, Integer> candidatsTez;
	public Map<IntKey, Integer> distanceSlots;
	public static int heureJournee;
	public static int minuteJournee;
	
	
	public Cloud(){
		listeClassesClient=new ArrayList<ClasseClients>();
		listeMachinesPhysique=new ArrayList<MachinePhysique>();
		candidatsMap= new HashMap<IntKey, Integer>();
		candidatsReduce= new HashMap<IntKey, Integer>();
		candidatsTez= new HashMap<IntKey, Integer>();
		distanceSlots=new HashMap<IntKey, Integer>();
		heureJournee=0;
		minuteJournee=0;
	}
	
	public void ajouterMinutes(int minutesAajouter) {
		minuteJournee=(minuteJournee+minutesAajouter)%60;
		heureJournee=heureJournee+(minuteJournee+minutesAajouter)/60;
	}
	
	public void ajouterVM(int indexMachinePhysique,VM vm){
		this.listeMachinesPhysique.get(indexMachinePhysique).ListeVMs.add(vm);
	}
	
	public void tousCandidatsMap(){
		for(int i=0;i<VariablesGlobales.mapSlotsIndex;i++){
			for(int j=0;j<VariablesGlobales.mapTasksIndex;j++){
				candidatsMap.put(new IntKey(i,j), 1);
			}
		}
	}
	
	public void tousCandidatsReduce(){
		for(int i=0;i<VariablesGlobales.reduceSlotsIndex;i++){
			for(int j=0;j<VariablesGlobales.reduceTasksIndex;j++){
				candidatsReduce.put(new IntKey(i,j), 1);
			}
		}
	}
	
	public void tousCandidatsTez(){
		for(int i=0;i<VariablesGlobales.tezSlotsIndex;i++){
			for(int j=0;j<VariablesGlobales.tezTasksIndex;j++){
				candidatsTez.put(new IntKey(i,j), 1);
			}
		}
	}
	
	
	public void setDefaultDistance(){
		for(MachinePhysique p1:listeMachinesPhysique){
			for(VM vm1:p1.ListeVMs){
				for(GroupeRessources t1: vm1.groupeTezRessources){
					for(MachinePhysique p2:listeMachinesPhysique){
						for(VM vm2:p2.ListeVMs){
							for(GroupeRessources t2: vm2.groupeTezRessources){
								if(p1==p2 && vm1==vm2){
									distanceSlots.put(new IntKey(t1.index,t1.index), 0);
								}
								else if (p1==p2 && vm1!=vm2){
									distanceSlots.put(new IntKey(t1.index,t2.index), 1);
								}
								else {
									distanceSlots.put(new IntKey(t1.index,t2.index), 2);
								}
							}
						}
					}
				}
			}
		}
	}
		
	public int getNbStages(){
		int nb=0;
		for(ClasseClients c : listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				nb+=r.nbStages();
			}
		}
		return nb;
	}
	
	public int getDistanceEntreSlots(int mapSlot,int reduceSlot){
		return distanceSlots.get(new IntKey(mapSlot,reduceSlot));
	}
	
	public int savoirSiCandidatsMap(int slot,int tache){
		if(candidatsMap.get(new IntKey(slot,tache))!=null && candidatsMap.get(new IntKey(slot,tache))==1) return 1;
		return 0;
	}
	
	public int savoirSiCandidatsReduce(int slot,int tache){
		if(candidatsReduce.get(new IntKey(slot,tache))!=null && candidatsReduce.get(new IntKey(slot,tache))==1) return 1;
		return 0;
	}
	
	
	
	public boolean ressourcesDispoTez(GroupeTachesTez tachesG,int instantCourant){
		for(MachinePhysique mp : listeMachinesPhysique){
			for(VM vm : mp.ListeVMs){
				if(tachesG.stage.processeurTacheTez<=vm.processeurTezSlots
					&& tachesG.stage.memoireTacheTez<=vm.memoireTezSlots){
					for(GroupeRessources g:vm.groupeTezRessources){
						int indexRessouces=0;
						if(g.getDisponibilite()==1 && vm.verifierDisponibiliteTez(indexRessouces,instantCourant,ModeleTempsReponse.msToFenetre(tachesG.stage.dureeTacheTezEnMs))){ 
							boolean trouv=false;
							for(GroupeTachesTez gg:tachesG.stage.groupesTezTaches){
								if(tachesG!=gg && gg.ressource==g){
									trouv=true;
								}
							}
							if(!trouv){
								return true;
							}
						}
						indexRessouces++;
					}
				}
			}
		}
		return false;
	}

	public void allouerRessourcesTez(PlanStatique gantt){
		for(TrancheTempsAlloue tta:gantt.tab){
			for(int t=tta.dateDebut;t<=tta.dateFin;t++){
			    this.listeMachinesPhysique.get(this.getIndexMachinePhysiqueTez(tta.indexRessource)).ListeVMs.get(this.getIndexVMTez(tta.indexRessource)).disponibliteTrancheTempsTez[this.getIndexRessourceDansVMTez(tta.indexRessource)][t-1]=0;
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void decalerTempsMR(int temps){
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				for(int t=0;t<VariablesGlobales.T-temps;t++){
					for(int i=0;i<vm.nbMapSlots;i++){
						vm.disponibliteTrancheTempsMap[i][t]=vm.disponibliteTrancheTempsMap[i][temps+t];
					}
					for(int i=0;i<vm.nbReduceSlots;i++){
						vm.disponibliteTrancheTempsReduce[i][t]=vm.disponibliteTrancheTempsReduce[i][temps+t];
					}
				}
				for(int t=VariablesGlobales.T-temps;t<VariablesGlobales.T;t++){
					for(int i=0;i<vm.nbMapSlots;i++){
						vm.disponibliteTrancheTempsMap[i][t]=1;
					}
					for(int i=0;i<vm.nbReduceSlots;i++){
						vm.disponibliteTrancheTempsReduce[i][t]=1;
					}
				}
			}
		}
	}
		
	public void decalerTempsTez(int temps){
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				for(int t=0;t<VariablesGlobales.T-temps;t++){
					for(int i=0;i<vm.nbTezSlots;i++){
						vm.disponibliteTrancheTempsTez[i][t]=vm.disponibliteTrancheTempsTez[i][temps+t];
					}
				}
				for(int t=VariablesGlobales.T-temps;t<VariablesGlobales.T;t++){
					for(int i=0;i<vm.nbTezSlots;i++){
						vm.disponibliteTrancheTempsTez[i][t]=1;
					}
				}
			}
		}
	}
	

	
	public int getIndexMachinePhysiqueMR(int indexRessource,int typeMouR){
		int index=0;
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				if(typeMouR==1){
					for(int i=0;i<vm.nbMapSlots;i++){
						if(index==indexRessource)
							return mp.indexMachinePhysique;
						index++;
					}
				}
				else{
					for(int i=0;i<vm.nbReduceSlots;i++){
						if(index==indexRessource)
							return mp.indexMachinePhysique;
						index++;
					}
				}
			}
		}
		return -1;
	}
	
	public int getIndexMachinePhysiqueTez(int indexRessource){
		int index=0;
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				for(int i=0;i<vm.nbTezSlots;i++){
					if(index==indexRessource)
						return mp.indexMachinePhysique;
					index++;
				}
			}
		}
		return -1;
	}
	

	
	public int getIndexVMTez(int indexRessource){
		int index=0;
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				for(int i=0;i<vm.nbTezSlots;i++){
					if(index==indexRessource)
						return vm.indexVM;
					index++;
				}
			}
		}
		return -1;
	}
	
	public int getIndexRessourceDansVMMR(int indexRessource,int typeMouR){
		int index=0;
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				if(typeMouR==1){
					for(int i=0;i<vm.nbMapSlots;i++){
						if(index==indexRessource)
							return index-vm.indexDebutSlotsMap;
						index++;
					}
				}
				else{
					for(int i=0;i<vm.nbReduceSlots;i++){
						if(index==indexRessource)
							return index-vm.indexDebutSlotsReduce;
						index++;
					}
				}
			}
		}
		return -1;
	}
	
	public int getIndexRessourceDansVMTez(int indexRessource){
		int index=0;
		for(MachinePhysique mp:this.listeMachinesPhysique){
			for(VM vm:mp.ListeVMs){
				for(int i=0;i<vm.nbTezSlots;i++){
					if(index==indexRessource)
						return index-vm.indexDebutSlotsTez;
					index++;
				}
			}
		}
		return -1;
	}

	
	public double tauxSurcharge() {
		double nbNonDisponibles=0;
		double total=0;
		for(MachinePhysique mp:this.listeMachinesPhysique) {
			for(VM vm:mp.ListeVMs) {
				for(int i=0;i<vm.nbTezSlots;i++) {
					for(int t=0;t<VariablesGlobales.T;t++) {
						if(vm.disponibliteTrancheTempsTez[i][t]==0) nbNonDisponibles++;
						total++;
					}
				}
			}
		}
		return nbNonDisponibles/total;
	}
	
	public void calculQuantiteRecuParTache() {
		for(ClasseClients c : this.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage1 : r.listeStages){
					double quantiteRecue=0;
					for(StageTez stage2 : r.listeStages){
						if(r.getLien(stage2,stage1)==1) {
							quantiteRecue+=stage2.quantiteStockeApresStage*stage2.nombreGroupesTachesTez;
						}
					}
					stage1.quantiteRecu=quantiteRecue;
				}
			}
		}
	}
	
	public double nbRessourceNonDispo() {
		double nbNonDisponibles=0;
		double total=0;
		for(MachinePhysique mp:this.listeMachinesPhysique) {
			for(VM vm:mp.ListeVMs) {
				for(int i=0;i<vm.nbTezSlots;i++) {
					for(int t=0;t<VariablesGlobales.T;t++) {
						if(vm.disponibliteTrancheTempsTez[i][t]==0) nbNonDisponibles++;
						total++;
					}
				}
			}
		}
		return nbNonDisponibles;
	}
	
	public double nbRessourceTotal() {
		double nbNonDisponibles=0;
		double total=0;
		for(MachinePhysique mp:this.listeMachinesPhysique) {
			for(VM vm:mp.ListeVMs) {
				for(int i=0;i<vm.nbTezSlots;i++) {
					for(int t=0;t<VariablesGlobales.T;t++) {
						if(vm.disponibliteTrancheTempsTez[i][t]==0) nbNonDisponibles++;
						total++;
					}
				}
			}
		}
		return total;
	}
	
	public void avancerDansTemps() {
		for(MachinePhysique mp:this.listeMachinesPhysique) {
			for(VM vm:mp.ListeVMs) {
				for(int i=0;i<vm.nbTezSlots;i++) {
					for(int t=0;t<VariablesGlobales.T-1;t++) {
						vm.disponibliteTrancheTempsTez[i][t]=vm.disponibliteTrancheTempsTez[i][t+1];
					}
					vm.disponibliteTrancheTempsTez[i][VariablesGlobales.T-1]=1;
				}
			}
		}
	}
	
	public void viderRequetesEnAttentes() {
		for(ClasseClients c : listeClassesClient){
			c.requeteTezEnAttente.clear();
		}
	}
	
	public RequeteTez getRequete(int indexRequete) {
		for(ClasseClients c:this.listeClassesClient) {
			for(RequeteTez r:c.requeteTezEnAttente) {
				if(r.index==indexRequete)
					return r;
			}
		}
		return null;
	}
	
	public void calculDateLimites() {
		for(ClasseClients c:this.listeClassesClient) {
			for(RequeteTez r:c.requeteTezEnAttente) {
				r.dateLimite=(int)Math.ceil(r.tailleRequete()*r.importanceDateLimite);
			}
		}
	}
}
