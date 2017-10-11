package com.FranckBarbier.Java._Banking_system._Consortium;

import com.pauware.pauware_engine._Exception.Statechart_exception;

import com.FranckBarbier.Java._Banking_system._ATM.*;
import com.FranckBarbier.Java._Banking_system._Consortium._Events.*;

public class Consortium {

    protected final ATM _atm;
    protected java.util.Hashtable _bank = new java.util.Hashtable();
    /**
     * JDBC
     */
    protected Banking_system_database _database;
    protected java.sql.PreparedStatement _start_of;
    protected java.sql.PreparedStatement _to_be_processed;
    protected java.sql.PreparedStatement _to_be_canceled;
    protected java.sql.PreparedStatement _end_of;

    public Consortium(ATM atm) throws Exception {
        _atm = atm;
        _database = new Banking_system_database(Banking_system_database.Java_DB);
        _start_of = _database.activate_connection().prepareStatement("INSERT INTO GonyVA VALUES(?,?,?)");
        _to_be_processed = _database.activate_connection().prepareStatement("INSERT INTO GonyPO VALUES(?,?,?,?)");
        _to_be_canceled = _database.activate_connection().prepareStatement("INSERT INTO GoCO VALUES(?,?,?)");
        _end_of = _database.activate_connection().prepareStatement("DELETE FROM GoaVA WHERE station_code = ? AND serial_number = ?");
    }

    public void stop() throws Exception {
        _database.disactivate_connection();
    }

    public void start_of(String station_code, Authorisation authorisation, java.sql.Timestamp now) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                response_to_start_of_not_ok(new Response_to_start_of_not_ok_event(this, "Fatal error (start_of):\nJDBC connection abnormally closed"));
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GonyVA IN EXCLUSIVE MODE");
            }
            _start_of.setString(1, station_code);
            _start_of.setString(2, authorisation.unique());
            _start_of.setTimestamp(3, now);
            _start_of.execute();
            if (_start_of.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                }
                if (_bank.get(authorisation.bank_code()) == null) {
                    _bank.put(authorisation.bank_code(), new Bank(authorisation.bank_code(), this, _database));
                }
                ((Bank) _bank.get(authorisation.bank_code())).authorisation_to_be_checked(station_code, authorisation);
            } else {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
                response_to_start_of_not_ok(new Response_to_start_of_not_ok_event(this, "Error (start_of):\nJDBC failure"));
            }
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
            response_to_start_of_not_ok(new Response_to_start_of_not_ok_event(this, "Fatal error (start_of):\n" + sqle.getMessage()));
        }
    }

    public void authorisation_checked(Portfolio portfolio, String text) throws Statechart_exception {
        if (!portfolio.account().isEmpty() && text.equals("no error")) {
            response_to_start_of_ok(new Response_to_start_of_ok_event(this, portfolio));
        }
        if (portfolio.account().isEmpty()) {
            response_to_start_of_not_ok(new Response_to_start_of_not_ok_event(this, "Portfolio is empty"));
        }
        if (!text.equals("no error")) {
            response_to_start_of_not_ok(new Response_to_start_of_not_ok_event(this, text));
        }
    }

    protected void response_to_start_of_not_ok(Response_to_start_of_not_ok_event response_to_start_of_not_ok_event) throws Statechart_exception {
        _atm.response_to_start_of_not_ok(response_to_start_of_not_ok_event.text());
    }

    protected void response_to_start_of_ok(Response_to_start_of_ok_event response_to_start_of_ok_event) throws Statechart_exception {
        _atm.response_to_start_of_ok(response_to_start_of_ok_event.portfolio());
    }

    public void to_be_processed(String station_code, Authorisation authorisation, Operation operation, java.sql.Timestamp now) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                response_to_to_be_processed_not_ok(new Response_to_to_be_processed_not_ok_event(this, "Fatal error (to_be_processed):\nJDBC connection abnormally closed"));
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GonyPO IN EXCLUSIVE MODE");
            }
            _to_be_processed.setString(1, station_code);
            _to_be_processed.setString(2, authorisation.unique());
            _to_be_processed.setString(3, operation.toString());
            _to_be_processed.setTimestamp(4, now);
            _to_be_processed.execute();
            if (_to_be_processed.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                }
                if (_bank.get(authorisation.bank_code()) == null) {
                    _bank.put(authorisation.bank_code(), new Bank(authorisation.bank_code(), this, _database));
                }
                ((Bank) _bank.get(authorisation.bank_code())).operation_to_be_processed(authorisation, operation.toString());
            } else {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
                response_to_to_be_processed_not_ok(new Response_to_to_be_processed_not_ok_event(this, "Error (to_be_processed):\nJDBC failure"));
            }
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
            response_to_to_be_processed_not_ok(new Response_to_to_be_processed_not_ok_event(this, "Fatal error (to_be_processed):\n" + sqle.getMessage()));
        }
    }

    public void operation_processed() throws Statechart_exception {
        response_to_to_be_processed_ok(new Response_to_to_be_processed_ok_event(this));
    }

    protected void response_to_to_be_processed_not_ok(Response_to_to_be_processed_not_ok_event response_to_to_be_processed_not_ok_event) throws Statechart_exception {
        _atm.response_to_to_be_processed_not_ok(response_to_to_be_processed_not_ok_event.text());
    }

    protected void response_to_to_be_processed_ok(Response_to_to_be_processed_ok_event response_to_to_be_processed_ok_event) throws Statechart_exception {
        _atm.response_to_to_be_processed_ok();
    }

    public void to_be_canceled(String station_code, Authorisation authorisation, Operation operation) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                throw new Exception("Fatal error (to_be_canceled):\nJDBC connection abnormally closed");
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GoCO IN EXCLUSIVE MODE");
            }
            _to_be_canceled.setString(1, station_code);
            _to_be_canceled.setString(2, authorisation.unique());
            _to_be_canceled.setString(3, operation.toString());
            _to_be_canceled.execute();
            if (_to_be_canceled.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                }
                if (_bank.get(authorisation.bank_code()) == null) {
                    _bank.put(authorisation.bank_code(), new Bank(authorisation.bank_code(), this, _database));
                }
                ((Bank) _bank.get(authorisation.bank_code())).operation_to_be_canceled(authorisation, operation.toString());
            } else {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
            }
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new Exception("Fatal error (to_be_canceled):\n" + sqle.getMessage());
        }
    }

    public void end_of(String station_code, Authorisation authorisation) throws Exception {
        try {
            if (_database.activate_connection().isClosed()) {
                throw new Exception("Fatal error (end_of):\nJDBC connection abnormally closed");
            }
            if (_database.Oracle()) {
                _database.activate_connection().createStatement().execute("LOCK TABLE GoaVA IN EXCLUSIVE MODE");
            }
            _end_of.setString(1, station_code);
            _end_of.setString(2, authorisation.unique());
            _end_of.execute();
            if (_end_of.getWarnings() == null) {
                if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().commit();
                } else if (_database.activate_connection().getTransactionIsolation() != java.sql.Connection.TRANSACTION_NONE) {
                    _database.activate_connection().rollback();
                }
            }
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new Exception("Fatal error (end_of):\n" + sqle.getMessage());
        }
    }
}
