package Modeles;

import Infrastructure.*;
import ParametresGlobeaux.VariablesGlobales;
import Requetes.*;

import org.omg.IOP.TaggedComponentHolder;

import AllocationStatique.*;

public class ModeleCommunication {
	public static double bwVM=0;
	public static double bwMPhysique=(80.0*1024/8/(256))/1000;  // bloc/ms  (80gbit/s)
	public static double bwReseauPhysique=(80.0*1024/8/(256))/1000; // bloc/ms  (80gbit/s)  
	
	
	public static double distanceToBW(int distance) {
		if(distance==1) return bwMPhysique;
		else if (distance==2) return bwReseauPhysique;
		return bwVM;
	}
	
	public static void rajouterTempsCommunicationsILP(Cloud cloud,ModelePlacementILP modelPlacement) {
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage1 : r.listeStages){
					for(GroupeTachesTez tache1:stage1.groupesTezTaches) {
						double tempsComm=0;
					    for(StageTez stage2 : r.listeStages){
						   if(r.getLien(stage1,stage2)==1){
								for(GroupeTachesTez tache2:stage2.groupesTezTaches) {
									if(modelPlacement.A[stage1.indexStage][tache1.indexDansStage]!=modelPlacement.A[stage2.indexStage][tache2.indexDansStage]) {
										double bw=ModeleCommunication.distanceToBW(cloud.getDistanceEntreSlots(modelPlacement.A[stage1.indexStage][tache1.indexDansStage], modelPlacement.A[stage2.indexStage][tache2.indexDansStage]));
										if(bw>0) {
											tempsComm+=r.getQuantiteTransfertStages(stage1, stage2)/bw;
										}
									}
								}
							}
						}
					    tache1.dureeCommunicationEnMs=(int)tempsComm;
					}
				}
			}
		}
	}
	
	public static void rajouterTempsCommunicationsGreedy(Cloud cloud) {
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage1 : r.listeStages){
					for(GroupeTachesTez tache1:stage1.groupesTezTaches) {
						double tempsComm=0;
					    for(StageTez stage2 : r.listeStages){
						   if(r.getLien(stage1,stage2)==1){
								for(GroupeTachesTez tache2:stage2.groupesTezTaches) {
									if(tache1.ressource!=tache2.ressource) {
										double bw=ModeleCommunication.distanceToBW(cloud.getDistanceEntreSlots(tache1.ressource.index, tache2.ressource.index));
										if(bw>0) {
											tempsComm+=r.getQuantiteTransfertStages(stage1, stage2)/bw;
										}
									}
								}
							}
						}
					    //tache1.dureeCommunication=(int)Math.ceil(tempsComm/VariablesGlobales.tailleFentreTemps);
					    tache1.dureeCommunicationEnMs=(int)tempsComm;
					}
				}
			}
		}
	}
}
