/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

/**
 *
 * @author greg1
 */
public class Incarceration {
   
    private
            java.util.Calendar _date_incarceration ;
   
    Incarceration (java.util.Calendar date){
        this._date_incarceration = date;
    }
    
    public java.util.Calendar getDate(){
        return(this._date_incarceration);
    }
    
    public void setDate(java.util.Calendar date){
        this._date_incarceration = date;
    }
   
    
    
}
