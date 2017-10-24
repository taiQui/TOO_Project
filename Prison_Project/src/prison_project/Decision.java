/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import javafx.beans.property.StringProperty;

/**
 *
 * @author greg1
 */
public class Decision {
    
    private
            int _n_type_decision;
            StringProperty _n_type_decisionSP;
            java.util.Calendar _date_decision;
    
    public int getTypeD(){
        return(this._n_type_decision);
    }
    
    public java.util.Calendar getDate(){
        return(this._date_decision);
    }
    
    public void setNDec(int dec){   
        this._n_type_decision = dec;
    }
    
    public void setDate(java.util.Calendar date){
        this._date_decision = date;
    }
}
