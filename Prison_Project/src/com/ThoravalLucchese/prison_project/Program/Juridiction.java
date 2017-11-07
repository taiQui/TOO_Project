/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.Program;

import javafx.beans.property.StringProperty;

/**
 *
 * @author greg1
 */
public class Juridiction {
    
    private
            String _nom_juridiction;
            StringProperty _nom_juridictionSP;
    
   public Juridiction(String juri){
        this._nom_juridiction = juri;
    }
    public String getNom(){
        return(this._nom_juridiction);
    }
    
    public void setNom(String nom){
        this._nom_juridiction = nom;
    }
    
}
