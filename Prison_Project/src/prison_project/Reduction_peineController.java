/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;


import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author greg1
 */


//////////    DATABANK ----- > Detenu Decision ReductionPeine


public class Reduction_peineController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private TableView<Detenu> tableview;
    @FXML
    private Text text_duree_reduc;
   
    @FXML
    private Button btn_ok_reduc;
    @FXML
    private Text test_peine_reduite;
    @FXML
    private Button btn_ok;
    @FXML
    private TextField text_necrou;
    
    @FXML
    private TextField text_fieldtemps;
    
    private bank_database _database;
    
    private int numero_ecrou;

    
        private TableColumn cnom = new TableColumn("Nom");
        private TableColumn cprenom = new TableColumn("Prenom");
        private TableColumn cecrou = new TableColumn("Ecrou");
        private TableColumn cdnaiss = new TableColumn("Date de naissance");
        private TableColumn clnaiss = new TableColumn("Lieu de naissance");
    @FXML
    private ChoiceBox<String> choice =new ChoiceBox<String>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cnom.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_nomFX"));
        cprenom.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_prenomFX"));
        cecrou.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_n_ecrouFX"));
        cdnaiss.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_date_naissanceFX"));
        clnaiss.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_lieu_naissanceFX"));
        tableview.getColumns().addAll(cecrou,cprenom,cnom,cdnaiss,clnaiss);
         
       
        choice.setItems(FXCollections.observableArrayList("Liberation definitive","Reduction de peine","Condamnation"));
        
      
         
        try {
            _database = new bank_database();
        } catch (Exception e){
            e.getCause();
        }
    }    

    @FXML
    private void onClickBtnMenu(MouseEvent event) throws IOException {
            Stage oldstage = new Stage();
            oldstage = (Stage)btnMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            oldstage.close();
            stage.show();
    }

    @FXML
    private void onclickBtn_ok_reduc(MouseEvent event) throws SQLException, ParseException {
        
        if(!text_fieldtemps.getText().isEmpty()){
            
        }

    }

    @FXML
    private void onclickBtn_ok(MouseEvent event) throws SQLException, ParseException {
                if(!text_necrou.getText().isEmpty()){
            System.out.println("test1 : onclickbtnok : reduccontrollerjava");
            System.out.println("Tu va chercher le numero : "+text_necrou.getText());
            ArrayList<Detenu> liste = _database.searchOnDatabase(text_necrou.getText());
            System.out.println("valeur : " + choice.getValue());
           System.out.println("after searchindatabase");
           System.out.println("list : "+ liste.get(0).getEcrou());
            if(liste.get(0).getEcrou() != null){
                System.out.println("test2 : onclickbtnok : reduccontrollerjava");
                ObservableList<Detenu> ajoutable = FXCollections.observableArrayList();
                tableview.setVisible(true);
                ajoutable.add(liste.get(0));
                tableview.setItems(ajoutable);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirme le choix");
                alert.setContentText("Est ce le bon detenu ?");
                ButtonType buttonOK = new ButtonType("oui");
                ButtonType buttonNO = new ButtonType("non");
                alert.getButtonTypes().setAll(buttonOK,buttonNO);
                Optional<ButtonType> result = alert.showAndWait();
                
                if(result.get() == buttonOK && choice.getValue()=="Reduction de peine") {
                    text_duree_reduc.setVisible(true);
                    btn_ok_reduc.setVisible(true);
                    text_fieldtemps.setVisible(true);
                    text_duree_reduc.setDisable(false);
                    btn_ok_reduc.setDisable(false);
                    text_fieldtemps.setDisable(false);
                    numero_ecrou = Integer.parseInt(text_necrou.getText());
                } else if ( result.get() == buttonOK && choice.getValue() == "Liberation definitive") {
                    text_duree_reduc.setDisable(false);
                    text_duree_reduc.setText("Date de liberation");
                    text_duree_reduc.setVisible(true);
                    btn_ok_reduc.setVisible(true);
                    btn_ok_reduc.setDisable(false);
                    text_fieldtemps.setVisible(true);
                    text_fieldtemps.setDisable(false);
                    
                }
                
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERREUR");
                alert.setHeaderText("Aucun prisionnier trouvé avec ce numero d'écrou");
                alert.setContentText("ERROR 404 NOT FOUND");
                alert.showAndWait();
            }
        }
    }
    
}
