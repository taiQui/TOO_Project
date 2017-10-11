package com.FranckBarbier.Java._Banking_system._Consortium._Events;

/**
 *
 * @author Franck BARBIER
 */
import com.FranckBarbier.Java._Banking_system._Consortium.Consortium;

public class Response_to_start_of_not_ok_event extends java.util.EventObject {

    private final String _text;

    public Response_to_start_of_not_ok_event(Consortium consortium, String text) {
        super(consortium);
        _text = text;
    }

    public String text() {
        return _text;
    }
}
