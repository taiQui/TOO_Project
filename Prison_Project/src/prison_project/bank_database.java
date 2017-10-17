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
            statement.execute("create table Prisoner(\n"
                    + "PrisonRegisterNumber int(5),\n"
                    + "FirstName varchar(20),\n"
                    + "LastName varchar(20),\n"
                    + "Birthday DATE(),\n"
                    + "Birthplace varchar(20),\n"
                    + "CaseNumber int(10),\n"
                    + "NameOfOriginCourt varchar(20),\n"
                    + "ExactNameOfJuridiction varchar(20),\n"
                    + "DayOfImprisonment DATE(),\n"
                    + "Reason varchar(50),\n"
                    + "DayOfFact DATE(),\n"
                    + "constraint Prisoner_Key primary key(PrisonRegisterNumber))");
                    
           
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
   
