package Requetes;

public class Client {
	public String name;
	public double poidsPenalites;
	public double importanceDateLimites;
	public int isStatUpdate;
	public int isHistogramsUsed;
	
	public Client(String name,double poidsPenalites,double importanceDateLimites,int isStatUpdate,int isHistogramsUsed) {
		this.name=name;
		this.poidsPenalites=poidsPenalites;
		this.importanceDateLimites=importanceDateLimites;
		this.isStatUpdate=isStatUpdate;
		this.isHistogramsUsed=isHistogramsUsed;
	}

}
