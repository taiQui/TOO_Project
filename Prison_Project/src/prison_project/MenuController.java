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
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
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
         DropShadow shadow = new DropShadow();
        
       
         
         
         
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
