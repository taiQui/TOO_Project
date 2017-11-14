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
import java.util.Calendar;
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
    @FXML
    private Button btn_stat;
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
       // barchart.setDisable(false);
       
        System.out.println("JE PASSE ICI");
//        CategoryAxis xAxis = new CategoryAxis();
//        NumberAxis yAxis = new NumberAxis();
//        barchart = new BarChart(xAxis,yAxis);
//        barchart.setTitle("Statistique d'ajouts");
//        xAxis.setLabel("Année");       
//        yAxis.setLabel("Nombre de prisonnier");
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("Annee");
//        series1.getData().add(new XYChart.Data("1998", 50.0));
//        series1.getData().add(new XYChart.Data("1999", 100));
//        series1.getData().add(new XYChart.Data("2000", 58));
//        
//        barchart.getData().add(series1);
//
//        VBox vbox = new VBox(barchart);
//        Scene scene = new Scene(vbox,400,200);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.showAndWait();
        
        //actualiseChart();
        try {
            _database = new bank_database();
        }catch (Exception e1){
            e1.getCause();
        }
    }

    
    private void actualiseChart() {

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
        ObservableList<Detenu>  ajoutable = FXCollections.observableArrayList();
        if(choiceBox.getValue().equals("Preventif"))
             liste = _database.getArray(1);
        else 
             liste = _database.getArray(3);
            
        liste.forEach((d) -> {
            ajoutable.add(d);
        });
        
            
        tableview.setItems(ajoutable);
        
 
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
                Detenu item = tableview.getItems().get(row);
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
            System.out.println("liste : "+list.get(i));
            if(!tabA.contains(list.get(i))){
                tabA.add(list.get(i));
                tabCount.add(1);
            } else {
                tabCount.set(tabA.indexOf(list.get(i)),tabCount.get(tabA.indexOf(list.get(i)))+1);
            }
        }
        
        System.out.println("TAILLE TABA : "+tabA.size());
        for(int i = 0; i < tabA.size(); i++)    
            data.getData().add(new XYChart.Data(tabA.get(i),tabCount.get(i)));    
        
        barchart.getData().add(data);
        barchart.setStyle("-fx-bar-color: #ffffff;");
        
        VBox vbox = new VBox(barchart);
        Scene scene = new Scene(vbox,500,300);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Statistiques");
        stage.initOwner(btn_voir.getScene().getWindow());
        stage.showAndWait();
        
        
        
        
    }

}
