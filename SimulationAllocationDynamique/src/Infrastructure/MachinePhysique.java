package Infrastructure;

import java.util.*;

import Divers.VariablesGlobales;

public class MachinePhysique {
	public ArrayList<VM> ListeVMs;
	public int indexMachinePhysique;
	
	public MachinePhysique(){
		this.ListeVMs=new ArrayList<VM>();
		indexMachinePhysique=VariablesGlobales.machinePhysiqueIndex;
		VariablesGlobales.machinePhysiqueIndex++;
	}
	
	public MachinePhysique(CloudParallele cloud){
		this.ListeVMs=new ArrayList<VM>();
		indexMachinePhysique=cloud.machinePhysiqueIndex;
		cloud.machinePhysiqueIndex++;
	}
}
