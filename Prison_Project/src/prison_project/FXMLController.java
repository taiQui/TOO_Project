/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

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
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        if(text_ExactName.getText().isEmpty())
            text_ExactName.setStyle("-fx-border-color: red");
        else
            text_ExactName.setStyle("-fx-background-color: Background.Empty");
        
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

    @FXML
    private void onClickBtnCreate(MouseEvent event) {
        Data data = new Data(text_LastName.getText(),text_FirstName.getText(),text_Birthday.getText(),text_Birthplace.getText(),text_CaseNumber.getText(),text_NameOrigin.getText(),text_ExactName.getText(),text_DayOfImprisonment.getText(),text_Reason.getText(),text_DayOfFact.getText());
        LoadingBar.setProgress(data.TestError());
        System.out.println(data.TestError());
        if(data.TestError() < 1.0)
            LoadingBar.setStyle("-fx-accent: red;");
        else
            LoadingBar.setStyle("-fx-accent: green;");
        
        if(text_area.getText().isEmpty()) {
            text_area.setStyle("-fx-text-fill: red;");
            text_area.setText(text_area.getText()+'\n'+"Error Erase All and try again");
        }
            
        
        CompleteText();
        if(data.TestVoid()){
            _database.addDatabase(data.getLastName(), data.getFirstName, data.getBithday, data.getBirthPlace, data.getCaseNumber, data.getNameOfOrigin, data.getExactName, data.getDayOfImprisonment, data.getReason, data.getDayOfFact);
              
        }
    }

    @FXML
    private void onClickBtnDelete(MouseEvent event) {
        System.out.println("user pressed delete !");
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }

    @FXML
    private void onClickBtnRead(MouseEvent event) {
        Data dataRead = new Data();
        dataRead = _database.readDatabase();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(dataRead.getFirstName());

        alert.showAndWait();
    }
}
