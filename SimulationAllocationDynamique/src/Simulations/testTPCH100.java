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
import AllocationStatique.*;
import CollectionStatistiques.*;
import ErreursEstimation.*;
import Divers.CSVUtils;
import AllocationDynamique.*;

public class testTPCH100 {
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub		
		VariablesGlobales.init();
		int algoStatique=2;
		int nb=VariablesGlobales.nbTacheParGroupe;
		boolean collectCloud=true;
		System.out.println(">>>>>>>> "+ModeleCoutEconomique.prixUnitePenalites);
		System.out.println(">>>>>>>> "+ModeleCoutEconomique.prixUniteCommunication);
		System.out.println(">>>>>>>> "+ModeleCoutEconomique.prixUniteRessources);
		System.out.println(">>>>>>>> "+ModeleCoutEconomique.prixUniteDisque);
		Cloud cloud=new Cloud();
		for(int j=0;j<5;j++) {
			cloud.listeMachinesPhysique.add(new MachinePhysique());
			for(int i=0;i<10;i++) {
				cloud.ajouterVM(j,new VM(1,nb*1,(int)(VariablesGlobales.tailleMemoireParGroupeEnGo*1024*1024*1024/VariablesGlobales.tailleBlocDonnees),i));
			}
		}
		cloud.setDefaultDistance();

		cloud.listeClassesClient.add(new ClasseClients());
		
		Client basicClient1=new Client("basicClient1",1,4,0,1);
		Client basicClient2=new Client("basicClient2",1,4,1,3);
		Client standardClient1=new Client("standardClient1",2,3,0,1);
		Client standardClient2=new Client("standardClient2",2,3,1,3);
		Client premiumClient1=new Client("premiumClient1",3,2,0,1);
		Client premiumClient2=new Client("premiumClient2",3,2,1,3);
		
		boolean q3=true;
		boolean q4=false;
		boolean q5=false;
		boolean q8=false;
		boolean q10=false;
		
		
		if(q3) {
			RequeteTez q3_basic1=TPCH100.q3_scale100(cloud,basicClient1);
			//RequeteTez q3_basic2=TPCH100.q3_scale100(cloud,basicClient2);
			//RequeteTez q3_standard1=TPCH100.q3_scale100(cloud,standardClient1);
			//RequeteTez q3_standard2=TPCH100.q3_scale100(cloud,standardClient2);
			//RequeteTez q3_premium1=TPCH100.q3_scale100(cloud,premiumClient1);
			//RequeteTez q3_premium2=TPCH100.q3_scale100(cloud,premiumClient2);
		}
		if(q4) {
			RequeteTez q4_basic1=TPCH100.q4_scale100(cloud,basicClient1);
			RequeteTez q4_basic2=TPCH100.q4_scale100(cloud,basicClient2);
			RequeteTez q4_standard1=TPCH100.q4_scale100(cloud,standardClient1);
			RequeteTez q4_standard2=TPCH100.q4_scale100(cloud,standardClient2);
			RequeteTez q4_premium1=TPCH100.q4_scale100(cloud,premiumClient1);
			RequeteTez q4_premium2=TPCH100.q4_scale100(cloud,premiumClient2);
		}
		if(q5) {
			RequeteTez q5_basic1=TPCH100.q5_scale100(cloud,basicClient1);
			RequeteTez q5_basic2=TPCH100.q5_scale100(cloud,basicClient2);
			RequeteTez q5_standard1=TPCH100.q5_scale100(cloud,standardClient1);
			RequeteTez q5_standard2=TPCH100.q5_scale100(cloud,standardClient2);
			RequeteTez q5_premium1=TPCH100.q5_scale100(cloud,premiumClient1);
			RequeteTez q5_premium2=TPCH100.q5_scale100(cloud,premiumClient2);
		}
		if(q8) {
			RequeteTez q8_basic1=TPCH100.q8_scale100(cloud,basicClient1);
			RequeteTez q8_basic2=TPCH100.q8_scale100(cloud,basicClient2);
			RequeteTez q8_standard1=TPCH100.q8_scale100(cloud,standardClient1);
			RequeteTez q8_standard2=TPCH100.q8_scale100(cloud,standardClient2);
			RequeteTez q8_premium1=TPCH100.q8_scale100(cloud,premiumClient1);
			RequeteTez q8_premium2=TPCH100.q8_scale100(cloud,premiumClient2);
		}
		if(q10) {
			RequeteTez q10_basic1=TPCH100.q10_scale100(cloud,basicClient1);
			RequeteTez q10_basic2=TPCH100.q10_scale100(cloud,basicClient2);
			RequeteTez q10_standard1=TPCH100.q10_scale100(cloud,standardClient1);
			RequeteTez q10_standard2=TPCH100.q10_scale100(cloud,standardClient2);
			RequeteTez q10_premium1=TPCH100.q10_scale100(cloud,premiumClient1);
			RequeteTez q10_premium2=TPCH100.q10_scale100(cloud,premiumClient2);
		}
		cloud.calculQuantiteRecuParTache();
		cloud.calculDateLimites();
		
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
		    CollectionStatistiques.placementCollecteurStatistiques(cloud,collectCloud);
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
			ModeleCoutEconomique modeleCout=gmpm.lancer(collectCloud);
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
		
		for(ClasseClients c:cloud.listeClassesClient) {
			for(RequeteTez req : c.requeteTezEnAttente) {
				req.descriptionRequete();
			}
		}
		
		//req3.descriptionRequete();
		//req4.descriptionRequete();
		/*req5.descriptionRequete();
		req8.descriptionRequete();
		req10.descriptionRequete();*/
		
		//SimulationErreurEstimation.simulerErreur(cloud,gantt,0.5);
   
		System.out.println("temps_TABLESCAN >> "+VariablesGlobales.temps_TABLESCAN/VariablesGlobales.cpt_TABLESCAN);
		System.out.println("temps_FILTER >> "+VariablesGlobales.temps_FILTER/VariablesGlobales.cpt_FILTER);
		System.out.println("temps_PROJECTION >> "+VariablesGlobales.temps_PROJECTION/VariablesGlobales.cpt_PROJECTION);
		System.out.println("temps_MAPJOIN >> "+VariablesGlobales.temps_MAPJOIN/VariablesGlobales.cpt_MAPJOIN);
		System.out.println("temps_MERGEJOIN >> "+VariablesGlobales.temps_MERGEJOIN/VariablesGlobales.cpt_MERGEJOIN);
		System.out.println("temps_GROUPBY >> "+VariablesGlobales.temps_GROUPBY/VariablesGlobales.cpt_GROUPBY);
		System.out.println("temps_LIMIT >> "+VariablesGlobales.temps_LIMIT/VariablesGlobales.cpt_LIMIT);
		System.out.println("temps_PARTITION >> "+VariablesGlobales.temps_PARTITION/VariablesGlobales.cpt_PARTITION);
		System.out.println("temps_LECTUREDISQUE >> "+VariablesGlobales.temps_LECTUREDISQUE/VariablesGlobales.cpt_LECTUREDISQUE);
		System.out.println("temps_ECRITUREDISQUE >> "+VariablesGlobales.temps_ECRITUREDISQUE/VariablesGlobales.cpt_ECRITUREDISQUE);
		System.out.println("temps_COLLECT >> "+VariablesGlobales.temps_COLLECT/VariablesGlobales.cpt_COLLECT);
		
		org.Interface.Gantt.showGantt("resultat.txt");
	}

}
