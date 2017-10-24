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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TableView<DetenuFX> tableview = new TableView<DetenuFX>();
    @FXML
    private Button btn_voir;

    private bank_database _database;
    final java.util.Calendar cal = Calendar.getInstance();
   // private ObservableList<DetenuFX> ajoutable ;//= FXCollections.observableArrayList();


    public TableColumn colonneDateNaiss = new TableColumn("Date de naissance");
    public  TableColumn colonneLieuxNaiss = new TableColumn("Lieu de naissance");
    public TableColumn colonneEcrou = new TableColumn("Ecrou");
    public TableColumn colonneNom = new TableColumn("Nom");
    public TableColumn colonnePrenom = new TableColumn("Prenom");
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //tableview.setEditable(true);

        colonneDateNaiss.setCellValueFactory(new PropertyValueFactory<DetenuFX,String>("_date_naissanceFX"));
        colonneLieuxNaiss.setCellValueFactory(new PropertyValueFactory<DetenuFX,String>("_lieu_naissanceFX"));
        colonneEcrou.setCellValueFactory(new PropertyValueFactory<DetenuFX,String>("_n_ecrouFX"));
        colonneNom.setCellValueFactory(new PropertyValueFactory<DetenuFX,String>("_nomFX"));
        colonnePrenom.setCellValueFactory(new PropertyValueFactory<DetenuFX,String>("_prenomFX"));

        tableview.getColumns().addAll(colonneEcrou,colonnePrenom,colonneNom,colonneDateNaiss,colonneLieuxNaiss);

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

       //  ResultSet rs = _database.readPrisonnierToDatabase();
        // String nom;
        // String prenom;
        // java.util.Calendar datenaiss;
        // String lieunaiss;
        // String ecrou;
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // java.util.Calendar calendar = Calendar.getInstance();

        ArrayList<Detenu> liste = _database.getArray();
        ObservableList<DetenuFX> ajoutable = FXCollections.observableArrayList();
         
        //rs.beforeFirst();
        for(Detenu d : liste){
            // prenom = rs.getString("prenom");
            // nom = rs.getString("nom");
            // ecrou = rs.getString("n_ecrou");
            // Date dateobj = sdf.parse(rs.getString("date_naissance"));
            // calendar.setTime(dateobj);
            // datenaiss = calendar;
            // lieunaiss = rs.getString("lieu_naissance");
            //
            // System.out.println(prenom+nom+ecrou+sdf.format(datenaiss.getTime())+ lieunaiss );
            //
            //
            // Detenu det = new Detenu(ecrou,prenom,nom,datenaiss,lieunaiss);
            //
            // System.out.println("Detenu : "+det.getNom() + det.getPrenom() + det.getEcrou() + det.getDNaiss() + det.getLieuNaiss());
            //
            // DetenuFX newDet = new DetenuFX (det);
            // System.out.println(newDet.getDNaiss().get());
            //
            //
            // ajoutable.add(newDet);
           DetenuFX detfx = new DetenuFX(d);
           System.out.println(detfx.getNom() + "\t" + detfx.getPrenom());
           ajoutable.add(detfx);



        }

         tableview.setItems(ajoutable);


    }

}
