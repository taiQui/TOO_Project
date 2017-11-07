/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.Program;

/**
 *
 * @author greg1
 */
public class Liberation_definitive extends Decision {
    
    private 
            java.util.Calendar _date_liberation;
    
    public java.util.Calendar getDate(){
        return(this._date_liberation);
    }
    
    public void setDate(java.util.Calendar date){
        this._date_liberation = date;
    }
    
}
