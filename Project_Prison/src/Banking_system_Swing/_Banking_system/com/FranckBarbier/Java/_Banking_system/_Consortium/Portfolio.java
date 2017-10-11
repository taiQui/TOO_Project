package com.FranckBarbier.Java._Banking_system._Consortium;

import java.sql.*;

public class Portfolio {

    private java.util.LinkedList _account = new java.util.LinkedList();
    /**
     * JDBC
     */
    private ResultSet _portofolio;
    private ResultSet _portofolio_first;

    public Portfolio() {
    }

    public Portfolio(Authorisation authorisation, Connection database) throws SQLException {
        _portofolio = database.createStatement().executeQuery("SELECT bank_code,account_number FROM Accessibility WHERE card_number = ANY (SELECT card_number FROM Plastic_card WHERE serial_number = '" + authorisation.unique() + "')");
        while (_portofolio.next()) {
            _account.add(new Account(_portofolio.getString("bank_code"), _portofolio.getString("account_number")));
        }
        _portofolio_first = database.createStatement().executeQuery("SELECT bank_code,account_number FROM Default_account WHERE bank_code = ANY (SELECT bank_code FROM Accessibility WHERE card_number = ANY (SELECT card_number FROM Plastic_card WHERE serial_number = '" + authorisation.unique() + "')) AND account_number = ANY (SELECT account_number FROM Accessibility WHERE card_number = ANY (SELECT card_number FROM Plastic_card WHERE serial_number = '" + authorisation.unique() + "'))");
        if (_portofolio_first.next()) {
            _account.addFirst(_account.remove(_account.indexOf(new Account(_portofolio_first.getString("bank_code"), _portofolio_first.getString("account_number")))));
        }
        _portofolio.close();
        _portofolio_first.close();
    }

    public java.util.LinkedList account() {
        return _account;
    }
}