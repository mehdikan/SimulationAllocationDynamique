package Modeles;

import ParametresGlobeaux.VariablesGlobales;

public class ModeleTempsReponse {
	public static double disqueBandwith=Math.ceil((100.0*1024*1024)/1000);   // en octets/ms
	public static double disqueLatency=2.0; // en ms
	public static double tailleMoyenneTuple=504; // en octet
	public static double vitesseCpu=100000000000.0;
	public static double nombreInsturctParOctet=3;
	public static double temps_comparaison_par_tuple=tailleMoyenneTuple*nombreInsturctParOctet/vitesseCpu;
	public static double temps_projection_par_tuple=tailleMoyenneTuple*nombreInsturctParOctet/vitesseCpu;
	public static double temps_group_by_par_tuple=tailleMoyenneTuple*nombreInsturctParOctet/vitesseCpu;
	public static double temps_repartion_par_tuple=tailleMoyenneTuple*nombreInsturctParOctet/vitesseCpu;
	public static double temps_limit_par_tuple=tailleMoyenneTuple*nombreInsturctParOctet/vitesseCpu;
	public static double temps_collect_stat_par_tuple=tailleMoyenneTuple*nombreInsturctParOctet/vitesseCpu;
	//0.0000000001
	public static double nombre_paquets_hachage_table=100;
	
	public static int nbGroups(int nbTaches) {
		return (int)Math.ceil((double)nbTaches/VariablesGlobales.nbTacheParGroupe);
	}
	
	public static int nbTaches(int nbTaches) {
		if(nbTaches==1) return 1;
		else return (int)Math.ceil((double)nbTaches/VariablesGlobales.nbTacheParGroupe)*VariablesGlobales.nbTacheParGroupe;
	}
	public static long compteTailleDonneesTransfertBroadcast(long taille,int nbTasksProducteur) {
		return (long)Math.ceil(((double)taille/nbTaches(nbTasksProducteur))*VariablesGlobales.nbTacheParGroupe*VariablesGlobales.nbTacheParGroupe/VariablesGlobales.tailleBlocDonnees);
	}
	
	public static long compteTailleDonneesTransfertShuffle(long taille,int nbTasksProducteur,int nbTasksConsomatteur) {		
		return (long)Math.ceil(((double)taille*VariablesGlobales.nbTacheParGroupe*VariablesGlobales.nbTacheParGroupe)/(nbTaches(nbTasksProducteur)*nbTaches(nbTasksConsomatteur))/VariablesGlobales.tailleBlocDonnees);
	}
	
	public static long compteTailleDonneesInitiales(long taille,int nbTasks) {
		return (long)Math.ceil(((double)taille/nbGroups(nbTasks)/VariablesGlobales.tailleBlocDonnees));
	}
	
	public static long tailleMemoireParTache(long tailleDivise,long tailleNoDivise,int nbTasks) {
		return (long)Math.ceil(((double)tailleDivise/nbTaches(nbTasks)+tailleNoDivise)/VariablesGlobales.tailleBlocDonnees);
	}
	
	
	
	public static double temps_operation_scan_table(double taille_donnee_en_entree,int nbTasks) { //octets
		VariablesGlobales.temps_TABLESCAN+=((taille_donnee_en_entree/nbTaches(nbTasks))/disqueBandwith+disqueLatency);
		VariablesGlobales.cpt_TABLESCAN+=1;
		return ((taille_donnee_en_entree/nbTaches(nbTasks))/disqueBandwith+disqueLatency);
	}
	
	public static double temps_lecture_disque(double taille_donnee_en_entree_divise,double taille_donnee_en_entree_no_divise,int nbTasks) { //octets
		VariablesGlobales.temps_LECTUREDISQUE+=((taille_donnee_en_entree_divise/nbTaches(nbTasks)+taille_donnee_en_entree_no_divise)/disqueBandwith+disqueLatency);
		VariablesGlobales.cpt_LECTUREDISQUE+=1;
		return ((taille_donnee_en_entree_divise/nbTaches(nbTasks)+taille_donnee_en_entree_no_divise)/disqueBandwith+disqueLatency);
	}
	
	public static double temps_ecriture_disque(double taille_donnee_en_entree,int nbTasks) { //octets
		VariablesGlobales.temps_ECRITUREDISQUE+=((taille_donnee_en_entree/nbTaches(nbTasks))/disqueBandwith+disqueLatency);
		VariablesGlobales.cpt_ECRITUREDISQUE+=1;
		return ((taille_donnee_en_entree/nbTaches(nbTasks))/disqueBandwith+disqueLatency);
	}
	
	public static double temps_operation_filter(double nombre_tuples,int nbTasks) {
		VariablesGlobales.temps_FILTER+=(temps_comparaison_par_tuple*(nombre_tuples/nbTaches(nbTasks)));
		VariablesGlobales.cpt_FILTER+=1;
		return (temps_comparaison_par_tuple*(nombre_tuples/nbTaches(nbTasks)));
	}
	
	public static double temps_operation_projection(double nombre_tuples,int nbTasks) {
		VariablesGlobales.temps_PROJECTION+=(temps_projection_par_tuple*(nombre_tuples/nbTaches(nbTasks)));
		VariablesGlobales.cpt_PROJECTION+=1;
		return  (temps_projection_par_tuple*(nombre_tuples/nbTaches(nbTasks)));
	}
	
	public static double temps_operation_map_join_operator(double nombre_tuples_petite_relation,double nombre_tuples_grande_relation,int nbTasks) {
		VariablesGlobales.temps_MAPJOIN+=(temps_repartion_par_tuple*nombre_tuples_petite_relation
				+temps_repartion_par_tuple*(nombre_tuples_grande_relation/nbTaches(nbTasks))
				+(nombre_tuples_petite_relation/nombre_paquets_hachage_table)*(nombre_tuples_grande_relation/nbTaches(nbTasks))*temps_comparaison_par_tuple);
		VariablesGlobales.cpt_MAPJOIN+=1;
		return (temps_repartion_par_tuple*nombre_tuples_petite_relation
				+temps_repartion_par_tuple*(nombre_tuples_grande_relation/nbTaches(nbTasks))
				+(nombre_tuples_petite_relation/nombre_paquets_hachage_table)*(nombre_tuples_grande_relation/nbTaches(nbTasks))*temps_comparaison_par_tuple);
	}
	
	public static double temps_operation_reduce_join_operator(double nombre_tuples_relation_1,double nombre_tuples_relation_2,int nbTasks_map_1,int nbTasks_map_2,int nbTasks_reduce) {
		VariablesGlobales.temps_MERGEJOIN+=((nombre_tuples_relation_1/(nbTaches(nbTasks_reduce)))*(nombre_tuples_relation_2/(nbTaches(nbTasks_reduce)))*temps_comparaison_par_tuple);
		VariablesGlobales.cpt_MERGEJOIN+=1;
		return ((nombre_tuples_relation_1/(nbTaches(nbTasks_reduce)))*(nombre_tuples_relation_2/(nbTaches(nbTasks_reduce)))*temps_comparaison_par_tuple);
	}
	
	public static double temps_operation_group(double nombre_tuples,int nbTasks) {
		VariablesGlobales.temps_GROUPBY+=(temps_group_by_par_tuple*nombre_tuples/nbTaches(nbTasks));
		VariablesGlobales.cpt_GROUPBY+=1;
		return (temps_group_by_par_tuple*nombre_tuples/nbTaches(nbTasks));
	}
	
	public static double temps_operation_limit(double limit) {
		VariablesGlobales.temps_LIMIT+=(temps_limit_par_tuple*limit);
		VariablesGlobales.cpt_LIMIT+=1;
		return  (temps_limit_par_tuple*limit);
	}
	
	public static double temps_repartition(double nombre_tuples,int nbTasks) {
		VariablesGlobales.temps_PARTITION+=(temps_repartion_par_tuple*nombre_tuples/nbTaches(nbTasks));
		VariablesGlobales.cpt_PARTITION+=1;
		return  (temps_repartion_par_tuple*nombre_tuples/nbTaches(nbTasks));
	}
	
	public static double temps_collect_stat(double nombre_tuples,int nbTasks) {
		VariablesGlobales.temps_COLLECT+=(temps_collect_stat_par_tuple*(double)nombre_tuples/nbTasks);
		VariablesGlobales.cpt_COLLECT+=1;
		return  (temps_collect_stat_par_tuple*(double)nombre_tuples/nbTasks);
	}
	
	public static int msToFenetre(double tempsEnMs) {
		return (int)Math.ceil((double)tempsEnMs/VariablesGlobales.tailleFenetreTemps);
	}

}
