/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_prison;

/**
 *
 * @author greg1
 */
public class Motif {
    
    private
            String _n_motif;
            String _libelle_motif;
     
    public String getMotif(){
        return(this._n_motif);
    }
    
    public String getLMotif(){
        return(this._libelle_motif);
    }
    
    public void setMotif(String motif){
        this._n_motif = motif;
    }
    
    public void setLMotif(String lmotif){
        this._libelle_motif = lmotif;
    }
}
