/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;


import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author greg1
 */
public class MenuController implements Initializable {

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


    public void switchScene(String name){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(scene);
            stage.show();
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
    private void onclickBtncomdanmnation(MouseEvent event) {
        String name = "NouvelleCondamnation.fxml";
        switchScene(name);
    }

    @FXML
    private void onclickBtnliberation(MouseEvent event) {
        String name = "Liberation.fxml";
        switchScene(name);
    }

    @FXML
    private void onclickBtnreducpeine(MouseEvent event) {
        String name = "reduction_peine.fxml";
        switchScene(name);
    }

    @FXML
    private void onclickBtnpreventif(MouseEvent event) {
        String name = "Prisonnier_Preventif.fxml";
        switchScene(name);
    }

}
