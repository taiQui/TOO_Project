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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author greg1
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
            // try {
            //_database = new bank_database();
            //  System.out.println(_database.toString );
            choiceBox.setItems(FXCollections.observableArrayList("vols et delits assimiles","coups et blessures","escroquerie","port d’armes prohibé","conduite en état d’ivresse","viol","pédophilie","abus de confiance","homicide","proxénétisme"));
            choiceBox.setValue("viol");
            
            
            
                createDatabase(); 
                lancement();// Creation base de donnée
            } catch (Exception s1){
                
                System.err.println(s1.getMessage());
            }

    }
   
    
    public void lancement() throws SQLException, ParseException{
        System.out.println("Ici y'a le null rouge: " + testecrou);
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
        if(text_LastName.getText().isEmpty())
            text_LastName.setStyle("-fx-border-color: red");
        else
            text_LastName.setStyle("-fx-background: Background.Empty");

        if(text_FirstName.getText().isEmpty())
            text_FirstName.setStyle("-fx-border-color: red");
        else
            text_FirstName.setStyle("-fx-background-color: Background.Empty");

        if(text_Birthday.getText().isEmpty())
            text_Birthday.setStyle("-fx-border-color: red");
        else
            text_Birthday.setStyle("-fx-background-color: Background.Empty");

        if(text_Birthplace.getText().isEmpty())
            text_Birthplace.setStyle("-fx-border-color: red");
        else
            text_Birthplace.setStyle("-fx-background-color: Background.Empty");

        if(text_CaseNumber.getText().isEmpty())
            text_CaseNumber.setStyle("-fx-border-color: red");
        else
            text_CaseNumber.setStyle("-fx-background-color: Background.Empty");

        if(text_NameOrigin.getText().isEmpty())
            text_NameOrigin.setStyle("-fx-border-color: red");
        else
            text_NameOrigin.setStyle("-fx-background-color: Background.Empty");

        if(text_DayOfImprisonment.getText().isEmpty())
            text_DayOfImprisonment.setStyle("-fx-border-color: red");
        else
            text_DayOfImprisonment.setStyle("-fx-background-color: Background.Empty");

        //if(text_Reason.getText().isEmpty())
         //   text_Reason.setStyle("-fx-border-color: red");
        //else
       //     text_Reason.setStyle("-fx-background-color: Background.Empty");

        if(text_DayOfFact.getText().isEmpty())
            text_DayOfFact.setStyle("-fx-border-color: red");
        else
            text_DayOfFact.setStyle("-fx-background-color: Background.Empty");
    }
    
    //onClick events for numbers
    @FXML
    private void onClickBtnPoint(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + '.';
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn3(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "3";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn0(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "0";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn5(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "5";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn2(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "2";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn1(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "1";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn4(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "4";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn6(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "6";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtnDel(MouseEvent event) {
        String aux;
        if(text_area.getText().toLowerCase().contains("error"))
            aux = "";
        else
            aux = text_area.getText().substring(0,text_area.getText().length() -1);
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn9(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "9";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn8(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "8";
        text_area.setText(aux);
    }

    @FXML
    private void onClickBtn7(MouseEvent event) {
        text_area.setStyle("-fx-text-fill: black");
        String aux = text_area.getText() + "7";
        text_area.setText(aux);
    }

    
    
    
    
    
    @FXML
    private void onClickBtnCreate(MouseEvent event)throws java.sql.SQLException, ParseException  {
        Data data = new Data(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_DayOfFact.getText(),text_area.getText());

        LoadingBar.setProgress(data.TestError());
        System.out.println(data.TestError());
        if(text_area.getText().isEmpty()) {
            text_area.setStyle("-fx-text-fill: red;");
            text_area.setText(text_area.getText()+'\n'+"Error Erase All and try again");
        }


        CompleteText();

        if(!data.TestVoid()){
        //Detenu
        java.util.Calendar calendar = Convertisseur.stringToCalendar(text_Birthday.getText(),"yyyy-MM-dd");
            
        Detenu detenu = new Detenu(text_area.getText(),text_FirstName.getText(),text_LastName.getText(),calendar,text_Birthplace.getText());

        //Affaire
        calendar = Convertisseur.stringToCalendar(text_DayOfFact.getText(),"yyyy-MM-dd");
        Affaire affaire = new Affaire(text_CaseNumber.getText(),calendar);

        //Juridiction
        Juridiction juridiction = new Juridiction(text_NameOrigin.getText());

        //Incarceration
        calendar = Convertisseur.stringToCalendar(text_DayOfImprisonment.getText(),"yyyy-MM-dd");
        Incarceration incarceration = new Incarceration(calendar);

        //Motif
       
        Motif motif = new Motif(Data.choiceMotif(choiceBox.getValue()));


        _database.addPrisionnierToDatabase(detenu,affaire,juridiction,incarceration,motif);
        LoadingBar.setProgress(1.0d);
        
        LoadingBar.setStyle("-fx-accent: green;");
        newW();
        LoadingBar.setStyle("-fx-accent: white;");
        }
    }

    @FXML
    private void onClickBtnDelete(MouseEvent event) throws SQLException {
        _database.DeletePrisonnier(text_area.getText());
        LoadingBar.setStyle("-fx-accent: green;");
        newW();
        LoadingBar.setStyle("-fx-accent: white;");
    }

    public void newW(){
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout Prisonnier");
            alert.setHeaderText("Succes");
            alert.setContentText("La modification est validé");
            alert.showAndWait();

    }
    
    public void read() throws java.sql.SQLException, ParseException  {
        ArrayList<Detenu> liste = _database.searchOnDatabase(text_area.getText(), 3);     // met le detenu avec le numero d'ecrou dans l'ArrayList " list "
      
      if(!liste.get(0)._nom.isEmpty()) {
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERREUR");
                alert.setHeaderText("Aucun prisionnier trouvé avec ce numero d'écrou");
                alert.setContentText("ERROR 404 NOT FOUND");
                alert.showAndWait();
      }
       
      


    }
    
    public void read(String ecrou) throws java.sql.SQLException, ParseException  {
        System.out.println("read ("+ ecrou + ")");
        ArrayList<Detenu> liste = _database.searchOnDatabase(ecrou, 3);     // met le detenu avec le numero d'ecrou dans l'ArrayList " list "
      if(!liste.get(0)._nom.isEmpty()) {
          
          ArrayList<String> prisonnier = new ArrayList<String>();
          prisonnier = _database.getPrisonnier(ecrou);
          if(prisonnier.isEmpty())
              System.out.println("La liste est vide biatch");
          text_area.setText(ecrou);
          text_FirstName.setText(liste.get(0).getPrenom());
          text_LastName.setText(liste.get(0).getNom());
          System.out.println("ca plante ici j'parie");
          text_Birthday.setText(Convertisseur.calendarToString (liste.get(0).getDNaiss(),"yyyy-MM-dd"));
          text_Birthplace.setText(liste.get(0).getLieuNaiss());
          text_CaseNumber.setText(prisonnier.get(0));
          System.out.println("ca plante ici j'parie"); //LE FAMEUX PRINTLN D2CAL2 mdr trololol
          text_NameOrigin.setText(prisonnier.get(1));
          text_DayOfFact.setText(prisonnier.get(2));
          text_DayOfImprisonment.setText(prisonnier.get(3));
          choiceBox.setValue(Data.getNmotif(prisonnier.get(4)));
          
      } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERREUR");
                alert.setHeaderText("Aucun prisionnier trouvé avec ce numero d'écrou");
                alert.setContentText("ERROR 404 NOT FOUND");
                alert.showAndWait();
      }
       
      


    }
    
    @FXML
    private void onClickBtnRead(MouseEvent event)throws java.sql.SQLException, ParseException  {
        read();
    }

    @FXML
    private void onClickBtnUpdate(MouseEvent event) throws ParseException, SQLException {
        //More natural usage
        if(text_LastName.getText().isEmpty()){
            onClickBtnRead(event);
        }else{
            java.util.Calendar calendar = Calendar.getInstance();
            calendar = Convertisseur.stringToCalendar(text_Birthday.getText(), "yyyy-MM-dd");
            Detenu detenu = new Detenu(text_area.getText(),text_FirstName.getText(),text_LastName.getText(),calendar,text_Birthplace.getText());

            //Affaire
            calendar = Convertisseur.stringToCalendar(text_DayOfFact.getText(), "yyyy-MM-dd");
            Affaire affaire = new Affaire(text_CaseNumber.getText(),calendar);

            //Juridiction
            Juridiction juridiction = new Juridiction(text_NameOrigin.getText());

            //Incarceration
            calendar = Convertisseur.stringToCalendar(text_DayOfImprisonment.getText(), "yyyy-MM-dd");
            Incarceration incarceration = new Incarceration(calendar);

            //Motif
            Motif motif = new Motif(Data.choiceMotif(choiceBox.getValue()));

            _database.UpdatePrisonnier(detenu, affaire, juridiction, incarceration, motif);
            LoadingBar.setStyle("-fx-accent: green;");
            newW();
            LoadingBar.setStyle("-fx-accent: white;"); 
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




}
