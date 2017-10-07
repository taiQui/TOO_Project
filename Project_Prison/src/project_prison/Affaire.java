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
public class Affaire {
    
    private
            String _n_affaire;
            java.util.Calendar _date_faits;
        
    public String getAffaire(){
        return(this._n_affaire);
    }
    
    public java.util.Calendar getDate(){
        return(this._date_faits);
    }
    
    public void setAffaire(String affaire){
        this._n_affaire = affaire;
    }
    
    public void setDate(java.util.Calendar date){
        this._date_faits = date;
    }
    
}
