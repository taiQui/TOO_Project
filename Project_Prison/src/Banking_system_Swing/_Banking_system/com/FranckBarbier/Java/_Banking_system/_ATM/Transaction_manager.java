package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

public final class Transaction_manager {

    private final java.util.LinkedList _Transactions = new java.util.LinkedList();

    public Transaction current() {
        assert (!_Transactions.isEmpty());
        return (Transaction) _Transactions.getLast();
    }

    public void start(ATM controller) throws Statechart_exception {
        if (!_Transactions.isEmpty()) {
            ((Transaction) _Transactions.getLast()).stop();
        }
        _Transactions.addLast(new Transaction(controller));
        ((Transaction) _Transactions.getLast()).start();
    }

    public void empty_() throws Statechart_exception {
        assert (!_Transactions.isEmpty());
        ((Transaction) _Transactions.getLast()).empty_();
    }

    public void error(String text) throws Statechart_exception {
        assert (!_Transactions.isEmpty());
        ((Transaction) _Transactions.getLast()).error(text);
    }

    public void to_be_added(Operation operation) throws Statechart_exception {
        assert (!_Transactions.isEmpty());
        ((Transaction) _Transactions.getLast()).to_be_added(operation);
    }

    public void to_be_recorded() throws Statechart_exception {
        assert (!_Transactions.isEmpty());
        ((Transaction) _Transactions.getLast()).to_be_recorded();
    }
}
