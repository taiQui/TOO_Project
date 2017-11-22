package com.ThoravalLucchese.prison_project.Program;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

/**
 *
 * @author greg1
 */
public class bank_database {
    static {
        try {
            java.sql.DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            java.sql.Statement statement = java.sql.DriverManager.getConnection("jdbc:derby:bank_database;create=true").createStatement();
            /*
            DEBUT DE REQUETE
             */

            statement.execute("create table Detenu(\n"
                    +"n_ecrou varchar(10),\n"
                    +"prenom varchar(30),\n"
                    +"nom varchar(30),\n"
                    +"date_naissance Date,\n"
                    +"lieu_naissance varchar(30),\n"
                    +"constraint Detenu_key primary key(n_ecrou))\n");
            statement.execute("create table Affaire(\n"
                    +"n_affaire varchar(10),\n"
                    +"nom_juridiction varchar(30),"
                    +"date_faits Date,\n"
                    +"constraint Affaire_key primary key(n_affaire,nom_juridiction))\n");
            statement.execute("create table Detenu_Affaire("
                    +"n_ecrou varchar(10),"
                    +"n_affaire varchar(10),"
                    +"nom_juridiction varchar(30),"
                    +"constraint Detenu_Affaire_key primary key(n_ecrou,n_affaire,nom_juridiction),"
                    +"constraint Detenu_Affaire_foreign_key foreign key(n_ecrou) references Detenu(n_ecrou),"
                    +"constraint Detenu_Affaire_foreign_key2 foreign key(n_affaire,nom_juridiction) references Affaire(n_affaire,nom_juridiction))");
            statement.execute("create table Motif(\n"
                    +"n_motif varchar(10),"
                    +"libelle_motif varchar(50) not null,"
                    +"constraint Motif_key primary key(n_motif),"
                    +"constraint Motif_unique unique(libelle_motif))");
            statement.execute("create table Incarceration(\n"
                    +"n_ecrou varchar(10),"
                    +"n_affaire varchar(10) not null,"
                    +"nom_juridiction varchar(30) not null,"
                    +"date_incarceration Date,"
                    +"n_motif varchar(10) not null,"
                    +"constraint Incarceration_key primary key(n_ecrou),"
                    +"constraint Incarceration_foreign_key foreign key(n_ecrou,n_affaire,nom_juridiction) references Detenu_Affaire(n_ecrou,n_affaire,nom_juridiction),"
                    +"constraint Incarceration_foreign_key2 foreign key(n_motif) references Motif(n_motif))");
            statement.execute("create table Decision(\n"
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"constraint Decision_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Decision_fk foreign key(n_ecrou) references Detenu(n_ecrou))");
            statement.execute("create table Condamnation(\n"
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"duree Integer,"
                    +"constraint Condamnation_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Condamnation_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade)");

            statement.execute("create table Reduction_peine(\n"
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"duree Integer,"
                    +"constraint Reduction_peine_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Reduction_peine_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade)");
            statement.execute("create table Liberation_definitive(\n"
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"date_liberation Date,"
                    +"constraint Liberation_definitive_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Liberation_definitive_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade)");


            statement.execute("INSERT INTO Motif VALUES('1','vols et delits assimiles')");
            statement.execute("INSERT INTO Motif VALUES('2','coups et blessures')");
            statement.execute("INSERT INTO Motif VALUES('3','escroquerie')");
            statement.execute("INSERT INTO Motif VALUES('4','port d’armes prohibé')");
            statement.execute("INSERT INTO Motif VALUES('5','conduite en état d’ivresse')");
            statement.execute("INSERT INTO Motif VALUES('6','viol')");
            statement.execute("INSERT INTO Motif VALUES('7','pédophilie')");
            statement.execute("INSERT INTO Motif VALUES('12','abus de confiance')");
            statement.execute("INSERT INTO Motif VALUES('14','homicide')");
            statement.execute("INSERT INTO Motif VALUES('15','proxénétisme')");

            statement.execute("INSERT INTO Detenu VALUES('1963','Franck','Barbier',DATE('1963-01-11'),'Montbeliard')");
            statement.execute("INSERT INTO Affaire VALUES('44','Nantes',DATE('1991-10-01'))");
            statement.execute("INSERT INTO Detenu_Affaire VALUES('1963','44','Nantes')");
            statement.execute("INSERT INTO Incarceration VALUES('1963','44','Nantes',DATE('2008-04-16'),'1')");
            statement.execute("INSERT INTO Decision VALUES('2','1963',DATE('2006-11-12'))");
            statement.execute("INSERT INTO Decision VALUES('3','1963',DATE('2006-11-12'))");
            statement.execute("INSERT INTO Condamnation VALUES('2','1963',DATE('2006-11-12'),10)");
            statement.execute("INSERT INTO Liberation_definitive VALUES ('3','1963',DATE('2006-11-12'),DATE('2010-01-01'))");

            statement.execute("INSERT INTO Detenu VALUES('1964','Sophie','Darnal',DATE(1964-07-28),'Besancon')");
            statement.execute("INSERT INTO Affaire VALUES('43','Nantes',DATE('1991-10-01'))");            
            statement.execute("INSERT INTO Detenu_Affaire VALUES('1964','43','Nantes')");
            statement.execute("INSERT INTO Incarceration VALUES('1964','43','Nantes',DATE('2008-04-16'),'1')");            
            /*
            FIN DE REQUETE
             */
        } catch (java.sql.SQLException sqle1) {
            System.err.println("'bank_database' probably already exists? " + sqle1.getMessage());
            java.sql.Connection connection;
            java.sql.Statement clean_up;
            try {
                connection = java.sql.DriverManager.getConnection("jdbc:derby:bank_database");
                clean_up = connection.createStatement();
                /*
                Nettoyage de la base
                 */
                connection.commit();
            } catch (java.sql.SQLException sqle2) {
                System.err.println("'bank_database' persistent error: " + sqle2.getMessage());
                System.exit(-1);
            }
        }
    }

   private java.sql.Connection _connection;
   private java.sql.ResultSet resultset;


   /*********************************************************************************************************/

                                                //CONSTRUCTOR
   /**********************************************************************************************************/

    public bank_database() throws java.sql.SQLException{
       System.out.println("Constructor database");
       _connection = java.sql.DriverManager.getConnection("jdbc:derby:bank_database");
       _connection.setAutoCommit(false);

   }

    public java.sql.Connection activate_connection() throws Exception {
        if (_connection == null) {
            System.out.println("testActivateConnection");
            throw new Exception("Fatal error: JDBC not initialized");
        }
        return _connection;
    }


       /*********************************************************************************************************/

                                                //AJOUT PRISIONNIER ( FXML.fxml )
   /**********************************************************************************************************/

   public void addPrisionnierToDatabase(Detenu detenu,Affaire affaire,Juridiction juridiction,Incarceration incarceration,Motif motif) throws java.sql.SQLException{

        ResultSet rs;
        System.out.println("time : "+Convertisseur.calendarToString(detenu.getDNaiss(),"yyyy-MM-dd"));
       _connection.createStatement().execute("insert into Detenu values('"+detenu.getEcrou()+"','"+detenu.getPrenom()+"','"+detenu.getNom()+"',DATE('"+Convertisseur.calendarToString(detenu.getDNaiss(),"yyyy-MM-dd")+"'),'"+detenu.getLieuNaiss()+"')");
       System.out.println("Ajout Detenu");

       rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire where n_affaire = '"+affaire.get_n_affaire()+"' and nom_juridiction = '"+juridiction.getNom()+"'");
       rs.beforeFirst();
       if(!rs.next()) {
            _connection.createStatement().execute("insert into Affaire values('"+affaire.get_n_affaire()+"','"+juridiction.getNom()+"',DATE('"+Convertisseur.calendarToString(affaire.get_date_faits(),"yyyy-MM-dd")+"'))");
       System.out.println("Ajout Affaire");
       }
      _connection.createStatement().execute("insert into Detenu_Affaire values('"+detenu.getEcrou()+"','"+affaire.get_n_affaire()+"','"+juridiction.getNom()+"')");
       System.out.println("Ajout Detenu Affaire");
      _connection.createStatement().execute("insert into Incarceration values('"+detenu.getEcrou()+"','"+affaire.get_n_affaire()+"','"+juridiction.getNom()+"',DATE('"+Convertisseur.calendarToString(incarceration.getDate(),"yyyy-MM-dd")+"'),'"+motif.getMotif()+"')");
       System.out.println("Ajout Incarceration");

      _connection.commit();


   }


   public int UpdatePrisonnier(Detenu detenu,Affaire affaire,Juridiction juridiction,Incarceration incarceration,Motif motif) throws SQLException {

       _connection.createStatement().executeUpdate("update Detenu set prenom = '"+detenu.getPrenom()+"', nom = '"+detenu.getNom()+"',date_naissance = '"+Convertisseur.calendarToString(detenu.getDNaiss(),"yyyy-MM-dd")+"',lieu_naissance = '"+detenu.getLieuNaiss()+"' where n_ecrou = '"+detenu.getEcrou()+"' ");
       int response;
       String tmpAff = null;
       String tmpEcrou = null;
       String tmpJuri = null;
       ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Incarceration where n_ecrou = '"+detenu.getEcrou()+"'");
       rs.beforeFirst();
       boolean present = false;
       if(rs.next()){
           tmpAff = rs.getString("n_affaire");
           tmpEcrou = rs.getString("n_ecrou");
           tmpJuri = rs.getString("nom_juridiction");
           if(affaire.get_n_affaire().equals(rs.getString("n_affaire"))){
               present = true;
           }
       }
       
       
       if(!present){
            if(getAffaire(affaire.get_n_affaire())){
                if(JuridictionInAffaire(affaire.get_n_affaire(),juridiction.getNom())){
                    if(SameDetenuAffaire(detenu.getEcrou(),affaire.get_n_affaire(),getNameJuridictionOfAffaire(affaire.get_n_affaire()))){
                        _connection.createStatement().executeUpdate("delete from Detenu_Affaire where n_ecrou = '"+tmpEcrou+"' and n_affaire = '"+tmpAff+"'"); 
                        _connection.createStatement().executeUpdate("update Incarceration set n_affaire = '"+affaire.get_n_affaire()+"',nom_juridiction='"+juridiction.getNom()+"',date_incarceration = '"+Convertisseur.calendarToString(incarceration.getDate(),"yyyy-MM-dd")+"',n_motif = '"+motif.getMotif()+"' where n_ecrou = '"+detenu.getEcrou()+"'");
                    } else {
                        _connection.createStatement().executeUpdate("insert into Detenu_Affaire values('"+detenu.getEcrou()+"','"+affaire.get_n_affaire()+"','"+juridiction.getNom()+"')");
                        _connection.createStatement().executeUpdate("update Incarceration set n_affaire = '"+affaire.get_n_affaire()+"',nom_juridiction='"+juridiction.getNom()+"',date_incarceration = '"+Convertisseur.calendarToString(incarceration.getDate(),"yyyy-MM-dd")+"',n_motif = '"+motif.getMotif()+"' where n_ecrou = '"+detenu.getEcrou()+"'");
                        _connection.createStatement().executeUpdate("delete from Detenu_Affaire where n_ecrou = '"+tmpEcrou+"' and n_affaire = '"+tmpAff+"'"); 
                    }
                    response = 0;
                } else {
                    if(SameDetenuAffaire(detenu.getEcrou(),affaire.get_n_affaire(),getNameJuridictionOfAffaire(affaire.get_n_affaire()))){
                        _connection.createStatement().executeUpdate("delete from Detenu_Affaire where n_ecrou = '"+tmpEcrou+"' and n_affaire = '"+tmpAff+"' and nom_juridiction = '"+tmpJuri+"'"); 
                        _connection.createStatement().executeUpdate("update Incarceration set n_affaire = '"+affaire.get_n_affaire()+"',nom_juridiction='"+getNameJuridictionOfAffaire(affaire.get_n_affaire())+"',date_incarceration = '"+Convertisseur.calendarToString(incarceration.getDate(),"yyyy-MM-dd")+"',n_motif = '"+motif.getMotif()+"' where n_ecrou = '"+detenu.getEcrou()+"'");
                    } else {
                        _connection.createStatement().executeUpdate("insert into Detenu_Affaire values('"+detenu.getEcrou()+"','"+affaire.get_n_affaire()+"','"+getNameJuridictionOfAffaire(affaire.get_n_affaire())+"')");
                        _connection.createStatement().executeUpdate("update Incarceration set n_affaire = '"+affaire.get_n_affaire()+"',nom_juridiction='"+getNameJuridictionOfAffaire(affaire.get_n_affaire())+"',date_incarceration = '"+Convertisseur.calendarToString(incarceration.getDate(),"yyyy-MM-dd")+"',n_motif = '"+motif.getMotif()+"' where n_ecrou = '"+detenu.getEcrou()+"'");
                        _connection.createStatement().executeUpdate("delete from Detenu_Affaire where n_ecrou = '"+tmpEcrou+"' and n_affaire = '"+tmpAff+"' and nom_juridiction = '"+tmpJuri+"'"); 
                    }
                     response = 1;
                }
            } else {
                _connection.createStatement().executeUpdate("insert into Affaire values('"+affaire.get_n_affaire()+"','"+juridiction.getNom()+"',DATE('"+Convertisseur.calendarToString(affaire.get_date_faits(),"yyyy-MM-dd")+"'))");
                _connection.createStatement().executeUpdate("insert into Detenu_Affaire values('"+detenu.getEcrou()+"','"+affaire.get_n_affaire()+"','"+juridiction.getNom()+"')");
                _connection.createStatement().executeUpdate("update Incarceration set n_affaire = '"+affaire.get_n_affaire()+"',nom_juridiction='"+juridiction.getNom()+"',date_incarceration = '"+Convertisseur.calendarToString(incarceration.getDate(),"yyyy-MM-dd")+"',n_motif = '"+motif.getMotif()+"' where n_ecrou = '"+detenu.getEcrou()+"'");
                _connection.createStatement().executeUpdate("delete from Detenu_Affaire where n_ecrou = '"+tmpEcrou+"' and n_affaire = '"+tmpAff+"' and nom_juridiction = '"+tmpJuri+"'");

                response = 2;
            }    
       } else {
           response = 3;
       }
       

        //A VOIR _connection.createStatement().executeUpdate("update Affaire set n_affaire = '"+affaire.getAffaire()+"', nom_juridiction = '"+juridiction.getNom()+"',date_faits = '"+Convertisseur.calendarToString(affaire.getDate(),"yyyy-MM-dd")+"' where Affaire.n_affaire = (select d.n_affaire from Detenu_Affaire d where d.n_ecrou = '"+detenu.getEcrou()+"')");
      // _connection.createStatement().executeUpdate("update Detenu_Affaire set n_affaire = '"+affaire.getAffaire()+"', nom_juridiction = '"+juridiction.getNom()+"' where n_ecrou = '"+detenu.getEcrou()+"'");
       _connection.commit();
       return(response);
   }


   public void DeletePrisonnier(String ecrou) throws SQLException{

       ResultSet rs;



       rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Reduction_peine where n_ecrou = '"+ecrou+"'");
       rs.beforeFirst();
       if(rs.next())
           _connection.createStatement().executeUpdate("delete from Reduction_peine where n_ecrou = '"+ecrou+"'");

       rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Liberation_definitive where n_ecrou = '"+ecrou+"'");
       rs.beforeFirst();
       if(rs.next())
           _connection.createStatement().executeUpdate("delete from Liberation_definitive where n_ecrou = '"+ecrou+"'");


       rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Decision where n_ecrou = '"+ecrou+"'");
       rs.beforeFirst();
       if(rs.next()){
           _connection.createStatement().executeUpdate("delete from Condamnation where n_ecrou = '"+ecrou+"'");
           _connection.createStatement().executeUpdate("delete from Decision where n_ecrou = '"+ecrou+"'");
       }

       rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu where n_ecrou = '"+ecrou+"'");
       rs.beforeFirst();
       if(rs.next()){
            _connection.createStatement().executeUpdate("delete from Incarceration where n_ecrou = '"+ecrou+"'");
            _connection.createStatement().executeUpdate("delete from Detenu_Affaire where n_ecrou = '"+ecrou+"'");
            _connection.createStatement().executeUpdate("delete from Detenu where n_ecrou = '"+ecrou+"' ");
       }

       _connection.commit();

   }


      /*********************************************************************************************************/

                                                //ajout reduction peine ( reducpeinecontroller )
   /**********************************************************************************************************/

   public void reducPeine(int duree,String ecrou,ProgressIndicator indicator,TextField t1, TextField t2) throws SQLException, ParseException{

       
       _connection.createStatement().execute("insert into Decision values('2' ,'"+ecrou+"',DATE('"+Convertisseur.calendarToString(java.util.Calendar.getInstance(),"yyyy-MM-dd")+"'))");
       _connection.createStatement().execute("insert into Reduction_peine values ('2' ,'"+ecrou+"',DATE('"+Convertisseur.calendarToString(java.util.Calendar.getInstance(),"yyyy-MM-dd")+"'),"+duree+") ");
       indicator.setProgress(indicator.getProgress()+0.1f);
        ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select date_liberation from Liberation_definitive where n_ecrou = '"+ecrou+"'");
        Calendar cal = Calendar.getInstance();
        rs.beforeFirst();
        if(rs.next()){
              cal = Convertisseur.stringToCalendar(rs.getString("date_liberation"), "yyyy-MM-dd");
              t1.setText(Convertisseur.calendarToString(cal, "yyyy-MM-dd"));
        }
       indicator.setProgress(indicator.getProgress()+0.1f);
       
       
       indicator.setProgress(indicator.getProgress()+0.1f);
       cal.add(Calendar.MONTH,-(duree));
       _connection.createStatement().executeUpdate("update Liberation_definitive set date_liberation = '"+Convertisseur.calendarToString(cal,"yyyy-MM-dd")+"' where n_ecrou = '"+ecrou+"' ");
       indicator.setProgress(indicator.getProgress()+0.1f);
       rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Liberation_definitive where Liberation_definitive.n_ecrou = '"+ecrou+"'  ");
       indicator.setProgress(indicator.getProgress()+0.1f);
       rs.beforeFirst();
       if(rs.next()){
               cal = Convertisseur.stringToCalendar(rs.getString("date_liberation"), "yyyy-MM-dd");
               t2.setText(Convertisseur.calendarToString(cal,"yyyy-MM-dd"));
               
       }
       indicator.setProgress(indicator.getProgress()+0.2f);
       indicator.setProgress(indicator.getProgress()+0.1f);
        _connection.commit();
   }



   public void condamnation(int duree,String ecrou,ProgressIndicator indicator) throws SQLException{
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,duree);
       _connection.createStatement().execute("insert into Decision values('1' ,'"+ecrou+"',DATE('"+Convertisseur.calendarToString(java.util.Calendar.getInstance(),"yyyy-MM-dd")+"'))");
       _connection.createStatement().execute("insert into Condamnation values ('1' ,'"+ecrou+"',DATE('"+Convertisseur.calendarToString(java.util.Calendar.getInstance(),"yyyy-MM-dd")+"'),"+duree+") ");
       _connection.createStatement().execute("insert into Decision values('3' ,'"+ecrou+"',DATE('"+Convertisseur.calendarToString(java.util.Calendar.getInstance(),"yyyy-MM-dd")+"'))");
       _connection.createStatement().execute("insert into Liberation_definitive values ('3' ,'"+ecrou+"',DATE('"+Convertisseur.calendarToString(java.util.Calendar.getInstance(),"yyyy-MM-dd")+"'),DATE('"+Convertisseur.calendarToString(calendar,"yyyy-MM-dd")+"')) ");
       indicator.setProgress(indicator.getProgress()+1.0f);
       _connection.commit();
   }

   
   public void AjoutCondamnation(String ecrou, String affaire, String juridiction,String date) throws SQLException{
                   ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire where n_affaire ='"+affaire+"' ");
                   System.out.println("time 2 : "+date);
                   rs.beforeFirst();
                   if(rs.next()){
                     _connection.createStatement().execute("insert into Detenu_Affaire values('"+ecrou+"','"+affaire+"','"+rs.getString("nom_juridiction")+"')");
                     
                   }
                      
                   else {
                       _connection.createStatement().execute("insert into Affaire values('"+affaire+"','"+juridiction+"',DATE('"+date+"'))");
                       _connection.createStatement().execute("insert into Detenu_Affaire values('"+ecrou+"','"+affaire+"','"+juridiction+"')");
                   }    
                   _connection.commit();


   }

   public ArrayList<Affaire> getArrays() throws SQLException{
                System.out.println("Je rentre ici");
                 ResultSet   rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire");
                 ArrayList<Affaire> liste = new ArrayList<>();
                 rs.beforeFirst();
                 while(rs.next()){
                     Affaire affaire = new Affaire(rs.getString("n_affaire"),rs.getString("nom_juridiction"),Convertisseur.stringToCalendar(rs.getString("date_faits"),"yyyy-MM-dd"));
                     liste.add(affaire);
                 }
                 //System.out.println("Taille affaire : "+liste.size());
                 return(liste);
   }
   
   public ArrayList<Detenu> getArray(int sql) throws SQLException, ParseException{

       ResultSet rs;
       if (sql == 1)
             rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu where Detenu.n_ecrou NOT IN( select D.n_ecrou from Detenu D, Condamnation C where D.n_ecrou = C.n_ecrou)");
       else if( sql == 2 )
             rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu d, Condamnation c where d.n_ecrou IN ( select D.n_ecrou from Detenu D, Condamnation C where D.n_ecrou = C.n_ecrou) ");
       else
             rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu ");
        java.util.Calendar datenaiss = Calendar.getInstance();
        ArrayList<Detenu> liste  = new ArrayList<Detenu>();
       rs.beforeFirst();
       while(rs.next()){
           datenaiss = Convertisseur.stringToCalendar(rs.getString("date_naissance"), "yyyy-MM-dd");
           Detenu det = new Detenu(rs.getString("n_ecrou"),rs.getString("prenom"),rs.getString("nom"),datenaiss,rs.getString("lieu_naissance"));
           liste.add(det);
       }
       return liste;
   }


   public ArrayList<String> getPrisonnier (String ecrou) throws SQLException{
       ResultSet rs;
       ArrayList<String> liste = new ArrayList<String>();
        rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire where Affaire.n_affaire = (select d.n_affaire from Incarceration d where d.n_ecrou = '"+ecrou+"')");
       rs.beforeFirst();
       if(rs.next()){
           liste.add(rs.getString("n_affaire"));
           liste.add(rs.getString("nom_juridiction"));
           liste.add(rs.getString("date_faits"));
           rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Incarceration where Incarceration.n_ecrou = '"+ecrou+"'");
           rs.beforeFirst();
           if(rs.next()){
            liste.add(rs.getString("date_incarceration"));
               liste.add(rs.getString("n_motif"));
           }
       }

       return liste;
   }


   public ArrayList<Detenu> searchOnDatabase(String ecrou,int choix) throws SQLException, ParseException {
       ArrayList<Detenu> liste = new ArrayList();
       ArrayList<Detenu> list = new ArrayList();
       if(choix == 1)
            liste = this.getArray(1);
       else if(choix == 2)
           liste = this.getArray(2);
       else
           liste = this.getArray(3);
       if(!liste.isEmpty()){
           
       
       int i = 0;
       boolean continuer = true;
       while(continuer && i < liste.size() ){
           if(liste.get(i).getEcrou().equals(ecrou)){
               list.add(liste.get(i));
               continuer = false;
           } else {
               i++;
           }

       }
       }
       if(!list.isEmpty()) {
           return list;
       } else {
           return(list);
       }
   }
   
   public ArrayList<Detenu> RecherchePredictive (String text ) throws SQLException{
       ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu where n_ecrou like '"+text+"%' or prenom like '"+text+"%' or nom like '"+text+"%' or lieu_naissance like '"+text+"%'");    
   
       ArrayList<Detenu> liste = new ArrayList<>();
       java.util.Calendar datenaiss = Calendar.getInstance();
       rs.beforeFirst();
       while(rs.next()){
           datenaiss = Convertisseur.stringToCalendar(rs.getString("date_naissance"),"yyyy-MM-dd");
           Detenu det = new Detenu(rs.getString("n_ecrou"),rs.getString("prenom"),rs.getString("nom"),datenaiss,rs.getString("lieu_naissance"));
           liste.add(det);
       }
       return(liste);
   }

   
   public ArrayList<String> getYearIncar() throws SQLException {
       ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select YEAR(date_incarceration) from Incarceration");
       
       rs.beforeFirst();
       
       ArrayList<String> list = new ArrayList<String>();
       
       while(rs.next())
           list.add(rs.getString(1));
  
       return list;
   }
   
   public boolean ReducToday(String ecrou,String date) throws SQLException{
              ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Reduction_peine where date_decision = '"+date+"' and n_ecrou = '"+ecrou+"'");
              boolean continuer = false;
              rs.beforeFirst();
              if(rs.next())
                  continuer = true;
              
              return continuer;
              
              
   }
   
   public boolean getAffaire(String affaire) throws SQLException{
                     ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire where n_affaire = '"+affaire+"'");
                     boolean continuer = false;
                     rs.beforeFirst();
                     if(rs.next())
                         continuer = true;
                     
                     return continuer;
   }
   
   private boolean JuridictionInAffaire(String affaire, String juridiction) throws SQLException {
        ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire where n_affaire = '"+affaire+"'");
        boolean continuer = false;
        rs.beforeFirst();
        if(rs.next()){
            return(juridiction.equals(rs.getString("nom_juridiction")));
        }
        return false;
   }
   
   private String getNameJuridictionOfAffaire(String affaire) throws SQLException{
       ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Affaire where n_affaire = '"+affaire+"'");
        boolean continuer = false;
        rs.beforeFirst();
        if(rs.next()){
            return(rs.getString("nom_juridiction"));
        }
        return "";
   }
  
   public boolean FindPrisonnier(String ecrou) throws SQLException{
        ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu where n_ecrou = '"+ecrou+"'");
        rs.beforeFirst();
        if(rs.next())
            return true;
        return false;
   }
   
    private boolean SameDetenuAffaire(String ecrou, String affaire, String juri) throws SQLException{
        ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu_Affaire where n_affaire = '"+affaire+"' and n_ecrou = '"+ecrou+"' and nom_juridiction = '"+juri+"'");
        rs.beforeFirst();
        if(rs.next())
            return true;
        return false;
    }
    
    public ArrayList<Detenu> getAllPrisonnerInCase(String affaire, String juridiction) throws SQLException {
        ArrayList<Detenu> liste = new ArrayList<Detenu>();
        ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu where n_ecrou = (select n_ecrou from Detenu_Affaire where n_affaire = '"+affaire+"' and nom_juridiction = '"+juridiction+"')");
        rs.beforeFirst();
        while(rs.next()){
            Detenu det = new Detenu(rs.getString("n_ecrou"),rs.getString("prenom"),rs.getString("nom"),Convertisseur.stringToCalendar(rs.getString("date_naissance"),"yyyy-MM-dd"),rs.getString("lieu_naissance"));
            liste.add(det);
        }
        
        return liste;
    }
    
}
