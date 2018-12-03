package TPCH;

import Infrastructure.*;
import ParametresGlobeaux.*;
import Requetes.*;
import Modeles.*;

public class TPCH100 {
	//public static int nb=VariablesGlobales.nbTacheParGroupe;
	public static long besoinMemoire=(int)(VariablesGlobales.tailleMemoireParGroupeEnGo*1024*1024*1024/VariablesGlobales.tailleBlocDonnees);
	
	public static RequeteTez q3_scale100(Cloud cloud,Client client) {
		RequeteTez req=new RequeteTez(client,"Q3_scale100_"+client.name);
		StageTez newStage;
		int nbTachesApres;
		
		newStage=new StageTez(
	    		"M2",
				req,
	    		ModeleTempsReponse.nbGroups(2),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,2),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(17142856,2),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,2)
	    		,ModeleTempsReponse.nbTaches(2)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1);
		Operation opM2_1=new Operation(newStage,TypeOperation.TABLESCAN,15000000,8730004039L,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM2_2=new Operation(newStage,TypeOperation.FILTER,2142857,216428557,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);		
		Operation opM2_3=new Operation(newStage,TypeOperation.PROJECTION,2142857,17142856,client.isHistogramsUsed+client.isStatUpdate,10,nbTachesApres);
		newStage.listeOperation.add(opM2_1);
		newStage.listeOperation.add(opM2_2);
		newStage.listeOperation.add(opM2_3);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,2142857,8730004039L,0,216428557,Math.min(isHistogramsUsed+isStatUpdate,3),10,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,2142857,0,2142857,216428557,0,17142856);
	    req.rajouterStage(newStage); //M2
	    
	    newStage=new StageTez(
	    		"M1",
	    		req,
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+757142948,17142856,2),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(757142948,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,25)
	    		,ModeleTempsReponse.nbTaches(25)
	    		,false);
	    nbTachesApres=ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1);
	    Operation opM1_1=new Operation(newStage,TypeOperation.TABLESCAN,150000000,79500000000L,client.isHistogramsUsed+client.isStatUpdate,10,nbTachesApres);
	    Operation opM1_2=new Operation(newStage,TypeOperation.FILTER,50000000,79500000000L,client.isHistogramsUsed+client.isStatUpdate,9,nbTachesApres);
	    Operation opM1_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,2142857,17142856,client.isHistogramsUsed+client.isStatUpdate,9,nbTachesApres);
	    Operation opM1_4=new Operation(newStage,TypeOperation.MAPJOIN,7142858,757142948,client.isHistogramsUsed+client.isStatUpdate,8,nbTachesApres);
	    newStage.listeOperation.add(opM1_1);
	    newStage.listeOperation.add(opM1_2);
	    newStage.listeOperation.add(opM1_3);
	    newStage.listeOperation.add(opM1_4);
	    //newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,3),9,ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,50000000,79500000000L,0,5700000000L,Math.min(isHistogramsUsed+isStatUpdate,3),8,ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,2142857,0,2142857,17142856,0,17142856);
	    //newStage.ajouterOperation(TypeOperation.MAPJOIN,2142857,50000000,7142858,17142856,5700000000L,757142948,Math.min(isHistogramsUsed+isStatUpdate,3),8,ModeleTempsReponse.nbTaches(23)+ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    req.rajouterStage(newStage); //M1
		
	    newStage=new StageTez(
	    		"M3",
	    		req,
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+5068028840L,757142948,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,23,18),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,23)
	    		,ModeleTempsReponse.nbTaches(23)
	    		,false);
	    nbTachesApres=ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1);
	    Operation opM3_1=new Operation(newStage,TypeOperation.TABLESCAN,600037902,483318264443L,client.isHistogramsUsed+client.isStatUpdate,9,nbTachesApres);
	    Operation opM3_2=new Operation(newStage,TypeOperation.FILTER,200012634,23601490812L,client.isHistogramsUsed+client.isStatUpdate,8,nbTachesApres);
	    Operation opM3_3=new Operation(newStage,TypeOperation.PROJECTION,200012634,4800303216L,client.isHistogramsUsed+client.isStatUpdate,7,nbTachesApres);
	    Operation opM3_4=new Operation(newStage,TypeOperation.LECTUREDISQUE,7142858,757142948,client.isHistogramsUsed+client.isStatUpdate,7,nbTachesApres);
	    Operation opM3_5=new Operation(newStage,TypeOperation.MAPJOIN,41541220,5068028840L,client.isHistogramsUsed+client.isStatUpdate,6,nbTachesApres);
	    Operation opM3_6=new Operation(newStage,TypeOperation.GROUPBY,41541220,4735699080L,3,5,nbTachesApres);
	    Operation opM3_7=new Operation(newStage,TypeOperation.PARTITION,41541220,4735699080L,3,4,nbTachesApres);
	    newStage.listeOperation.add(opM3_1);
	    newStage.listeOperation.add(opM3_2);
	    newStage.listeOperation.add(opM3_3);
	    newStage.listeOperation.add(opM3_4);
	    newStage.listeOperation.add(opM3_5);
	    newStage.listeOperation.add(opM3_6);
	    newStage.listeOperation.add(opM3_7);
	    //newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,3),8,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,200012634,483318264443L,0,23601490812L,Math.min(isHistogramsUsed+isStatUpdate,3),7,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PROJECTION,200012634,0,200012634,23601490812L,0,4800303216L);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,7142858,0,7142858,757142948,0,757142948);
	    //newStage.ajouterOperation(TypeOperation.MAPJOIN,7142858,200012634,41541220,757142948,4800303216L,5068028840L,Math.min(isHistogramsUsed+isStatUpdate,3),5,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.GROUPBY,41541220,0,41541220,5068028840L,0,5068028840L,3,4,ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,41541220,0,41541220,4735699080L,0,4735699080L);
	    req.rajouterStage(newStage); //M3
	    
	    newStage=new StageTez(
	    		"R4",
	    		req,
	    		ModeleTempsReponse.nbGroups(18),
	    		//ModeleTempsReponse.tailleMemoireParTache(4735699080L,0,18),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,18,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(18)
	    		,true);
	    nbTachesApres=ModeleTempsReponse.nbTaches(18)+ModeleTempsReponse.nbTaches(1);
	    Operation opR4_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,41541220,4735699080L,3,3,nbTachesApres);
	    Operation opR4_2=new Operation(newStage,TypeOperation.GROUPBY,41541220,4735699080L,3,2,nbTachesApres);
	    Operation opR4_3=new Operation(newStage,TypeOperation.PARTITION,41541220,4735699080L,3,1,nbTachesApres);
	    newStage.listeOperation.add(opR4_1);
	    newStage.listeOperation.add(opR4_2);
	    newStage.listeOperation.add(opR4_3);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,41541220,0,41541220,4735699080L,0,4735699080L);
	    //newStage.ajouterOperation(TypeOperation.GROUPBY,41541220,0,41541220,4735699080L,0,4735699080L,3,2,ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,41541220,0,41541220,4735699080L,0,4735699080L);
	    req.rajouterStage(newStage); //R4
	    
	    newStage=new StageTez(
	    		"R5",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(4735699080L,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
	    Operation opR5_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,10,1140,3,0,0);
	    Operation opR5_2=new Operation(newStage,TypeOperation.LIMIT,10,1140,1,0,0);
	    Operation opR5_3=new Operation(newStage,TypeOperation.ECRITUREDISQUE,10,1140,1,0,0);
	    newStage.listeOperation.add(opR5_1);
	    newStage.listeOperation.add(opR5_2);
	    newStage.listeOperation.add(opR5_3);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,10,0,10,1140,0,1140);
	    //newStage.ajouterOperation(TypeOperation.LIMIT,10,0,10,1140,0,1140);
	    //newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,10,0,10,1140,0,1140);
	    req.rajouterStage(newStage); //R5
	    
		opM2_1.connecter(null, null,opM2_2);
		opM2_2.connecter(opM2_1, null, opM2_3);
		opM2_3.connecter(opM2_2, null, opM1_3);
		opM1_1.connecter(null, null, opM1_2);
		opM1_2.connecter(opM1_1, null, opM1_4);
		opM1_3.connecter(opM2_3, null, opM1_4);
		opM1_4.connecter(opM1_3, opM1_2, opM3_4);
		opM3_1.connecter(null, null, opM3_2);
		opM3_2.connecter(opM3_1, null, opM3_3);
		opM3_3.connecter(opM3_2, null, opM3_5);
		opM3_4.connecter(opM1_4, null, opM3_5);
		opM3_5.connecter(opM3_4, opM3_3, opM3_6);
		opM3_6.connecter(opM3_5, null, opM3_7);
	    opM3_7.connecter(opM3_6, null, opR4_1);
	    opR4_1.connecter(opM3_7, null, opR4_2);
	    opR4_2.connecter(opR4_1, null, opR4_3);
	    opR4_3.connecter(opR4_2, null, opR5_1);
	    opR5_1.connecter(opR4_3, null, opR5_2);
	    opR5_2.connecter(opR5_1, null, opR5_3);
	    opR5_3.connecter(opR5_2, null, null);
	    
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
		
		req.majDureeStages();
		return req;
	}
	
	public static RequeteTez q4_scale100(Cloud cloud,Client client) {
		RequeteTez req=new RequeteTez(client,"Q4_scale100");
		
		StageTez newStage;
		int nbTachesApres;
		
		newStage=new StageTez(
				"M4",
	    		req,
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L,0,25),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1600101072,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,25),
	    		ModeleTempsReponse.nbTaches(25)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM4_1=new Operation(newStage,TypeOperation.TABLESCAN,600037902,483318264443L,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM4_2=new Operation(newStage,TypeOperation.FILTER,200012634,39202476264L,client.isHistogramsUsed+1+client.isStatUpdate,11,nbTachesApres);
		Operation opM4_3=new Operation(newStage,TypeOperation.PROJECTION,200012634,1600101072,client.isHistogramsUsed+1+client.isStatUpdate,10,nbTachesApres);
		Operation opM4_4=new Operation(newStage,TypeOperation.GROUPBY,200012634,39202476264L,3,9,nbTachesApres);
		newStage.listeOperation.add(opM4_1);
		newStage.listeOperation.add(opM4_2);
		newStage.listeOperation.add(opM4_3);
		newStage.listeOperation.add(opM4_4);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,200012634,483318264443L,0,39202476264L,Math.min(isHistogramsUsed+1+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,200012634,0,200012634,39202476264L,0,1600101072);
		//newStage.ajouterOperation(TypeOperation.GROUPBY,200012634,0,200012634,39202476264L,0,39202476264L,3,9,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M4
	    
		newStage=new StageTez(
				"M1",
	    		req,
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+1533333272,1600101072,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2300,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,24),
	    		ModeleTempsReponse.nbTaches(24)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM1_1=new Operation(newStage,TypeOperation.TABLESCAN,150000000,79500000000L,client.isHistogramsUsed+client.isStatUpdate,9,nbTachesApres);
		Operation opM1_2=new Operation(newStage,TypeOperation.FILTER,16666666,3233333204L,client.isHistogramsUsed+1+client.isStatUpdate,8,nbTachesApres);
		Operation opM1_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,200012634,1600101072,3,8,nbTachesApres);
		Operation opM1_4=new Operation(newStage,TypeOperation.MAPJOIN,16666666,1533333272,3,7,nbTachesApres);
		Operation opM1_5=new Operation(newStage,TypeOperation.GROUPBY,23,2300,3,6,nbTachesApres);
		Operation opM1_6=new Operation(newStage,TypeOperation.PARTITION,23,2300,3,5,nbTachesApres);
		newStage.listeOperation.add(opM1_1);
		newStage.listeOperation.add(opM1_2);
		newStage.listeOperation.add(opM1_3);
		newStage.listeOperation.add(opM1_4);
		newStage.listeOperation.add(opM1_5);
		newStage.listeOperation.add(opM1_6);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,3),9,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,16666666,79500000000L,0,3233333204L,Math.min(isHistogramsUsed+1+isStatUpdate,3),8,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,200012634,0,200012634,1600101072,0,1600101072);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,1600101072,0,1600101072,79500000000L,0,3233333204L,3,7,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.GROUPBY,16666666,0,23,1533333272,0,2300,3,6,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,23,0,23,2300,0,2300);
		req.rajouterStage(newStage); //M1
	    
		newStage=new StageTez(
				"R2",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(2300,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
		nbTachesApres=ModeleTempsReponse.nbTaches(1);
		Operation opR2_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,23,2300,3,4,nbTachesApres);
		Operation opR2_2=new Operation(newStage,TypeOperation.GROUPBY,1,100,3,3,nbTachesApres);
		Operation opR2_3=new Operation(newStage,TypeOperation.PARTITION,1,100,3,2,nbTachesApres);
		newStage.listeOperation.add(opR2_1);
		newStage.listeOperation.add(opR2_2);
		newStage.listeOperation.add(opR2_3);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,23,0,23,2300,0,2300);
		//newStage.ajouterOperation(TypeOperation.GROUPBY,23,0,1,2300,0,100,3,6,ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,1,0,1,100,0,100);
		req.rajouterStage(newStage); //R2
		
		newStage=new StageTez(
				"R3",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(100,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
		Operation opR3_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,1,100,3,1,0);
		Operation opR3_2=new Operation(newStage,TypeOperation.ECRITUREDISQUE,1,100,3,0,0);
		newStage.listeOperation.add(opR3_1);
		newStage.listeOperation.add(opR3_2);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1,0,1,100,0,100);
		//newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,1,0,1,100,0,100);
		req.rajouterStage(newStage); //R3	
		
		opM4_1.connecter(null, null, opM4_2);
		opM4_2.connecter(opM4_1, null,opM4_3);
		opM4_3.connecter(opM4_2, null, opM4_4);
		opM4_4.connecter(opM4_3, null, opM1_3);
		opM1_1.connecter(null, null, opM1_2);
		opM1_2.connecter(opM1_1, null, opM1_4);
		opM1_3.connecter(opM4_4, null, opM1_4);
		opM1_4.connecter(opM1_3, opM1_2, opM1_5);
		opM1_5.connecter(opM1_4, null, opM1_6);
		opM1_6.connecter(opM1_5, null, opR2_1);
		opR2_1.connecter(opM1_6, null, opR2_2);
		opR2_2.connecter(opR2_1, null, opR2_3);
		opR2_3.connecter(opR2_2, null, opR3_1);
		opR3_1.connecter(opR2_3, null, opR3_2);
		opR3_2.connecter(opR3_1, null, null);
		
		
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1600101072,25));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2300,24,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		
		req.majDureeStages();
		return req;
	}
	
	public static RequeteTez q5_scale100(Cloud cloud,Client client) {
		RequeteTez req=new RequeteTez(client,"Q5_scale100");
		StageTez newStage;
		int nbTachesApres;
		newStage=new StageTez(
				"M7",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM7_1=new Operation(newStage,TypeOperation.TABLESCAN,5,1240,client.isHistogramsUsed+client.isStatUpdate,16,nbTachesApres);
		Operation opM7_2=new Operation(newStage,TypeOperation.FILTER,1,99,client.isHistogramsUsed+client.isStatUpdate,15,nbTachesApres);
		Operation opM7_3=new Operation(newStage,TypeOperation.PROJECTION,1,8,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		newStage.listeOperation.add(opM7_1);
		newStage.listeOperation.add(opM7_2);
		newStage.listeOperation.add(opM7_3);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,5,0,5,1240,0,1240,Math.min(isHistogramsUsed+isStatUpdate,3),16,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,5,0,1,1240,0,99,Math.min(isHistogramsUsed+isStatUpdate,3),15,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,1,0,1,99,0,8);
		req.rajouterStage(newStage); //M7
	    
		newStage=new StageTez(
				"M2",
	    		req,
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9),
	    		ModeleTempsReponse.nbTaches(9)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(26)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM2_1=new Operation(newStage,TypeOperation.TABLESCAN,15000000,8730004039L,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM2_2=new Operation(newStage,TypeOperation.FILTER,15000000,240000000,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		newStage.listeOperation.add(opM2_1);
		newStage.listeOperation.add(opM2_2);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(26)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,15000000,8730004039L,0,240000000,Math.min(isHistogramsUsed+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(26)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M2
	    
		newStage=new StageTez(
				"M6",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625+594,8,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(594,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM6_1=new Operation(newStage,TypeOperation.TABLESCAN,25,6625,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM6_2=new Operation(newStage,TypeOperation.FILTER,25,2675,client.isHistogramsUsed+1+client.isStatUpdate,13,nbTachesApres);
		Operation opM6_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,1,8,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM6_4=new Operation(newStage,TypeOperation.MAPJOIN,6,594,client.isHistogramsUsed+1+client.isStatUpdate,12,nbTachesApres);
		newStage.listeOperation.add(opM6_1);
		newStage.listeOperation.add(opM6_2);
		newStage.listeOperation.add(opM6_3);
		newStage.listeOperation.add(opM6_4);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,25,0,2675,Math.min(isHistogramsUsed+1+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1,0,1,8,0,8);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,1,25,6,2675,8,594,Math.min(isHistogramsUsed+1+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M6
	    
		newStage=new StageTez(
				"M1",
	    		req,
	    		ModeleTempsReponse.nbGroups(26),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+266666656,240000000,26),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,26),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,26),
	    		ModeleTempsReponse.nbTaches(26)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM1_1=new Operation(newStage,TypeOperation.TABLESCAN,150000000,79500000000L,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM1_2=new Operation(newStage,TypeOperation.FILTER,16666666,1833333260,client.isHistogramsUsed+1+client.isStatUpdate,13,nbTachesApres);
		Operation opM1_3=new Operation(newStage,TypeOperation.PROJECTION,16666666,1833333260,client.isHistogramsUsed+1+client.isStatUpdate,12,nbTachesApres);
		Operation opM1_4=new Operation(newStage,TypeOperation.LECTUREDISQUE,15000000,240000000,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM1_5=new Operation(newStage,TypeOperation.MAPJOIN,16666666,266666656,client.isHistogramsUsed+1+client.isStatUpdate,11,nbTachesApres);
		newStage.listeOperation.add(opM1_1);
		newStage.listeOperation.add(opM1_2);
		newStage.listeOperation.add(opM1_3);
		newStage.listeOperation.add(opM1_4);
		newStage.listeOperation.add(opM1_5);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,16666666,79500000000L,0,1833333260,Math.min(isHistogramsUsed+1+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,16666666,0,16666666,1833333260,0,266666656);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,15000000,0,15000000,240000000,0,240000000);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,15000000,16666666,16666666,240000000,266666656,266666656,Math.min(isHistogramsUsed+1+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    req.rajouterStage(newStage); //M1
	    
	    newStage=new StageTez(
	    		"M8",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(479501083+21400107,594,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(21400107,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(479501083,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
	    nbTachesApres=ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
	    Operation opM8_1=new Operation(newStage,TypeOperation.TABLESCAN,1000000,479501083,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
	    Operation opM8_2=new Operation(newStage,TypeOperation.FILTER,1000000,16000000,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);
	    Operation opM8_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,6,594,client.isHistogramsUsed+1+client.isStatUpdate,11,nbTachesApres);
	    Operation opM8_4=new Operation(newStage,TypeOperation.MAPJOIN,200001,21400107,client.isHistogramsUsed+1+client.isStatUpdate,10,nbTachesApres);
	    newStage.listeOperation.add(opM8_1);
	    newStage.listeOperation.add(opM8_2);
	    newStage.listeOperation.add(opM8_3);
	    newStage.listeOperation.add(opM8_4);
	    //newStage.ajouterOperation(TypeOperation.TABLESCAN,1000000,0,1000000,479501083,0,479501083,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.FILTER,1000000,0,1000000,479501083,0,16000000,Math.min(isHistogramsUsed+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,6,0,6,594,0,594);
	    //newStage.ajouterOperation(TypeOperation.MAPJOIN,6,1000000,200001,594,16000000,21400107,Math.min(isHistogramsUsed+1+isStatUpdate,3),10,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    req.rajouterStage(newStage); //M8
	    
	    newStage=new StageTez(
	    		"M3",
	    		req,
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+3101743936L+458559093,266666656+21400107,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(42,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,24),
	    		ModeleTempsReponse.nbTaches(24)
	    		,false);
	    nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
	    Operation opM3_1=new Operation(newStage,TypeOperation.TABLESCAN,600037902,483318264443L,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);
	    Operation opM3_2=new Operation(newStage,TypeOperation.FILTER,600037902,19201212864L,client.isHistogramsUsed+1+client.isStatUpdate,10,nbTachesApres);
	    Operation opM3_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,16666666,266666656,client.isHistogramsUsed+1+client.isStatUpdate,9,nbTachesApres);
	    Operation opM3_4=new Operation(newStage,TypeOperation.MAPJOIN,96929498,3101743936L,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);
	    Operation opM3_5=new Operation(newStage,TypeOperation.LECTUREDISQUE,200001,21400107,client.isHistogramsUsed+1+client.isStatUpdate,10,nbTachesApres);
	    Operation opM3_6=new Operation(newStage,TypeOperation.MAPJOIN,4285599,458559093,3,9,nbTachesApres);
	    Operation opM3_7=new Operation(newStage,TypeOperation.GROUPBY,42,4158,3,7,nbTachesApres);
	    Operation opM3_8=new Operation(newStage,TypeOperation.PARTITION,42,4158,3,6,nbTachesApres);
	    newStage.listeOperation.add(opM3_1);
	    newStage.listeOperation.add(opM3_2);
	    newStage.listeOperation.add(opM3_3);
	    newStage.listeOperation.add(opM3_4);
	    newStage.listeOperation.add(opM3_5);
	    newStage.listeOperation.add(opM3_6);
	    newStage.listeOperation.add(opM3_7);
	    newStage.listeOperation.add(opM3_8);
	    //newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,600037902,483318264443L,0,19201212864L,Math.min(isHistogramsUsed+1+isStatUpdate,3),10,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,16666666,0,16666666,266666656,0,266666656);
	    //newStage.ajouterOperation(TypeOperation.MAPJOIN,16666666,600037902,96929498,266666656,483318264443L,3101743936L,Math.min(isHistogramsUsed+1+isStatUpdate,3),9,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,200001,0,200001,21400107,0,21400107);
	    //newStage.ajouterOperation(TypeOperation.MAPJOIN,200001,96929498,4285599,21400107,3101743936L,458559093,3,8,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.GROUPBY,4285599,0,42,458559093,0,4158,3,7,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,42,0,42,4158,0,4158);										
	    req.rajouterStage(newStage); //M3
	    
	    newStage=new StageTez(
	    		"R4",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(4158,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(594,1,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,42,0,42,4158,0,4158);
	    //newStage.ajouterOperation(TypeOperation.GROUPBY,42,0,6,4158,0,594,3,4,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
	    //newStage.ajouterOperation(TypeOperation.PARTITION,6,0,6,594,0,594);
	    nbTachesApres=ModeleTempsReponse.nbTaches(1);
	    Operation opR4_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,42,4158,3,5,nbTachesApres);
	    Operation opR4_2=new Operation(newStage,TypeOperation.GROUPBY,6,594,3,4,nbTachesApres);
	    Operation opR4_3=new Operation(newStage,TypeOperation.PARTITION,6,594,3,3,nbTachesApres);
	    newStage.listeOperation.add(opR4_1);
	    newStage.listeOperation.add(opR4_2);
	    newStage.listeOperation.add(opR4_3);
	    req.rajouterStage(newStage); //R4
	    
	    newStage=new StageTez(
	    		"R5",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(594,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
	    Operation opR5_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,6,594,3,2,0);
	    Operation opR5_2=new Operation(newStage,TypeOperation.PROJECTION,6,594,3,1,0);
	    Operation opR5_3=new Operation(newStage,TypeOperation.ECRITUREDISQUE,6,594,3,0,0);
	    newStage.listeOperation.add(opR5_1);
	    newStage.listeOperation.add(opR5_2);
	    newStage.listeOperation.add(opR5_3);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,6,0,6,594,0,594);
	    //newStage.ajouterOperation(TypeOperation.PROJECTION,6,0,6,594,0,594);
	    //newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,6,0,6,594,0,594);
	    req.rajouterStage(newStage); //R5
	    
	    opM7_1.connecter(null, null, opM7_2);
	    opM7_2.connecter(opM7_1, null, opM7_3);
	    opM7_3.connecter(opM7_2, null, opM6_3);
	    opM2_1.connecter(null, null, opM2_2);
	    opM2_2.connecter(opM2_1, null, opM1_4);
	    opM6_1.connecter(null, null, opM6_2);
	    opM6_2.connecter(opM6_1, null, opM6_4);
	    opM6_3.connecter(opM7_3, null, opM6_4);
	    opM6_4.connecter(opM6_3, opM6_2, opM8_3);
	    opM1_1.connecter(null, null, opM1_2);
	    opM1_2.connecter(opM1_1, null, opM1_3);
	    opM1_3.connecter(opM1_2, null, opM1_5);
	    opM1_4.connecter(opM2_2,null, opM1_5);
	    opM1_5.connecter(opM1_4, opM1_3, opM3_3);
	    opM8_1.connecter(null, null, opM8_2);
	    opM8_2.connecter(opM8_1, null, opM8_4);
	    opM8_3.connecter(opM6_4, null, opM8_4);
	    opM8_4.connecter(opM8_3, opM8_2, opM3_5);
	    opM3_1.connecter(null, null, opM3_2);
	    opM3_2.connecter(opM3_1, null, opM3_3);
	    opM3_3.connecter(opM1_5, null, opM3_4);
	    opM3_4.connecter(opM3_3, opM3_2, opM3_6);
	    opM3_5.connecter(opM8_4, null, opM3_6);
	    opM3_6.connecter(opM3_5, opM3_4, opM3_7);
	    opM3_7.connecter(opM3_6, null, opM3_8);
	    opM3_8.connecter(opM3_7, null, opR4_1);
	    opR4_1.connecter(opM3_8, null, opR4_2);
	    opR4_2.connecter(opR4_1, null, opR4_3);
	    opR4_3.connecter(opR4_2, null, opR5_1);
	    opR5_1.connecter(opR4_3, null, opR5_2);
	    opR5_2.connecter(opR5_1, null, opR5_3);
	    opR5_3.connecter(opR5_2, null, null);
	    
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
		
		req.majDureeStages();
		return req;
	}
	
	public static RequeteTez q8_scale100(Cloud cloud,Client client) {
		RequeteTez req=new RequeteTez(client,"Q8_scale100");
	    
		StageTez newStage;
		
		newStage=new StageTez(
				"M10",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		int nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM10_1=new Operation(newStage,TypeOperation.TABLESCAN,5,1240,client.isHistogramsUsed+client.isStatUpdate,18,nbTachesApres);
        Operation opM10_2=new Operation(newStage,TypeOperation.FILTER,1,99,client.isHistogramsUsed+1+client.isStatUpdate,17,nbTachesApres);
		Operation opM10_3=new Operation(newStage,TypeOperation.PROJECTION,1,8,client.isHistogramsUsed+1+client.isStatUpdate,16,nbTachesApres);
		newStage.listeOperation.add(opM10_1);
		newStage.listeOperation.add(opM10_2);
		newStage.listeOperation.add(opM10_3);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,5,0,5,1240,0,1240,Math.min(isHistogramsUsed+isStatUpdate,3),17,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,5,0,1,1240,0,99,Math.min(isHistogramsUsed+1+isStatUpdate,3),16,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,1,0,1,99,0,8);
		req.rajouterStage(newStage); //M10
	    
		newStage=new StageTez(
				"M2",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM2_1=new Operation(newStage,TypeOperation.TABLESCAN,25,6625,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM2_2=new Operation(newStage,TypeOperation.FILTER,25,2475,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		newStage.listeOperation.add(opM2_1);
		newStage.listeOperation.add(opM2_2);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,6625,0,2475,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M2
	    
		newStage=new StageTez(
				"M7",
	    		req,
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9),
	    		ModeleTempsReponse.nbTaches(9)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM7_1=new Operation(newStage,TypeOperation.TABLESCAN,15000000,8730004039L,client.isHistogramsUsed+client.isStatUpdate,16,nbTachesApres);
		Operation opM7_2=new Operation(newStage,TypeOperation.FILTER,15000000,240000000,client.isHistogramsUsed+client.isStatUpdate,15,nbTachesApres);
		newStage.listeOperation.add(opM7_1);
		newStage.listeOperation.add(opM7_2);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,3),15,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,15000000,8730004039L,0,240000000,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M7
	    
		newStage=new StageTez(
				"M9",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625+48,8,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(48,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM9_1=new Operation(newStage,TypeOperation.TABLESCAN,25,6625,client.isHistogramsUsed+client.isStatUpdate,16,nbTachesApres);
		Operation opM9_2=new Operation(newStage,TypeOperation.FILTER,25,400,client.isHistogramsUsed+1+client.isStatUpdate,15,nbTachesApres);
		Operation opM9_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,1,8,client.isHistogramsUsed+1+client.isStatUpdate,15,nbTachesApres);
		Operation opM9_4=new Operation(newStage,TypeOperation.MAPJOIN,6,48,client.isHistogramsUsed+1+client.isStatUpdate,14,nbTachesApres);
		newStage.listeOperation.add(opM9_1);
		newStage.listeOperation.add(opM9_2);
		newStage.listeOperation.add(opM9_3);
		newStage.listeOperation.add(opM9_4);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,3),15,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,6625,0,400,Math.min(isHistogramsUsed+1+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1,0,1,8,0,8);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,1,25,6,8,400,48,Math.min(isHistogramsUsed+1+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(25)+ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M9
	    
		newStage=new StageTez(
				"M1",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(479501083+99000000,2475,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(99000000,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(479501083,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM1_1=new Operation(newStage,TypeOperation.TABLESCAN,1000000,479501083,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM1_2=new Operation(newStage,TypeOperation.FILTER,1000000,16000000,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM1_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,25,2475,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM1_4=new Operation(newStage,TypeOperation.MAPJOIN,2475,99000000,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);
		newStage.listeOperation.add(opM1_1);
		newStage.listeOperation.add(opM1_2);
		newStage.listeOperation.add(opM1_3);
		newStage.listeOperation.add(opM1_4);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,1000000,0,1000000,479501083,0,479501083,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,1000000,0,1000000,479501083,0,16000000,Math.min(isHistogramsUsed+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,25,0,25,2475,0,2475);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,25,1000000,1000000,2475,16000000,99000000,Math.min(isHistogramsUsed+isStatUpdate,3),10,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));					
		req.rajouterStage(newStage); //M1
	    
		newStage=new StageTez(
				"M6",
	    		req,
	    		ModeleTempsReponse.nbGroups(12),
	    		//ModeleTempsReponse.tailleMemoireParTache(12360000000L,0,12),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(969696,12),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(12360000000L,12),
	    		ModeleTempsReponse.nbTaches(12)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM6_1=new Operation(newStage,TypeOperation.TABLESCAN,20000000,12360000000L,client.isHistogramsUsed+client.isStatUpdate,15,nbTachesApres);
		Operation opM6_2=new Operation(newStage,TypeOperation.FILTER,121212,13696956,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM6_3=new Operation(newStage,TypeOperation.PROJECTION,121212,969696,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		newStage.listeOperation.add(opM6_1);
		newStage.listeOperation.add(opM6_2);
		newStage.listeOperation.add(opM6_3);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,20000000,0,20000000,12360000000L,0,12360000000L,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,20000000,0,121212,12360000000L,0,13696956,Math.min(isHistogramsUsed+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,121212,0,121212,13696956,0,969696);
		req.rajouterStage(newStage); //M6
	    
		newStage=new StageTez(
				"M8",
	    		req,
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L,240000000+48,25),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1530000102,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,25),
	    		ModeleTempsReponse.nbTaches(25)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM8_1=new Operation(newStage,TypeOperation.TABLESCAN,150000000,79500000000L,client.isHistogramsUsed+client.isStatUpdate,15,nbTachesApres);
		Operation opM8_2=new Operation(newStage,TypeOperation.FILTER,75000000,8250000000L,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM8_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,15000000,240000000,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM8_4=new Operation(newStage,TypeOperation.MAPJOIN,75000000,8250000000L,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM8_5=new Operation(newStage,TypeOperation.LECTUREDISQUE,6,48,client.isHistogramsUsed+1+client.isStatUpdate,13,nbTachesApres);
		Operation opM8_6=new Operation(newStage,TypeOperation.MAPJOIN,15000001,1530000102,client.isHistogramsUsed+1+client.isStatUpdate,12,nbTachesApres);
		newStage.listeOperation.add(opM8_1);
		newStage.listeOperation.add(opM8_2);
		newStage.listeOperation.add(opM8_3);
		newStage.listeOperation.add(opM8_4);
		newStage.listeOperation.add(opM8_5);
		newStage.listeOperation.add(opM8_6);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,75000000,79500000000L,0,8250000000L,Math.min(isHistogramsUsed+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,15000000,0,15000000,240000000,0,240000000);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,15000000,75000000,75000000,8250000000L,240000000,8250000000L,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,6,0,6,48,0,48);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,6,75000000,15000001,48,8250000000L,1530000102,Math.min(isHistogramsUsed+1+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(24)+ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M8
	    
		newStage=new StageTez(
				"M3",
	    		req,
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+1062698658+623872844+116370880,969696+1530000102+99000000,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(52870580,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,24),
	    		ModeleTempsReponse.nbTaches(24)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1);
		Operation opM3_1=new Operation(newStage,TypeOperation.TABLESCAN,600037902,483318264443L,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM3_2=new Operation(newStage,TypeOperation.FILTER,600037902,24001516080L,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM3_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,121212,969696,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM3_4=new Operation(newStage,TypeOperation.MAPJOIN,3636590,116370880L,client.isHistogramsUsed+1+client.isStatUpdate,11,nbTachesApres);
		Operation opM3_5=new Operation(newStage,TypeOperation.LECTUREDISQUE,15000001,1530000102,client.isHistogramsUsed+1+client.isStatUpdate,11,nbTachesApres);
		Operation opM3_6=new Operation(newStage,TypeOperation.MAPJOIN,5287058,623872844,client.isHistogramsUsed+1+client.isStatUpdate,10,nbTachesApres);
		Operation opM3_7=new Operation(newStage,TypeOperation.LECTUREDISQUE,1000000,99000000,client.isHistogramsUsed+client.isStatUpdate,10,nbTachesApres);
		Operation opM3_8=new Operation(newStage,TypeOperation.MAPJOIN,5287058,1062698658L,client.isHistogramsUsed+1+client.isStatUpdate,9,nbTachesApres);
		Operation opM3_9=new Operation(newStage,TypeOperation.GROUPBY,2643529,52870580,3,8,nbTachesApres);
		Operation opM3_10=new Operation(newStage,TypeOperation.PARTITION,2643529,52870580,3,7,nbTachesApres);
		newStage.listeOperation.add(opM3_1);
		newStage.listeOperation.add(opM3_2);
		newStage.listeOperation.add(opM3_3);
		newStage.listeOperation.add(opM3_4);
		newStage.listeOperation.add(opM3_5);
		newStage.listeOperation.add(opM3_6);
		newStage.listeOperation.add(opM3_7);
		newStage.listeOperation.add(opM3_8);
		newStage.listeOperation.add(opM3_9);
		newStage.listeOperation.add(opM3_10);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,600037902,24001516080L,0,24001516080L,Math.min(isHistogramsUsed+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,121212,0,121212,969696,0,969696);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,121212,600037902,3636590,969696,24001516080L,116370880L,Math.min(isHistogramsUsed+1+isStatUpdate,3),10,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,15000001,0,15000001,1530000102,0,1530000102);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,15000001,3636590,5287058,1530000102,116370880,623872844,Math.min(isHistogramsUsed+1+isStatUpdate,3),9,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,1000000,0,1000000,99000000,0,99000000);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,1000000,5287058,5287058,99000000,623872844,1062698658L,Math.min(isHistogramsUsed+1+isStatUpdate,3),8,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.GROUPBY,5287058,0,2643529,1062698658,0,52870580,3,7,ModeleTempsReponse.nbTaches(1)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,2643529,0,2643529,52870580,0,52870580);
		req.rajouterStage(newStage); //M3
	    
		newStage=new StageTez(
				"R4",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(52870580,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(31722348,1,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
		nbTachesApres=ModeleTempsReponse.nbTaches(1);
		Operation opR4_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,2643529,52870580,3,6,nbTachesApres);
		Operation opR4_2=new Operation(newStage,TypeOperation.GROUPBY,2643529,52870580,3,5,nbTachesApres);
		Operation opR4_3=new Operation(newStage,TypeOperation.PROJECTION,2643529,31722348,3,4,nbTachesApres);
		Operation opR4_4=new Operation(newStage,TypeOperation.PARTITION,2643529,31722348,3,3,nbTachesApres);
		newStage.listeOperation.add(opR4_1);
		newStage.listeOperation.add(opR4_2);
		newStage.listeOperation.add(opR4_3);
		newStage.listeOperation.add(opR4_4);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,2643529,0,2643529,52870580,0,52870580);
		//newStage.ajouterOperation(TypeOperation.GROUPBY,2643529,0,2643529,52870580,0,52870580,3,5,ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,2643529,0,2643529,52870580,0,31722348);
		//newStage.ajouterOperation(TypeOperation.PARTITION,2643529,0,2643529,31722348,0,31722348);
		req.rajouterStage(newStage); //R4
	    
		newStage=new StageTez(
				"R5",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(31722348,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(31722348,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
		Operation opR5_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,2643529,31722348,3,2,0);
		Operation opR5_2=new Operation(newStage,TypeOperation.PROJECTION,2643529,31722348,3,1,0);
		Operation opR5_3=new Operation(newStage,TypeOperation.ECRITUREDISQUE,2643529,31722348,3,0,0);
		newStage.listeOperation.add(opR5_1);
		newStage.listeOperation.add(opR5_2);
		newStage.listeOperation.add(opR5_3);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,2643529,0,2643529,31722348,0,31722348);
		//newStage.ajouterOperation(TypeOperation.PROJECTION,2643529,0,2643529,31722348,0,31722348);
		//newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,2643529,0,2643529,31722348,0,31722348);
		req.rajouterStage(newStage); //R5
		
		opM10_1.connecter(null, null, opM10_2);
		opM10_2.connecter(opM10_1, null, opM10_3);
		opM10_3.connecter(opM10_2, null, opM9_3);
		opM2_1.connecter(null, null, opM2_2);
		opM2_2.connecter(opM2_1, null, opM1_3);
		opM7_1.connecter(null, null, opM7_2);
		opM7_2.connecter(opM7_1, null, opM8_3);
		opM9_1.connecter(null, null, opM9_2);
		opM9_2.connecter(opM9_1, null, opM9_4);
		opM9_3.connecter(opM10_3, null, opM9_4);
		opM9_4.connecter(opM9_3, opM9_2, opM8_5);
		opM1_1.connecter(null, null, opM1_2);
		opM1_2.connecter(opM1_1, null, opM1_4);
		opM1_3.connecter(opM2_2, null, opM1_4);
		opM1_4.connecter(opM1_3, opM1_2, opM3_7);
		opM6_1.connecter(null, null, opM6_2);
		opM6_2.connecter(opM6_1, null, opM6_3);
		opM6_3.connecter(opM6_2, null, opM3_3);
		opM8_1.connecter(null, null, opM8_2);
		opM8_2.connecter(opM8_1, null, opM8_4);
		opM8_3.connecter(opM7_2, null, opM8_4);
		opM8_4.connecter(opM8_2, opM8_3, opM8_6);
		opM8_5.connecter(opM9_4, null, opM8_6);
		opM8_6.connecter(opM8_5, opM8_4, opM3_5);
		opM3_1.connecter(null, null, opM3_2);
		opM3_2.connecter(opM3_1, null, opM3_4);
		opM3_3.connecter(opM6_3, null, opM3_4);
		opM3_4.connecter(opM3_3, opM3_2, opM3_6);
		opM3_5.connecter(opM8_6, null, opM3_6);
		opM3_6.connecter(opM3_5, opM3_4, opM3_8);
		opM3_7.connecter(opM1_4, null, opM3_8);
		opM3_8.connecter(opM3_7, opM3_6, opM3_9);
		opM3_9.connecter(opM3_8, null, opM3_10);
		opM3_10.connecter(opM3_9, null, opR4_1);
		opR4_1.connecter(opM3_10, null, opR4_2);
		opR4_2.connecter(opR4_1, null, opR4_3);
		opR4_3.connecter(opR4_2, null, opR4_4);
		opR4_4.connecter(opR4_3, null, opR5_1);
		opR5_1.connecter(opR4_4, null, opR5_2);
		opR5_2.connecter(opR5_1, null, opR5_3);
		opR5_3.connecter(opR5_2, null, null);
		
		
	    
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
		
		req.majDureeStages();
		return req;
	}

	public static RequeteTez q10_scale100(Cloud cloud,Client client) {
		RequeteTez req=new RequeteTez(client,"Q10_scale100");
	    
		StageTez newStage=new StageTez(
				"M5",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1),
	    		ModeleTempsReponse.nbTaches(1)
	    		,false);
		int nbTachesApres=ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1);
		Operation opM5_1=new Operation(newStage,TypeOperation.TABLESCAN,25,6625,client.isHistogramsUsed+client.isStatUpdate,15,nbTachesApres);
		Operation opM5_2=new Operation(newStage,TypeOperation.FILTER,25,2475,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		newStage.listeOperation.add(opM5_1);
		newStage.listeOperation.add(opM5_2);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,25,0,25,6625,0,6625,Math.min(isHistogramsUsed+isStatUpdate,3),15,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,25,0,25,6625,0,2475,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		req.rajouterStage(newStage); //M5
	    
		newStage=new StageTez(
				"M6",
	    		req,
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L,0,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,23),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,23),
	    		ModeleTempsReponse.nbTaches(23)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1);
		Operation opM6_1=new Operation(newStage,TypeOperation.TABLESCAN,150000000,79500000000L,client.isHistogramsUsed+client.isStatUpdate,15,nbTachesApres);
		Operation opM6_2=new Operation(newStage,TypeOperation.FILTER,16666666,1833333260,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		newStage.listeOperation.add(opM6_1);
		newStage.listeOperation.add(opM6_2);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,150000000,0,150000000,79500000000L,0,79500000000L,Math.min(isHistogramsUsed+isStatUpdate,3),15,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,150000000,0,16666666,79500000000L,0,1833333260,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(9)+ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,16666666,0,16666666,1833333260,0,266666656);
		req.rajouterStage(newStage); //M6
	    
		newStage=new StageTez(
				"M1",
	    		req,
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L+8595000000L+9683332946L,25+266666656,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(9683332946L,9,101),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9),
	    		ModeleTempsReponse.nbTaches(9)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1);
		Operation opM1_1=new Operation(newStage,TypeOperation.TABLESCAN,15000000,8730004039L,client.isHistogramsUsed+client.isStatUpdate,14,nbTachesApres);
		Operation opM1_2=new Operation(newStage,TypeOperation.FILTER,15000000,7350000000L,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM1_3=new Operation(newStage,TypeOperation.LECTUREDISQUE,25,2475,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM1_4=new Operation(newStage,TypeOperation.MAPJOIN,15000000,8595000000L,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM1_5=new Operation(newStage,TypeOperation.LECTUREDISQUE,16666666,266666656,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM1_6=new Operation(newStage,TypeOperation.MAPJOIN,16666666,9683332946L,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);
		Operation opM1_7=new Operation(newStage,TypeOperation.PARTITION,16666666,9683332946L,client.isHistogramsUsed+client.isStatUpdate,10,nbTachesApres);
		newStage.listeOperation.add(opM1_1);
		newStage.listeOperation.add(opM1_2);
		newStage.listeOperation.add(opM1_3);
		newStage.listeOperation.add(opM1_4);
		newStage.listeOperation.add(opM1_5);
		newStage.listeOperation.add(opM1_6);
		newStage.listeOperation.add(opM1_7);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,15000000,0,15000000,8730004039L,0,8730004039L,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,15000000,0,15000000,8730004039L,0,7350000000L,Math.min(isHistogramsUsed+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,25,0,25,2475,0,2475);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,25,15000000,15000000,2475,7350000000L,8595000000L,Math.min(isHistogramsUsed+isStatUpdate,3),12,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,16666666,0,16666666,266666656,0,266666656);
		//newStage.ajouterOperation(TypeOperation.MAPJOIN,16666666,15000000,16666666,266666656,8595000000L,9683332946L,Math.min(isHistogramsUsed+isStatUpdate,3),11,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,16666666,0,16666666,9683332946L,0,9683332946L);
		req.rajouterStage(newStage); //M1
	    
		newStage=new StageTez(
				"M7",
	    		req,
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L,0,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(7200454824L,23,101),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,23),
	    		ModeleTempsReponse.nbTaches(23)
	    		,false);
		nbTachesApres=ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1);
		Operation opM7_1=new Operation(newStage,TypeOperation.TABLESCAN,600037902,483318264443L,client.isHistogramsUsed+client.isStatUpdate,13,nbTachesApres);
		Operation opM7_2=new Operation(newStage,TypeOperation.FILTER,300018951,32702065659L,client.isHistogramsUsed+client.isStatUpdate,12,nbTachesApres);
		Operation opM7_3=new Operation(newStage,TypeOperation.PROJECTION,300018951,7200454824L,client.isHistogramsUsed+client.isStatUpdate,11,nbTachesApres);
		Operation opM7_4=new Operation(newStage,TypeOperation.PARTITION,300018951,7200454824L,client.isHistogramsUsed+client.isStatUpdate,10,nbTachesApres);
		newStage.listeOperation.add(opM7_1);
		newStage.listeOperation.add(opM7_2);
		newStage.listeOperation.add(opM7_3);
		newStage.listeOperation.add(opM7_4);
		//newStage.ajouterOperation(TypeOperation.TABLESCAN,600037902,0,600037902,483318264443L,0,483318264443L,Math.min(isHistogramsUsed+isStatUpdate,3),14,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.FILTER,600037902,0,300018951,483318264443L,0,32702065659L,Math.min(isHistogramsUsed+isStatUpdate,3),13,ModeleTempsReponse.nbTaches(101)+ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PROJECTION,300018951,0,300018951,32702065659L,0,7200454824L);
		//newStage.ajouterOperation(TypeOperation.PARTITION,300018951,0,300018951,7200454824L,0,7200454824L);
		req.rajouterStage(newStage); //M7
	    
		newStage=new StageTez(
				"R2",
	    		req,
	    		ModeleTempsReponse.nbGroups(101),
	    		//ModeleTempsReponse.tailleMemoireParTache(7200454824L+9683332946L+57091473733L,0,101),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,101,253),
	    		0,
	    		ModeleTempsReponse.nbTaches(101)
	    		,true);
		nbTachesApres=ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1);
		Operation opR2_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,16666666,9683332946L,client.isHistogramsUsed+client.isStatUpdate,9,nbTachesApres);
		Operation opR2_2=new Operation(newStage,TypeOperation.LECTUREDISQUE,300018951,7200454824L,client.isHistogramsUsed+client.isStatUpdate,9,nbTachesApres);
		Operation opR2_3=new Operation(newStage,TypeOperation.MERGEJOIN,96929497,57091473733L,client.isHistogramsUsed+client.isStatUpdate,8,nbTachesApres);
		Operation opR2_4=new Operation(newStage,TypeOperation.GROUPBY,96929497,56316037757L,3,7,nbTachesApres);
		Operation opR2_5=new Operation(newStage,TypeOperation.PARTITION,96929497,56316037757L,3,6,nbTachesApres);
		newStage.listeOperation.add(opR2_1);
		newStage.listeOperation.add(opR2_2);
		newStage.listeOperation.add(opR2_3);
		newStage.listeOperation.add(opR2_4);
		newStage.listeOperation.add(opR2_5);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,16666666,0,16666666,9683332946L,0,9683332946L);
	    //newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,300018951,0,300018951,7200454824L,0,7200454824L);
		//newStage.ajouterOperation(TypeOperation.MERGEJOIN,16666666,300018951,96929497,9683332946L,7200454824L,57091473733L,Math.min(isHistogramsUsed+isStatUpdate,3),8,ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.GROUPBY,96929497,0,96929497,57091473733L,0,56316037757L,3,7,ModeleTempsReponse.nbTaches(253)+ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,96929497,0,96929497,56316037757L,0,56316037757L);
		req.rajouterStage(newStage); //R2
	    
		newStage=new StageTez(
				"R3",
	    		req,
	    		ModeleTempsReponse.nbGroups(253),
	    		//ModeleTempsReponse.tailleMemoireParTache(56316037757L,0,253),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,253,1),
	    		0,
	    		ModeleTempsReponse.nbTaches(253)
	    		,true);
		nbTachesApres=ModeleTempsReponse.nbTaches(1);
		Operation opR3_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,96929497,56316037757L,3,5,nbTachesApres);
		Operation opR3_2=new Operation(newStage,TypeOperation.GROUPBY,96929497,56316037757L,3,4,nbTachesApres);
		Operation opR3_3=new Operation(newStage,TypeOperation.PARTITION,96929497,56316037757L,3,3,nbTachesApres);
		newStage.listeOperation.add(opR3_1);
		newStage.listeOperation.add(opR3_2);
		newStage.listeOperation.add(opR3_3);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,96929497,0,96929497,56316037757L,0,56316037757L);
		//newStage.ajouterOperation(TypeOperation.GROUPBY,96929497,0,96929497,56316037757L,0,56316037757L,3,4,ModeleTempsReponse.nbTaches(1));
		//newStage.ajouterOperation(TypeOperation.PARTITION,96929497,0,96929497,56316037757L,0,56316037757L);
		req.rajouterStage(newStage); //R3
	    
		newStage=new StageTez(
				"R4",
	    		req,
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(56316037757L,0,1),
	    		besoinMemoire,
	    		0,
	    		0,
	    		ModeleTempsReponse.nbTaches(1)
	    		,true);
		Operation opR4_1=new Operation(newStage,TypeOperation.LECTUREDISQUE,20,11620,3,2,0);
		Operation opR4_2=new Operation(newStage,TypeOperation.LIMIT,20,11620,3,1,0);
		Operation opR4_3=new Operation(newStage,TypeOperation.ECRITUREDISQUE,20,11620,3,0,0);
		newStage.listeOperation.add(opR4_1);
		newStage.listeOperation.add(opR4_2);
		newStage.listeOperation.add(opR4_3);
		//newStage.ajouterOperation(TypeOperation.LECTUREDISQUE,20,0,20,11620,0,11620);
		//newStage.ajouterOperation(TypeOperation.LIMIT,20,0,20,11620,0,11620);
		//newStage.ajouterOperation(TypeOperation.ECRITUREDISQUE,20,0,20,11620,0,11620);
		req.rajouterStage(newStage); //R4
		
		
		opM5_1.connecter(null, null, opM5_2);
		opM5_2.connecter(opM5_1, null, opM1_3);
		opM6_1.connecter(null, null, opM6_2);
		opM6_2.connecter(opM6_1, null, opM1_5);
		opM1_1.connecter(null, null, opM1_2);
		opM1_2.connecter(opM1_1, null, opM1_4);
		opM1_3.connecter(opM5_2, null, opM1_4);
		opM1_4.connecter(opM1_3, opM1_2, opM1_6);
		opM1_5.connecter(opM6_2, null, opM1_6);
		opM1_6.connecter(opM1_5, opM1_4, opM1_7);
		opM1_7.connecter(opM1_6, null, opR2_1);
		opM7_1.connecter( null,  null, opM7_2);
		opM7_2.connecter(opM7_1, null, opM7_3);
		opM7_3.connecter(opM7_2, null, opM7_4);
		opM7_4.connecter(opM7_3, null, opR2_2);
		opR2_1.connecter(opM1_7, null, opR2_3);
		opR2_2.connecter(opM7_4, null, opR2_3);
		opR2_3.connecter(opR2_1, opR2_2, opR2_4);
		opR2_4.connecter(opR2_3, null, opR2_5);
		opR2_5.connecter(opR2_4, null, opR3_1);
		opR3_1.connecter(opR2_5,null,opR3_2);
		opR3_2.connecter(opR3_1,null,opR3_3);
		opR3_3.connecter(opR3_2,null,opR4_1);
		opR4_1.connecter(opR3_3,null,opR4_2);
		opR4_2.connecter(opR4_1,null,opR4_3);
		opR4_3.connecter(opR4_2,null,null);
		
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
		
		req.majDureeStages();
		return req;
	}
}