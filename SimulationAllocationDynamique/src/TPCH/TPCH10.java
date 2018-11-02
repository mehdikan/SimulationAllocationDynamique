package TPCH;

import Infrastructure.*;
import ParametresGlobeaux.*;
import Requetes.RequeteTez;
import Requetes.StageTez;
import Modeles.*;

public class TPCH10 {
	public static int nb=VariablesGlobales.nbTacheParGroupe;

	
	/*public static RequeteTez q3_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q3_scale10");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(873600072,1)+ModeleTempsReponse.temps_operation_filter(1500000,1)+ModeleTempsReponse.temps_operation_projection(214286,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(873600072,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1714288,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(873600072,1))); //M2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(7950000000.0,16)+ModeleTempsReponse.temps_operation_filter(15000000,16)+ModeleTempsReponse.temps_lecture_disque(1714288,16)+ModeleTempsReponse.temps_operation_map_join_operator(214286,5000000,16),
	    		ModeleTempsReponse.nbGroups(16),
	    		nb*(ModeleTempsReponse.tailleMemoireParTache(7950000000L+75714528,1714288,16)),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(75714528,16),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(7950000000L,16))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(48316060157.0,25)+ModeleTempsReponse.temps_operation_filter(59986052,25)+ModeleTempsReponse.temps_operation_projection(19995350,25)+ModeleTempsReponse.temps_lecture_disque(75714528,25)+ModeleTempsReponse.temps_operation_map_join_operator(75714528,19995350,25)+ModeleTempsReponse.temps_operation_group(3783426,25)+ModeleTempsReponse.temps_repartition(1891713,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(479888400+461577972,75714528,25),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(215655282,25,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(48316060157L,25))); //M3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(215655282,1)+ModeleTempsReponse.temps_operation_group(1891713,1)+ModeleTempsReponse.temps_repartition(1891713,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(215655282,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(215655282,1,1),
	    		0)); //R4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(215655282,1)+ModeleTempsReponse.temps_operation_limit(10)+ModeleTempsReponse.temps_ecriture_disque(1140,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(215655282,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1140,1),
	    		0)); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1714288,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(75714528,16));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(215655282,25,1));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(215655282,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	
	public static RequeteTez q4_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q4_scale10");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(48316060157.0,24)+ModeleTempsReponse.temps_operation_filter(59986052.0,24)+ModeleTempsReponse.temps_operation_group(19995350.0,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(48316060157L,0,24),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(79981400,24),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(48316060157L,24))); //M4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(7950000000.0,16)+ModeleTempsReponse.temps_operation_filter(15000000,16)+ModeleTempsReponse.temps_lecture_disque(79981400,16)+ModeleTempsReponse.temps_operation_map_join_operator(9997675,1666666,16)+ModeleTempsReponse.temps_operation_group(1666666,16)+ModeleTempsReponse.temps_repartition(300,16),
	    		ModeleTempsReponse.nbGroups(16),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(7950000000L+153333272,79981400,16),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(300,16,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(7950000000L,16))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(300,1)+ModeleTempsReponse.temps_operation_group(3,1)+ModeleTempsReponse.temps_repartition(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(300,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1),
	    		0)); //R2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(100,1)+ModeleTempsReponse.temps_operation_projection(1,1)+ModeleTempsReponse.temps_ecriture_disque(100,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(100,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(100,1),
	    		0)); //R3
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(79981400,24));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(300,16,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}
	
	
	public static RequeteTez q5_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q5_scale10");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1240,1)+ModeleTempsReponse.temps_operation_filter(5,1)+ModeleTempsReponse.temps_operation_projection(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1))); //M6
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1)+ModeleTempsReponse.temps_lecture_disque(8,1)+ModeleTempsReponse.temps_operation_map_join_operator(1,25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(594+6625,8,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(594,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M5
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(47900000,1)+ModeleTempsReponse.temps_operation_filter(100000,1)+ModeleTempsReponse.temps_lecture_disque(594,1)+ModeleTempsReponse.temps_operation_map_join_operator(6,100000,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(47900000+2140107,594,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2140107,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(47900000,1))); //M7
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(7950000000.0,16)+ModeleTempsReponse.temps_operation_filter(15000000,16)+ModeleTempsReponse.temps_operation_projection(1666666,16),
	    		ModeleTempsReponse.nbGroups(16),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(7950000000L,0,16),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(26666656,16),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(7950000000L,16))); //M4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(873600072,3)+ModeleTempsReponse.temps_operation_filter(1500000,3),
	    		ModeleTempsReponse.nbGroups(3),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(873600072L,0,3),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(24000000,3),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(873600072L,3))); //M8
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(48316060157.0,24)+ModeleTempsReponse.temps_operation_filter(59986052,24)+ModeleTempsReponse.temps_lecture_disque(26666656,24)+ModeleTempsReponse.temps_operation_map_join_operator(1666666,59986052,24)+ModeleTempsReponse.temps_lecture_disque(2140107,24)+ModeleTempsReponse.temps_operation_map_join_operator(20001,8827963,24)+ModeleTempsReponse.temps_lecture_disque(24000000,24)+ModeleTempsReponse.temps_operation_map_join_operator(1500000,1765593,24)+ModeleTempsReponse.temps_operation_group(98464,24)+ModeleTempsReponse.temps_repartition(1,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(48316060157L+282494816+217167939+10535648,26666656+2140107+24000000,24),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(99,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(48316060157L,24))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(99,1)+ModeleTempsReponse.temps_operation_group(1,1)+ModeleTempsReponse.temps_repartition(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(99,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(99,1,1),
	    		0)); //R2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(99,1)+ModeleTempsReponse.temps_ecriture_disque(99,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(99,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(99,1),
	    		0)); //R3
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(594,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2140107,1));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(26666656,16));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(24000000,3));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(99,24,1));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(99,1,1));
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
	
	
	public static RequeteTez q8_scale10(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q8_scale10");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1240,1)+ModeleTempsReponse.temps_operation_filter(5,1)+ModeleTempsReponse.temps_operation_projection(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1))); //M10
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(873600072,3)+ModeleTempsReponse.temps_operation_filter(1500000,3),
	    		ModeleTempsReponse.nbGroups(3),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(873600072,0,3),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(24000000,3),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(873600072,3))); //M7
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1)+ModeleTempsReponse.temps_lecture_disque(8,1)+ModeleTempsReponse.temps_operation_map_join_operator(1,25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(400+48,8,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(48,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M9
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(47900000,1)+ModeleTempsReponse.temps_operation_filter(100000,1)+ModeleTempsReponse.temps_lecture_disque(25,1)+ModeleTempsReponse.temps_operation_map_join_operator(25,100000,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(47900000+9900000,2475,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(9900000,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(47900000,1))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1236000000,2)+ModeleTempsReponse.temps_operation_filter(2000000,2)+ModeleTempsReponse.temps_operation_projection(12121,2),
	    		ModeleTempsReponse.nbGroups(2),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(1236000000,0,2),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(96968,2),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1236000000,2))); //M6
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(7950000000.0,16)+ModeleTempsReponse.temps_operation_filter(15000000,16)+ModeleTempsReponse.temps_lecture_disque(24000000,16)+ModeleTempsReponse.temps_operation_map_join_operator(1500000,7500000,16)+ModeleTempsReponse.temps_lecture_disque(24000000,16)+ModeleTempsReponse.temps_operation_map_join_operator(1500000,7500000,16),
	    		ModeleTempsReponse.nbGroups(16),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(153000102+48+6625,48+8,16),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(153000102,16),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(7950000000L,16))); //M8
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(48316060157.0,25)+ModeleTempsReponse.temps_operation_filter(59986052,25)+ModeleTempsReponse.temps_lecture_disque(96968,25)+ModeleTempsReponse.temps_operation_map_join_operator(12121,59986052,25)+ModeleTempsReponse.temps_lecture_disque(153000102,25)+ModeleTempsReponse.temps_operation_map_join_operator(1500001,363546,25)+ModeleTempsReponse.temps_lecture_disque(9900000,25)+ModeleTempsReponse.temps_operation_map_join_operator(100000,481517,25)+ModeleTempsReponse.temps_operation_group(481517,25)+ModeleTempsReponse.temps_repartition(240758,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(96784917+56819006+11633472+48316060157L,9900000+153000102+96968,25),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4815160,25,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(48316060157L,25))); //M3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(4815160,1)+ModeleTempsReponse.temps_operation_group(240758,1)+ModeleTempsReponse.temps_operation_projection(240758,1)+ModeleTempsReponse.temps_repartition(240758,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(4815160,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2889096,1,1),
	    		0)); //R4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(2889096,1)+ModeleTempsReponse.temps_operation_projection(240758,1)+ModeleTempsReponse.temps_ecriture_disque(2889096,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(2889096,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2889096,1),
	    		0)); //R5
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(24000000,3));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(6), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(48,1));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(9900000,1));
	    req.majQuantiteTransfertStages(req.getStage(5), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(96968,2));
	    req.majQuantiteTransfertStages(req.getStage(6), req.getStage(7), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(153000102,16));
	    req.majQuantiteTransfertStages(req.getStage(7), req.getStage(8), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4815160,25,1));
	    req.majQuantiteTransfertStages(req.getStage(8), req.getStage(9), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2889096,1,1));
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
		RequeteTez req=new RequeteTez(1,25,"Q10_scale10");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(873600072,3)+ModeleTempsReponse.temps_operation_filter(1500000,3)+ModeleTempsReponse.temps_lecture_disque(2475,3)+ModeleTempsReponse.temps_operation_map_join_operator(25,1500000,3),
	    		ModeleTempsReponse.nbGroups(3),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(859500000+873600072,2475,3),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(859500000,3),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(873600072,3))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(7950000000.0,16)+ModeleTempsReponse.temps_operation_filter(15000000,16)+ModeleTempsReponse.temps_lecture_disque(859500000,16)+ModeleTempsReponse.temps_operation_map_join_operator(1500000,1666666,16),
	    		ModeleTempsReponse.nbGroups(16),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(968332946+7950000000L,859500000,16),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(968332946,16),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(7950000000L,16))); //M3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(48316060157.0,24)+ModeleTempsReponse.temps_operation_filter(59986052,24)+ModeleTempsReponse.temps_operation_projection(29993026,24)+ModeleTempsReponse.temps_lecture_disque(968332946,24)+ModeleTempsReponse.temps_operation_map_join_operator(1666666,29993026,24)+ModeleTempsReponse.temps_operation_group(8827962,24)+ModeleTempsReponse.temps_repartition(8827962,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(5199669618L+48316060157L,968332946,24),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(5129045922L,24,20),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(48316060157L,24))); //M4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(5129045922.0,20)+ModeleTempsReponse.temps_operation_group(8827962,20)+ModeleTempsReponse.temps_repartition(8827962,20),
	    		ModeleTempsReponse.nbGroups(20),
	    		nb*ModeleTempsReponse.tailleMemoireParTache(5129045922L,0,20),
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(5129045922L,20,1),
	    		0)); //R5
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(5129045922.0,1)+ModeleTempsReponse.temps_operation_limit(20)+ModeleTempsReponse.temps_ecriture_disque(11620,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		ModeleTempsReponse.tailleMemoireParTache(5129045922L,0,1),
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(11620,1),
	    		0)); //R6
	    req.stageFinal=req.listeStages.get(req.listeStages.size()-1);
	    req.initLiens();
	    req.majQuantiteTransfertStages(req.getStage(0), req.getStage(1), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1));
	    req.majQuantiteTransfertStages(req.getStage(1), req.getStage(2), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(859500000,3));
	    req.majQuantiteTransfertStages(req.getStage(2), req.getStage(3), ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(968332946,16));
	    req.majQuantiteTransfertStages(req.getStage(3), req.getStage(4), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(5129045922L,24,20));
	    req.majQuantiteTransfertStages(req.getStage(4), req.getStage(5), ModeleTempsReponse.compteTailleDonneesTransfertShuffle(5129045922L,20,1));
	    req.majTypeLien(req.getStage(0), req.getStage(1), 1);
	    req.majTypeLien(req.getStage(1), req.getStage(2), 1);
	    req.majTypeLien(req.getStage(2), req.getStage(3), 1);
	    req.majTypeLien(req.getStage(3), req.getStage(4), 1);
	    req.majTypeLien(req.getStage(4), req.getStage(5), 1);
		cloud.listeClassesClient.get(0).requeteTezEnAttente.add(req);
		return req;
	}*/
	
}