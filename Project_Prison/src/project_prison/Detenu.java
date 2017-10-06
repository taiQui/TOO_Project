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
public class Detenu {
    
    private
            String _n_ecrou;
            String _prenom;
            String _nom;
            java.util.Calendar _date_naissance;
            java.util.Calendar _lieu_naissance;
            
            
    //All getter
    public String getEcrou() {
        return(this._n_ecrou);
    }
    
    public String getPrenom(){
        return(this._prenom);
    }
    
    public String getNom(){
        return(this._nom);
    }
    
    public java.util.Calendar getDNaiss(){
        return(this._date_naissance);
    }
            
    public java.util.Calendar getLieuNaiss(){
        return(this._lieu_naissance);
    }
    
    //All setter
    public void setEcrou(String ecrou){
        this._n_ecrou = ecrou;
    }
    
    public void setPrenom(String prenom){
        this._prenom = prenom;
    }
    
    public void setNom(String nom){
        this._nom = nom;
    }
    
    public void setDNaiss(java.util.Calendar date){
        this._date_naissance = date;
    }
    
    public void setLieuNaiss(java.util.Calendar date ){
        this._lieu_naissance = date;
    }
}
