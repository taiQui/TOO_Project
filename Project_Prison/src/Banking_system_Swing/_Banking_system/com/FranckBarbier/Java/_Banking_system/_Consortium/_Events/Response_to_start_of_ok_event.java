package com.FranckBarbier.Java._Banking_system._Consortium._Events;

/**
 *
 * @author Franck BARBIER
 */
import com.FranckBarbier.Java._Banking_system._Consortium.Consortium;
import com.FranckBarbier.Java._Banking_system._Consortium.Portfolio;

public class Response_to_start_of_ok_event extends java.util.EventObject {

    private final Portfolio _portfolio;

    public Response_to_start_of_ok_event(Consortium consortium, Portfolio portfolio) {
        super(consortium);
        _portfolio = portfolio;
    }

    public Portfolio portfolio() {
        return _portfolio;
    }
}
