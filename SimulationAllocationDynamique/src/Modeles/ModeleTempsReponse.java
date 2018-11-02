package Modeles;

import ParametresGlobeaux.VariablesGlobales;

public class ModeleTempsReponse {
	public static double disqueBandwith=100*(1024*1024/1000);   // en octets/ms
	public static double disqueLatency=2; // en ms
	public static double temps_comparaison_par_tuple=0.0000000001;
	public static double temps_projection_par_tuple=0.0000000001;
	public static double temps_group_by_par_tuple=0.0000000001;
	public static double temps_repartion_par_tuple=0.0000000001;
	public static double temps_limit_par_tuple=0.0000000001;
	//public static double nombre_paquets_hachage_partition=100;
	public static double nombre_paquets_hachage_table=100;
	
	public static int nbGroups(int nbTaches) {
		return (int)Math.ceil((double)nbTaches/VariablesGlobales.nbTacheParGroupe);
	}
	
	public static long nbTaches(int nbTaches) {
		if(nbTaches==1) return 1;
		else return (long)Math.ceil((double)nbTaches/VariablesGlobales.nbTacheParGroupe)*VariablesGlobales.nbTacheParGroupe;
	}
	public static long compteTailleDonneesTransfertBroadcast(long taille,int nbTasksProducteur) {
		return (long)Math.ceil(((double)taille/nbTaches(nbTasksProducteur))*VariablesGlobales.nbTacheParGroupe*VariablesGlobales.nbTacheParGroupe/VariablesGlobales.tailleBlocDonnees);
	}
	
	public static long compteTailleDonneesTransfertShuffle(long taille,int nbTasksProducteur,int nbTasksConsomatteur) {		
		return (long)Math.ceil(((double)taille*VariablesGlobales.nbTacheParGroupe*VariablesGlobales.nbTacheParGroupe)/(nbTasksProducteur*nbTasksConsomatteur)/VariablesGlobales.tailleBlocDonnees);
	}
	
	public static long compteTailleDonneesInitiales(long taille,int nbTasks) {
		return (long)Math.ceil(((double)taille/nbGroups(nbTasks)/VariablesGlobales.tailleBlocDonnees));
	}
	
	public static long tailleMemoireParTache(long tailleDivise,long tailleNoDivise,int nbTasks) {
		return (long)Math.ceil(((double)tailleDivise/nbTaches(nbTasks)+tailleNoDivise)/VariablesGlobales.tailleBlocDonnees);
	}
	
	
	
	public static int temps_operation_scan_table(double taille_donnee_en_entree,int nbTasks) { //octets
		return (int)((taille_donnee_en_entree/nbTaches(nbTasks))/disqueBandwith+disqueLatency);
	}
	
	public static int temps_lecture_disque(double taille_donnee_en_entree_divise,double taille_donnee_en_entree_no_divise,int nbTasks) { //octets
		return (int)((taille_donnee_en_entree_divise/nbTaches(nbTasks)+taille_donnee_en_entree_no_divise)/disqueBandwith+disqueLatency);
	}
	
	public static int temps_ecriture_disque(double taille_donnee_en_entree,int nbTasks) { //octets
		return (int)((taille_donnee_en_entree/nbTaches(nbTasks))/disqueBandwith+disqueLatency);
	}
	
	public static int temps_operation_filter(double nombre_tuples,int nbTasks) { 
		return (int)(temps_comparaison_par_tuple*(nombre_tuples/nbTaches(nbTasks)));
	}
	
	public static int temps_operation_projection(double nombre_tuples,int nbTasks) {
		return  (int)(temps_projection_par_tuple*(nombre_tuples/nbTaches(nbTasks)));
	}
	
	public static int temps_operation_map_join_operator(double nombre_tuples_petite_relation,double nombre_tuples_grande_relation,int nbTasks) {
		return (int)(temps_repartion_par_tuple*nombre_tuples_petite_relation
				+temps_repartion_par_tuple*(nombre_tuples_grande_relation/nbTaches(nbTasks))
				+(nombre_tuples_petite_relation/nombre_paquets_hachage_table)*(nombre_tuples_grande_relation/nbTaches(nbTasks))*temps_comparaison_par_tuple);
	}
	
	public static int temps_operation_reduce_join_operator(double nombre_tuples_relation_1,double nombre_tuples_relation_2,int nbTasks_map_1,int nbTasks_map_2,int nbTasks_reduce) {
		return (int)((nombre_tuples_relation_1/(nbTaches(nbTasks_map_1)*nbTaches(nbTasks_reduce)))*(nombre_tuples_relation_2/(nbTaches(nbTasks_map_2)*nbTaches(nbTasks_reduce)))*temps_comparaison_par_tuple);
	}
	
	public static int temps_operation_group(double nombre_tuples,int nbTasks) {
		return (int)(temps_group_by_par_tuple*nombre_tuples/nbTaches(nbTasks));
		
	}
	
	public static int temps_operation_limit(double limit) {
		return  (int)(temps_limit_par_tuple*limit);
	}
	
	public static int temps_repartition(double nombre_tuples,int nbTasks) {
		return  (int)(temps_repartion_par_tuple*nombre_tuples/nbTaches(nbTasks));
	}
	
	//Lecture disque (dans les op ?)
	//Ecriture disque (dans les op ?)
}
