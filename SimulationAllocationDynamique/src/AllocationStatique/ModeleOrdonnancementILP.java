package AllocationStatique;

import org.gnu.glpk.*;

import ParametresGlobeaux.*;
import PlanAllocation.PlanStatique;
import PlanAllocation.TrancheTempsAlloue;
import Requetes.*;
import Infrastructure.*;
import Modeles.*;

public class ModeleOrdonnancementILP implements GlpkCallbackListener{
	glp_prob lp;
	Cloud cloud;
	int nbStages;
	int[] nbTezTasks;
	int nbTezSlots;
	int T;
	double P[];
	int D[];
	int Dt_max[];
	int Dt_traitement[][];
	int Dt_traitement_et_communication[][];
	int A[][];
	int F[][];
	int seq[][];
	int pip[][];
	//double Ws;
	//double q[];
	double quantiteRecue[];
	
	int indexDebR;
	int indexDebC;
	
	boolean bonneSolutionTrouve=false;
	
	public ModeleCoutEconomique modeleCout;
	
	public ModeleOrdonnancementILP(Cloud cloud,ModeleCoutEconomique modeleCout,int AIn[][]){
		////////////////////////
		this.modeleCout=modeleCout;
		this.cloud=cloud;
		nbStages=cloud.getNbStages();                
		nbTezSlots=VariablesGlobales.tezSlotsIndex;			 	
		T=VariablesGlobales.T;
		////////////////////////
		
		nbTezTasks=new int[nbStages];
		quantiteRecue=new double[nbStages];
		
		
		////////////////////////
		for(ClasseClients c : cloud.listeClassesClient){
		for(RequeteTez r : c.requeteTezEnAttente){
			for(StageTez stage1 : r.listeStages){
				quantiteRecue[stage1.indexStage]=stage1.quantiteRecu;
				nbTezTasks[stage1.indexStage]=stage1.nombreGroupesTachesTez;
			}
		}
		}
		////////////////////////
		
		P=new double[nbStages];
		D=new int[nbStages];
		Dt_max=new int[nbStages];
		Dt_traitement=new int[nbStages][];
		Dt_traitement_et_communication=new int[nbStages][];
		A=new int[nbStages][];
		for(int i=0;i<nbStages;i++) {
			A[i]=new int[nbTezTasks[i]];
			Dt_traitement[i]=new int[nbTezTasks[i]];
			Dt_traitement_et_communication[i]=new int[nbTezTasks[i]];
		}
		F=new int[nbTezSlots][T];
		seq=new int[nbStages][nbStages];
		pip=new int[nbStages][nbStages];
		
		////////////////////////
		for(int i=0;i<nbStages;i++){	
			for(int m=0;m<nbTezTasks[i];m++){
				A[i][m]=AIn[i][m];
			}
		}
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez rq : c.requeteTezEnAttente){
				for(StageTez stage1 : rq.listeStages){
					Dt_max[stage1.indexStage]=0;
					for(GroupeTachesTez tache: stage1.groupesTezTaches) {
						Dt_max[stage1.indexStage]=Math.max(Dt_max[stage1.indexStage], ModeleTempsReponse.msToFenetre(stage1.dureeTacheTezEnMs+tache.dureeCommunicationEnMs));
						Dt_traitement[stage1.indexStage][tache.indexDansStage]= ModeleTempsReponse.msToFenetre(stage1.dureeTacheTezEnMs); 
						Dt_traitement_et_communication[stage1.indexStage][tache.indexDansStage]=ModeleTempsReponse.msToFenetre(stage1.dureeTacheTezEnMs+tache.dureeCommunicationEnMs);
					}
					//Dt[stage1.indexStage]=stage1.dureeTacheTez;
					D[stage1.indexStage]=rq.dateLimite;
					if(stage1==rq.stageFinal) P[stage1.indexStage]=rq.poids;
					else P[stage1.indexStage]=0;
				}
			}
		}
		
		for(int i=0;i<nbStages;i++) {
			for(int j=0;j<nbStages;j++) {
				seq[i][j]=0;
				pip[i][j]=0;
			}
		}
		
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage1 : r.listeStages){
					for(StageTez stage2 : r.listeStages){
						if(r.getLien(stage1,stage2)==1)
							seq[stage1.indexStage][stage2.indexStage]=1;
						else if(r.getLien(stage1,stage2)==2)
							pip[stage1.indexStage][stage2.indexStage]=1;
					}
				}
			}
		}
		
		for(int t=0;t<T;t++){
			for(MachinePhysique mp : cloud.listeMachinesPhysique){
				for(VM vm : mp.ListeVMs){
					for(int a=vm.indexDebutSlotsTez;a<vm.indexDebutSlotsTez+vm.nbTezSlots;a++){
						F[a][t]=vm.disponibliteTrancheTempsTez[a-vm.indexDebutSlotsTez][t];
					}
				}
			}
		}
		//////////////////////
	}
	
	public PlanStatique resoudre(){
		
        glp_iocp iocp;
        SWIGTYPE_p_int ind;
        SWIGTYPE_p_double val;
        int ret;
        PlanStatique gantt=null;

        try {
            // Create problem
            lp = GLPK.glp_create_prob();
             if(!VariablesGlobales.verbose) GLPK.glp_term_out(GLPKConstants.GLP_OFF);
            if(VariablesGlobales.verbose) System.out.println("Problem cr��");
            GLPK.glp_set_prob_name(lp, "Probleme ordonnancement");

            // Define columns
            
            int index=1;
            for(int i=0;i<nbStages;i++){
            	for(int j=0;j<nbTezTasks[i];j++){
            		for(int t=0;t<T;t++){
            			 GLPK.glp_add_cols(lp, 1);
            			 GLPK.glp_set_col_name(lp, index, "X["+i+"]["+j+"]["+t+"]");
            			 GLPK.glp_set_col_kind(lp, index, GLPKConstants.GLP_BV);
            			 index++;
            		}
            	}
            }

            indexDebR=index;
            for(int i=0;i<nbStages;i++){
            	for(int t=0;t<T;t++){
            		GLPK.glp_add_cols(lp, 1);
       			 	GLPK.glp_set_col_name(lp, index, "R["+i+"]["+t+"]");
       			 	GLPK.glp_set_col_kind(lp, index, GLPKConstants.GLP_BV);
            		index++;
            	}
        	}
            
            indexDebC=index;
            for(int i=0;i<nbStages;i++){
            	for(int j=0;j<nbTezTasks[i];j++){
            		for(int t=0;t<T;t++){
            			 GLPK.glp_add_cols(lp, 1);
            			 GLPK.glp_set_col_name(lp, index, "C["+i+"]["+j+"]["+t+"]");
            			 GLPK.glp_set_col_kind(lp, index, GLPKConstants.GLP_BV);
            			 index++;
            		}
            	}
            }
            
            int nbVariables=index-1;

            // Create constraints            
            ind = GLPK.new_intArray(3);
            val = GLPK.new_doubleArray(3);
            int contIndex=1;
            for(int i=0;i<nbStages;i++){		
				for(int r=0;r<nbTezTasks[i];r++){
					for(int t=Dt_traitement_et_communication[i][r];t<T;t++){		
						GLPK.glp_add_rows(lp, 1);
						GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
						GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_LO, 1, 1);
			            GLPK.intArray_setitem(ind, 1, getXIndex(i,r,t-Dt_traitement_et_communication[i][r]));
			            GLPK.intArray_setitem(ind, 2, getRIndex(i,t));
			            GLPK.doubleArray_setitem(val, 1, 1);
			            GLPK.doubleArray_setitem(val, 2, 1);
			            GLPK.glp_set_mat_row(lp, contIndex, 2, ind, val);
						contIndex++;
					}
				}
			}
                
            for(int i=0;i<nbStages;i++){
  			  for(int m=0;m<nbTezTasks[i];m++){	
  				  for(int t=0;t<T;t++){
  					  if(t<T-1){
  						  GLPK.glp_add_rows(lp, 1);
  						  GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
  						  GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_LO, 0, 0);
  						  GLPK.intArray_setitem(ind, 1, getXIndex(i,m,t));
			              GLPK.intArray_setitem(ind, 2, getXIndex(i,m,t+1));
			              GLPK.doubleArray_setitem(val, 1, -1);
			              GLPK.doubleArray_setitem(val, 2, 1);
				          GLPK.glp_set_mat_row(lp, contIndex, 2, ind, val);
				          contIndex++;
  					  }
  				  }
  			  }
  		  }
            
            ind = GLPK.new_intArray(4);
            val = GLPK.new_doubleArray(4);
            for(int i=0;i<nbStages;i++) {
            	for(int j=0;j<nbStages;j++) {
            		if(seq[i][j]>0) {
	                	for(int m=0;m<nbTezTasks[i];m++) {
	                		for(int r=0;r<nbTezTasks[j];r++) {
	                			for(int t=0;t<T-Dt_traitement[i][m];t++) {
	                				GLPK.glp_add_rows(lp, 1);
	                				GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
	                				GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_UP, 1-seq[i][j], 1-seq[i][j]);
	                				GLPK.intArray_setitem(ind, 1, getXIndex(i,m,t));
		      			            GLPK.intArray_setitem(ind, 2, getXIndex(j,r,t+Dt_traitement[i][m]));
		      			            GLPK.intArray_setitem(ind, 3, getCIndex(j,r,t+Dt_traitement[i][m]));
		      			            GLPK.doubleArray_setitem(val, 1, seq[i][j]);
		      			            GLPK.doubleArray_setitem(val, 2, -seq[i][j]);
		      			            GLPK.doubleArray_setitem(val, 3, -1);
		      				        GLPK.glp_set_mat_row(lp, contIndex, 3, ind, val);
		      				        contIndex++;
	                			}
                			}
                		}
                	}
                }
            }
            
            
           
            for(int i=0;i<nbStages;i++) {
            	for(int j=0;j<nbStages;j++) {
            		if(pip[i][j]>0) {
	                	for(int m=0;m<nbTezTasks[i];m++) {
	                		for(int r=0;r<nbTezTasks[j];r++) {
	                			for(int t=0;t<T;t++) {
	                				GLPK.glp_add_rows(lp, 1);
	                				GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
	                				GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_UP, 1-pip[i][j], 1-pip[i][j]);
	                				GLPK.intArray_setitem(ind, 1, getXIndex(i,m,t));
		      			            GLPK.intArray_setitem(ind, 2, getXIndex(j,r,t));
		      			            GLPK.intArray_setitem(ind, 3, getCIndex(i,m,t));
		      			            GLPK.doubleArray_setitem(val, 1, pip[i][j]);
		      			            GLPK.doubleArray_setitem(val, 2, -pip[i][j]);
		      			            GLPK.doubleArray_setitem(val, 3, -1);
		      				        GLPK.glp_set_mat_row(lp, contIndex, 3, ind, val);
		      				        contIndex++;
	                			}
                			}
                		}
                	}
                }
            }
            
                   
            for(int k=0;k<nbTezSlots;k++){
    			for(int t=0;t<T;t++){
    				int nbElement=0;
    				for(int i=0;i<nbStages;i++){
    					for(int m=0;m<nbTezTasks[i];m++){
    						if(A[i][m]==k && t-Dt_traitement_et_communication[i][m]>=1){
    							nbElement+=2;
    						}
    						else if(A[i][m]==k && t-Dt_traitement_et_communication[i][m]<1){
    							nbElement+=1;
    						}
    					}
    				}
			        ind = GLPK.new_intArray(nbElement+1);
			        val = GLPK.new_doubleArray(nbElement+1);  
			        GLPK.glp_add_rows(lp, 1);
			        GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
			        GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_UP, F[k][t], F[k][t]);
			        int iii=1;
    				for(int i=0;i<nbStages;i++){
    					for(int m=0;m<nbTezTasks[i];m++){
    						if(A[i][m]==k && t-Dt_traitement_et_communication[i][m]>=1){
      						    GLPK.intArray_setitem(ind, iii, getXIndex(i,m,t));
      						    GLPK.doubleArray_setitem(val, iii, 1);
      						    iii++;
      						    GLPK.intArray_setitem(ind, iii, getXIndex(i,m,t-Dt_traitement_et_communication[i][m]));
    						    GLPK.doubleArray_setitem(val, iii, -1);
    						    iii++;
    						}
    						else if(A[i][m]==k && t-Dt_traitement_et_communication[i][m]<1){
    							GLPK.intArray_setitem(ind, iii, getXIndex(i,m,t));
      						    GLPK.doubleArray_setitem(val, iii, 1);
      						    iii++;
    						}
    					}
    				}
    				GLPK.glp_set_mat_row(lp, contIndex, nbElement, ind, val);
			        contIndex++;
    			}
    		}
            

            for(int i=0;i<nbStages;i++){
    			for(int j=0;j<nbStages;j++){
    			  for(int m=0;m<nbTezTasks[i];m++){
    				  for(int r=0;r<nbTezTasks[j];r++){	
    					  for(int t=0;t<T;t++){
    						  if(i!=j && t-Dt_traitement_et_communication[i][m]>=1){
    							    ind = GLPK.new_intArray(3);
    	  				            val = GLPK.new_doubleArray(3);  
    	  							GLPK.glp_add_rows(lp, 1);
    	  							GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
    	  							GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_UP, 1-seq[i][j], 1-seq[i][j]);
    	  						    GLPK.intArray_setitem(ind, 1, getXIndex(j,r,t));
    	  						    GLPK.intArray_setitem(ind, 2, getXIndex(i,m,t-Dt_traitement_et_communication[i][m]));
    	  						    GLPK.doubleArray_setitem(val, 1, 1);
    	  						    GLPK.doubleArray_setitem(val, 2, -1);
    	  						    GLPK.glp_set_mat_row(lp, contIndex, 2, ind, val);
    	  						    contIndex++;
    						  }
    					  }
    				  }
    			   }
    			}
    		}     
            
            for(int i=0;i<nbStages;i++){
    			for(int j=0;j<nbStages;j++){
				  for(int r=0;r<nbTezTasks[j];r++){	
					  for(int t=0;t<T;t++){
						  if(i!=j && t-Dt_max[i]<1){
							ind = GLPK.new_intArray(2);
  				            val = GLPK.new_doubleArray(2);  
  							GLPK.glp_add_rows(lp, 1);
  							GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
  							GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_UP, 1-seq[i][j], 1-seq[i][j]);
  						    GLPK.intArray_setitem(ind, 1, getXIndex(j,r,t));
  						    GLPK.doubleArray_setitem(val, 1, 1);
  						    GLPK.glp_set_mat_row(lp, contIndex, 1, ind, val);
  						    contIndex++;
						  }
					  }
				  }
    			}
    		}  

            
            for(int i=0;i<nbStages;i++){
    			for(int j=0;j<nbStages;j++){
    			  for(int m=0;m<nbTezTasks[i];m++){
    				  for(int r=0;r<nbTezTasks[j];r++){	
    					  for(int t=0;t<T;t++){
    						  if(i!=j){
    							    ind = GLPK.new_intArray(3);
    	  				            val = GLPK.new_doubleArray(3);  
    	  							GLPK.glp_add_rows(lp, 1);
    	  							GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
    	  							GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_UP, 1-pip[i][j], 1-pip[i][j]);
    	  						    GLPK.intArray_setitem(ind, 1, getXIndex(j,r,t));
    	  						    GLPK.intArray_setitem(ind, 2, getXIndex(i,m,t));
    	  						    GLPK.doubleArray_setitem(val, 1, 1);
    	  						    GLPK.doubleArray_setitem(val, 2, -1);
    	  						    GLPK.glp_set_mat_row(lp, contIndex, 2, ind, val);
    	  						    contIndex++;
    						  }
    					  }
    				  }
    			  }
    			}
    		}
            
            for(int i=0;i<nbStages;i++){
            	for(int m=0;m<nbTezTasks[i];m++){
            		ind = GLPK.new_intArray(2);
			        val = GLPK.new_doubleArray(2);  
					GLPK.glp_add_rows(lp, 1);
					GLPK.glp_set_row_name(lp, contIndex, "c"+contIndex);
					GLPK.glp_set_row_bnds(lp, contIndex, GLPKConstants.GLP_FX,1 ,1);
					GLPK.intArray_setitem(ind, 1, getXIndex(i,m,T-1));
					GLPK.doubleArray_setitem(val, 1, 1);
					GLPK.glp_set_mat_row(lp, contIndex, 1, ind, val);
					contIndex++;
            	}
            }

            GLPK.delete_doubleArray(val);
            GLPK.delete_intArray(ind);

            // Define objective
            GLPK.glp_set_obj_name(lp, "obj");
            GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MIN);
            for(int i=0;i<=nbVariables;i++){
            	GLPK.glp_set_obj_coef(lp, 0, 0);
            }            
            index=indexDebR;
            for(int i=0;i<nbStages;i++){
				for(int t=0;t<T;t++){				
					if(t>D[i]){
						GLPK.glp_set_obj_coef(lp, index, ModeleCoutEconomique.prixUnitePenalites*P[i]);
					}
					index++;
				}
            }
            index=indexDebC;
            for(int i=0;i<nbStages;i++) {
            	for(int m=0;m<nbTezTasks[i];m++) {
            		for(int t=0;t<T;t++){
            			GLPK.glp_set_obj_coef(lp, index, ModeleCoutEconomique.prixUniteDisque*quantiteRecue[i]);
            		}
            		index++;
            	}
            }

            GlpkCallback.addListener(this);
            // Solve model
            iocp = new glp_iocp();
            GLPK.glp_init_iocp(iocp);
            iocp.setPresolve(GLPKConstants.GLP_ON);
            ret = GLPK.glp_intopt(lp, iocp);

            // Retrieve solution
            
            if (ret == 0 || bonneSolutionTrouve) {
                //write_mip_solution(lp);
            	//afficher(lp);
            	
            	gantt=ecrireResultatEtCalculCoutsPenaliteDisque(lp);
            } else {
            	if(VariablesGlobales.verbose) System.out.println("The problem could not be solved");
            };

            // free memory
            GLPK.glp_delete_prob(lp);
            //GLPK.glp_free_env();
        } catch (GlpkException ex) {
            ex.printStackTrace();
            ret = 1;
        }
        
        return gantt;
        //System.exit(ret);
	}
	
	static void write_mip_solution(glp_prob lp) {
        int i;
        int n;
        String name;
        double val;

        name = GLPK.glp_get_obj_name(lp);
        val  = GLPK.glp_mip_obj_val(lp);
        if(VariablesGlobales.verbose) System.out.print(name);
        if(VariablesGlobales.verbose) System.out.print(" = ");
        if(VariablesGlobales.verbose) System.out.println(val);
        n = GLPK.glp_get_num_cols(lp);
        for(i=1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            val  = GLPK.glp_mip_col_val(lp, i);
            if(VariablesGlobales.verbose) System.out.print(name);
            if(VariablesGlobales.verbose) System.out.print(" = ");
            if(VariablesGlobales.verbose) System.out.println(val);
        }
    }
	
	public PlanStatique ecrireResultatEtCalculCoutsPenaliteDisque(glp_prob lp){	
		String name;
		double val;
		int Req[];
		Req=new int[nbStages];
		
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage1 : r.listeStages){
					Req[stage1.indexStage]=r.index;
				}
			}
		}
		
		if(VariablesGlobales.verbose) System.out.println("Co�t ordonnancement :"+GLPK.glp_mip_obj_val(lp));
		int index=1;
		if(VariablesGlobales.verbose) System.out.println("X = ");
		for(int i=0;i<nbStages;i++){
			for(int j=0;j<nbTezTasks[i];j++){
				if(VariablesGlobales.verbose) System.out.print("(");
				for(int t=0;t<T;t++){
					name = GLPK.glp_get_col_name(lp, index);
		            val  = GLPK.glp_mip_col_val(lp, index);
		            //System.out.print(name);
		            //System.out.print(" = ");
		            if(VariablesGlobales.verbose) System.out.print(val+" ");
		            index++;
				}
				if(VariablesGlobales.verbose) System.out.println(")");
				if(VariablesGlobales.verbose) System.out.println("");
			}
		}	
		
		index=this.indexDebC;
		modeleCout.coutDisque=0;
		if(VariablesGlobales.verbose) System.out.println("C = ");
		for(int i=0;i<nbStages;i++){
        	for(int j=0;j<nbTezTasks[i];j++){
        		if(VariablesGlobales.verbose) System.out.print("(");
        		for(int t=0;t<T;t++){
        			name = GLPK.glp_get_col_name(lp, index);
		            val  = GLPK.glp_mip_col_val(lp, index);
		            if(VariablesGlobales.verbose) System.out.print(val+" ");
		            index++;
		            modeleCout.coutDisque+=val*quantiteRecue[i]*VariablesGlobales.tailleFenetreTemps;
        		}
        		if(VariablesGlobales.verbose) System.out.println(")");
        		if(VariablesGlobales.verbose) System.out.println("");
        	}
        }
		
		for(ClasseClients c : cloud.listeClassesClient){
			for(RequeteTez r : c.requeteTezEnAttente){
				for(StageTez stage : r.listeStages){
					for(GroupeTachesTez tache: stage.groupesTezTaches){
						modeleCout.coutDisque+=stage.donneeInitiale*ModeleTempsReponse.msToFenetre(stage.dureeTacheTezEnMs+tache.dureeCommunicationEnMs)*VariablesGlobales.tailleFenetreTemps;
					}
				}
			}
		}
		
	    int debut,fin;
	    PlanStatique gantt=new PlanStatique();
	    
	    modeleCout.coutPenalite=0;
	    double finStage;
	    for(int i=0;i<nbStages;i++){
	    	finStage=0;
			for(int j=0;j<nbTezTasks[i];j++){
				debut=0;
				fin=T-1;
				for(int t=0;t<T-1;t++){
					if(t-Dt_traitement_et_communication[i][j]>=0){
						if((GLPK.glp_mip_col_val(lp, getXIndex(i,j,t))-GLPK.glp_mip_col_val(lp, getXIndex(i,j,t-Dt_traitement_et_communication[i][j])))< (GLPK.glp_mip_col_val(lp, getXIndex(i,j,t+1))-GLPK.glp_mip_col_val(lp,getXIndex(i,j,t+1-Dt_traitement_et_communication[i][j]))))
							debut=t+1;
						else if((GLPK.glp_mip_col_val(lp, getXIndex(i,j,t))-GLPK.glp_mip_col_val(lp, getXIndex(i,j,t-Dt_traitement_et_communication[i][j])))>(GLPK.glp_mip_col_val(lp, getXIndex(i,j,t+1))-GLPK.glp_mip_col_val(lp, getXIndex(i,j,t+1-Dt_traitement_et_communication[i][j]))) )
							fin=t;
					}
					else{
						if(GLPK.glp_mip_col_val(lp, getXIndex(i,j,t))<GLPK.glp_mip_col_val(lp, getXIndex(i,j,t+1)))
							debut=t+1;
						else if(GLPK.glp_mip_col_val(lp, getXIndex(i,j,t))>GLPK.glp_mip_col_val(lp, getXIndex(i,j,t+1)))
							fin=t;
						else if(GLPK.glp_mip_col_val(lp, getXIndex(i,j,0))==1){
							debut=1;
							fin=Dt_traitement_et_communication[i][j];
						}
					}
				}
				finStage=Math.max(finStage, fin);
				for(ClasseClients c : cloud.listeClassesClient){
					for(RequeteTez r : c.requeteTezEnAttente){
						for(StageTez stage : r.listeStages){
							for(GroupeTachesTez tache: stage.groupesTezTaches){
								if(Req[i]==r.index && i==stage.indexStage && j==tache.index) {
									gantt.ajouterTrancheTemps(new TrancheTempsAlloue(A[i][j], r, stage, tache, debut, fin));
								}
							}
						}
					}
				}
			}
			
			if(P[i]>0 && finStage>D[i]) {
				modeleCout.coutPenalite+=P[i]*(finStage-D[i]);
			}
		}
	    
	    return gantt;
	}
	
	
	public int getXIndex(int ii, int jj,int tt){
		int index=0;
        for(int i=0;i<nbStages;i++){
        	if(i<ii){
        		index+=nbTezTasks[i]*T;
        	}
        	else{
        		for(int j=0;j<nbTezTasks[i];j++){
        			if(j<jj){
        				index+=T;
        			}
        			else{
        				index+=(tt+1);
        				break;
        			}
        		}
        		break;
        	}
        }
        return index;
	}
	
	
	public int getRIndex(int ii, int tt){
        return this.indexDebR+ii*T+tt;
	}
	
	public int getCIndex(int ii, int jj,int tt){
		int index= this.indexDebC-1;
        for(int i=0;i<nbStages;i++){
        	if(i<ii){
        		index+=nbTezTasks[i]*T;
        	}
        	else{
        		for(int j=0;j<nbTezTasks[i];j++){
        			if(j<jj){
        				index+=T;
        			}
        			else{
        				index+=(tt+1);
        				break;
        			}
        		}
        		break;
        	}
        }
        return index;
	}

	@Override
	public void callback(glp_tree arg0) {
		// TODO Auto-generated method stub
		int reason = GLPK.glp_ios_reason(arg0);	
		
        if (reason == GLPKConstants.GLP_IBINGO || reason == GLPKConstants.GLP_IHEUR) {
            if(GLPK.glp_ios_mip_gap(arg0) <= 0.26){
            	//if (reason == GLPKConstants.GLP_IBINGO) System.out.println(">>>>>>> GLP_IBINGO");
            	//else if (reason == GLPKConstants.GLP_IHEUR) System.out.println(">>>>>>> GLP_IHEUR");
            	bonneSolutionTrouve=true;
                GLPK.glp_ios_terminate(arg0);
            }
            else{
            	//afficher(lp);
            }
        	//System.out.println("GLP_IBINGO >>>>>>>>>>>>>>"+GLPK.glp_mip_obj_val(lp));
        	
            //write_mip_solution(lp);
            //GLPK.glp_ios_terminate(arg0);
        }
	}
}
