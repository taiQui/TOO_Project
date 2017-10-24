/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
           
            String _lieu_naissance;
            
            
    Detenu(){
            _n_ecrou = "";
            _prenom  = "";
            _nom = "";
            _date_naissance = Calendar.getInstance();
            _lieu_naissance = "";
    }        
            
    Detenu(String ecrou,String prenom,String nom, java.util.Calendar date_naiss,String lieu_naiss){
        
        this._n_ecrou = ecrou;
        //this._n_ecrouFX = new SimpleStringProperty(ecrou);
        this._prenom = prenom;
       // this._prenomFX = new SimpleStringProperty(prenom);
        this._nom = nom;
       // this._nomFX = new SimpleStringProperty(nom);
        this._date_naissance = date_naiss;
       // this._date_naissanceFX = new SimpleStringProperty(sdf.format(date_naiss.getTime()));
        this._lieu_naissance = lieu_naiss;
       // this._lieu_naissanceFX = new SimpleStringProperty(lieu_naiss);
        
    }        
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
            
    public String getLieuNaiss(){
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
    
    public void setLieuNaiss(String date ){
        this._lieu_naissance = date;
    }
}
