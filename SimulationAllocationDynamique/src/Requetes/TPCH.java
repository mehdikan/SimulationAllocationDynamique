package Requetes;

import Infrastructure.*;
import ParametresGlobeaux.VariablesGlobales;

public class TPCH {
	public static long convertByteToBloc(long nbBytes) {
		return (int)Math.ceil((double)nbBytes/VariablesGlobales.tailleBlocDonnees);
	}
	
	public static int compteNbGroups(int nbTaches) {
		return (int)Math.ceil((double)nbTaches/VariablesGlobales.nbTacheParGroupe);
	}
	public static int compteTailleDonneesTransfertBroadcast(long taille,int nbTasksProducteur) {
		int n=(int)Math.ceil((double)nbTasksProducteur/VariablesGlobales.nbTacheParGroupe);
		return (int)Math.ceil((double)taille*VariablesGlobales.nbTacheParGroupe/n/VariablesGlobales.tailleBlocDonnees);
	}
	public static int compteTailleDonneesTransfertShuffle(long taille,int nbTasksProducteur,int nbTasksConsomatteur) {
		int n=(int)Math.ceil((double)nbTasksProducteur/VariablesGlobales.nbTacheParGroupe);
		int m=(int)Math.ceil((double)nbTasksConsomatteur/VariablesGlobales.nbTacheParGroupe);
		return (int)Math.ceil((double)taille/(n*m)/VariablesGlobales.tailleBlocDonnees);
	}
	
	public static RequeteTez q3_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(1714288,1),convertByteToBloc(873600072/compteNbGroups(1)))); //M2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(16),nb*2,compteTailleDonneesTransfertBroadcast(75714528,16),convertByteToBloc(7950000000L/compteNbGroups(16)))); //M1
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(25),nb*1,compteTailleDonneesTransfertShuffle(215655282,25,1),convertByteToBloc(48316060157L/compteNbGroups(25)))); //M3
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertShuffle(215655282,1,1),convertByteToBloc(0))); //R4
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertBroadcast(1140,1),convertByteToBloc(0))); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), compteTailleDonneesTransfertBroadcast(1714288,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertBroadcast(75714528,16));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), compteTailleDonneesTransfertShuffle(215655282,25,1));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), compteTailleDonneesTransfertShuffle(215655282,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q3_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(2),nb*2,compteTailleDonneesTransfertBroadcast(17142856,2),convertByteToBloc(8730004039L/compteNbGroups(2)))); //M2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(25),nb*2,compteTailleDonneesTransfertBroadcast(757142948,25),convertByteToBloc(79500000000L/compteNbGroups(25)))); //M1
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(23),nb*1,compteTailleDonneesTransfertShuffle(4735699080L,23,18),convertByteToBloc(483318264443L/compteNbGroups(23)))); //M3
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(18),nb*2,compteTailleDonneesTransfertShuffle(4735699080L,18,1),convertByteToBloc(0))); //R4
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertBroadcast(1140,1),convertByteToBloc(0))); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), compteTailleDonneesTransfertBroadcast(17142856,2));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertBroadcast(757142948,25));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), compteTailleDonneesTransfertShuffle(4735699080L,23,18));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), compteTailleDonneesTransfertShuffle(4735699080L,18,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q4_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(24),nb*2,compteTailleDonneesTransfertBroadcast(79981400,24),convertByteToBloc(48316060157L/compteNbGroups(24)))); //M4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(16),nb*2,compteTailleDonneesTransfertShuffle(300,16,1),convertByteToBloc(7950000000L/compteNbGroups(16)))); //M1
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertShuffle(100,1,1),convertByteToBloc(0))); //R2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(100,1),convertByteToBloc(0))); //R3
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), compteTailleDonneesTransfertBroadcast(79981400,24));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertShuffle(300,16,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), compteTailleDonneesTransfertShuffle(100,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q4_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(24),nb*2,compteTailleDonneesTransfertBroadcast(1600101072,25),convertByteToBloc(483318264443L/compteNbGroups(24)))); //M4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(16),nb*2,compteTailleDonneesTransfertShuffle(2300,24,100),convertByteToBloc(79500000000L/compteNbGroups(16)))); //M1
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertShuffle(100,100,100),convertByteToBloc(0))); //R2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(100,100),convertByteToBloc(0))); //R3
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), compteTailleDonneesTransfertBroadcast(1600101072,25));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertShuffle(2300,24,100));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), compteTailleDonneesTransfertShuffle(100,100,100));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q5_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(8,1),convertByteToBloc(1240/compteNbGroups(1)))); //M6
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(594,1),convertByteToBloc(6625/compteNbGroups(1)))); //M5
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertBroadcast(2140107,1),convertByteToBloc(47900000/compteNbGroups(1)))); //M7
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(16),nb*2,compteTailleDonneesTransfertBroadcast(26666656,16),convertByteToBloc(7950000000L/compteNbGroups(16)))); //M4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(3),nb*2,compteTailleDonneesTransfertBroadcast(24000000,3),convertByteToBloc(873600072L/compteNbGroups(3)))); //M8
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(24),nb*2,compteTailleDonneesTransfertShuffle(99,24,1),convertByteToBloc(48316060157L/compteNbGroups(24)))); //M1
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertShuffle(99,1,1),convertByteToBloc(0))); //R2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(99,1),convertByteToBloc(0))); //R3
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertBroadcast(594,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(5), compteTailleDonneesTransfertBroadcast(2140107,1));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(5), compteTailleDonneesTransfertBroadcast(26666656,16));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), compteTailleDonneesTransfertBroadcast(24000000,3));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(6), compteTailleDonneesTransfertShuffle(99,24,1));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), compteTailleDonneesTransfertShuffle(99,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(5), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(5), 1);
	    req.majTypeLien(req.getStage(4), req.getStage(5), 1);
	    req.majTypeLien(req.getStage(5), req.getStage(6), 1);
	    req.majTypeLien(req.getStage(6), req.getStage(7), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q5_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(8,1),convertByteToBloc(1240/compteNbGroups(1)))); //M7
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(9),nb*2,compteTailleDonneesTransfertBroadcast(240000000,9),convertByteToBloc(8730004039L/compteNbGroups(9)))); //M2
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertBroadcast(594,1),convertByteToBloc(6625/compteNbGroups(1)))); //M6
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(26),nb*2,compteTailleDonneesTransfertBroadcast(266666656,26),convertByteToBloc(79500000000L/compteNbGroups(26)))); //M1
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(21400107,1),convertByteToBloc(479501083/compteNbGroups(1)))); //M8
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(24),nb*2,compteTailleDonneesTransfertShuffle(42,24,1),convertByteToBloc(483318264443L/compteNbGroups(24)))); //M3
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertShuffle(594,1,1),convertByteToBloc(0))); //R4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(594,1),convertByteToBloc(0))); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(2), compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(3), compteTailleDonneesTransfertBroadcast(240000000,9));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(4), compteTailleDonneesTransfertBroadcast(594,1));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(5), compteTailleDonneesTransfertBroadcast(266666656,26));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), compteTailleDonneesTransfertBroadcast(21400107,1));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(6), compteTailleDonneesTransfertShuffle(42,24,1));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), compteTailleDonneesTransfertShuffle(594,1,1));
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
	
	public static RequeteTez q8_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(8,1),convertByteToBloc(1240/compteNbGroups(1)))); //M10
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(2475,1),convertByteToBloc(6625/compteNbGroups(1)))); //M2
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(3),nb*1,compteTailleDonneesTransfertBroadcast(24000000,3),convertByteToBloc(873600072/compteNbGroups(3)))); //M7
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(48,1),convertByteToBloc(6625/compteNbGroups(1)))); //M9
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(9900000,1),convertByteToBloc(47900000/compteNbGroups(1)))); //M1
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(2),nb*2,compteTailleDonneesTransfertBroadcast(96968,2),convertByteToBloc(1236000000/compteNbGroups(2)))); //M6
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(16),nb*1,compteTailleDonneesTransfertBroadcast(153000102,16),convertByteToBloc(7950000000L/compteNbGroups(16)))); //M8
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(25),nb*2,compteTailleDonneesTransfertShuffle(4815160,25,1),convertByteToBloc(48316060157L/compteNbGroups(25)))); //M3
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertShuffle(2889096,1,1),convertByteToBloc(0))); //R4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(2889096,1),convertByteToBloc(0))); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(3), compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(4), compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(6), compteTailleDonneesTransfertBroadcast(24000000,3));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(6), compteTailleDonneesTransfertBroadcast(48,1));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(7), compteTailleDonneesTransfertBroadcast(9900000,1));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(7), compteTailleDonneesTransfertBroadcast(96968,2));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), compteTailleDonneesTransfertBroadcast(153000102,16));
	    req.majQuantiteTransfertStages(req.getStage(7), req.getStage(8), compteTailleDonneesTransfertShuffle(4815160,25,1));
	    req.majQuantiteTransfertStages(req.getStage(8), req.getStage(9), compteTailleDonneesTransfertShuffle(2889096,1,1));
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
	
	public static RequeteTez q8_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(8,1),convertByteToBloc(1240/compteNbGroups(1)))); //M10
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(2475,1),convertByteToBloc(6625/compteNbGroups(1)))); //M2
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(9),nb*1,compteTailleDonneesTransfertBroadcast(240000000,9),convertByteToBloc(8730004039L/compteNbGroups(9)))); //M7
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(48,1),convertByteToBloc(6625/compteNbGroups(1)))); //M9
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(99000000,1),convertByteToBloc(479501083/compteNbGroups(1)))); //M1
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(12),nb*2,compteTailleDonneesTransfertBroadcast(969696,12),convertByteToBloc(12360000000L/compteNbGroups(12)))); //M6
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(25),nb*1,compteTailleDonneesTransfertBroadcast(1530000102,25),convertByteToBloc(79500000000L/compteNbGroups(25)))); //M8
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(24),nb*2,compteTailleDonneesTransfertShuffle(52870580,24,1),convertByteToBloc(483318264443L/compteNbGroups(24)))); //M3
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(1),nb*1,compteTailleDonneesTransfertShuffle(31722348,1,1),convertByteToBloc(0))); //R4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(31722348,1),convertByteToBloc(0))); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(3), compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(4), compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(6), compteTailleDonneesTransfertBroadcast(240000000,9));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(6), compteTailleDonneesTransfertBroadcast(48,1));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(7), compteTailleDonneesTransfertBroadcast(99000000,1));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(7), compteTailleDonneesTransfertBroadcast(969696,12));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), compteTailleDonneesTransfertBroadcast(1530000102,25));
	    req.majQuantiteTransfertStages(req.getStage(7), req.getStage(8), compteTailleDonneesTransfertShuffle(52870580,24,1));
	    req.majQuantiteTransfertStages(req.getStage(8), req.getStage(9), compteTailleDonneesTransfertShuffle(31722348,1,1));
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

	public static RequeteTez q10_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(2475,1),convertByteToBloc(6625/compteNbGroups(1)))); //M2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(3),nb*2,compteTailleDonneesTransfertBroadcast(859500000,3),convertByteToBloc(873600072/compteNbGroups(3)))); //M1
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(16),nb*1,compteTailleDonneesTransfertBroadcast(968332946,16),convertByteToBloc(7950000000L/compteNbGroups(16)))); //M3
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(24),nb*2,compteTailleDonneesTransfertShuffle(5129045922L,24,20),convertByteToBloc(48316060157L/compteNbGroups(24)))); //M4
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(20),nb*2,compteTailleDonneesTransfertShuffle(5129045922L,20,1),convertByteToBloc(0))); //R5
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(11620,1),convertByteToBloc(0))); //R6
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertBroadcast(859500000,3));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), compteTailleDonneesTransfertBroadcast(968332946,16));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), compteTailleDonneesTransfertShuffle(5129045922L,24,20));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), compteTailleDonneesTransfertShuffle(5129045922L,20,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
	    req.majTypeLien(req.getStage(4), req.getStage(5), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	public static RequeteTez q10_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25);
		int nb=VariablesGlobales.tailleBlocDonnees;
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(2475,1),convertByteToBloc(6625/compteNbGroups(1)))); //M5
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(23),nb*2,compteTailleDonneesTransfertBroadcast(266666656,23),convertByteToBloc(79500000000L/compteNbGroups(23)))); //M6
	    req.rajouterStage(new StageTez(req,2,compteNbGroups(9),nb*1,compteTailleDonneesTransfertShuffle(9683332946L,9,101),convertByteToBloc(8730004039L/compteNbGroups(9)))); //M1
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(23),nb*2,compteTailleDonneesTransfertShuffle(7200454824L,23,101),convertByteToBloc(483318264443L/compteNbGroups(23)))); //M7
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(101),nb*2,compteTailleDonneesTransfertShuffle(56316037757L,101,253),convertByteToBloc(0))); //R2
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(253),nb*2,compteTailleDonneesTransfertShuffle(56316037757L,253,1),convertByteToBloc(0))); //R3
	    req.rajouterStage(new StageTez(req,3,compteNbGroups(1),nb*2,compteTailleDonneesTransfertBroadcast(11620,1),convertByteToBloc(0))); //R4
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(2), compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), compteTailleDonneesTransfertBroadcast(266666656,23));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(4), compteTailleDonneesTransfertShuffle(9683332946L,9,101));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), compteTailleDonneesTransfertShuffle(7200454824L,23,101));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), compteTailleDonneesTransfertShuffle(56316037757L,101,253));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(6), compteTailleDonneesTransfertShuffle(56316037757L,253,1));
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