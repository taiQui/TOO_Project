/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prison_project;

/**
 *
 * @author greg1
 */
public class bank_database {
    static {
        try {
            java.sql.DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            java.sql.Statement statement = java.sql.DriverManager.getConnection("jdbc:derby:bank_database;create=true").createStatement();
            // Create table 
            statement.execute("create table Detenu(\n"
                    + "n_ecrou varchar(10),\n"
                    + "prenom varchar(30),\n"
                    + "nom varchar(30),\n"
                    + "date_naissance Date,\n"
                    + "lieu_naissance varchar(30),\n"
                    + "constraint Detenu_key primary key(n_ecrou));\n"
            );
                    
           
            statement.execute("create table Affaire(\n"
                    + "n_affaire varchar(10),\n"
                    + "nom_juridiction varchar(30),\n"
                    + "date_faits Date,\n"
                    + "constraint Affaire_key primary key(n_affaire,nom_juridiction));\n"
            );
            
            statement.execute("create table Detenu_Affaire(\n"
                    + "n_ecrou varchar(10),\n"
                    + "n_affaire varchar(10),\n"
                    + "nom_juridiction varchar(30),\n"
                    + "constraint Detenu_Affaire_key primary key(n_ecrou,n_affaire,nom_juridiction),\n"
                    + "constraint Detenu_Affaire_foreign_key foreign key(n_ecrou) references Detenu(n_ecrou),\n"
                    + "constraint Detenu_Affaire_foreign_key2 foreign key(n_affaire,nom_juridiction) references Affaire(n_affaire,nom_juridiction);"
            );
            
            statement.execute("create table Motif(\n"
                    + "n_motif varchar(10),\n"
                    + "libelle_motif varchar(50) not null,\n"
                    + "constraint Motif_key primary key(n_motif),\n"
                    + "constraint Motif_unique unique(libelle_motif));\n"
            );
            
            statement.execute("create table Incarceration("
                    + "n_ecrou varchar(10),\n"
                    + "n_affaire varchar(10) not null,\n"
                    + "nom_juridiction varchar(30) not null,\n"
                    + "date_incarceration Date,\n"
                    + "n_motif varchar(10) not null,\n"
                    + "constraint Incarceration_key primary key(n_ecrou),"
                    + "constraint Incarceration_foreign_key foreign key(n_ecrou,n_affaire,nom_juridiction) references Detenu_Affaire(n_ecrou,n_affaire,nom_juridiction),\n"
                    + "constraint Incarceration_foreign_key2 foreign key(n_motif) references Motif(n_motif));"
            );
            
            statement.execute("create table Decision("
                    + "n_type_decision varchar(1),\n"
                    + "n_ecrou varchar(10),\n"
                    + "date_decision Date,\n"
                    + "constraint Decision_key primary key(n_type_decision,n_ecrou,date_decision),\n"
                    + "constraint Decision_fk foreign key(n_ecrou) references Detenu(n_ecrou));"
            );
            
            statement.execute("create table Condamnation("
                    + "n_type_decision varchar(1),\n"
                    + "n_ecrou varchar(10),\n"
                    + "date_decision Date,\n"
                    + "duree Integer,\n"
                    + "constraint Reduction_peine_key primary key(n_type_decision,n_ecrou,date_decision),"
                    + "constraint Reduction_peine_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade);"
            );
            
            statement.execute("create table Liberation_definitive("
                    + "n_type_decision varchar(1),\n"
                    + "n_ecrou varchar(10),\n"
                    + "date_decision Date,\n"
                    + "date_liberation Date,\n"
                    + "constraint Liberation_definitive_key primary key(n_type_decision,n_ecrou,date_decision),"
                    + "constraint Liberation_definitive_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade);"
            );
        } catch (java.sql.SQLException sqle1) {
            System.err.println("'bank_database' probably already exists? " + sqle1.getMessage());
            java.sql.Connection connection;
            java.sql.Statement clean_up;
            try {
                connection = java.sql.DriverManager.getConnection("jdbc:derby:bank_database");
                clean_up = connection.createStatement();
                connection.commit();
            } catch (java.sql.SQLException sqle2) {
                System.err.println("'bank_database' persistent error: " + sqle2.getMessage());
                System.exit(-1);
            }
        }
       
       
    }
    
   private java.sql.Connection _connection;
   
    public bank_database() throws java.sql.SQLException{
       
       _connection = java.sql.DriverManager.getConnection("jdbc:derby:bank_database");
       _connection.setAutoCommit(false);

       
   }
   
   public void addDatabase(String lastName, String firstName, String birthday, String birthplace, String caseNumber, String nameOfOrigin, String exactName, String dayOfImprisonment, String reason, String dayOfFact) throws java.sql.SQLException{
       _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("insert into Prisoner values("+lastName+","+firstName+","+birthday+","+birthplace+","+nameOfOrigin+","+exactName+","+dayOfImprisonment+","+reason+","+dayOfFact+")");
   }
   
   public Data readDatabase() throws java.sql.SQLException {
       Data data = new Data();
       data.setLastName(_connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select LastName from Prisoner ").getString("LastName"));
       data.setFirstName(_connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select FirstName from Prisoner ").getString("FirstName"));
       return(data);
   }
}
   
