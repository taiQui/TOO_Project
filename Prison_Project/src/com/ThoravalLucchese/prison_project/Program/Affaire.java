/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.Program;

import javafx.beans.property.StringProperty;

/**
 *
 * @author greg
 * 
 * Class Affaire
 * 
 */
public class Affaire {
    
    private
            String _n_affaire;
            String _juridiction;
            java.util.Calendar _date_faits;
            
    public Affaire(String affaire,String juridiction, java.util.Calendar date){
        this._n_affaire = affaire;
        this._date_faits = date;
        this._juridiction = juridiction;
    }
    
    public String get_juridiction(){
        return _juridiction;
    }
            
    public String get_n_affaire(){
        return(this._n_affaire);
    }
    
    public java.util.Calendar get_date_faits(){
        return(this._date_faits);
    }
    
    public void setAffaire(String affaire){
        this._n_affaire = affaire;
    }
    
    public void setDate(java.util.Calendar date){
        this._date_faits = date;
    }
    
}
