/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg1
 */

/////// DATABANK  --------> Detenu (Comdamnation == rien )

public class Prisionnier_PreventifController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private TableView<Detenu> tableview = new TableView<Detenu>();
    @FXML
    private Button btn_voir;

    private bank_database _database;
    final java.util.Calendar cal = Calendar.getInstance();


    public TableColumn colonneDateNaiss = new TableColumn("Date de naissance");
    public  TableColumn colonneLieuxNaiss = new TableColumn("Lieu de naissance");
    public TableColumn colonneEcrou = new TableColumn("Ecrou");
    public TableColumn colonneNom = new TableColumn("Nom");
    public TableColumn colonnePrenom = new TableColumn("Prenom");
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<String>();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("inPrisonPreventif");
        //tableview.setEditable(true);

        colonneDateNaiss.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_date_naissanceFX"));
        colonneDateNaiss.setStyle("-fx-text-fill: black;");
        
        colonneLieuxNaiss.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_lieu_naissanceFX"));
        colonneLieuxNaiss.setStyle("-fx-text-fill: black;");
        
        colonneEcrou.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_n_ecrouFX"));
        colonneEcrou.setStyle("-fx-text-fill: black;");
        
        colonneNom.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_nomFX"));
        colonneNom.setStyle("-fx-text-fill: black;");

        colonnePrenom.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_prenomFX"));
        colonnePrenom.setStyle("-fx-text-fill: red;");
        
        tableview.getStyleClass().add("Times New Roman");
        tableview.setStyle("-fx-text-fill: black;");
        
    
        tableview.getColumns().addAll(colonneEcrou,colonnePrenom,colonneNom,colonneDateNaiss,colonneLieuxNaiss);

        choiceBox.setItems(FXCollections.observableArrayList("Preventif","Tous"));
        choiceBox.setValue("Tous");
        
        try {
            _database = new bank_database();
        }catch (Exception e1){
            e1.getCause();
        }
    }

    @FXML
    private void onClickBtnMenu(MouseEvent event) throws IOException {
        Stage oldstage = (Stage) btnMenu.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        oldstage.close();
        scene.getStylesheets().add(MenuController.class.getResource("stylecss.css").toExternalForm());
        stage.show();
    }

    @FXML
    private void clickbtnvoir(MouseEvent event) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Detenu> liste;
        ObservableList<Detenu>  ajoutable = FXCollections.observableArrayList();
        if(choiceBox.getValue().equals("Preventif")){
             liste = _database.getArray(1);

            //rs.beforeFirst();
            for(Detenu d : liste){

              
                System.out.println("TIME2 : "+sdf.format(d.getDNaiss().getTime()));
                 ajoutable.add(d);

            }

            
        } else if (choiceBox.getValue().equals("Tous")){
            liste = _database.getArray(3);

            //rs.beforeFirst();
            for(Detenu d : liste){

              
                System.out.println("TIME2 : "+sdf.format(d.getDNaiss().getTime()));
               ajoutable.add(d);

            }
        }
            
        tableview.setItems(ajoutable);
 
    }

}
