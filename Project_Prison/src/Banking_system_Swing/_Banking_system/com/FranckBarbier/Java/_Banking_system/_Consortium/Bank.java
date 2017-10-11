package com.FranckBarbier.Java._Banking_system._Consortium;

import java.sql.*;

import com.pauware.pauware_engine._Exception.Statechart_exception;

import com.FranckBarbier.Java._Banking_system._Consortium._Events.*;

public class Bank implements com.pauware.pauware_engine._Core.Unique {

    protected final String _bank_code;
    protected java.util.ArrayList _consortium = new java.util.ArrayList();

    public boolean equals(Object bank) {
        if (this == bank) {
            return true;
        }
        if (bank instanceof Bank) {
            return _bank_code == null ? false : unique().equals(((Bank) bank).unique());
        }
        return false;
    }

    public int hashCode() {
        return _bank_code == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _bank_code;
    }
    /**
     * JDBC
     */
    protected Banking_system_database _database;
    protected PreparedStatement _authorisation_to_be_checked;
    protected PreparedStatement _authorisation_checked;
    protected PreparedStatement _operation_to_be_processed;
    protected PreparedStatement _operation_to_be_canceled;

    public Bank(String bank_code, Consortium consortium, Banking_system_database database) throws Exception {
        _bank_code = bank_code;
        _consortium.add(consortium);
        _database = database;
        // The SQL statements which follow are all overspecified in order to prevent any incoherent situation in the database
        _authorisation_to_be_checked = _database.activate_connection().prepareStatement("DELETE FROM GonyVA WHERE station_code = ? AND serial_number = ? AND serial_number = ANY (SELECT serial_number FROM Plastic_card WHERE card_number = ANY (SELECT card_number FROM Logical_card WHERE bank_code = ?))");
        _authorisation_checked = _database.activate_connection().prepareStatement("INSERT INTO GoaVA VALUES(?,?)");
        _operation_to_be_processed = _database.activate_connection().prepareStatement("DELETE FROM GonyPO WHERE serial_number = ? AND operation = ? AND serial_number = ANY (SELECT serial_number FROM Plastic_card WHERE card_number = ANY (SELECT card_number FROM Logical_card WHERE bank_code = ?))");
        _operation_to_be_canceled = _database.activate_connection().prepareStatement("DELETE FROM GoCO WHERE serial_number = ? AND operation = ? AND serial_number = ANY (SELECT serial_number FROM Plastic_card WHERE card_number = ANY (SELECT card_number FROM Logical_card WHERE bank_code = ?))");
    }

    public void authorisation_to_be_checked(String station_code, Authorisation authorisation) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                authorisation_checked(new Authorisation_checked_event(this, new Portfolio(), "Fatal error (authorisation_to_be_checked):\nJDBC connection abnormally closed"));
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GonyVA IN EXCLUSIVE MODE");
            }
            _authorisation_to_be_checked.setString(1, station_code);
            _authorisation_to_be_checked.setString(2, authorisation.unique());
            _authorisation_to_be_checked.setString(3, _bank_code);
            _authorisation_to_be_checked.execute();
            if (_authorisation_to_be_checked.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                }
                authorisation_checked(station_code, authorisation);
            } else {
                if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
                authorisation_checked(new Authorisation_checked_event(this, new Portfolio(), "Error (authorisation_to_be_checked):\nJDBC failure"));
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            authorisation_checked(new Authorisation_checked_event(this, new Portfolio(), "Fatal error (authorisation_to_be_checked):\n" + sqle.getMessage()));
        }
    }

    protected void authorisation_checked(String station_code, Authorisation authorisation) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                authorisation_checked(new Authorisation_checked_event(this, new Portfolio(), "Fatal error (authorisation_checked):\nJDBC connection abnormally closed"));
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GoaVA IN EXCLUSIVE MODE");
            }
            _authorisation_checked.setString(1, station_code);
            _authorisation_checked.setString(2, authorisation.unique());
            _authorisation_checked.execute();
            if (_authorisation_checked.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                }
                authorisation_checked(new Authorisation_checked_event(this, new Portfolio(authorisation, _database.activate_connection()), "no error"));
            } else {
                if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
                authorisation_checked(new Authorisation_checked_event(this, new Portfolio(), "Error (authorisation_checked):\nJDBC failure"));
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            authorisation_checked(new Authorisation_checked_event(this, new Portfolio(), "Fatal error (authorisation_checked):\n" + sqle.getMessage()));
        }
    }

    protected void authorisation_checked(Authorisation_checked_event authorisation_checked_event) throws Statechart_exception {
        java.util.Iterator i = _consortium.iterator();
        while (i.hasNext()) {
            ((Consortium) i.next()).authorisation_checked(authorisation_checked_event.portfolio(), authorisation_checked_event.text());
        }
    }

    public void operation_to_be_processed(Authorisation authorisation, String operation) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                operation_processed(new Operation_processed_event(this, "Fatal error (operation_to_be_processed):\nJDBC connection abnormally closed"));
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GonyPO IN EXCLUSIVE MODE");
            }
            _operation_to_be_processed.setString(1, authorisation.unique());
            _operation_to_be_processed.setString(2, operation);
            _operation_to_be_processed.setString(3, _bank_code);
            _operation_to_be_processed.execute();
            if (_operation_to_be_processed.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                }
                operation_processed(new Operation_processed_event(this, "no error"));
            } else {
                if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
                operation_processed(new Operation_processed_event(this, "Error (operation_to_be_processed):\nJDBC failure"));
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            operation_processed(new Operation_processed_event(this, "Fatal error (operation_to_be_processed):\n" + sqle.getMessage()));
        }
    }

    protected void operation_processed(Operation_processed_event operation_processed_event) throws Statechart_exception {
        java.util.Iterator i = _consortium.iterator();
        while (i.hasNext()) {
            ((Consortium) i.next()).operation_processed();
        }
    }

    public void operation_to_be_canceled(Authorisation authorisation, String operation) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                throw new Exception("Fatal error (operation_to_be_canceled):\nJDBC connection abnormally closed");
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GoCO IN EXCLUSIVE MODE");
            }
            _operation_to_be_canceled.setString(1, authorisation.unique());
            _operation_to_be_canceled.setString(2, operation);
            _operation_to_be_canceled.setString(3, _bank_code);
            _operation_to_be_canceled.execute();
            if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                if (_operation_to_be_canceled.getWarnings() == null) {
                    if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                        _database.activate_connection().commit();
                    } else if (_database.activate_connection().getTransactionIsolation() != Connection.TRANSACTION_NONE) {
                        _database.activate_connection().rollback();
                    }
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new Exception("Fatal error (operation_to_be_canceled):\n" + sqle.getMessage());
        }
    }
}
