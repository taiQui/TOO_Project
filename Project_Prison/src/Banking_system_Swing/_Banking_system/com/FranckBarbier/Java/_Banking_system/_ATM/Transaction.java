package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

public final class Transaction /* implements java.io.Serializable */ {

    String _error_message = "no error";
    ATM _controller;
    java.sql.Time _start;
    final java.util.LinkedList _operation = new java.util.LinkedList();
    AbstractStatechart _Empty;
    AbstractStatechart _Not_empty;
    AbstractStatechart _Active;
    AbstractStatechart _Recorded;
    AbstractStatechart_monitor _Transaction_state_machine;

    private void init_structure(ATM controller) throws Statechart_exception {
        _controller = controller;
        _start = new java.sql.Time(java.util.GregorianCalendar.getInstance().getTimeInMillis());
    }

    private void init_behavior() throws Statechart_exception {
        _Empty = new Statechart("Empty");
        _Empty.inputState();
        _Empty.allowedEvent("error", this, "set_error_message", new Object[]{""});
        _Not_empty = new Statechart("Not empty");
        _Active = new Statechart("Active");
        _Active.inputState();
        _Recorded = new Statechart("Recorded");
        _Recorded.outputState();

        AbstractStatechart_monitor_listener pv = null;
        // Toggle comment to activate PauWare view (and link 'PauWare_view' NetBeans project as library):
//        pv = new com.pauware.pauware_view.PauWare_view();
        _Transaction_state_machine = new Statechart_monitor((_Empty.xor(_Not_empty)).and(_Active.xor(_Recorded)), "Transaction-" + _start.toString(), AbstractStatechart_monitor.Show_on_system_out, pv);
    }

    public void start() throws Statechart_exception {
        _Transaction_state_machine.fires("empty_", _Empty, _Empty, true, _controller, "transaction_empty");
        _Transaction_state_machine.fires("empty_", _Not_empty, _Not_empty, true, _controller, "transaction_not_empty");
        _Transaction_state_machine.fires("to_be_added", _Empty, _Not_empty);
        _Transaction_state_machine.fires("to_be_recorded", _Active, _Recorded);
        _Transaction_state_machine.start();
    }

    public void stop() throws Statechart_exception {
        _Transaction_state_machine.stop();
    }

    Transaction(ATM controller) throws Statechart_exception {
        init_structure(controller);
        init_behavior();
    }

    public void empty_() throws Statechart_exception {
        assert (_controller != null);
        _Transaction_state_machine.run_to_completion("empty_");
    }

    public void error(String text) throws Statechart_exception {
        assert (_controller != null);
        _Empty.allowedEvent("error", this, "set_error_message", new Object[]{text});
        _Transaction_state_machine.run_to_completion("error");
    }

    public void to_be_added(Operation operation) throws Statechart_exception {
        assert (_controller != null);
        /**
         * post-condition(s)
         */
        _operation.addFirst(operation);
        _Transaction_state_machine.run_to_completion("to_be_added");
    }

    public void to_be_recorded() throws Statechart_exception {
        assert (_controller != null);
        /**
         * post-condition(s)
         */
        _controller = null;
//        java.util.ListIterator i = _operation.listIterator();
//        while (i.hasNext()) {
//            System.out.println("to_be_recorded: " + ((Operation) i.next()).toString());
//        }
        _Transaction_state_machine.run_to_completion("to_be_recorded");
    }

    public void set_error_message(String text) {
        _error_message = text;
    }
}
