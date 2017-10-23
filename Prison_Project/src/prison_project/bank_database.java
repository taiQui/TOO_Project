package prison_project;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
                    +"constraint Detenu_key primary key(n_ecrou))");
            statement.execute("create table Affaire("
                    +"n_affaire varchar(10),"
                    +"nom_juridiction varchar(30),"
                    +"date_faits Date,"
                    +"constraint Affaire_key primary key(n_affaire,nom_juridiction))");
            statement.execute("create table Detenu_Affaire("
                    +"n_ecrou varchar(10),"
                    +"n_affaire varchar(10),"
                    +"nom_juridiction varchar(30),"
                    +"constraint Detenu_Affaire_key primary key(n_ecrou,n_affaire,nom_juridiction),"
                    +"constraint Detenu_Affaire_foreign_key foreign key(n_ecrou) references Detenu(n_ecrou),"
                    +"constraint Detenu_Affaire_foreign_key2 foreign key(n_affaire,nom_juridiction) references Affaire(n_affaire,nom_juridiction))");
            statement.execute("create table Motif("
                    +"n_motif varchar(10),"
                    +"libelle_motif varchar(50) not null,"
                    +"constraint Motif_key primary key(n_motif),"
                    +"constraint Motif_unique unique(libelle_motif))");
            statement.execute("create table Incarceration("
                    +"n_ecrou varchar(10),"
                    +"n_affaire varchar(10) not null,"
                    +"nom_juridiction varchar(30) not null,"
                    +"date_incarceration Date,"
                    +"n_motif varchar(10) not null,"
                    +"constraint Incarceration_key primary key(n_ecrou),"
                    +"constraint Incarceration_foreign_key foreign key(n_ecrou,n_affaire,nom_juridiction) references Detenu_Affaire(n_ecrou,n_affaire,nom_juridiction),"
                    +"constraint Incarceration_foreign_key2 foreign key(n_motif) references Motif(n_motif))");
            statement.execute("create table Decision("
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"constraint Decision_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Decision_fk foreign key(n_ecrou) references Detenu(n_ecrou))");
            statement.execute("create table Condamnation("
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"duree Integer,"
                    +"constraint Condamnation_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Condamnation_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade)");

            statement.execute("create table Reduction_peine("
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"duree Integer,"
                    +"constraint Reduction_peine_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Reduction_peine_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade)");
            statement.execute("create table Liberation_definitive("
                    +"n_type_decision varchar(1),"
                    +"n_ecrou varchar(10),"
                    +"date_decision Date,"
                    +"date_liberation Date,"
                    +"constraint Liberation_definitive_key primary key(n_type_decision,n_ecrou,date_decision),"
                    +"constraint Liberation_definitive_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade)");

            statement.execute("INSERT INTO Detenu VALUES('1963','Franck','Barbier',DATE('1963-01-11'),'Montbeliard')");
            statement.execute("INSERT INTO Detenu VALUES('1964','Sophie','Darnal',DATE(1964-07-28),'Besancon')");


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

    public bank_database() throws java.sql.SQLException{
       System.out.println("Constructor database");
       _connection = java.sql.DriverManager.getConnection("jdbc:derby:bank_database");
       _connection.setAutoCommit(false);
       resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu ");
       resultset.beforeFirst();
       while(resultset.next()){
        System.out.println(resultset.getString("prenom"));

       }



   }

    public java.sql.Connection activate_connection() throws Exception {
        if (_connection == null) {
            System.out.println("testActivateConnection");
            throw new Exception("Fatal error: JDBC not initialized");
        }
        return _connection;
    }

   public void addPrisionnierToDatabase(Detenu detenu,Affaire affaire,Juridiction juridiction,Incarceration incarceration,Motif motif) throws java.sql.SQLException{
       _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Detenu values("+detenu.getEcrou()+","+detenu.getPrenom()+","+detenu.getNom()+","+detenu.getDNaiss()+","+detenu.getLieuNaiss()+")");
       _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Affaire values("+affaire.getAffaire()+","+affaire.getDate()+")");
       _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Juridiction values("+juridiction.getNom()+")");
       _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Incarceration values("+incarceration.getDate()+")");
       _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Motif values("+motif.getMotif()+", test )");
      // _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Affaire values("+data.getN()+","+data.getFirstName()+","+data.getLastName()+","+data.getBirthday()+","+data.getBirthplace()+")");
   }

   public ResultSet readPrisonnierToDatabase() throws java.sql.SQLException {
      // Data data = new Data();
      // data.setLastName((_connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select prenom from Detenu ")).getString("prenom"));
       //data.setFirstName((_connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select FirstName from Prisoner ")).getString("FirstName"));

       ResultSet rs = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Detenu ");
       rs.beforeFirst();
      //int i = 0;
       
      //while(rs.next()){
        //   i++;     
          // System.out.println("i = " + i + " : "+rs.getString(i));
                
            //}
           
         
         //System.out.println("test4");
       
         
         
       return(rs);
   }
}
