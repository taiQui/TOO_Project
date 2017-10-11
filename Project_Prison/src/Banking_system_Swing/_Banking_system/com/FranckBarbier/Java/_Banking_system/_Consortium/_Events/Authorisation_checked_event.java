package com.FranckBarbier.Java._Banking_system._Consortium._Events;

/**
 *
 * @author Franck BARBIER
 */
import com.FranckBarbier.Java._Banking_system._Consortium.Bank;
import com.FranckBarbier.Java._Banking_system._Consortium.Portfolio;

public class Authorisation_checked_event extends java.util.EventObject {

    private final Portfolio _portfolio;
    private final String _text;

    public Authorisation_checked_event(Bank bank, Portfolio portfolio, String text) {
        super(bank);
        _portfolio = portfolio;
        _text = text;
    }

    public Portfolio portfolio() {
        return _portfolio;
    }

    public String text() {
        return _text;
    }
}
