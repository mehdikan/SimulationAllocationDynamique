package CollectionStatistiques;

import Requetes.*;

public class CollecteurPotentiel {
	public TypeCollecteur type;
	public Operation operationAssocie;
	public boolean mettreCollecteur;
	public int niveauInexactitude;
	public int nombreOperationsAffectes;
	public long nombreTachesApres;
	
	
	public CollecteurPotentiel(TypeCollecteur type,
			Operation operationAssocie,
			int niveauInexactitude,
			int nombreOperationsAffectes,
			long nombreTachesApres) {
		this.type=type;
		this.mettreCollecteur=false;
		this.niveauInexactitude=Math.min(niveauInexactitude,3);
		this.nombreOperationsAffectes=nombreOperationsAffectes;
		this.nombreTachesApres=nombreTachesApres;
		this.operationAssocie=operationAssocie;
	}

	

}
