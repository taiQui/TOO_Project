package com.FranckBarbier.Java._Banking_system._Consortium._Events;

/**
 *
 * @author Franck BARBIER
 */
import com.FranckBarbier.Java._Banking_system._Consortium.Consortium;

public class Response_to_to_be_processed_ok_event extends java.util.EventObject {

    public Response_to_to_be_processed_ok_event(Consortium consortium) {
        super(consortium);
    }
}
