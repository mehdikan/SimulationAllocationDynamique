package TPCH;

import Infrastructure.*;
import ParametresGlobeaux.*;
import Requetes.*;
import Modeles.*;

public class TPCH100 {
	//public static int nb=VariablesGlobales.nbTacheParGroupe;
	public static long besoinMemoire=(int)(4L*1024*1024*1024/VariablesGlobales.tailleBlocDonnees);
	
	public static RequeteTez q3_scale100(Cloud cloud,int isStatUpdate,int isHistogramsUsed) {
		RequeteTez req=new RequeteTez(1,25,"Q3_scale100 "+besoinMemoire);
		StageTez newStage;
		
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,2)+ModeleTempsReponse.temps_operation_filter(15000000,2)+ModeleTempsReponse.temps_operation_projection(2142857,2),
	    		ModeleTempsReponse.nbGroups(2),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,2),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(17142856,2),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,2)
	    		,ModeleTempsReponse.nbTaches(2));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,2142857,8730004039L,0,216428557,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),10,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,2142857,0,2142857,216428557,0,17142856);
	    req.rajouterStage(newStage); //M2
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,25)+ModeleTempsReponse.temps_operation_filter(150000000,25)+ModeleTempsReponse.temps_lecture_disque(0,17142856,25)+ModeleTempsReponse.temps_operation_map_join_operator(2142857,50000000,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+757142948,17142856,2),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(757142948,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,25)
	    		,ModeleTempsReponse.nbTaches(25));
	    newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),9,ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,50000000,79500000000L,0,5700000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),8,ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,2142857,0,2142857,17142856,0,17142856);
	    newStage.ajouterOperation(TypeOperation.MAPJOIN,2142857,50000000,7142858,17142856,5700000000L,757142948,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),8,ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    req.rajouterStage(newStage); //M1
		
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,23)+ModeleTempsReponse.temps_operation_filter(600037902,23)+ModeleTempsReponse.temps_operation_projection(200012634,23)+ModeleTempsReponse.temps_lecture_disque(0,757142948,23)+ModeleTempsReponse.temps_operation_map_join_operator(7142858,200012634,23)+ModeleTempsReponse.temps_operation_group(41541220,23)+ModeleTempsReponse.temps_repartition(41541220,23),
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+5068028840L,757142948,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,23,18),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,23)
	    		,ModeleTempsReponse.nbTaches(23));
	    newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),8,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,200012634,483318264443L,0,23601490812L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),7,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PROJECTION,200012634,0,200012634,23601490812L,0,4800303216L);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,7142858,0,7142858,757142948,0,757142948);
	    newStage.ajouterOperation(TypeOperation.MAPJOIN,7142858,200012634,41541220,757142948,4800303216L,5068028840L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),5,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.GROUPBY,41541220,0,41541220,5068028840L,0,5068028840L,VariablesGlobales.nbNiveauxInexactitude,4,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,41541220,0,41541220,4735699080L,0,4735699080L);
	    req.rajouterStage(newStage); //M3
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(4735699080L,0,18)+ModeleTempsReponse.temps_operation_group(41541220,18)+ModeleTempsReponse.temps_repartition(41541220,18),
	    		ModeleTempsReponse.nbGroups(18),
	    		//ModeleTempsReponse.tailleMemoireParTache(4735699080L,0,18),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,18,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(18));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,41541220,0,41541220,4735699080L,0,4735699080L);
	    newStage.ajouterOperation(TypeOperation.GROUPBY,41541220,0,41541220,4735699080L,0,4735699080L,VariablesGlobales.nbNiveauxInexactitude,2,ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,41541220,0,41541220,4735699080L,0,4735699080L);
	    req.rajouterStage(newStage); //R4
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(1140,0,1)+ModeleTempsReponse.temps_operation_limit(10)+ModeleTempsReponse.temps_ecriture_disque(1140,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(4735699080L,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,10,0,10,1140,0,1140);
	    //newStage.ajouterOperation(TypeOperation.LIMIT,10,0,10,1140,0,1140);
	    //newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,10,0,10,1140,0,1140);
	    req.rajouterStage(newStage); //R5
	    
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(17142856,2));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(757142948,25));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,23,18));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,18,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q4_scale100(Cloud cloud,int isStatUpdate,int isHistogramsUsed) {
		RequeteTez req=new RequeteTez(1,25,"Q4_scale100");
		
		StageTez newStage;
		
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,25)+ModeleTempsReponse.temps_operation_filter(600037902,25)+ModeleTempsReponse.temps_operation_projection(200012634,25)+ModeleTempsReponse.temps_operation_group(200012634,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L,0,25),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1600101072,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,25),
	    		ModeleTempsReponse.nbTaches(25));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,200012634,483318264443L,0,39202476264L,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,200012634,0,200012634,39202476264L,0,1600101072);
		newStage.ajouterOperation(TypeOperation.GROUPBY,200012634,0,200012634,39202476264L,0,39202476264L,VariablesGlobales.nbNiveauxInexactitude,9,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M4
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,24)+ModeleTempsReponse.temps_operation_filter(3233333204L,24)+ModeleTempsReponse.temps_lecture_disque(0,1600101072,24)+ModeleTempsReponse.temps_operation_map_join_operator(200012634,16666666,24)+ModeleTempsReponse.temps_operation_group(16666666,24)+ModeleTempsReponse.temps_repartition(23,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+1533333272,1600101072,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2300,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,24),
	    		ModeleTempsReponse.nbTaches(24));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),9,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,16666666,79500000000L,0,3233333204L,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),8,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,200012634,0,200012634,1600101072,0,1600101072);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,1600101072,0,1600101072,79500000000L,0,3233333204L,VariablesGlobales.nbNiveauxInexactitude,7,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.GROUPBY,16666666,0,23,1533333272,0,2300,VariablesGlobales.nbNiveauxInexactitude,6,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,23,0,23,2300,0,2300);
		req.rajouterStage(newStage); //M1
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(2300,0,1)+ModeleTempsReponse.temps_operation_group(23,1)+ModeleTempsReponse.temps_repartition(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(2300,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,23,0,23,2300,0,2300);
		newStage.ajouterOperation(TypeOperation.GROUPBY,23,0,1,2300,0,100,VariablesGlobales.nbNiveauxInexactitude,6,ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,1,0,1,100,0,100);
		req.rajouterStage(newStage); //R2
		
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(100,0,1)+ModeleTempsReponse.temps_ecriture_disque(100,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(100,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1,0,1,100,0,100);
		//newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,1,0,1,100,0,100);
		req.rajouterStage(newStage); //R3
		
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1600101072,25));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2300,24,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q5_scale100(Cloud cloud,int isStatUpdate,int isHistogramsUsed) {
		RequeteTez req=new RequeteTez(1,25,"Q5_scale100");
		StageTez newStage;
		
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1240,1)+ModeleTempsReponse.temps_operation_filter(5,1)+ModeleTempsReponse.temps_operation_projection(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,5,0,5,1240,0,1240,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),16,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,5,0,1,1240,0,99,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),15,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,1,0,1,99,0,8);
		req.rajouterStage(newStage); //M7
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,9)+ModeleTempsReponse.temps_operation_filter(15000000,9)+ModeleTempsReponse.temps_operation_projection(15000000,9),
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9),
	    		ModeleTempsReponse.nbTaches(9));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(26)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,15000000,8730004039L,0,240000000,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(26)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M2
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1)+ModeleTempsReponse.temps_lecture_disque(0,8,1)+ModeleTempsReponse.temps_operation_map_join_operator(1,25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625+594,8,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(594,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,6625,25,0,6625,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,25,0,6625,25,0,2675,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1,0,8,1,0,8);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,1,25,6,2675,8,594,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M6
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,26)+ModeleTempsReponse.temps_operation_filter(150000000,26)+ModeleTempsReponse.temps_operation_projection(16666666,26)+ModeleTempsReponse.temps_lecture_disque(0,240000000,26)+ModeleTempsReponse.temps_operation_map_join_operator(15000000,16666666,26),
	    		ModeleTempsReponse.nbGroups(26),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+266666656,240000000,26),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,26),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,26),
	    		ModeleTempsReponse.nbTaches(26));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,16666666,79500000000L,0,1833333260,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,16666666,0,16666666,1833333260,0,266666656);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,15000000,0,15000000,240000000,0,240000000);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,15000000,16666666,16666666,240000000,266666656,266666656,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    req.rajouterStage(newStage); //M1
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(479501083,1)+ModeleTempsReponse.temps_operation_filter(1000000,1)+ModeleTempsReponse.temps_lecture_disque(0,594,1)+ModeleTempsReponse.temps_operation_map_join_operator(6,1000000,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(479501083+21400107,594,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(21400107,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(479501083,1),
	    		ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.TABLESCAN,1000000,0,1000000,479501083,0,479501083,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.FILTER,1000000,0,1000000,479501083,0,16000000,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,6,0,6,594,0,594);
	    newStage.ajouterOperation(TypeOperation.MAPJOIN,6,1000000,200001,594,16000000,21400107,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),10,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    req.rajouterStage(newStage); //M8
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,24)+ModeleTempsReponse.temps_operation_filter(600037902,24)+ModeleTempsReponse.temps_lecture_disque(0,266666656,24)+ModeleTempsReponse.temps_operation_map_join_operator(16666666,600037902,24)+ModeleTempsReponse.temps_lecture_disque(0,21400107,24)+ModeleTempsReponse.temps_operation_map_join_operator(200001,96929498,24)+ModeleTempsReponse.temps_operation_group(4285599,24)+ModeleTempsReponse.temps_repartition(42,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+3101743936L+458559093,266666656+21400107,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(42,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,24),
	    		ModeleTempsReponse.nbTaches(24));
	    newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,600037902,483318264443L,0,19201212864L,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),10,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,16666666,0,16666666,266666656,0,266666656);
	    newStage.ajouterOperation(TypeOperation.MAPJOIN,16666666,600037902,96929498,266666656,483318264443L,3101743936L,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),9,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,200001,0,200001,21400107,0,21400107);
	    newStage.ajouterOperation(TypeOperation.MAPJOIN,200001,96929498,4285599,21400107,3101743936L,458559093,VariablesGlobales.nbNiveauxInexactitude,8,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    newStage.ajouterOperation(TypeOperation.GROUPBY,4285599,0,42,458559093,0,4158,VariablesGlobales.nbNiveauxInexactitude,7,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,42,0,42,4158,0,4158);										
	    req.rajouterStage(newStage); //M3
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(4158,0,1)+ModeleTempsReponse.temps_operation_group(42,1)+ModeleTempsReponse.temps_repartition(6,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(4158,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(594,1,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,42,0,42,4158,0,4158);
	    newStage.ajouterOperation(TypeOperation.GROUPBY,42,0,6,4158,0,594,VariablesGlobales.nbNiveauxInexactitude,4,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,6,0,6,594,0,594);
	    req.rajouterStage(newStage); //R4
	    
	    newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(594,0,1)+ModeleTempsReponse.temps_operation_projection(6,1)+ModeleTempsReponse.temps_ecriture_disque(594,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(594,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,6,0,6,594,0,594);
	    //newStage.ajouterOperation(TypeOperation.PROJECTION,6,0,6,594,0,594);
	    //newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,6,0,6,594,0,594);
	    req.rajouterStage(newStage); //R5
	    
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(594,1));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,26));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(21400107,1));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(42,24,1));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(594,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(4), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(5), 1);
	    req.majTypeLien(req.getStage(4), req.getStage(5), 1);
	    req.majTypeLien(req.getStage(5), req.getStage(6), 1);
	    req.majTypeLien(req.getStage(6), req.getStage(7), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q8_scale100(Cloud cloud,int isStatUpdate,int isHistogramsUsed) {
		RequeteTez req=new RequeteTez(1,25,"Q8_scale100");
	    
		StageTez newStage;
		
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1240,1)+ModeleTempsReponse.temps_operation_filter(5,1)+ModeleTempsReponse.temps_operation_projection(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,5,0,5,1240,0,1240,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),17,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,5,0,1,1240,0,99,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),16,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,1,0,1,99,0,8);
		req.rajouterStage(newStage); //M10
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,6625,0,2475,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M2
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,9)+ModeleTempsReponse.temps_operation_filter(15000000,9),
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9),
	    		ModeleTempsReponse.nbTaches(9));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),15,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,15000000,8730004039L,0,240000000,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M7
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1)+ModeleTempsReponse.temps_lecture_disque(0,8,1)+ModeleTempsReponse.temps_operation_map_join_operator(1,25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625+48,8,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(48,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),15,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,6625,0,400,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1,0,1,8,0,8);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,1,25,6,8,400,48,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M9
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(479501083,1)+ModeleTempsReponse.temps_operation_filter(1000000,1)+ModeleTempsReponse.temps_lecture_disque(0,2475,1)+ModeleTempsReponse.temps_operation_map_join_operator(25,1000000,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(479501083+99000000,2475,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(99000000,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(479501083,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,1000000,0,1000000,479501083,0,479501083,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,1000000,0,1000000,479501083,0,16000000,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,25,0,25,2475,0,2475);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,25,1000000,1000000,2475,16000000,99000000,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),10,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));					
		req.rajouterStage(newStage); //M1
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(12360000000L,12)+ModeleTempsReponse.temps_operation_filter(13696956,12)+ModeleTempsReponse.temps_operation_projection(969696,12),
	    		ModeleTempsReponse.nbGroups(12),
	    		//ModeleTempsReponse.tailleMemoireParTache(12360000000L,0,12),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(969696,12),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(12360000000L,12),
	    		ModeleTempsReponse.nbTaches(12));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,20000000,0,20000000,12360000000L,0,12360000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,20000000,0,121212,12360000000L,0,13696956,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,121212,0,121212,13696956,0,969696);
		req.rajouterStage(newStage); //M6
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,25)+ModeleTempsReponse.temps_operation_filter(150000000,25)+ModeleTempsReponse.temps_lecture_disque(0,240000000,25)+ModeleTempsReponse.temps_operation_map_join_operator(15000000,75000000,25)+ModeleTempsReponse.temps_lecture_disque(0,48,25)+ModeleTempsReponse.temps_operation_map_join_operator(6,15000001,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L,240000000+48,25),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1530000102,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,25),
	    		ModeleTempsReponse.nbTaches(25));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,75000000,79500000000L,0,8250000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,15000000,0,15000000,240000000,0,240000000);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,15000000,75000000,75000000,8250000000L,240000000,8250000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,6,0,6,48,0,48);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,6,75000000,15000001,48,8250000000L,1530000102,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M8
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,24)+ModeleTempsReponse.temps_operation_filter(600037902,24)+ModeleTempsReponse.temps_lecture_disque(0,969696,24)+ModeleTempsReponse.temps_operation_map_join_operator(600037902,121212,24)+ModeleTempsReponse.temps_lecture_disque(0,1530000102,24)+ModeleTempsReponse.temps_operation_map_join_operator(15000001,3636590,24)+ModeleTempsReponse.temps_lecture_disque(0,99000000,24)+ModeleTempsReponse.temps_operation_map_join_operator(1000000,5287058,24)+ModeleTempsReponse.temps_operation_group(5287058,24)+ModeleTempsReponse.temps_repartition(2643529,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+1062698658+623872844+116370880,969696+1530000102+99000000,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(52870580,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,24),
	    		ModeleTempsReponse.nbTaches(24));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,600037902,24001516080L,0,24001516080L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,121212,0,121212,969696,0,969696);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,121212,600037902,3636590,969696,24001516080L,116370880L,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),10,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,15000001,0,15000001,1530000102,0,1530000102);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,15000001,3636590,5287058,1530000102,116370880,623872844,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),9,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1000000,0,1000000,99000000,0,99000000);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,1000000,5287058,5287058,99000000,623872844,1062698658L,Math.min(isHistogramsUsed+1+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),8,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.GROUPBY,5287058,0,2643529,1062698658,0,52870580,VariablesGlobales.nbNiveauxInexactitude,7,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,2643529,0,2643529,52870580,0,52870580);
		req.rajouterStage(newStage); //M3
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(52870580,0,1)+ModeleTempsReponse.temps_operation_group(2643529,1)+ModeleTempsReponse.temps_operation_projection(2643529,1)+ModeleTempsReponse.temps_repartition(2643529,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(52870580,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(31722348,1,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,2643529,0,2643529,52870580,0,52870580);
		newStage.ajouterOperation(TypeOperation.GROUPBY,2643529,0,2643529,52870580,0,52870580,VariablesGlobales.nbNiveauxInexactitude,5,ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,2643529,0,2643529,52870580,0,31722348);
		//newStage.ajouterOperation(TypeOperation.PARTITION,2643529,0,2643529,31722348,0,31722348);
		req.rajouterStage(newStage); //R4
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(31722348,0,1)+ModeleTempsReponse.temps_operation_projection(2643529,1)+ModeleTempsReponse.temps_ecriture_disque(31722348,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(31722348,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(31722348,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,2643529,0,2643529,31722348,0,31722348);
		//newStage.ajouterOperation(TypeOperation.PROJECTION,2643529,0,2643529,31722348,0,31722348);
		//newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,2643529,0,2643529,31722348,0,31722348);
		req.rajouterStage(newStage); //R5
	    
		req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(48,1));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(99000000,1));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(969696,12));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1530000102,25));
	    req.majQuantiteTransfertStages(req.getStage(7), req.getStage(8), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(52870580,24,1));
	    req.majQuantiteTransfertStages(req.getStage(8), req.getStage(9), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(31722348,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(4), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(6), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(6), 1);
	    req.majTypeLien(req.getStage(4), req.getStage(7), 1);
	    req.majTypeLien(req.getStage(5), req.getStage(7), 1);
	    req.majTypeLien(req.getStage(6), req.getStage(7), 1);
	    req.majTypeLien(req.getStage(7), req.getStage(8), 1);
	    req.majTypeLien(req.getStage(8), req.getStage(9), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}

	public static RequeteTez q10_scale100(Cloud cloud,int isStatUpdate,int isHistogramsUsed) {
		RequeteTez req=new RequeteTez(1,25,"Q10_scale100");
	    
		StageTez newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),15,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,6625,0,2475,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M5
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,23)+ModeleTempsReponse.temps_operation_filter(150000000,23)+ModeleTempsReponse.temps_operation_projection(16666666,23),
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L,0,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,23),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,23),
	    		ModeleTempsReponse.nbTaches(23));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),15,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,16666666,79500000000L,0,1833333260,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,16666666,0,16666666,1833333260,0,266666656);
		req.rajouterStage(newStage); //M6
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,9)+ModeleTempsReponse.temps_operation_filter(15000000,9)+ModeleTempsReponse.temps_lecture_disque(0,2475,9)+ModeleTempsReponse.temps_operation_map_join_operator(25,15000000,9)+ModeleTempsReponse.temps_lecture_disque(0,266666656,9)+ModeleTempsReponse.temps_operation_map_join_operator(16666666,15000000,9)+ModeleTempsReponse.temps_repartition(16666666,9),
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L+8595000000L+9683332946L,25+266666656,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(9683332946L,9,101),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9),
	    		ModeleTempsReponse.nbTaches(9));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,15000000,8730004039L,0,7350000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,25,0,25,2475,0,2475);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,25,15000000,15000000,2475,7350000000L,8595000000L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),12,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,16666666,0,16666666,266666656,0,266666656);
		newStage.ajouterOperation(TypeOperation.MAPJOIN,16666666,15000000,16666666,266666656,8595000000L,9683332946L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),11,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,16666666,0,16666666,9683332946L,0,9683332946L);
		req.rajouterStage(newStage); //M1
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,23)+ModeleTempsReponse.temps_operation_filter(600037902,23)+ModeleTempsReponse.temps_operation_projection(300018951,23)+ModeleTempsReponse.temps_repartition(300018951,23),
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L,0,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(7200454824L,23,101),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,23),
	    		ModeleTempsReponse.nbTaches(23));
		newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),14,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,300018951,483318264443L,0,32702065659L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),13,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,300018951,0,300018951,32702065659L,0,7200454824L);
		//newStage.ajouterOperation(TypeOperation.PARTITION,300018951,0,300018951,7200454824L,0,7200454824L);
		req.rajouterStage(newStage); //M7
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(7200454824L,0,101)+ModeleTempsReponse.temps_lecture_disque(9683332946L,0,101)+ModeleTempsReponse.temps_operation_reduce_join_operator(7200454824L,9683332946L,23,9,101)+ModeleTempsReponse.temps_operation_projection(96929497,101)+ModeleTempsReponse.temps_operation_group(96929497,101)+ModeleTempsReponse.temps_repartition(96929497,101),
	    		ModeleTempsReponse.nbGroups(101),
	    		//ModeleTempsReponse.tailleMemoireParTache(7200454824L+9683332946L+57091473733L,0,101),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,101,253),
	    		0,
	    		ModeleTempsReponse.nbTaches(101));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,16666666,0,16666666,9683332946L,0,9683332946L);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,300018951,0,300018951,7200454824L,0,7200454824L);
		newStage.ajouterOperation(TypeOperation.MERGEJOIN,16666666,300018951,96929497,9683332946L,7200454824L,57091473733L,Math.min(isHistogramsUsed+isStatUpdate,VariablesGlobales.nbNiveauxInexactitude),8,ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		newStage.ajouterOperation(TypeOperation.GROUPBY,96929497,0,96929497,57091473733L,0,56316037757L,VariablesGlobales.nbNiveauxInexactitude,7,ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,96929497,0,96929497,56316037757L,0,56316037757L);
		req.rajouterStage(newStage); //R2
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(56316037757L,0,253)+ModeleTempsReponse.temps_operation_group(96929497,253)+ModeleTempsReponse.temps_repartition(96929497,253),
	    		ModeleTempsReponse.nbGroups(253),
	    		//ModeleTempsReponse.tailleMemoireParTache(56316037757L,0,253),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,253,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(253));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,96929497,0,96929497,56316037757L,0,56316037757L);
		newStage.ajouterOperation(TypeOperation.GROUPBY,96929497,0,96929497,56316037757L,0,56316037757L,VariablesGlobales.nbNiveauxInexactitude,4,ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,96929497,0,96929497,56316037757L,0,56316037757L);
		req.rajouterStage(newStage); //R3
	    
		newStage=new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(11620,0,1)+ModeleTempsReponse.temps_operation_limit(20)+ModeleTempsReponse.temps_ecriture_disque(11620,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(56316037757L,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,20,0,20,11620,0,11620);
		//newStage.ajouterOperation(TypeOperation.LIMIT,20,0,20,11620,0,11620);
		//newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,20,0,20,11620,0,11620);
		req.rajouterStage(newStage); //R4
		
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,23));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(9683332946L,9,101));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(7200454824L,23,101));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,101,253));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,253,1));
	    req.majTypeLien(req.getStage(0), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(4), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
	    req.majTypeLien(req.getStage(4), req.getStage(5), 1);
	    req.majTypeLien(req.getStage(5), req.getStage(6), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
}