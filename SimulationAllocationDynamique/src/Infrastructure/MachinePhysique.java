package Infrastructure;

import java.util.*;

import ParametresGlobeaux.*;

public class MachinePhysique {
	public ArrayList<VM> ListeVMs;
	public int indexMachinePhysique;
	
	public MachinePhysique(){
		this.ListeVMs=new ArrayList<VM>();
		indexMachinePhysique=VariablesGlobales.machinePhysiqueIndex;
		VariablesGlobales.machinePhysiqueIndex++;
	}
}
