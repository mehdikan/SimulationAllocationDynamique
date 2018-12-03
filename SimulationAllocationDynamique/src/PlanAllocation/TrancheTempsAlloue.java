package PlanAllocation;

import Requetes.GroupeTachesTez;
import Requetes.RequeteTez;
import Requetes.StageTez;

public class TrancheTempsAlloue {
	public int indexRessource;
	public RequeteTez requete;
	public StageTez stage;
	public GroupeTachesTez tache;
	public int dateDebut;
	public int dateFin;
	public int decalage;
	
	public TrancheTempsAlloue(int indexRessource,RequeteTez requete,StageTez stage,GroupeTachesTez tache,int dateDebut,int dateFin){
		this.indexRessource=indexRessource;
		this.requete=requete;
		this.stage=stage;
		this.tache=tache;
		this.dateDebut=dateDebut;
		this.dateFin=dateFin;
		this.decalage=0;
	}
}
