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
            java.sql.Statement statement = java.sql.DriverManager.getConnection("jdbc:derby:Banking_system_database;create=true").createStatement();
            // Create table 
           
        } catch (java.sql.SQLException sqle1) {
            System.err.println("'Banking_system_database' probably already exists? " + sqle1.getMessage());
            java.sql.Connection connection;
            java.sql.Statement clean_up;
            try {
                connection = java.sql.DriverManager.getConnection("jdbc:derby:Banking_system_database");
                clean_up = connection.createStatement();
                clean_up.execute("delete from GoCO");
                clean_up.execute("delete from GonyPO");
                clean_up.execute("delete from GoaVA");
                clean_up.execute("delete from GonyVA");
                connection.commit();
            } catch (java.sql.SQLException sqle2) {
                System.err.println("'Banking_system_database' persistent error: " + sqle2.getMessage());
                System.exit(-1);
            }
        }
       
       
    }
}
