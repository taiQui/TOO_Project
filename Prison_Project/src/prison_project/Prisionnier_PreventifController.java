/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<Detenu> tableview;
    @FXML
    private Button btn_voir;

    private bank_database _database;
    
    private ObservableList<Detenu> ajoutable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn colonneDateNaiss = new TableColumn("Date de naissance");
        TableColumn colonneLieuxNaiss = new TableColumn("Lieu de naissance");
        TableColumn colonneEcrou = new TableColumn("Ecrou");
        tableview.getColumns().addAll(colonneDateNaiss,colonneLieuxNaiss,colonneEcrou);
        
        try {
            _database = new bank_database();
        }catch (Exception e1){
            e1.getCause();
        }
    }    

    @FXML
    private void onClickBtnMenu(MouseEvent event) {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickbtnvoir(MouseEvent event) throws SQLException, ParseException {

        ResultSet rs = _database.readPrisonnierToDatabase();
        String nom;
        String prenom;
        java.util.Calendar datenaiss;
        String lieunaiss;
        String ecrou;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Calendar calendar = Calendar.getInstance();

        rs.beforeFirst();
        while(rs.next()){
            prenom = rs.getString("prenom");
            nom = rs.getString("nom");
            ecrou = rs.getString("n_ecrou");
            Date dateobj = sdf.parse(rs.getString("date_naissance"));
            calendar.setTime(dateobj);
            datenaiss = calendar;
            lieunaiss = rs.getString("lieu_naissance");
            System.out.println(prenom+nom+ecrou+datenaiss+ lieunaiss );
            ajoutable = FXCollections.observableArrayList(
                        new Detenu (ecrou,prenom,nom,datenaiss,lieunaiss),
                        new Detenu (ecrou,prenom,nom,datenaiss,lieunaiss));
            ajoutable.add(new Detenu (ecrou,prenom,nom,datenaiss,lieunaiss));
            
            
        }
        tableview.setItems(ajoutable);
        
    }
    
}
