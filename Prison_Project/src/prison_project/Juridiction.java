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
public class Juridiction {
    
    private
            String _nom_juridiction;
    
    Juridiction(String juri){
        this._nom_juridiction = juri;
    }
    public String getNom(){
        return(this._nom_juridiction);
    }
    
    public void setNom(String nom){
        this._nom_juridiction = nom;
    }
    
}
