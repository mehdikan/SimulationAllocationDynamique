package Requetes;

import java.util.ArrayList;

import Modeles.ModeleTempsReponse;
import ParametresGlobeaux.*;

public class StageTez {
	public RequeteTez requeteTez;
	public double dureeTacheTezEnMs;
	//public int dureeTacheTezEnFenetres;
	public int nombreGroupesTachesTez;
	public int nombreTachesElementairesTez;
	public int processeurTacheTez;
	public long memoireTacheTez;
	public int indexStage;
	public int indexDebutTasksTez;
	public ArrayList<GroupeTachesTez> groupesTezTaches;
	public long ordreArrive;
	public double quantiteStockeApresStage;
	public double quantiteRecu=0;
	public double donneeInitiale=0;
	public boolean isReduce;
	public ArrayList<Operation> listeOperation;
	public String name;
	
	public StageTez(String name,RequeteTez reqTez,int nombreGroupesTachesTez,long memoireTacheTez,double quantiteStockeApresStage,double donneeInitiale,int nombreTachesElementairesTez,boolean isReduce){
		this.name=name;
		this.requeteTez=reqTez;
		this.dureeTacheTezEnMs=0;
		//this.dureeTacheTezEnFenetres=0;
		this.nombreGroupesTachesTez=nombreGroupesTachesTez;
		this.processeurTacheTez=VariablesGlobales.nbTacheParGroupe;
		this.memoireTacheTez=memoireTacheTez;
		this.quantiteStockeApresStage=quantiteStockeApresStage;
		this.donneeInitiale=donneeInitiale;
		this.nombreTachesElementairesTez=nombreTachesElementairesTez;
		this.isReduce=isReduce;
		this.ordreArrive=VariablesGlobales.stageOrdreArrive;
		VariablesGlobales.stageOrdreArrive+=1;
		
		this.indexStage=VariablesGlobales.stageIndex;
		VariablesGlobales.stageIndex++;
		this.indexDebutTasksTez=VariablesGlobales.tezTasksIndex;
		VariablesGlobales.tezTasksIndex+=nombreGroupesTachesTez;
		
		this.groupesTezTaches=new ArrayList<GroupeTachesTez>();
		for(int i=0;i<nombreGroupesTachesTez;i++){
			this.groupesTezTaches.add(new GroupeTachesTez(this,i));
		}
		listeOperation=new ArrayList<Operation>();
	}
	
	public void calculDureeTacheTez() {
		this.dureeTacheTezEnMs=0;
		int nbTasks=nombreTachesElementairesTez;
		for(Operation op:this.listeOperation) {

			if(op.type==TypeOperation.TABLESCAN) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_scan_table(op.outputSize,nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.LECTUREDISQUE) {
				if(op.operationSuivante.type!=TypeOperation.LIMIT) {
					if(this.isReduce) {
						op.dureeEnMs=ModeleTempsReponse.temps_lecture_disque(op.operationPrec1.outputSize,0, nbTasks);
						this.dureeTacheTezEnMs+=op.dureeEnMs;
					}
					else {
						op.dureeEnMs=ModeleTempsReponse.temps_lecture_disque(0, op.operationPrec1.outputSize, nbTasks);
						this.dureeTacheTezEnMs+=op.dureeEnMs;
					}
				}
				else {
					if(this.isReduce) {
						op.dureeEnMs=ModeleTempsReponse.temps_lecture_disque(op.operationSuivante.outputSize,0, nbTasks);
						this.dureeTacheTezEnMs+=op.dureeEnMs;
					}
					else {
						op.dureeEnMs=ModeleTempsReponse.temps_lecture_disque(0, op.operationSuivante.outputSize, nbTasks);
						this.dureeTacheTezEnMs+=op.dureeEnMs;
					}
				}
					
			}
			else if(op.type==TypeOperation.ECRITUREDISQUE) {
				op.dureeEnMs=ModeleTempsReponse.temps_ecriture_disque(op.outputSize, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.FILTER) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_filter(op.operationPrec1.outputNbTuples, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.PROJECTION) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_projection(op.operationPrec1.outputNbTuples, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.MAPJOIN) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_map_join_operator(op.operationPrec1.outputNbTuples,op.operationPrec2.outputNbTuples, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.MERGEJOIN) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_reduce_join_operator(op.operationPrec1.outputNbTuples,op.operationPrec2.outputNbTuples,op.operationPrec1.stage.nombreTachesElementairesTez,op.operationPrec2.stage.nombreTachesElementairesTez, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.GROUPBY) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_group(op.operationPrec1.outputNbTuples, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.LIMIT) {
				op.dureeEnMs=ModeleTempsReponse.temps_operation_limit(op.outputNbTuples);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			else if(op.type==TypeOperation.PARTITION) {
				op.dureeEnMs=ModeleTempsReponse.temps_repartition(op.outputNbTuples, nbTasks);
				this.dureeTacheTezEnMs+=op.dureeEnMs;
			}
			
			if(op.mettreCollecteur) {
				op.dureeEnMs+=ModeleTempsReponse.temps_collect_stat(op.outputNbTuples, op.stage.nombreGroupesTachesTez);
				this.dureeTacheTezEnMs+=ModeleTempsReponse.temps_collect_stat(op.outputNbTuples, op.stage.nombreGroupesTachesTez);
			}

		}
		
	}
	
	public void descriptionStage() {
		//System.out.println("--------------------------------------------------------");
		System.out.println("Stage : "+this.name);
		//System.out.println("Duree stage en ms : "+this.dureeTacheTezEnMs);
		
		for(Operation op : this.listeOperation) {
			System.out.println("operation "+op.dureeEnMs);
		}
		
		/*for(GroupeTachesTez tache: this.groupesTezTaches)
		{
			System.out.println("tache "+this.dureeTacheTezEnMs+" "+tache.dureeCommunicationEnMs+" - "+ModeleTempsReponse.msToFenetre(this.dureeTacheTezEnMs+tache.dureeCommunicationEnMs));
		}*/
		//System.out.println("--------------------------------------------------------------------------");
	}
}
