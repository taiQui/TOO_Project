/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.text.SimpleDateFormat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author greg1
 */
public class DetenuFX {
    
    StringProperty _n_ecrouFX;
    StringProperty _prenomFX;
    StringProperty _nomFX;
    StringProperty _date_naissanceFX;
    StringProperty _lieu_naissanceFX;
    
    DetenuFX(Detenu D){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this._n_ecrouFX = new SimpleStringProperty(D.getEcrou());
        this._prenomFX = new SimpleStringProperty(D.getPrenom());
        this._nomFX = new SimpleStringProperty(D.getNom());
        this._lieu_naissanceFX = new SimpleStringProperty(D.getLieuNaiss());
        this._date_naissanceFX = new SimpleStringProperty(sdf.format(D.getDNaiss().getTime()));
        System.out.println("detenufx");
    }
    
    public StringProperty getEcrou(){
        return this._n_ecrouFX;
    }
    
    public StringProperty getNom(){
        return this._nomFX;
    }
    
    public StringProperty getPrenom(){
        return this._prenomFX;
    }
    
    public StringProperty getLieuNaiss(){
        return this._lieu_naissanceFX;
    }
    
    public StringProperty getDNaiss(){
        return this._date_naissanceFX;
    }
    
    
    
}
