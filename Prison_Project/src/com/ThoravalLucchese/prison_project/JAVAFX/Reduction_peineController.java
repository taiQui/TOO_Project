/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.JAVAFX;

import com.ThoravalLucchese.prison_project.Program.Detenu;
import com.ThoravalLucchese.prison_project.Program.bank_database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author greg1
 * 
 */

public class Reduction_peineController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private TableView<Detenu> tableview;
    @FXML
    private Text text_duree_reduc;
   
    @FXML
    private Button btn_ok_reduc;
    
    private Text test_peine_reduite;
    @FXML
    private Button btn_ok;
    @FXML
    private TextField text_necrou;
    
    @FXML
    private TextField text_fieldtemps;
    
    private bank_database _database;
    
    private String numero_ecrou;

    
    private TableColumn cnom = new TableColumn("Nom");
    private TableColumn cprenom = new TableColumn("Prenom");
    private TableColumn cecrou = new TableColumn("Ecrou");
    private TableColumn cdnaiss = new TableColumn("Date de naissance");
    private TableColumn clnaiss = new TableColumn("Lieu de naissance");
        
    private TableColumn cnomR = new TableColumn("Nom");
    private TableColumn cprenomR = new TableColumn("Prenom");
    private TableColumn cecrouR = new TableColumn("Ecrou");
    private TableColumn cdnaissR = new TableColumn("Date de naissance");
    private TableColumn clnaissR = new TableColumn("Lieu de naissance");
        
    @FXML
    private ChoiceBox<String> choice =new ChoiceBox<String>();
    @FXML
    private ProgressIndicator indicator;
    @FXML
    private TextField text_oldDate;
    @FXML
    private TextField text_newDate;
    @FXML
    private Text text_fleche;
    @FXML
    private TableView<Detenu> tableviewRecherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //premiere tableview
        cnom.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_nomFX"));
        cprenom.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_prenomFX"));
        cecrou.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_n_ecrouFX"));
        cdnaiss.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_date_naissanceFX"));
        clnaiss.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_lieu_naissanceFX"));
        tableview.getColumns().addAll(cecrou,cprenom,cnom,cdnaiss,clnaiss);
         
        //deuxieme tableview
        cnomR.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_nomFX"));
        cprenomR.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_prenomFX"));
        cecrouR.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_n_ecrouFX"));
        cdnaissR.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_date_naissanceFX"));
        clnaissR.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_lieu_naissanceFX"));
        tableviewRecherche.getColumns().addAll(cecrouR,cprenomR,cnomR,cdnaissR,clnaissR);
        
       
        choice.setItems(FXCollections.observableArrayList("Reduction de peine","Condamnation"));
        
        text_oldDate.setVisible(false);
        text_newDate.setVisible(false);
        text_fleche.setVisible(false);
        indicator.setVisible(false);
        tableviewRecherche.setVisible(false);
        tableviewRecherche.setDisable(true);
         
        choice.setValue("Condamnation");
        
        try {
            _database = new bank_database();
        } catch (Exception e){
            e.getCause();
        }
    }    

    
        public void newW(String titre, String header, String content){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.initOwner(tableview.getScene().getWindow());
        alert.showAndWait();
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
            scene.getStylesheets().add(MenuController.class.getResource("stylecss.css").toExternalForm());
            stage.setFullScreen(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.show();
    }

    @FXML
    private void onclickBtn_ok_reduc(MouseEvent event) throws SQLException, ParseException {
        
        if(!text_fieldtemps.getText().isEmpty()){
            switch(choice.getValue()){
                case "Reduction de peine":
                    if(text_fieldtemps.getText().matches("[-+]?\\d*\\.?\\d+")){
                        _database.reducPeine(Integer.parseInt(text_fieldtemps.getText()), numero_ecrou, indicator,text_oldDate, text_newDate);
                         indicator.setProgress(indicator.getProgress()+0.2f);
                         
                    } else {
                        newW("Erreur","Vous n'avez pas rentré un chiffre","La modification est refusé");
                    }
                    break;
                case "Condamnation":
                    if(text_fieldtemps.getText().matches("[-+]?\\d*\\.?\\d+"))
                        _database.condamnation(Integer.parseInt(text_fieldtemps.getText()), numero_ecrou, indicator);
                    else
                        newW("Erreur","Vous n'avez pas rentré un chiffre","La modification est refusé");
                    
                    break;
            }
        }

    }

    @FXML
    private void onclickBtn_ok(MouseEvent event) throws SQLException, ParseException {
           if(!text_necrou.getText().isEmpty()){
            ArrayList<Detenu> liste = new ArrayList<>();
            if(choice.getValue().equals("Condamnation"))
                liste = _database.searchOnDatabase(text_necrou.getText(),1);
            else if(choice.getValue().equals("Reduction de peine"))
                liste = _database.searchOnDatabase(text_necrou.getText(),2);
            else
                liste.clear();
            if(!liste.isEmpty()){
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
                alert.initOwner(tableview.getScene().getWindow());
                Optional<ButtonType> result = alert.showAndWait();
                numero_ecrou = text_necrou.getText();
                if(result.get() == buttonOK && choice.getValue()=="Reduction de peine") {
                    text_duree_reduc.setVisible(true);
                    btn_ok_reduc.setVisible(true);
                    text_duree_reduc.setText("Reduction de peine ( en mois )");
                    text_fieldtemps.setVisible(true);
                    text_duree_reduc.setDisable(false);
                    btn_ok_reduc.setDisable(false);
                    text_fieldtemps.setDisable(false);
                    indicator.setVisible(true);
                    text_oldDate.setVisible(true);
                    text_newDate.setVisible(true);
                    text_fleche.setVisible(true);
                } else if ( result.get() == buttonOK && choice.getValue() == "Condamnation"){
                    text_duree_reduc.setDisable(false);
                    text_duree_reduc.setText("Condamnation ( en mois )");
                    text_duree_reduc.setVisible(true);
                    btn_ok_reduc.setVisible(true);
                    btn_ok_reduc.setDisable(false);
                    text_fieldtemps.setVisible(true);
                    text_fieldtemps.setDisable(false);
                    indicator.setVisible(true);
                }
                
            } else {
                System.out.println("je connais pas !");
                text_duree_reduc.setDisable(true);
                text_duree_reduc.setVisible(false);
                btn_ok_reduc.setVisible(false);
                btn_ok_reduc.setDisable(true);
                text_fieldtemps.setVisible(false);
                text_fieldtemps.setDisable(true);
                indicator.setProgress(0.0f);
                indicator.setVisible(false);
                newW("ERREUR","Aucun prisionnier trouvé avec ce numero d'écrou","ERROR 404 NOT FOUND");
            }
        }
    }

    @FXML
    private void onRecherchePredictive(KeyEvent event) throws SQLException {
        ArrayList<Detenu> liste = _database.RecherchePredictive(text_necrou.getText());
        ObservableList<Detenu> ajoutable = FXCollections.observableArrayList();
        if(liste.size()>= 1){
            for(Detenu d : liste){
                ajoutable.add(d);
            }
            tableviewRecherche.setVisible(true);
            tableviewRecherche.setDisable(false);
            tableviewRecherche.setItems(ajoutable);

        } else {
            tableviewRecherche.setVisible(false);
            tableviewRecherche.setDisable(true);
        }
        if(text_necrou.getText().isEmpty()){
            tableviewRecherche.setVisible(false);
            tableviewRecherche.setDisable(true);
        }
    }

    @FXML
    private void onContextMenuClicked(ContextMenuEvent event) {
        final ContextMenu tableContextMenu = new ContextMenu();
        final MenuItem recuperer = new MenuItem("recuperer le numero d'ecrou");
        tableContextMenu.getItems().addAll(recuperer);
        tableviewRecherche.setContextMenu(tableContextMenu);

        recuperer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableviewRecherche.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Detenu item = tableviewRecherche.getItems().get(row);
                text_necrou.setText(item.getEcrou());
            }
        });
    }

    
}
