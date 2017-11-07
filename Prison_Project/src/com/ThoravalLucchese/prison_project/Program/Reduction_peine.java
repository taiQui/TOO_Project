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
public class Reduction_peine extends Decision {
    
    private
            int _duree;
            StringProperty _dureeSP;
    
    public int getDuree(){
        return(this._duree);
    }
    
    public void setDuree(int duree){
        this._duree = duree;
    }
}
