/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.JAVAFX;

import com.ThoravalLucchese.prison_project.Program.Detenu;
import com.ThoravalLucchese.prison_project.Program.bank_database;
import com.ThoravalLucchese.prison_project.Program.Affaire;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg1
 */

public class Prisionnier_PreventifController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private TableView<Detenu> tableview ;
    @FXML
    private Button btn_voir;

    private bank_database _database;
    final java.util.Calendar cal = Calendar.getInstance();


    public TableColumn colonneDateNaiss = new TableColumn("Date de naissance");
    public  TableColumn colonneLieuxNaiss = new TableColumn("Lieu de naissance");
    public TableColumn colonneEcrou = new TableColumn("Ecrou");
    public TableColumn colonneNom = new TableColumn("Nom");
    public TableColumn colonnePrenom = new TableColumn("Prenom");
    
    
    public TableColumn colonneDateNaiss2 = new TableColumn("Date de naissance");
    public  TableColumn colonneLieuxNaiss2 = new TableColumn("Lieu de naissance");
    public TableColumn colonneEcrou2 = new TableColumn("Ecrou");
    public TableColumn colonneNom2 = new TableColumn("Nom");
    public TableColumn colonnePrenom2 = new TableColumn("Prenom");
    
    
    public TableColumn colonneNAffaire = new TableColumn("Numero d'affaire");
    public  TableColumn colonneNjuridiction = new TableColumn("Nom de juridiction");
    public TableColumn colonnedatefais = new TableColumn("Date des faits");

    
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<String>();
    @FXML
    private Button btn_stat;
    @FXML
    private TableView<Affaire> tableviewAffaire;
    @FXML
    private TableView<Detenu> tableviewDetAff;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("inPrisonPreventif");
        
        tableview.setEditable(true);

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

        
        
        tableviewAffaire.setEditable(true);

        colonneNAffaire.setCellValueFactory(new PropertyValueFactory<Affaire,String>("_n_affaire"));
        colonneNAffaire.setStyle("-fx-text-fill: black;");
        
        colonneNjuridiction.setCellValueFactory(new PropertyValueFactory<Affaire,String>("_juridiction"));
        colonneNjuridiction.setStyle("-fx-text-fill: black;");
        
        colonnedatefais.setCellValueFactory(new PropertyValueFactory<Affaire,String>("_date_faitsString"));
        colonnedatefais.setStyle("-fx-text-fill: black;");
    
        tableviewAffaire.getColumns().addAll(colonneNAffaire,colonneNjuridiction,colonnedatefais);
        
        
        
        
        colonneDateNaiss2.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_date_naissanceFX"));
        colonneDateNaiss2.setStyle("-fx-text-fill: black;");
        
        colonneLieuxNaiss2.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_lieu_naissanceFX"));
        colonneLieuxNaiss2.setStyle("-fx-text-fill: black;");
        
        colonneEcrou2.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_n_ecrouFX"));
        colonneEcrou2.setStyle("-fx-text-fill: black;");
        
        colonneNom2.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_nomFX"));
        colonneNom2.setStyle("-fx-text-fill: black;");

        colonnePrenom2.setCellValueFactory(new PropertyValueFactory<Detenu,String>("_prenomFX"));
        colonnePrenom2.setStyle("-fx-text-fill: red;");
        
        tableviewDetAff.getStyleClass().add("Times New Roman");
        tableviewDetAff.setStyle("-fx-text-fill: black;");
       
        tableviewDetAff.getColumns().addAll(colonneEcrou2,colonnePrenom2,colonneNom2,colonneDateNaiss2,colonneLieuxNaiss2);
        tableviewDetAff.setVisible(false);
        tableviewDetAff.setDisable(true);
        
        choiceBox.setItems(FXCollections.observableArrayList("Preventif","Tous","Affaire"));
        choiceBox.setValue("Tous");
       // barchart.setDisable(false);
       
        System.out.println("JE PASSE ICI");
        tableviewAffaire.setVisible(false);
        tableview.setVisible(true);
        tableview.setDisable(false);
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
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }
    

 

    private void show() throws SQLException, ParseException {
        
       
        ArrayList<Detenu> liste;
        ArrayList<Affaire> listeAffaire;
        
        ObservableList<Detenu>  ajoutable = FXCollections.observableArrayList();
        ObservableList<Affaire>  ajoutable2 = FXCollections.observableArrayList();
       
       
        System.out.println("TEST 1 : " +choiceBox.getValue().equals("Preventif") + " TEST 2 : "+ choiceBox.getValue().equals("Tous") + " TEST 3 : "+choiceBox.getValue().equals("Affaire") + " TEST 4 : "+(choiceBox.getValue().equals("Tous") || choiceBox.getValue().equals("Preventif")));
        
        if(choiceBox.getValue().equals("Preventif")){
             liste = _database.getArray(1);
             listeAffaire = null;
        } else if(choiceBox.getValue().equals("Tous")) {
             liste = _database.getArray(3);
             listeAffaire = null;
        } else if(choiceBox.getValue().equals("Affaire")){
            listeAffaire = _database.getArrays();
            liste = null;
        } else {
            liste = null;
            listeAffaire= null;
        }
       
        
        if(choiceBox.getValue().equals("Tous") || choiceBox.getValue().equals("Preventif") ) {
         liste.forEach((d) -> {
            ajoutable.add(d);
        }); 
        } else {
            
            listeAffaire.forEach((d) -> {
                ajoutable2.add(d);
            });

        }

      
            if(liste != null && listeAffaire == null){
                tableview.setVisible(true);
                tableview.setDisable(false);
                tableviewAffaire.setVisible(false);
                tableviewAffaire.setDisable(true);
                tableviewDetAff.setVisible(false);
                tableviewDetAff.setDisable(true);
                tableview.setItems(ajoutable);
                
            } else {
                tableviewAffaire.setVisible(true);
                tableviewAffaire.setDisable(false);
                tableview.setVisible(false);
                tableview.setDisable(true);
                tableviewAffaire.setItems(ajoutable2);
            }

    }
    
    
    
    @FXML
    private void clickbtnvoir(MouseEvent event) throws SQLException, ParseException {
        show();
    }
    
    //Experimental
    public void switchScene(String name){         // changement de scene classic
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
            stage.setFullScreen(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.show();
        } catch (IOException ex){
          ex.getCause();
        }
    }
    
    public void switchScene2(String name,String text) throws SQLException, ParseException{ // changement de scene en passant un parametre qui est le numero d'ecrou pour ouvrir directement le fichier fxml avec directement toutes les informations correspondant a un numero d'ecrou
        try {
            System.out.println("name: " + name);
            System.out.println("text:" + text);
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
            stage.setFullScreen(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
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
                Detenu item = (Detenu) tableview.getItems().get(row);
                System.out.println("item :"+item.getEcrou());
                
               
                try {
                   // cont.read(item.getEcrou());
                    switchScene2("FXML.fxml",String.valueOf(item.getEcrou()));
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
                Detenu item = (Detenu) tableview.getItems().get(row);
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

    @FXML
    private void onclickBtnstats(MouseEvent event) throws SQLException {

        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("année");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de prisonnier");
        
        BarChart barchart = new BarChart(xAxis,yAxis);
       
        XYChart.Series data = new XYChart.Series();
        data.setName("Annee");
        
        ArrayList<String> list = _database.getYearIncar();

        ArrayList<String> tabA = new ArrayList<String>();
        ArrayList<Integer> tabCount = new ArrayList<Integer>();
        
        for(int i = 0; i < list.size() ; i++ ) {
            //System.out.println("liste : "+list.get(i));
            if(!tabA.contains(list.get(i))){
                tabA.add(list.get(i));
                tabCount.add(1);
            } else {
                tabCount.set(tabA.indexOf(list.get(i)),tabCount.get(tabA.indexOf(list.get(i)))+1);
            }
        }
        
        //System.out.println("TAILLE TABA : "+tabA.size());
        
        tabA.sort(new Comparator<String>(){
            @Override
            public int compare(String s1,String s2){
                return(s1.compareTo(s2));
            }
        });
        
        for(int i = 0; i < tabA.size(); i++)    
            data.getData().add(new XYChart.Data(tabA.get(i),tabCount.get(i)));    
        
        barchart.getData().add(data);
        
        barchart.setId("barchart");
        barchart.setStyle("-fx-bar-fill: navy;");
       // barchart.getStylesheets().add(MenuController.class.getResource("stylecss.css").toExternalForm());
        VBox vbox = new VBox(barchart);
        Scene scene = new Scene(vbox,500,300);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Statistiques");
        stage.initOwner(btn_voir.getScene().getWindow());
        stage.showAndWait();
        
        
        
        
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
    private void onContextMenuRequested(ContextMenuEvent event) {
        final ContextMenu tableContextMenu = new ContextMenu();
        final MenuItem find = new MenuItem("Afficher les detenus de cette affaires");
        final MenuItem delete = new MenuItem("Suppimer l'affaire");
        tableContextMenu.getItems().addAll(find,delete);
        tableviewAffaire.setVisible(true);
        tableviewAffaire.setDisable(false);
        tableviewAffaire.setContextMenu(tableContextMenu);
        
        find.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                TablePosition pos = tableviewAffaire.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Affaire item = (Affaire) tableviewAffaire.getItems().get(row);
                
                //System.out.println("item :"+item.get_n_affaire());
                ObservableList<Detenu>  ajoutable = FXCollections.observableArrayList();
                ArrayList<Detenu> liste = new ArrayList<Detenu>();
                try {
                     liste = _database.getAllPrisonnerInCase(item.get_n_affaire(),item.get_juridiction());
                } catch (SQLException ex) {
                    Logger.getLogger(Prisionnier_PreventifController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                liste.forEach((d) -> {
                    ajoutable.add(d);
                }); 
                
                tableviewDetAff.setVisible(true);
                tableviewDetAff.setDisable(false);
                tableviewDetAff.setItems(ajoutable);
                
                
            }
        });
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                TablePosition pos = tableviewAffaire.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Affaire item = (Affaire) tableviewAffaire.getItems().get(row);
                
                try {
                    if(_database.CanDelete(item.get_n_affaire(),item.get_juridiction())){
                        _database.deleteAffaire(item.get_n_affaire(),item.get_juridiction());
                        newW("Succès","L'affaire à ete supprimer","");
                        show();
                    } else {
                        newW("Erreur","Affaire non supprimé","L'affaire est encore liée a des detenus");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Prisionnier_PreventifController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Prisionnier_PreventifController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
   }

}
