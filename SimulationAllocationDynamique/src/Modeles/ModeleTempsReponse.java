package Modeles;

public class ModeleTempsReponse {
	public static double disqueBandwith=100;   // en Mb/s
	public static double disqueLatency=2/1000; // en secondes
	public static double temps_comparaison_par_tuple=3;
	public static double temps_projection_par_tuple=3;
	public static double temps_group_by_par_tuple=3;
	public static double temps_repartion_par_tuple=3;
	public static double temps_limit_par_tuple=3;
	//public static double nombre_paquets_hachage_partition=100;
	public static double nombre_paquets_hachage_table=100;
	
	public static double temps_operation_scan_table(double taille_donnee_en_entree) { //octets
		return taille_donnee_en_entree/disqueBandwith+disqueLatency;
	}
	
	public static double temps_operation_filter(double nombre_tuples) { 
		return temps_comparaison_par_tuple*nombre_tuples;
	}
	
	public static double temps_operation_projection(double nombre_tuples) {
		return  temps_projection_par_tuple*nombre_tuples;
	}
	
	public static double temps_operation_map_join_operator(double nombre_tuples_petite_relation,double nombre_tuples_grande_relation) {
		return temps_repartion_par_tuple*nombre_tuples_petite_relation
				+temps_repartion_par_tuple*nombre_tuples_grande_relation
				+(nombre_tuples_petite_relation/nombre_paquets_hachage_table)*nombre_tuples_grande_relation*temps_comparaison_par_tuple;
	}
	
	public static double temps_operation_group(double nombre_tuples) {
		return  temps_group_by_par_tuple*nombre_tuples;
		
	}
	
	public static double temps_limit(double limit) {
		return  temps_limit_par_tuple*limit;
	}
	
	public static double temps_repartition(double nombre_tuples) {
		return  temps_repartion_par_tuple*nombre_tuples;
	}
	
	//Lecture disque (dans les op ?)
	//Ecriture disque (dans les op ?)
}
