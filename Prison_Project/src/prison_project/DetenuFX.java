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

    SimpleStringProperty _n_ecrouFX;
    SimpleStringProperty _prenomFX;
    SimpleStringProperty _nomFX;
    SimpleStringProperty _date_naissanceFX;
    SimpleStringProperty _lieu_naissanceFX;

    DetenuFX(Detenu D){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this._n_ecrouFX = new SimpleStringProperty(D.getEcrou());
        this._prenomFX = new SimpleStringProperty(D.getPrenom());
        this._nomFX = new SimpleStringProperty(D.getNom());
        this._lieu_naissanceFX = new SimpleStringProperty(D.getLieuNaiss());
        this._date_naissanceFX = new SimpleStringProperty(sdf.format(D.getDNaiss().getTime()));
        System.out.println("detenufx");
    }


    public String get_n_ecrouFX(){
        return this._n_ecrouFX.get();
    }

    // public StringProperty getEcrouProperty(){
    //     return this._n_ecrouFX;
    // }

    public String get_nomFX(){
        return this._nomFX.get();
    }

    // public StringProperty getNomProperty(){
    //     return this._nomFX;
    // }

    public String get_prenomFX(){
        return this._prenomFX.get();
    }

    // public StringProperty getPrenomProperty(){
    //     return this._prenomFX;
    // }

    public String get_lieu_naissanceFX(){
        return this._lieu_naissanceFX.get();
    }

    // public StringProperty getLieuNaissProperty(){
    //     return this._lieu_naissanceFX;
    // }

    public String get_date_naissanceFX(){
        return this._date_naissanceFX.get();
    }

    // public StringProperty getDNaissProperty(){
    //     return this._date_naissanceFX;
    // }

    // public void setNom (SimpleStringProperty f){
    //     this._nomFX = f;
    // }

}
