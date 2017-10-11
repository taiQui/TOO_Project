package com.FranckBarbier.Java._Banking_system._Consortium;

public class Banking_system_database {

    public final static byte Microsoft_Access = -128;
    public final static byte Oracle_ODBC = -127;
    public final static byte Oracle_thin = -126;
    public final static byte Oracle_OCI = -125;
    public final static byte Oracle_DataSource = -124;
    public final static byte Java_DB = -123;
    // 'Banking_system' must be set up as a data source within Microsoft Windows:
    private final static String _ODBC_connection = "jdbc:odbc:Banking_system";
    // Non ODBC:
    private final static String _Oracle_thin_connection = "jdbc:oracle:thin:@scinfr099.univ-pau.fr:1521:etud10";
    private final static String _Oracle_OCI_connection = "jdbc:oracle:oci:barbier/fbarb@Banking_system";
    private byte _RDBMS;
    private java.sql.Connection _connection;

    public java.sql.Connection activate_connection() throws Exception {
        if (_connection == null) {
            throw new Exception("Fatal error: JDBC not initialized");
        }
        return _connection;
    }

    public void disactivate_connection() throws Exception {
        if (_connection == null) {
            throw new Exception("Fatal error: JDBC not initialized");
        }
        _connection.commit();
        _connection.close();
    }

    public Banking_system_database(byte RDBMS) throws java.sql.SQLException {
        _RDBMS = RDBMS;
        // JDBC-ODBC bridge driver is obsolete:
//        if (_RDBMS == Microsoft_Access) {
//            DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver()); // JDBC-ODBC bridge driver
//            _connection = DriverManager.getConnection(_ODBC_connection, "barbier", "lena150496");
//            _connection.setAutoCommit(false);
//        }
//        if (_RDBMS == Oracle_ODBC) {
//            DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver()); // JDBC-ODBC bridge driver
//            _connection = DriverManager.getConnection(_ODBC_connection, "barbier", "lena150496");
//            _connection.setAutoCommit(false);
//        }
        if (_RDBMS == Oracle_thin) {
            java.sql.DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // file "ojdbc14.jar" must be added to CLASSPATH
            _connection = java.sql.DriverManager.getConnection(_Oracle_thin_connection, "barbier", "fbarb");
            _connection.setAutoCommit(false);
        }
        if (_RDBMS == Oracle_OCI) {
            java.sql.DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // file "ojdbc14.jar" must be added to CLASSPATH
            _connection = java.sql.DriverManager.getConnection(_Oracle_OCI_connection/*,"barbier","fbarb"*/);
            _connection.setAutoCommit(false);
        }
        if (_RDBMS == Oracle_DataSource) {
            // Code below is not portable. It enables the possibility to avoid JNDI
            oracle.jdbc.pool.OracleDataSource data_source = new oracle.jdbc.pool.OracleDataSource(); // file "ojdbc14.jar" must be added to CLASSPATH
            data_source.setURL("jdbc:oracle:thin:@scinfr099.univ-pau.fr:1521:etud10");
            data_source.setUser("barbier");
            data_source.setPassword("fbarb");
            _connection = data_source.getConnection();
            _connection.setAutoCommit(false);
        }
//        javax.sql.ConnectionPoolDataSource data_source = new oracle.jdbc.pool.OracleConnectionPoolDataSource();
//        javax.sql.PooledConnection pooled_connection = data_source.getPooledConnection();
//        _connection = pooled_connection.getConnection();
        if (_RDBMS == Java_DB) {
            java.sql.DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver()); // Java DB Driver must be added as a "NetBeans library" in the project
            _connection = java.sql.DriverManager.getConnection("jdbc:derby://localhost:1527/Banking_system_database", "FranckBarbier", "lena150496");
            _connection.setAutoCommit(false);
        }
    }

    public boolean Microsoft_Access() {
        return _RDBMS == Microsoft_Access;
    }

    public boolean Oracle() {
        return _RDBMS == Oracle_ODBC || _RDBMS == Oracle_thin || _RDBMS == Oracle_OCI || _RDBMS == Oracle_DataSource;
    }

    public boolean Java_DB() {
        return _RDBMS == Java_DB;
    }
}
