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
            SimpleStringProperty _n_ecrouFX;
            String _prenom;
            SimpleStringProperty _prenomFX;
            String _nom;
            SimpleStringProperty _nomFX;
            java.util.Calendar _date_naissance;
            SimpleStringProperty _date_naissanceFX;
            String _lieu_naissance;
            SimpleStringProperty _lieu_naissanceFX;
            
            
     
    Detenu(){
            String _n_ecrou ="";
            StringProperty _n_ecrouFX = new SimpleStringProperty("");
            String _prenom = "";
            StringProperty _prenomFX= new SimpleStringProperty("");
            String _nom="";
            StringProperty _nomFX= new SimpleStringProperty("");
            java.util.Calendar _date_naissance = Calendar.getInstance();
            StringProperty _date_naissanceFX= new SimpleStringProperty("");
            String _lieu_naissance = "";
            StringProperty _lieu_naissanceFX= new SimpleStringProperty("");
    }        
            
    Detenu(String ecrou,String prenom,String nom, java.util.Calendar date_naiss,String lieu_naiss){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this._n_ecrou = ecrou;
        this._n_ecrouFX = new SimpleStringProperty(ecrou);
        this._prenom = prenom;
        this._prenomFX = new SimpleStringProperty(prenom);
        this._nom = nom;
        this._nomFX = new SimpleStringProperty(nom);
        this._date_naissance = date_naiss;
        this._date_naissanceFX = new SimpleStringProperty(sdf.format(date_naiss.getTime()));
        this._lieu_naissance = lieu_naiss;
        this._lieu_naissanceFX = new SimpleStringProperty(lieu_naiss);
        
    }        
    //All getter
    public String getEcrou() {
        return(this._n_ecrou);
    }
    
    public String get_n_ecrouFX(){
        return this._n_ecrouFX.get();
    }
    
    public String getPrenom(){
        return(this._prenom);
    }
    
    public String get_prenomFX(){
        return this._prenomFX.get();
    }
    
    public String getNom(){
        return(this._nom);
    }
    
    public String get_nomFX(){
        return this._nomFX.get();
    }
    
    public java.util.Calendar getDNaiss(){
        return(this._date_naissance);
    }
    
    public String get_date_naissanceFX(){
        return this._date_naissanceFX.get();
    }
            
    public String getLieuNaiss(){
        return(this._lieu_naissance);
    }
    
    public String get_lieu_naissanceFX(){
        return this._lieu_naissanceFX.get();
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
