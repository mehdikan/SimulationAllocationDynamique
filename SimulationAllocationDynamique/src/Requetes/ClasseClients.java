package Requetes;

import java.util.*;

public class ClasseClients {
	public ArrayList<Requete> requeteEnAttente;
	public ArrayList<RequeteTez> requeteTezEnAttente;
	
	public ClasseClients(){
		requeteEnAttente=new ArrayList<Requete>();
		requeteTezEnAttente=new ArrayList<RequeteTez>();
	}
	
	public void vider(){
		requeteEnAttente.clear();
		requeteTezEnAttente.clear();
	}
	
}
