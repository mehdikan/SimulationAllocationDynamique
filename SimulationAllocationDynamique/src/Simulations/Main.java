package Simulations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import ParametresGlobeaux.Cout;
import ParametresGlobeaux.VariablesGlobales;
import PlanAllocation.TrancheTempsAlloue;
import Requetes.ClasseClients;
import Infrastructure.Cloud;
import Infrastructure.MachinePhysique;
import Requetes.RequeteTez;
import Requetes.StageTez;
import Types.CSVUtils;
import Infrastructure.VM;
import AllocationStatique.*;
import AllocationDynamique.*;

public class Main {
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub		
		Cloud cloud=new Cloud();
		cloud.listeMachinesPhysique.add(new MachinePhysique());
		for(int i=0;i<5;i++)
			cloud.ajouterVM(0,new VM(1,8*1,8*2,8*2));
		for(int i=0;i<5;i++)
			cloud.ajouterVM(0,new VM(1,8*1,8*1,8*1));
		cloud.setDefaultDistance();

		cloud.listeClassesClient.add(new ClasseClients());
		RequeteTez req=new RequeteTez(1,15);
		req.rajouterStage(new StageTez(req,3,4,8*1,8*2,8*2));
		req.rajouterStage(new StageTez(req,2,3,8*1,8*1,8*1));
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), 2);
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
		req=new RequeteTez(1,15);
	    req.rajouterStage(new StageTez(req,3,5,8*1,8*2,8*2));
	    req.rajouterStage(new StageTez(req,3,4,8*1,8*2,8*2));
	    req.rajouterStage(new StageTez(req,2,3,8*1,8*1,8*1));
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), 2);
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), 2);
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
	    VariablesGlobales.writer_pl2p=new FileWriter("compTez1.csv",true);
	    Cout cout=new Cout();
		PlanAllocation.PlanStatique gantt;
		ModelePlacementILP modelPlacement=new ModelePlacementILP(cloud,cout);
		long startTime = System.currentTimeMillis();
		modelPlacement.resoudre();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    if(VariablesGlobales.verbose) System.out.println("Temps Placement = "+elapsedTime);
	    ModeleOrdonnancementILP modeleOrdo=new ModeleOrdonnancementILP(cloud,cout,modelPlacement.A);
	    startTime = System.currentTimeMillis();
	    gantt=modeleOrdo.resoudre();
	    stopTime = System.currentTimeMillis();
	    elapsedTime = elapsedTime+ (stopTime - startTime);
	    if(VariablesGlobales.verbose) System.out.println("Temps Ordonnancement = "+elapsedTime);
	    
	    TrancheTempsAlloue tranche=null;
	    for(TrancheTempsAlloue tta:gantt.tab){
	    	if(tta.indexRequete==1 && tta.indexStage==2 && tta.indexTache==3) tranche=tta;
	    }
	    ShiftAlgorithm.shiftRight(cloud,gantt,tranche,8);
	   
	    gantt.ecrireDansFichier();
	    cloud.allouerRessourcesTez(gantt);
	    CSVUtils.writeLine(VariablesGlobales.writer_pl2p, Arrays.asList(Long.toString(elapsedTime), Double.toString(0), Double.toString(cout.coutRess()),Double.toString(cout.coutComm),Double.toString(cout.coutPenalite),Double.toString(cout.sommeCouts())));
		VariablesGlobales.writer_pl2p.flush();
		VariablesGlobales.writer_pl2p.close();
		org.Interface.Gantt.showGantt("resultat.txt");
	}

}
