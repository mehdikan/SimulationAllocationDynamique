package AllocationStatique;

public class TrancheTempsAlloue {
	public int indexRessource;
	public int indexRequete;
	public int indexStage;
	public int indexTache;
	public int dateDebut;
	public int dateFin;
	
	public TrancheTempsAlloue(int indexRessource,int indexRequete,int indexStage,int indexTache,int dateDebut,int dateFin){
		this.indexRessource=indexRessource;
		this.indexRequete=indexRequete;
		this.indexStage=indexStage;
		this.indexTache=indexTache;
		this.dateDebut=dateDebut;
		this.dateFin=dateFin;
	}
}
