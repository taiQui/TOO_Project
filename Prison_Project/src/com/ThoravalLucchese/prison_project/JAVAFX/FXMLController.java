/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ThoravalLucchese.prison_project.JAVAFX;
import com.ThoravalLucchese.prison_project.Program.Affaire;
import com.ThoravalLucchese.prison_project.Program.Convertisseur;
import com.ThoravalLucchese.prison_project.Program.Data;
import com.ThoravalLucchese.prison_project.Program.Detenu;
import com.ThoravalLucchese.prison_project.Program.Incarceration;
import com.ThoravalLucchese.prison_project.Program.Juridiction;
import com.ThoravalLucchese.prison_project.Program.Motif;
import com.ThoravalLucchese.prison_project.Program.bank_database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author greg1
 * 
 */
public class FXMLController implements Initializable {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btn_point;
    @FXML
    private Font x2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_0;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_1;
    @FXML
    private Button btn_4;
    @FXML
    private Button btn_6;
    @FXML
    private Button btn_del;
    @FXML
    private Button btn_9;
    @FXML
    private Button btn_8;
    @FXML
    private Button btn_7;
    @FXML
    private TextArea text_area;
    @FXML
    private Font x4;
    @FXML
    private Font x3;
    @FXML
    private Button btn_create;
    @FXML
    private TextField text_DayOfImprisonment;
    private TextField text_Reason;
    @FXML
    private TextField text_NameOrigin;
    @FXML
    private TextField text_CaseNumber;
    @FXML
    private TextField text_LastName;
    @FXML
    private TextField text_FirstName;
    @FXML
    private TextField text_Birthday;
    @FXML
    private TextField text_Birthplace;
    @FXML
    private TextField text_DayOfFact;
    @FXML
    private ProgressBar LoadingBar;

    private bank_database _database;



    @FXML
    private Button btnRead;
    @FXML
    private Button Btn_update;
    @FXML
    private Button btnMenu;
    @FXML
    private ChoiceBox<String> choiceBox;

    private String testecrou;
    @FXML
    private TextArea text_aide;
    @FXML
    private Button btn_aide;
    @FXML
    private Circle circleValid;
    
    
    private boolean NumeroEcrouValide = false;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                // SuppRep rep = new SuppRep();
                // File file = new File("C:\\Users\\greg1\\Documents\\TOO_Project\\Prison_Project\\bank_database");       // Supprime un repertoire ( base de donnée pour eviter de le supprimer
                // if(file.exists()){                                                                                     // avant chaque execution
                //     rep.deleteAll(file);
                //     System.out.println("Repertoire supprimer");
                // }
                // if(file.exists())
                //     System.out.println("Le fichier n'as pas ete supprimer");
                choiceBox.setItems(FXCollections.observableArrayList("vols et delits assimiles","coups et blessures","escroquerie","port d’armes prohibé","conduite en état d’ivresse","viol","pédophilie","abus de confiance","homicide","proxénétisme"));
                choiceBox.setValue("viol");
                text_aide.setVisible(false);
                text_aide.setDisable(true);
                circleValid.setFill(Color.WHITE);
                createDatabase(); // Creation base de donnée 
                lancement();
            } catch (Exception s1) {
                System.err.println(s1.getMessage());
            }

    }
   
    
    public void lancement() throws SQLException, ParseException{
        //System.out.println("Ici y'a le null rouge: " + testecrou);
        if(!testecrou.isEmpty()){
            read(testecrou);
        }
    }

    public void createDatabase() throws Exception {
        _database = new bank_database();
    }

    public void getE(String e) throws SQLException, ParseException{
        System.out.println("getE");
        System.out.println("e: " + e);
        if(e != null)
             this.testecrou = e;
        else
             System.out.println("e = NULL");
        read(e);
        
    }
    
    
    private void CompleteText() {                    // Regarde si les champs sont remplis ou pas et les colores en consequence
        if(text_LastName.getText().isEmpty() || text_LastName.getText().length() > 30)
            text_LastName.setStyle("-fx-border-color: red");
        else {
            text_LastName.setStyle("-fx-background: transparent");
            text_LastName.setStyle("-fx-border-color: transparent");
        }

        if(text_FirstName.getText().isEmpty() || text_FirstName.getText().length() > 30 )
            text_FirstName.setStyle("-fx-border-color: red");
        else {
            text_FirstName.setStyle("-fx-background: transparent");
            text_FirstName.setStyle("-fx-border-color: transparent");
        }
        
        //System.out.println("test : "+!DateValide(text_Birthday.getText()));
        if(text_Birthday.getText().isEmpty() || !Data.DateValide(text_Birthday.getText()))
            text_Birthday.setStyle("-fx-border-color: red");
        else {
            text_Birthday.setStyle("-fx-background: transparent");
            text_Birthday.setStyle("-fx-border-color: transparent");
        }

        if(text_Birthplace.getText().isEmpty() || text_Birthplace.getText().length() > 30)
            text_Birthplace.setStyle("-fx-border-color: red");
        else {
            text_Birthplace.setStyle("-fx-background: transparent");
            text_Birthplace.setStyle("-fx-border-color: transparent");
        }

        if(text_CaseNumber.getText().isEmpty() || text_CaseNumber.getText().length() > 10)
            text_CaseNumber.setStyle("-fx-border-color: red");
        else {
            text_CaseNumber.setStyle("-fx-background: transparent");
            text_CaseNumber.setStyle("-fx-border-color: transparent");
        }

        if(text_NameOrigin.getText().isEmpty() || text_NameOrigin.getText().length() > 30)
            text_NameOrigin.setStyle("-fx-border-color: red");
        else {
            text_NameOrigin.setStyle("-fx-background: transparent");
            text_NameOrigin.setStyle("-fx-border-color: transparent");
        }

        if(text_DayOfImprisonment.getText().isEmpty() || !Data.DateValide(text_DayOfImprisonment.getText()))
            text_DayOfImprisonment.setStyle("-fx-border-color: red");
        else {
            text_DayOfImprisonment.setStyle("-fx-background: transparent");
            text_DayOfImprisonment.setStyle("-fx-border-color: transparent");
        }
        
        if(text_DayOfFact.getText().isEmpty() || !Data.DateValide(text_DayOfFact.getText()))
            text_DayOfFact.setStyle("-fx-border-color: red");
        else {
            text_DayOfFact.setStyle("-fx-background: transparent");
            text_DayOfFact.setStyle("-fx-border-color: transparent");
        }
    }
    
    //onClick events for numbers
    @FXML
    private void onClickBtnPoint(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        text_area.setText("");
        TestEcrou();
    }

    @FXML
    private void onClickBtn3(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "3";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn0(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "0";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn5(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "5";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn2(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "2";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn1(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "1";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn4(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "4";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn6(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "6";
        text_area.setText(aux);
        TestEcrou();
        
    }

    @FXML
    private void onClickBtnDel(MouseEvent event) throws SQLException {
        String aux = text_area.getText();
        if(text_area.getText().toLowerCase().contains("erreur"))
            aux = "";
        else {
            if(!text_area.getText().isEmpty())
                aux = text_area.getText().substring(0,text_area.getText().length() -1);
        }
            
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn9(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "9";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn8(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "8";
        text_area.setText(aux);
        TestEcrou();
    }

    @FXML
    private void onClickBtn7(MouseEvent event) throws SQLException {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "7";
        text_area.setText(aux);
        TestEcrou();
    }

    
    public boolean TestLongueur(){
        return(text_LastName.getText().length() <= 30 && text_FirstName.getText().length() <= 30  && text_Birthplace.getText().length() <= 30 && text_CaseNumber.getText().length() <= 10 && text_NameOrigin.getText().length() <= 30  && text_area.getText().length() <= 10);
            
    }
    
    
    @FXML
    private void onClickBtnCreate(MouseEvent event)throws java.sql.SQLException, ParseException  {
       
        //Data data = new Data(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText(),text_area.getText());

        LoadingBar.setProgress(Data.TestError(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText()));
        if(!Data.DateValide(text_Birthday.getText())) {
            if(LoadingBar.getProgress() > 0.0f)
                LoadingBar.setProgress(LoadingBar.getProgress() - 0.1f);
        }
        if(!Data.DateValide(text_DayOfImprisonment.getText())) {
            if(LoadingBar.getProgress() > 0.0f)
                LoadingBar.setProgress(LoadingBar.getProgress() - 0.1f);
        }
        if(!Data.DateValide(text_DayOfFact.getText())) {
            if(LoadingBar.getProgress() > 0.0f)
                LoadingBar.setProgress(LoadingBar.getProgress() - 0.1f);
        }
       // System.out.println(Data.TestError(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText()));
        if(text_area.getText().isEmpty()) {
            text_area.setStyle("-fx-text-fill: red;");
            text_area.setText(text_area.getText()+'\n'+"Erreur. Supprimer tous et recommencer");
        }


        CompleteText();
        //System.out.println("TEST: "+(!Data.TestVoid(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText(),text_area.getText())) + NumeroEcrouValide + DateValide(text_Birthday.getText()) + DateValide(text_DayOfImprisonment.getText()) + DateValide(text_DayOfFact.getText()) + TestLongueur());
        if((!Data.TestVoid(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText(),text_area.getText())) && NumeroEcrouValide && Data.DateValide(text_Birthday.getText()) && Data.DateValide(text_DayOfImprisonment.getText()) && Data.DateValide(text_DayOfFact.getText()) && TestLongueur() ){
            
            //Si tous les champs sont non vide ET si l'ecrou n'est pas deja dans la base ET si les 3 date a remplir sont dans le bon format ET si les longueurs de saisies ne depasse pas les longueurs maximal 
            
            
            
        //Detenu
        java.util.Calendar calendar = Convertisseur.stringToCalendar(text_Birthday.getText(),"yyyy-MM-dd");
            
        Detenu detenu = new Detenu(text_area.getText(),text_FirstName.getText(),text_LastName.getText(),calendar,text_Birthplace.getText());

        //Affaire
        calendar = Convertisseur.stringToCalendar(text_DayOfFact.getText(),"yyyy-MM-dd");
        Affaire affaire = new Affaire(text_CaseNumber.getText(),"",calendar);

        //Juridiction
        Juridiction juridiction = new Juridiction(text_NameOrigin.getText());

        //Incarceration
        calendar = Convertisseur.stringToCalendar(text_DayOfImprisonment.getText(),"yyyy-MM-dd");
        Incarceration incarceration = new Incarceration(calendar);

        //Motif
       
        Motif motif = new Motif(Data.choiceMotif(choiceBox.getValue()));

        
        
        System.out.println("ajjouuuuue");
        _database.addPrisionnierToDatabase(detenu,affaire,juridiction,incarceration,motif);
        LoadingBar.setProgress(1.0d);
        
        
        
        LoadingBar.setStyle("-fx-accent: green;");
        newW("Ajout Prisonnier","Succes","La modification est validé");
        LoadingBar.setStyle("-fx-accent: white;");
        } else {
            newW("Ajout Prisonnier","Erreur","Le numéro d'ecrou est deja associé ou/et les champs ne sont pas remplis correctement ");
        }
    }

    @FXML
    private void onClickBtnDelete(MouseEvent event) throws SQLException {
        _database.DeletePrisonnier(text_area.getText());
        LoadingBar.setStyle("-fx-accent: green;");
        newW("Suppression Prisonnier","Succes","La modification est validé");
        LoadingBar.setStyle("-fx-accent: white;");
    }

    public void newW(String titre, String header, String content){

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(titre);
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.initOwner(LoadingBar.getScene().getWindow());
                alert.showAndWait();
    }
    
    public void read() throws java.sql.SQLException, ParseException  {
        ArrayList<Detenu> liste = _database.searchOnDatabase(text_area.getText(), 3);     // met le detenu avec le numero d'ecrou dans l'ArrayList " list "
      
      if(!liste.isEmpty()) {
          //System.out.println("LE NOM EST : "+ liste.get(0).getNom());
          ArrayList<String> prisonnier = new ArrayList<String>();
          prisonnier = _database.getPrisonnier(text_area.getText());
          if(prisonnier.isEmpty())
              System.out.println("La liste est vide biatch");
          text_FirstName.setText(liste.get(0).getPrenom());
          text_LastName.setText(liste.get(0).getNom());
          text_Birthday.setText((String)liste.get(0).get_date_naissanceFX().toString());
          text_Birthplace.setText(liste.get(0).getLieuNaiss());
          text_CaseNumber.setText(prisonnier.get(0));
          text_NameOrigin.setText(prisonnier.get(1));
          text_DayOfFact.setText(prisonnier.get(2));
          text_DayOfImprisonment.setText(prisonnier.get(3));
          choiceBox.setValue(Data.getNmotif(prisonnier.get(4)));
          
      } else {
                newW("ERREUR","Aucun prisionnier trouvé avec ce numero d'écrou","ERROR 404 NOT FOUND");
      }
       
      


    }
    
    public void read(String ecrou) throws java.sql.SQLException, ParseException  {
        System.out.println("read ("+ ecrou + ")");
        ArrayList<Detenu> liste = new ArrayList<Detenu>();
        liste = _database.searchOnDatabase(ecrou, 3);     // met le detenu avec le numero d'ecrou dans l'ArrayList " list "
      if(!liste.isEmpty()) {
          
          ArrayList<String> prisonnier = new ArrayList<String>();
          prisonnier = _database.getPrisonnier(ecrou);
          if(prisonnier.isEmpty())
              System.out.println("La liste est vide biatch");
          text_area.setText(ecrou);
          text_FirstName.setText(liste.get(0).getPrenom());
          text_LastName.setText(liste.get(0).getNom());
          //System.out.println("ca plante ici j'parie");
          text_Birthday.setText(Convertisseur.calendarToString(liste.get(0).getDNaiss(),"yyyy-MM-dd"));
          text_Birthplace.setText(liste.get(0).getLieuNaiss());
          text_CaseNumber.setText(prisonnier.get(0));
          //System.out.println("ca plante ici j'parie"); //LE FAMEUX PRINTLN D2CAL2 mdr trololol
          text_NameOrigin.setText(prisonnier.get(1));
          text_DayOfFact.setText(prisonnier.get(2));
          text_DayOfImprisonment.setText(prisonnier.get(3));
          choiceBox.setValue(Data.getNmotif(prisonnier.get(4)));
          
      } else {
                newW("ERREUR","Aucun prisionnier trouvé avec ce numero d'écrou","ERROR 404 NOT FOUND");
      }
       
      


    }
    
    @FXML
    private void onClickBtnRead(MouseEvent event)throws java.sql.SQLException, ParseException  {
        read();
    }

    @FXML
    private void onClickBtnUpdate(MouseEvent event) throws ParseException, SQLException {
        //More natural usage
        //Data data = new Data(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText(),text_area.getText());

        if(Data.TestVoidWithOutEcrou(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText())){
            System.out.println("JE PASSE ICI MDR");
            TestEcrou();
            if(!NumeroEcrouValide){
                read(text_area.getText());
            }
            //System.out.println("tst 1 : "+DateValide(text_Birthplace.getText()) + " test 2 : "+DateValide(text_DayOfImprisonment.getText())+" test 3 : "+DateValide(text_DayOfFact.getText()));
        }else if (!Data.TestVoidWithOutEcrou(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText()) && !NumeroEcrouValide && Data.DateValide(text_Birthday.getText()) && Data.DateValide(text_DayOfImprisonment.getText()) && Data.DateValide(text_DayOfFact.getText()) && TestLongueur() && _database.FindPrisonnier(text_area.getText())){
            
            //Si les champs sauf le numero d'ecrou sont remplie ET que le numero d'ecrou existe ET que les dates sont au bon format ET que les champs ne depasse pas leurs longueur maximal
            
            
            System.out.println("JE PASSE ICI AUSSI LOL");
            java.util.Calendar calendar = Calendar.getInstance();
            calendar = Convertisseur.stringToCalendar(text_Birthday.getText(), "yyyy-MM-dd");
            Detenu detenu = new Detenu(text_area.getText(),text_FirstName.getText(),text_LastName.getText(),calendar,text_Birthplace.getText());

            //Affaire
            calendar = Convertisseur.stringToCalendar(text_DayOfFact.getText(), "yyyy-MM-dd");
            Affaire affaire = new Affaire(text_CaseNumber.getText(),"",calendar);

            //Juridiction
            Juridiction juridiction = new Juridiction(text_NameOrigin.getText());

            //Incarceration
            calendar = Convertisseur.stringToCalendar(text_DayOfImprisonment.getText(), "yyyy-MM-dd");
            Incarceration incarceration = new Incarceration(calendar);

            //Motif
            Motif motif = new Motif(Data.choiceMotif(choiceBox.getValue()));

            int reponse = _database.UpdatePrisonnier(detenu, affaire, juridiction, incarceration, motif);
            switch(reponse){
                case 0:
                    newW("Modification prisonnier","Succès","la modification est validé, la nouvelle affaire est changé.");
                    break;
                case 1:
                    newW("Modification prisonnier","Succès","la modification est validé, la juridiction n'étant pas la meme que le numéro d'affaire donné en paramètre\nLa juridiction ecrite a été remplacé par la juridiction de l'affaire donnée.");
                    break;
                case 2:
                    newW("Modification prisonnier","Succès","la modification est validé, l'affaire passe en paramètre n'existe pas, elle a ete crée.");
                    break;
                case 3:
                    newW("Modification prisonnier","Succès","La modification est validé");
                    break;
            }
            LoadingBar.setStyle("-fx-accent: green;");
            //newW("Modification Prisonnier","Succes","La modification est validé");
            LoadingBar.setStyle("-fx-accent: white;"); 
            read(text_area.getText());
        } else {
            newW("Mise a jour Prisonnier","Erreur","Numero ecrou invalide ou format date invalide");
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


    @FXML
    private void onclickBtnaide(MouseEvent event) {
        switch(btn_aide.getText()){
            case "Aide":
                btn_aide.setText("Cacher");
                text_aide.setVisible(true);
                text_aide.setDisable(false);
                break;
            case "Cacher":
                btn_aide.setText("Aide");
                text_aide.setVisible(false);
                text_aide.setDisable(true);
                break;
        }
    }

    @FXML
    private void onkeyRelease(KeyEvent event) throws SQLException, ParseException {   // permet de n'avoir que des chiffres dans le numero d'ecrou
        
       String test = text_area.getText();
       //if(!test.matches("//d{0,7}([\\.]\\d{0,4})?")) {   
       //}
       if(!(test.length() <= 0)) {
            if(!Character.isDigit(test.charAt(test.length()-1))){
                if(test.length() > 0 ){
                     String aux = test.substring(0,test.length()-1);
                     text_area.setText("");
                     text_area.setText(aux);
                     text_area.positionCaret(text_area.getText().length());
                } else {
                     text_area.setText("");
                }
            }


         }
         int i = 0;
         System.out.println("---------------------------------\n-------------------");  
         while(i < test.length()){
             if(!Character.isDigit(test.charAt(i))){
                 System.out.println("chatAt : "+test.charAt(i)+"\nTest : "+test+"\nsub1 : "+test.substring(0,i)+"\nsub2 : "+test.substring(i+1,test.length()));
                 test = test.substring(0,i) + test.substring(i+1,test.length());
             } else {
                 i++;
             }
         }
         text_area.setText(test);
         text_area.positionCaret(text_area.getText().length());
         TestEcrou();
       
        }
    
        public void TestEcrou() throws SQLException{
         ArrayList<String> liste = new ArrayList<String>();
         liste = _database.getPrisonnier(text_area.getText());
         if(liste.isEmpty()){
             NumeroEcrouValide = true;
             circleValid.setFill(Color.GREEN);
         } else {
             NumeroEcrouValide = false;
             circleValid.setFill(Color.RED);
         }
        }

        private String OnlyDigitInDate(String date){
            String test = date;
            int i = 0;
            while(i < test.length()){
             if(!Character.isDigit(test.charAt(i)) && (!('-' == test.charAt(i)))){
                 //System.out.println("charAt : "+ test.charAt(i) + "\ntest : "+ (test.charAt(i) == '-'));
                 //System.out.println("chatAt : "+test.charAt(i)+"\nTest : "+test+"\nsub1 : "+test.substring(0,i)+"\nsub2 : "+test.substring(i+1,test.length()));
                 test = test.substring(0,i) + test.substring(i+1,test.length());
             } else {
                 i++;
             }
         }
         return(test);
        }

    @FXML
    private void OnkeyReleasedTextDayOfImprisonment(KeyEvent event) {
        text_DayOfImprisonment.setText(OnlyDigitInDate(text_DayOfImprisonment.getText()));
        text_DayOfImprisonment.positionCaret(text_DayOfImprisonment.getText().length());
    }

    @FXML
    private void OnkeyReleasedTextBirhtday(KeyEvent event) {
        text_Birthday.setText(OnlyDigitInDate(text_Birthday.getText()));
        text_Birthday.positionCaret(text_Birthday.getText().length());
    }

    @FXML
    private void OnkeyReleasedTextDayOfFact(KeyEvent event) {
        text_DayOfFact.setText(OnlyDigitInDate(text_DayOfFact.getText()));
        text_DayOfFact.positionCaret(text_DayOfFact.getText().length());
        
    }

}
