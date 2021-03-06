/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.JAVAFX;
//import com.ThoravalLucchese.prison_project.Program.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author greg1
 */
public class MenuController  implements Initializable {

    @FXML
    private Button btn_newIncar;
    @FXML
    private Font x1;
    @FXML
    private Button btn_condamnation;
    @FXML
    private Button btn_liberation;
    @FXML
    private Button btn_reductionpeine;
    @FXML
    private Button btn_preventif;

    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void stop(){
        System.err.println("Stage is closing");
    }*/
    

    public void switchScene(String name){
        try {
            Stage oldstage = new Stage();
            oldstage = (Stage)btn_newIncar.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(scene);
            oldstage.close();
            scene.getStylesheets().add(MenuController.class.getResource("stylecss.css").toExternalForm());
            stage.setFullScreen(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            System.out.println("TEST1");
            //stage.
            stage.show();
            System.out.println("TEST");
        } catch (IOException ex){
          ex.getCause();
        }
    }

    @FXML
    private void onclickBtnnewIncar(MouseEvent event) {
        String name = "FXML.fxml";
        switchScene(name);
    }


    @FXML
    private void onclickBtnreducpeine(MouseEvent event) {
        String name = "reduction_peine.fxml";
        Stage stage = new Stage();
        Stage oldstage = new Stage();
        
        switchScene(name);
    }

    @FXML
    private void onclickBtnpreventif(MouseEvent event) {
        String name = "Prisonnier_Preventif.fxml";

        switchScene(name);
    }


    

}
