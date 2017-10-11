package com.FranckBarbier.Java._Banking_system._Consortium._Events;

/**
 *
 * @author Franck BARBIER
 */
import com.FranckBarbier.Java._Banking_system._Consortium.Bank;

public class Operation_processed_event extends java.util.EventObject {

    private final String _text;

    public Operation_processed_event(Bank bank, String text) {
        super(bank);
        _text = text;
    }

    public String text() {
        return _text;
    }
}
