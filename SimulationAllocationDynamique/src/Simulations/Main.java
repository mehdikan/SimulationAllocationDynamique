package Simulations;

import java.io.FileWriter;
import java.util.Arrays;

import Divers.CSVUtils;
import ParametresGlobeaux.VariablesGlobales;
import Requetes.ClasseClients;
import Infrastructure.Cloud;
import AllocationStatique.Cout;
import Infrastructure.MachinePhysique;
import Requetes.RequeteTez;
import Requetes.StageTez;
import Infrastructure.TypeVM;
import Infrastructure.VM;
import Output.Gantt;
import AllocationStatique.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Cloud cloud=new Cloud();
		cloud.listeMachinesPhysique.add(new MachinePhysique());
		for(int i=0;i<5;i++)
			cloud.ajouterVM(0,new VM(1,8*1,8*2,8*2));
		for(int i=0;i<5;i++)
			cloud.ajouterVM(0,new VM(1,8*1,8*1,8*1));
		cloud.setDefaultDistance();

		cloud.listeClassesClient.add(new ClasseClients());
		RequeteTez req=new RequeteTez(1,30);
		req.rajouterStage(new StageTez(req,3,4,8*1,8*2,8*2));
		req.rajouterStage(new StageTez(req,2,3,8*1,8*1,8*1));
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), 2);
	    req.majTypeLien(req.getStage(0), req.getStage(1), 2);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
		req=new RequeteTez(2,25);
	    req.rajouterStage(new StageTez(req,3,5,8*2,8*2,8*2));
	    req.rajouterStage(new StageTez(req,3,4,8*2,8*2,8*2));
	    req.rajouterStage(new StageTez(req,2,3,8*1,8*1,8*1));
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), 2);
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), 2);
	    req.majTypeLien(req.getStage(0), req.getStage(1), 2);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 2);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
	    VariablesGlobales.writer_pl2p=new FileWriter("compTez1.csv",true);
	    Cout cout=new Cout();
		Gantt gantt;
		ModelePlacementGLPKTEZ modelPlacement=new ModelePlacementGLPKTEZ(cloud,cout);
		long startTime = System.currentTimeMillis();
		modelPlacement.resoudre();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    if(VariablesGlobales.verbose) System.out.println("Temps Placement = "+elapsedTime);
	    ModeleOrdonnancementGLPKTez modeleOrdo=new ModeleOrdonnancementGLPKTez(cloud,cout,modelPlacement.A);
	    startTime = System.currentTimeMillis();
	    gantt=modeleOrdo.resoudre();
	    stopTime = System.currentTimeMillis();
	    elapsedTime = elapsedTime+ (stopTime - startTime);
	    if(VariablesGlobales.verbose) System.out.println("Temps Ordonnancement = "+elapsedTime);
	    gantt.ecrireDansFichier();
	    cloud.allouerRessourcesTez(gantt);
	    CSVUtils.writeLine(VariablesGlobales.writer_pl2p, Arrays.asList(Long.toString(elapsedTime), Double.toString(0), Double.toString(cout.coutRess()),Double.toString(cout.coutComm),Double.toString(cout.coutPenalite),Double.toString(cout.sommeCouts())));
		VariablesGlobales.writer_pl2p.flush();
		VariablesGlobales.writer_pl2p.close();
				
	}

}
