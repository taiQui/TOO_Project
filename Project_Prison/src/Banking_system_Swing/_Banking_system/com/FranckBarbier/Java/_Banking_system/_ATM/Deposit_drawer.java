package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

public class Deposit_drawer {

    protected ATM _atm;
    protected AbstractStatechart _Idle;
    protected AbstractStatechart _Out_of_order;
    protected AbstractStatechart _Working;
    protected AbstractStatechart_monitor _Deposit_drawer_state_machine;

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
        _Deposit_drawer_state_machine = new Statechart_monitor((_Idle.xor(_Out_of_order)).xor(_Working), "Deposit drawer", AbstractStatechart_monitor.Show_on_system_out, pv);
    }

    protected void start() throws Statechart_exception {
        _Deposit_drawer_state_machine.fires("breakdown", _Working, _Out_of_order, true, _atm, "deposit_drawer_breakdown");
        _Deposit_drawer_state_machine.fires("go_on", _Working, _Idle, true, _atm, "deposit_done");
        _Deposit_drawer_state_machine.fires("repairing", _Out_of_order, _Idle);
        _Deposit_drawer_state_machine.fires("to_be_deposited", _Idle, _Working, true, this, "go_on", null, AbstractStatechart.Reentrance);
        _Deposit_drawer_state_machine.start();
    }

    protected void stop() throws Statechart_exception {
        _Deposit_drawer_state_machine.stop();
    }

    public Deposit_drawer(ATM atm) throws Statechart_exception {
        init_structure(atm);
        init_behavior();
    }

    public void breakdown() throws Statechart_exception {
        _Deposit_drawer_state_machine.run_to_completion("breakdown");
    }

    public void go_on() throws Statechart_exception {
        _Deposit_drawer_state_machine.run_to_completion("go_on");
    }

    public void repairing() throws Statechart_exception {
        _Deposit_drawer_state_machine.run_to_completion("repairing");
    }

    public void to_be_deposited() throws Statechart_exception {
        _Deposit_drawer_state_machine.run_to_completion("to_be_deposited");
    }

    public void activate_device() {
        _atm._user_interface.activate_deposit_drawer();
    }
}
