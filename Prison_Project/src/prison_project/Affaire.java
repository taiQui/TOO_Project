/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import javafx.beans.property.StringProperty;

/**
 *
 * @author greg1ol
 */
public class Affaire {
    
    private
            String _n_affaire;
            StringProperty _n_affaireSP;
            java.util.Calendar _date_faits;
            
    Affaire(String affaire, java.util.Calendar date){
        this._n_affaire = affaire;
        this._date_faits = date;
    }
            
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
