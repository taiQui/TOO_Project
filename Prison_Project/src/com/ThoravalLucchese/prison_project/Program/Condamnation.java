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
 * 
 * Class Condamnation
 * 
 */
public class Condamnation extends Decision {
    
    private
            int _duree;
            StringProperty _dureeSP;
    
    public int getDuree(){
        return(this._duree);
    }
    
    public void setDuree(int dur){
        this._duree = dur;
    }
    
}
