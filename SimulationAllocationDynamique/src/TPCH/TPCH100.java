package TPCH;

import Infrastructure.*;
import ParametresGlobeaux.*;
import Requetes.RequeteTez;
import Requetes.StageTez;
import Modeles.*;

public class TPCH100 {
	//public static int nb=VariablesGlobales.nbTacheParGroupe;
	public static long besoinMemoire=(int)(4L*1024*1024*1024/VariablesGlobales.tailleBlocDonnees);
	
	public static RequeteTez q3_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q3_scale100 "+besoinMemoire);
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,2)+ModeleTempsReponse.temps_operation_filter(15000000,2)+ModeleTempsReponse.temps_operation_projection(2142857,2),
	    		ModeleTempsReponse.nbGroups(2),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,2),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(17142856,2),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,2))); //M2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,25)+ModeleTempsReponse.temps_operation_filter(150000000,25)+ModeleTempsReponse.temps_lecture_disque(0,17142856,25)+ModeleTempsReponse.temps_operation_map_join_operator(2142857,50000000,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+757142948,17142856,2),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(757142948,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,25))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,23)+ModeleTempsReponse.temps_operation_filter(600037902,23)+ModeleTempsReponse.temps_operation_projection(200012634,23)+ModeleTempsReponse.temps_lecture_disque(0,757142948,23)+ModeleTempsReponse.temps_operation_map_join_operator(7142858,200012634,23)+ModeleTempsReponse.temps_operation_group(41541220,23)+ModeleTempsReponse.temps_repartition(41541220,23),
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+5068028840L,757142948,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,23,18),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,23))); //M3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(4735699080L,0,18)+ModeleTempsReponse.temps_operation_group(41541220,18)+ModeleTempsReponse.temps_repartition(41541220,18),
	    		ModeleTempsReponse.nbGroups(18),
	    		//ModeleTempsReponse.tailleMemoireParTache(4735699080L,0,18),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(4735699080L,18,1),
	    		0)); //R4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(1140,0,1)+ModeleTempsReponse.temps_operation_limit(10)+ModeleTempsReponse.temps_ecriture_disque(1140,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(4735699080L,0,1),
	    		besoinMemoire,
	    		0,
	    		0)); //R5
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
	
	public static RequeteTez q4_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q4_scale100");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,25)+ModeleTempsReponse.temps_operation_filter(600037902,25)+ModeleTempsReponse.temps_operation_projection(200012634,25)+ModeleTempsReponse.temps_operation_group(200012634,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L,0,25),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1600101072,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,25))); //M4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,24)+ModeleTempsReponse.temps_operation_filter(3233333204L,24)+ModeleTempsReponse.temps_lecture_disque(0,1600101072,24)+ModeleTempsReponse.temps_operation_map_join_operator(200012634,16666666,24)+ModeleTempsReponse.temps_operation_group(16666666,24)+ModeleTempsReponse.temps_repartition(23,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+1533333272,1600101072,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(2300,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,24))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(2300,0,1)+ModeleTempsReponse.temps_operation_group(23,1)+ModeleTempsReponse.temps_repartition(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(2300,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(100,1,1),
	    		0)); //R2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(100,0,1)+ModeleTempsReponse.temps_ecriture_disque(100,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(100,0,1),
	    		besoinMemoire,
	    		0,
	    		0)); //R3
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
	
	public static RequeteTez q5_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q5_scale100");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1240,1)+ModeleTempsReponse.temps_operation_filter(5,1)+ModeleTempsReponse.temps_operation_projection(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1))); //M7
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,9)+ModeleTempsReponse.temps_operation_filter(15000000,9)+ModeleTempsReponse.temps_operation_projection(15000000,9),
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9))); //M2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1)+ModeleTempsReponse.temps_lecture_disque(0,8,1)+ModeleTempsReponse.temps_operation_map_join_operator(1,25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625+594,8,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(594,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M6
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,26)+ModeleTempsReponse.temps_operation_filter(150000000,26)+ModeleTempsReponse.temps_operation_projection(16666666,26)+ModeleTempsReponse.temps_lecture_disque(0,240000000,26)+ModeleTempsReponse.temps_operation_map_join_operator(15000000,16666666,26),
	    		ModeleTempsReponse.nbGroups(26),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L+266666656,240000000,26),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,26),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,26))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(479501083,1)+ModeleTempsReponse.temps_operation_filter(1000000,1)+ModeleTempsReponse.temps_lecture_disque(0,594,1)+ModeleTempsReponse.temps_operation_map_join_operator(6,1000000,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(479501083+21400107,594,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(21400107,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(479501083,1))); //M8
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,24)+ModeleTempsReponse.temps_operation_filter(600037902,24)+ModeleTempsReponse.temps_lecture_disque(0,266666656,24)+ModeleTempsReponse.temps_operation_map_join_operator(16666666,600037902,24)+ModeleTempsReponse.temps_lecture_disque(0,21400107,24)+ModeleTempsReponse.temps_operation_map_join_operator(200001,96929498,24)+ModeleTempsReponse.temps_operation_group(4285599,24)+ModeleTempsReponse.temps_repartition(42,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+3101743936L+458559093,266666656+21400107,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(42,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,24))); //M3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(4158,0,1)+ModeleTempsReponse.temps_operation_group(42,1)+ModeleTempsReponse.temps_repartition(6,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(4158,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(594,1,1),
	    		0)); //R4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(594,0,1)+ModeleTempsReponse.temps_operation_projection(6,1)+ModeleTempsReponse.temps_ecriture_disque(594,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(594,0,1),
	    		besoinMemoire,
	    		0,
	    		0)); //R5
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
	
	public static RequeteTez q8_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q8_scale100");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(1240,1)+ModeleTempsReponse.temps_operation_filter(5,1)+ModeleTempsReponse.temps_operation_projection(1,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(1240,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(8,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(1240,1))); //M10
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,9)+ModeleTempsReponse.temps_operation_filter(15000000,9),
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L,0,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(240000000,9),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9))); //M7
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1)+ModeleTempsReponse.temps_lecture_disque(0,8,1)+ModeleTempsReponse.temps_operation_map_join_operator(1,25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625+48,8,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(48,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M9
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(479501083,1)+ModeleTempsReponse.temps_operation_filter(1000000,1)+ModeleTempsReponse.temps_lecture_disque(0,2475,1)+ModeleTempsReponse.temps_operation_map_join_operator(25,1000000,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(479501083+99000000,2475,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(99000000,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(479501083,1))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(12360000000L,12)+ModeleTempsReponse.temps_operation_filter(13696956,12)+ModeleTempsReponse.temps_operation_projection(969696,12),
	    		ModeleTempsReponse.nbGroups(12),
	    		//ModeleTempsReponse.tailleMemoireParTache(12360000000L,0,12),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(969696,12),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(12360000000L,12))); //M6
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,25)+ModeleTempsReponse.temps_operation_filter(150000000,25)+ModeleTempsReponse.temps_lecture_disque(0,240000000,25)+ModeleTempsReponse.temps_operation_map_join_operator(15000000,75000000,25)+ModeleTempsReponse.temps_lecture_disque(0,48,25)+ModeleTempsReponse.temps_operation_map_join_operator(6,15000001,25),
	    		ModeleTempsReponse.nbGroups(25),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L,240000000+48,25),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(1530000102,25),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,25))); //M8
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,24)+ModeleTempsReponse.temps_operation_filter(600037902,24)+ModeleTempsReponse.temps_lecture_disque(0,969696,24)+ModeleTempsReponse.temps_operation_map_join_operator(600037902,121212,24)+ModeleTempsReponse.temps_lecture_disque(0,1530000102,24)+ModeleTempsReponse.temps_operation_map_join_operator(15000001,3636590,24)+ModeleTempsReponse.temps_lecture_disque(0,99000000,24)+ModeleTempsReponse.temps_operation_map_join_operator(1000000,5287058,24)+ModeleTempsReponse.temps_operation_group(5287058,24)+ModeleTempsReponse.temps_repartition(2643529,24),
	    		ModeleTempsReponse.nbGroups(24),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L+1062698658+623872844+116370880,969696+1530000102+99000000,24),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(52870580,24,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,24))); //M3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(52870580,0,1)+ModeleTempsReponse.temps_operation_group(2643529,1)+ModeleTempsReponse.temps_operation_projection(2643529,1)+ModeleTempsReponse.temps_repartition(2643529,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(52870580,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(31722348,1,1),
	    		0)); //R4
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(31722348,0,1)+ModeleTempsReponse.temps_operation_projection(2643529,1)+ModeleTempsReponse.temps_ecriture_disque(31722348,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(31722348,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(31722348,1),
	    		0)); //R5
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

	public static RequeteTez q10_scale100(Cloud cloud) {
		RequeteTez req=new RequeteTez(1,25,"Q10_scale100");
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(6625,1)+ModeleTempsReponse.temps_operation_filter(25,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(6625,0,1),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(2475,1),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(6625,1))); //M5
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(79500000000L,23)+ModeleTempsReponse.temps_operation_filter(150000000,23)+ModeleTempsReponse.temps_operation_projection(16666666,23),
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(79500000000L,0,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertBroadcast(266666656,23),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(79500000000L,23))); //M6
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(8730004039L,9)+ModeleTempsReponse.temps_operation_filter(15000000,9)+ModeleTempsReponse.temps_lecture_disque(0,2475,9)+ModeleTempsReponse.temps_operation_map_join_operator(25,15000000,9)+ModeleTempsReponse.temps_lecture_disque(0,266666656,9)+ModeleTempsReponse.temps_operation_map_join_operator(16666666,15000000,9)+ModeleTempsReponse.temps_repartition(16666666,9),
	    		ModeleTempsReponse.nbGroups(9),
	    		//ModeleTempsReponse.tailleMemoireParTache(8730004039L+8595000000L+9683332946L,25+266666656,9),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(9683332946L,9,101),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(8730004039L,9))); //M1
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_operation_scan_table(483318264443L,23)+ModeleTempsReponse.temps_operation_filter(600037902,23)+ModeleTempsReponse.temps_operation_projection(300018951,23)+ModeleTempsReponse.temps_repartition(300018951,23),
	    		ModeleTempsReponse.nbGroups(23),
	    		//ModeleTempsReponse.tailleMemoireParTache(483318264443L,0,23),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(7200454824L,23,101),
	    		ModeleTempsReponse.compteTailleDonneesInitiales(483318264443L,23))); //M7
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(7200454824L,0,101)+ModeleTempsReponse.temps_lecture_disque(9683332946L,0,101)+ModeleTempsReponse.temps_operation_reduce_join_operator(7200454824L,9683332946L,23,9,101)+ModeleTempsReponse.temps_operation_projection(96929497,101)+ModeleTempsReponse.temps_operation_group(96929497,101)+ModeleTempsReponse.temps_repartition(96929497,101),
	    		ModeleTempsReponse.nbGroups(101),
	    		//ModeleTempsReponse.tailleMemoireParTache(7200454824L+9683332946L+57091473733L,0,101),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,101,253),
	    		0)); //R2
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(56316037757L,0,253)+ModeleTempsReponse.temps_operation_group(96929497,253)+ModeleTempsReponse.temps_repartition(96929497,253),
	    		ModeleTempsReponse.nbGroups(253),
	    		//ModeleTempsReponse.tailleMemoireParTache(56316037757L,0,253),
	    		besoinMemoire,
	    		ModeleTempsReponse.compteTailleDonneesTransfertShuffle(56316037757L,253,1),
	    		0)); //R3
	    req.rajouterStage(new StageTez(
	    		req,
	    		ModeleTempsReponse.temps_lecture_disque(11620,0,1)+ModeleTempsReponse.temps_operation_limit(20)+ModeleTempsReponse.temps_ecriture_disque(11620,1),
	    		ModeleTempsReponse.nbGroups(1),
	    		//ModeleTempsReponse.tailleMemoireParTache(56316037757L,0,1),
	    		besoinMemoire,
	    		0,
	    		0)); //R4
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