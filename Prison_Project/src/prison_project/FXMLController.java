/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    private Font x1;
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
    @FXML
    private TextField text_Reason;
    @FXML
    private TextField text_NameOrigin;
    @FXML
    private TextField text_ExactName;
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


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // SuppRep rep = new SuppRep();
        // File file = new File("C:\\Users\\greg1\\Documents\\TOO_Project\\Prison_Project\\bank_database");
        // if(file.exists()){
        //     rep.deleteAll(file);
        //     System.out.println("Repertoire supprimer");
        // }
        // if(file.exists())
        //     System.out.println("Le fichier n'as pas ete supprimer");
       // try {
            //_database = new bank_database();
          //  System.out.println(_database.toString );

        try {
            createDatabase();
        } catch (Exception s1){
            
            System.err.println(s1.getMessage());
        }


       // } catch (java.sql.SQLException sql1) {

       // } catch (Exception e1){

        //}

    }

    public void createDatabase() throws Exception {
        _database = new bank_database();
    }


    private void CompleteText() {
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

        if(text_Reason.getText().isEmpty())
            text_Reason.setStyle("-fx-border-color: red");
        else
            text_Reason.setStyle("-fx-background-color: Background.Empty");

        if(text_DayOfFact.getText().isEmpty())
            text_DayOfFact.setStyle("-fx-border-color: red");
        else
            text_DayOfFact.setStyle("-fx-background-color: Background.Empty");
    }
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

    
    
    
    public static Calendar stringToCalendar(String stringDate, String datePattern) {
    if (stringDate == null) {
      return null;
    }
    Calendar calendar = new GregorianCalendar();
    try {
      Timestamp newDate = Timestamp.valueOf(stringDate);
      calendar.setTime(newDate);
    }
    catch (Exception e) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
      try {
        calendar.setTime(simpleDateFormat.parse(stringDate));
      }
      catch (ParseException pe) {
        calendar = null;
      }
    }
    return calendar;
  }
    
    @FXML
    private void onClickBtnCreate(MouseEvent event)throws java.sql.SQLException, ParseException  {
        Data data = new Data(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_DayOfImprisonment.getText(),text_Reason.getText(),text_DayOfFact.getText(),text_area.getText());





        LoadingBar.setProgress(data.TestError());
        System.out.println(data.TestError());
        if(text_area.getText().isEmpty()) {
            text_area.setStyle("-fx-text-fill: red;");
            text_area.setText(text_area.getText()+'\n'+"Error Erase All and try again");
        }


        CompleteText();



        System.out.println(!data.TestVoid());
        if(!data.TestVoid()){
        //Detenu
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date dateobj = sdf.parse(text_Birthday.getText());
        java.util.Calendar calendar = stringToCalendar(text_Birthday.getText(),"dd-MM-yyyy");
       // calendar.setTime(dateobj);
        
            
        System.out.println("Creation det apres appuis bouton create : "+ sdf.format(calendar.getTime()));
        Detenu detenu = new Detenu(text_area.getText(),text_FirstName.getText(),text_LastName.getText(),calendar,text_Birthplace.getText());

        //Affaire
        //dateobj = sdf.parse(text_DayOfFact.getText());
        calendar = stringToCalendar(text_DayOfFact.getText(),"dd-MM-yyyy");
        Affaire affaire = new Affaire(text_CaseNumber.getText(),calendar);

        //Juridiction
        Juridiction juridiction = new Juridiction(text_NameOrigin.getText());

        //Incarceration
        //dateobj = sdf.parse(text_DayOfImprisonment.getText());
        calendar = stringToCalendar(text_DayOfImprisonment.getText(),"dd-MM-yyyy");
        Incarceration incarceration = new Incarceration(calendar);

        //Motif
        Motif motif = new Motif(text_Reason.getText());


        _database.addPrisionnierToDatabase(detenu,affaire,juridiction,incarceration,motif);
        LoadingBar.setProgress(1.0d);
        LoadingBar.setStyle("-fx-accent: green;");
        }
    }

    @FXML
    private void onClickBtnDelete(MouseEvent event) throws SQLException {
        _database.DeletePrisonnier(text_area.getText());
    }

    public void newW(){
        Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("BITE");
            alert.setHeaderText("BITE");
            alert.setContentText("BITE");
            alert.showAndWait();
            newW();

    }

    @FXML
    private void onClickBtnRead(MouseEvent event)throws java.sql.SQLException, ParseException  {
      ArrayList<Detenu> liste = _database.searchOnDatabase(text_area.getText(), 3);
      
      if(!liste.get(0)._nom.isEmpty()) {
          ArrayList<String> prisonnier = new ArrayList<String>();
          prisonnier = _database.getPrisonnier(text_area.getText());
          if(prisonnier.isEmpty())
              System.out.println("La liste est vide biatch");
          for(int i = 0; i< prisonnier.size() ; i++)
              System.out.println("String("+i+") = "+ prisonnier.get(i));
          text_FirstName.setText(liste.get(0).getPrenom());
          text_LastName.setText(liste.get(0).getNom());
          text_Birthday.setText((String)liste.get(0).get_date_naissanceFX().toString());
          text_Birthplace.setText(liste.get(0).getLieuNaiss());
          text_CaseNumber.setText(prisonnier.get(0));
          text_NameOrigin.setText(prisonnier.get(1));
          text_DayOfFact.setText(prisonnier.get(2));
          text_DayOfImprisonment.setText(prisonnier.get(3));
          text_Reason.setText(prisonnier.get(4));
          
      } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERREUR");
                alert.setHeaderText("Aucun prisionnier trouvé avec ce numero d'écrou");
                alert.setContentText("ERROR 404 NOT FOUND");
                alert.showAndWait();
      }
       
      


    }

    @FXML
    private void onClickBtnUpdate(MouseEvent event) throws ParseException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateobj = sdf.parse(text_Birthday.getText());
        java.util.Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateobj);
        Detenu detenu = new Detenu(text_area.getText(),text_FirstName.getText(),text_LastName.getText(),calendar,text_Birthplace.getText());

        //Affaire
        dateobj = sdf.parse(text_DayOfFact.getText());
        calendar.setTime(dateobj);
        Affaire affaire = new Affaire(text_CaseNumber.getText(),calendar);

        //Juridiction
        Juridiction juridiction = new Juridiction(text_NameOrigin.getText());

        //Incarceration
        dateobj = sdf.parse(text_DayOfImprisonment.getText());
        calendar.setTime(dateobj);
        Incarceration incarceration = new Incarceration(calendar);

        //Motif
        Motif motif = new Motif(text_Reason.getText());
        
        _database.UpdatePrisonnier(detenu, affaire, juridiction, incarceration, motif);
        
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
