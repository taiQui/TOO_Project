package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

import com.FranckBarbier.Java._Banking_system._Consortium.Authorisation;
import com.FranckBarbier.Java._Banking_system._Consortium.Plastic_card;

public class Card_reader extends Timer_monitor {

    protected ATM _atm;
    Plastic_card _reading_zone; // _stock;
    protected AbstractStatechart _Busy;
    protected AbstractStatechart _Ejecting;
    protected AbstractStatechart _Storing;
    protected AbstractStatechart _Idle;
    protected AbstractStatechart _Out_of_order;
    protected AbstractStatechart _Working;
    protected AbstractStatechart_monitor _Card_reader_state_machine;
    Plastic_card _plastic_card;

    private void init_structure(ATM atm) throws Statechart_exception {
        _atm = atm;
        _plastic_card = null;
        _reading_zone = null;
    }

    private void init_behavior() throws Statechart_exception {
        _Busy = new Statechart("Busy");
        _Ejecting = new Statechart("Ejecting");
        _Ejecting.set_entryAction(this, "to_be_set", new Object[]{_Ejecting, Long.valueOf(5000)});
        _Ejecting.set_exitAction(this, "to_be_killed", new Object[]{_Ejecting});
        _Storing = new Statechart("Storing");
        _Storing.outputState();
        _Storing.doActivity(this, "store_card");
        _Storing.set_exitAction(_atm, "card_put_down");
        _Idle = new Statechart("Idle").set_exitAction(this, "swallow_card");
        _Idle.inputState();
        _Out_of_order = new Statechart("Out of order");
        _Working = ((_Busy.xor(_Ejecting)).xor(_Storing)).name("Working");

        AbstractStatechart_monitor_listener pv = null;
        // Toggle comment to activate PauWare view (and link 'PauWare_view' NetBeans project as library):
//        pv = new com.pauware.pauware_view.PauWare_view();
        _Card_reader_state_machine = new Statechart_monitor((_Idle.xor(_Out_of_order)).xor(_Working), "Card reader", AbstractStatechart_monitor.Show_on_system_out, pv);
    }

    void start() throws Statechart_exception {
        _Card_reader_state_machine.fires("breakdown", _Ejecting, _Out_of_order, true, _atm, "card_reader_breakdown");
        _Card_reader_state_machine.fires("card_inserted", _Idle, _Busy, this, "read_card_OK", _atm, "card_read", new Object[]{new Authorisation(null)});
        _Card_reader_state_machine.fires("card_inserted", _Idle, _Busy, this, "read_card_not_OK", _atm, "card_unreadable");
        _Card_reader_state_machine.fires("card_taken", _Ejecting, _Idle, true, _atm, "card_removed");
        _Card_reader_state_machine.fires("card_to_be_ejected", _Busy, _Ejecting, true, this, "eject_card");
        _Card_reader_state_machine.fires("card_to_be_put_down", _Busy, _Storing);
        _Card_reader_state_machine.fires(AbstractStatechart.Completion, _Working, _Idle);
        _Card_reader_state_machine.fires("repairing", _Out_of_order, _Idle);
        _Card_reader_state_machine.fires("time_out", _Ejecting, _Storing, true, this, "swallow_card");
        _Card_reader_state_machine.fires("time_out", _Ejecting, _Storing);
        _Card_reader_state_machine.start();
    }

    void stop() throws Statechart_exception {
        _Card_reader_state_machine.stop();
    }

    public Card_reader(ATM atm) throws Statechart_exception {
        init_structure(atm);
        init_behavior();
    }

    public void breakdown() throws Statechart_exception {
        _Card_reader_state_machine.run_to_completion("breakdown");
    }

    public void card_inserted(Plastic_card plastic_card) throws Statechart_exception {
        /**
         * Post-condition(s)
         */
        _plastic_card = plastic_card;
        /**
         * End of post-condition(s)
         */
        _Card_reader_state_machine.fires("card_inserted", _Idle, _Busy, this, "read_card_OK", _atm, "card_read", new Object[]{new Authorisation(_plastic_card)});
        _Card_reader_state_machine.run_to_completion("card_inserted");
    }

    public void card_taken() throws Statechart_exception {
        _Card_reader_state_machine.run_to_completion("card_taken");
    }

    public void card_to_be_ejected() throws Statechart_exception {
        _Card_reader_state_machine.run_to_completion("card_to_be_ejected");
    }

    public void card_to_be_put_down() throws Statechart_exception {
        _Card_reader_state_machine.run_to_completion("card_to_be_put_down");
    }

    public void repairing() throws Statechart_exception {
        _Card_reader_state_machine.run_to_completion("repairing");
    }

    public void time_out(long delay, AbstractStatechart context) throws Statechart_exception {
        _Card_reader_state_machine.run_to_completion("time_out");
    }

    public void time_out_error(Statechart_exception se) throws Statechart_exception {
        se.printStackTrace();
        // possible fault recovery process here...
    }

    public void eject_card() {
        _reading_zone = null;
    }

    public void swallow_card() {
        _reading_zone = _plastic_card;
    }

    public void store_card() {
        /**
         * serialization here
         */
        _reading_zone = null;
    }

    public boolean read_card_OK() {
        return !read_card_not_OK();
    }

    public boolean read_card_not_OK() {
        // One-in-ten chance of failure
        return new java.util.Random().nextInt(10) == 0;
    }
}
