package Simulations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import ParametresGlobeaux.*;
import PlanAllocation.*;
import Requetes.*;
import Infrastructure.*;
import Modeles.*;
import Requetes.*;
import Divers.*;
import Infrastructure.*;
import AllocationStatique.*;
import Divers.CSVUtils;
import AllocationDynamique.*;

public class Main {
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub		
		int algoStatique=2;
		int nb=VariablesGlobales.nbTacheParGroupe;
		Cloud cloud=new Cloud();
		cloud.listeMachinesPhysique.add(new MachinePhysique());
		for(int i=0;i<5;i++)
			cloud.ajouterVM(0,new VM(1,nb*1,nb*2,i));
		for(int i=0;i<5;i++)
			cloud.ajouterVM(0,new VM(1,nb*1,nb*1,i+5));
		cloud.setDefaultDistance();

		cloud.listeClassesClient.add(new ClasseClients());
		RequeteTez req=new RequeteTez(1,15,"Req1");
		req.rajouterStage(new StageTez(req,3*60000,4,nb*2,nb*2,nb*2));
		req.rajouterStage(new StageTez(req,2*60000,3,nb*1,nb*1,0));
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), nb*2);
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
		req=new RequeteTez(1,15,"Req2");
	    req.rajouterStage(new StageTez(req,3*60000,5,nb*2,nb*2,nb*2));
	    req.rajouterStage(new StageTez(req,3*60000,4,nb*2,nb*2,nb*2));
	    req.rajouterStage(new StageTez(req,2*60000,3,nb*1,nb*1,0));
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), nb*2);
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), nb*2);
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
		cloud.calculQuantiteRecuParTache();
		
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
		else {
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
	    /*TrancheTempsAlloue tranche=null;
	    for(TrancheTempsAlloue tta:gantt.tab){
	    	if(tta.indexRequete==1 && tta.indexStage==2 && tta.indexTache==3) tranche=tta;
	    }
	    ShiftAlgorithm.shiftRight(cloud,gantt,tranche,8);*/
	   
		
		
		org.Interface.Gantt.showGantt("resultat.txt");
	}

}
