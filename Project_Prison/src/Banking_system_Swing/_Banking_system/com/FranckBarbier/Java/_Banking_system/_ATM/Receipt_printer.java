package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

public class Receipt_printer {

    protected ATM _atm;
    protected AbstractStatechart _Idle;
    protected AbstractStatechart _Out_of_order;
    protected AbstractStatechart _Working;
    protected AbstractStatechart_monitor _Receipt_printer_state_machine;

    private void init_structure(ATM atm) throws Statechart_exception {
        _atm = atm;
    }

    private void init_behavior() throws Statechart_exception {
        _Idle = new Statechart("Idle");
        _Idle.inputState();
        _Out_of_order = new Statechart("Out of order");
        _Working = new Statechart("Working").doActivity(this, "activate_device");

        AbstractStatechart_monitor_listener pv = null;
        // Toggle comment to activate PauWare view (and link 'PauWare_view' NetBeans project as library):
//        pv = new com.pauware.pauware_view.PauWare_view();
        _Receipt_printer_state_machine = new Statechart_monitor((_Idle.xor(_Out_of_order)).xor(_Working), "Receipt printer", AbstractStatechart_monitor.Show_on_system_out, pv);
    }

    protected void start() throws Statechart_exception {
        _Receipt_printer_state_machine.fires("breakdown", _Working, _Out_of_order, true, _atm, "deposit_drawer_breakdown");
        _Receipt_printer_state_machine.fires("go_on", _Working, _Idle);
        _Receipt_printer_state_machine.fires("repairing", _Out_of_order, _Idle);
        _Receipt_printer_state_machine.fires("to_be_printed", _Idle, _Working, true, this, "go_on", null, AbstractStatechart.Reentrance);
        _Receipt_printer_state_machine.start();
    }

    protected void stop() throws Statechart_exception {
        _Receipt_printer_state_machine.stop();
    }

    public Receipt_printer(ATM atm) throws Statechart_exception {
        init_structure(atm);
        init_behavior();
    }

    public void breakdown() throws Statechart_exception {
        _Receipt_printer_state_machine.run_to_completion("breakdown");
    }

    public void go_on() throws Statechart_exception {
        _Receipt_printer_state_machine.run_to_completion("go_on");
    }

    public void repairing() throws Statechart_exception {
        _Receipt_printer_state_machine.run_to_completion("repairing");
    }

    public void to_be_printed(Transaction_manager transaction_manager) throws Statechart_exception {
        Transaction transaction = transaction_manager.current();
        _Receipt_printer_state_machine.run_to_completion("to_be_printed");
    }

    public void activate_device() {
        _atm._user_interface.activate_receipt_printer();
    }
}
