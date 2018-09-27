package Requetes;

import java.util.*;

public class ClasseClients {
	public ArrayList<RequeteTez> requeteTezEnAttente;
	
	public ClasseClients(){
		requeteTezEnAttente=new ArrayList<RequeteTez>();
	}
	
	public void vider(){
		requeteTezEnAttente.clear();
	}
	
}
