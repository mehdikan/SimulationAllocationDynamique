package Simulations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import ParametresGlobeaux.*;
import PlanAllocation.*;
import Requetes.*;
import TPCH.*;
import Infrastructure.*;
import Modeles.*;
import Requetes.*;
import Divers.*;
import Infrastructure.*;
import AllocationStatique.*;
import CollectionStatistiques.*;
import Divers.CSVUtils;
import AllocationDynamique.*;

public class testTPCH100 {
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub		
		int algoStatique=2;
		int nb=VariablesGlobales.nbTacheParGroupe;
		Cloud cloud=new Cloud();
		for(int j=0;j<7;j++) {
			cloud.listeMachinesPhysique.add(new MachinePhysique());
			for(int i=0;i<7;i++) {
				cloud.ajouterVM(j,new VM(1,nb*1,(int)(4L*1024*1024*1024/VariablesGlobales.tailleBlocDonnees),i));
			}
		}
		cloud.setDefaultDistance();

		cloud.listeClassesClient.add(new ClasseClients());
		RequeteTez req3=TPCH100.q3_scale100(cloud);
		RequeteTez req4=TPCH100.q4_scale100(cloud);
		RequeteTez req5=TPCH100.q5_scale100(cloud);
		RequeteTez req8=TPCH100.q8_scale100(cloud);
		RequeteTez req10=TPCH100.q10_scale100(cloud);	
		cloud.calculQuantiteRecuParTache();
		
		PlacementStatistiques.placementCollecteurStatistiquesCloud(cloud);
		
		req3.descriptionRequete();
		req4.descriptionRequete();
		req5.descriptionRequete();
		req8.descriptionRequete();
		req10.descriptionRequete();
		
		PlanAllocation.PlanStatique gantt;
		if(algoStatique==1) {
		    VariablesGlobales.writer_pl2p=new FileWriter("cout.csv",true);
		    ModeleCoutEconomique modeleCout=new ModeleCoutEconomique();
			ModelePlacementILP modelPlacement=new ModelePlacementILP(cloud,modeleCout);
			long startTime = System.currentTimeMillis();
			modelPlacement.resoudre();
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    if(VariablesGlobales.verbose) System.out.println("Temps Placement = "+elapsedTime);
		    ModeleOrdonnancementILP modeleOrdo=new ModeleOrdonnancementILP(cloud,modeleCout,modelPlacement.A);
		    startTime = System.currentTimeMillis();
		    gantt=modeleOrdo.resoudre();
		    stopTime = System.currentTimeMillis();
		    elapsedTime = elapsedTime+ (stopTime - startTime);
		    if(VariablesGlobales.verbose) System.out.println("Temps Ordonnancement = "+elapsedTime);
		    gantt.ecrireDansFichier();
		    cloud.allouerRessourcesTez(gantt);
		    CSVUtils.writeLine(VariablesGlobales.writer_pl2p, Arrays.asList(Long.toString(elapsedTime), Double.toString(modeleCout.prixRessources()),Double.toString(modeleCout.prixCommunication()),Double.toString(modeleCout.prixPenalites()),Double.toString(modeleCout.prixDisque()),Double.toString(modeleCout.prixTotal())));
			VariablesGlobales.writer_pl2p.flush();
			VariablesGlobales.writer_pl2p.close();
			modeleCout.print();
		}
		else if(algoStatique==2) {
		    VariablesGlobales.writer_gmpm=new FileWriter("comp63-gmpm.csv",true);
			GMPM gmpm=new GMPM(cloud);
			long startTime = System.currentTimeMillis();
			ModeleCoutEconomique modeleCout=gmpm.lancer();
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    System.out.println("Temps = "+elapsedTime);
		    gantt=gmpm.ecrireResultats();
		    gantt.ecrireDansFichier();
		    cloud.allouerRessourcesTez(gantt);
		    CSVUtils.writeLine(VariablesGlobales.writer_gmpm, Arrays.asList(Long.toString(elapsedTime), Double.toString(modeleCout.prixRessources()),Double.toString(modeleCout.prixCommunication()),Double.toString(modeleCout.prixPenalites()),Double.toString(modeleCout.prixDisque()),Double.toString(modeleCout.prixTotal())));
		    VariablesGlobales.writer_gmpm.flush();
			VariablesGlobales.writer_gmpm.close();
			modeleCout.print();
		}
   
		org.Interface.Gantt.showGantt("resultat.txt");
	}

}
