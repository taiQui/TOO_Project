/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
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

    private void show() throws SQLException, ParseException {
        ArrayList<Detenu> liste;
        ObservableList<Detenu>  ajoutable = FXCollections.observableArrayList();
        if(choiceBox.getValue().equals("Preventif")){
             liste = _database.getArray(1);
            for(Detenu d : liste){
                
                 ajoutable.add(d);

            }

            
        } else if (choiceBox.getValue().equals("Tous")){
            liste = _database.getArray(3);
            for(Detenu d : liste){

               ajoutable.add(d);

            }
        }
            
        tableview.setItems(ajoutable);
 
    }
    
    @FXML
    private void clickbtnvoir(MouseEvent event) throws SQLException, ParseException {
        show();
    }
    
    //Experimental
    public void switchScene(String name){
        try {
            Stage oldstage = new Stage();
            oldstage = (Stage)tableview.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(scene);
            oldstage.close();
            scene.getStylesheets().add(MenuController.class.getResource("stylecss.css").toExternalForm());
            stage.show();
        } catch (IOException ex){
          ex.getCause();
        }
    }
    
    public void switchScene2(String name,String text) throws SQLException, ParseException{
        try {
            Stage oldstage = new Stage();
            oldstage = (Stage)tableview.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(name));
            System.out.println("testAVANT");
            Scene scene = new Scene(fxmlLoader.load());
            System.out.println("testAPRES");
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(scene);
            oldstage.close();
            scene.getStylesheets().add(MenuController.class.getResource("stylecss.css").toExternalForm());
           // FXMLLoader control = new FXMLLoader(getClass().getResource("FXML.fxml"));
            FXMLController cont = fxmlLoader.<FXMLController>getController();
            System.out.println("test1");
            cont.getE(text);
            System.out.println("test2");
            stage.show();
        } catch (IOException ex){
          ex.getCause();
        }
    }
    @FXML
    private void onContextMenuClicked(ContextMenuEvent event) {
        final ContextMenu tableContextMenu = new ContextMenu();
        final MenuItem addPrisoner = new MenuItem("Ajouter un nouveau detenu");
        final MenuItem modifyPrisoner = new MenuItem("Modifier ce detenu");
        final MenuItem deletePrisoner = new MenuItem("Supprimer ce detenu");
        tableContextMenu.getItems().addAll(addPrisoner, modifyPrisoner,deletePrisoner);
        tableview.setContextMenu(tableContextMenu);

        addPrisoner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("On change de page pour aller sur celle de création");
                try{
                    switchScene("FXML.fxml");
                    
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("echec");
                }

            }
        });
        
        modifyPrisoner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("On ouvre la page de modification avec les informations préremplies");
                //System.out.println(tableview.getSelectionModel().getSelectedItems());
                TablePosition pos = tableview.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Detenu item = tableview.getItems().get(row);
                System.out.println("item :"+item.getEcrou());
                
               
                try {
                   // cont.read(item.getEcrou());
                    switchScene2("FXML.fxml",item.getEcrou());
                } catch (SQLException ex) {
                    Logger.getLogger(Prisionnier_PreventifController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Prisionnier_PreventifController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    //Trouver un moyen pour ecrire dans une nouvelle scène
                    
                    
                    
            
                   
                
            }
        });
        
        deletePrisoner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("On supprime directement le detenu");
                TablePosition pos = tableview.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Detenu item = tableview.getItems().get(row);
                System.out.println(item.getEcrou());
                try{
                    _database.DeletePrisonnier(item.getEcrou());
                    show();
                }catch(SQLException e){
                    e.printStackTrace();
                }catch(ParseException e2){
                    e2.printStackTrace();
                }
            }
        });
    }

}
