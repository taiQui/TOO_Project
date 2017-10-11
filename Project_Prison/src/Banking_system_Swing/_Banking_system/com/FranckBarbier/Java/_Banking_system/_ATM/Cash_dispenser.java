package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

public class Cash_dispenser {

    protected ATM _atm;
    protected int _cash_on_hand;
    protected int _dispensed;
    protected AbstractStatechart _Idle;
    protected AbstractStatechart _Out_of_order;
    protected AbstractStatechart _Working;
    protected AbstractStatechart_monitor _Card_dispenser_state_machine;

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
        _Card_dispenser_state_machine = new Statechart_monitor((_Idle.xor(_Out_of_order)).xor(_Working), "Cash dispenser", AbstractStatechart_monitor.Show_on_system_out, pv);
    }

    protected void start(int cash) throws Statechart_exception {
        /**
         * <initialization> post-condition(s)
         */
        set_cash_on_hand(cash);
        set_dispensed();
        _Card_dispenser_state_machine.fires("breakdown", _Working, _Out_of_order, true, _atm, "cash_dispenser_breakdown");
        _Card_dispenser_state_machine.fires("repairing", _Out_of_order, _Idle);
        _Card_dispenser_state_machine.fires("reset", _Idle, _Idle, true, this, "set_dispensed");
        _Card_dispenser_state_machine.fires("to_be_dispensed", _Idle, _Working, true, this, "withdrawal_realized", null, AbstractStatechart.Reentrance);
        _Card_dispenser_state_machine.fires("withdrawal_realized", _Working, _Idle, true, _atm, "withdrawal_done");
        _Card_dispenser_state_machine.start();
    }

    protected void stop() throws Statechart_exception {
        _Card_dispenser_state_machine.stop();
    }

    public Cash_dispenser(ATM atm) throws Statechart_exception {
        init_structure(atm);
        init_behavior();
    }

    public void breakdown() throws Statechart_exception {
        _Card_dispenser_state_machine.run_to_completion("breakdown");
    }

    public void enough_(Integer amount) throws Statechart_exception {
        boolean guard = _cash_on_hand < _dispensed + amount.intValue();
        _Card_dispenser_state_machine.fires("enough_", _Idle, _Idle, guard, _atm, "not_enough_cash");
        guard = _cash_on_hand >= _dispensed + amount.intValue();
        _Card_dispenser_state_machine.fires("enough_", _Idle, _Idle, guard, _atm, "enough_cash");
        _Card_dispenser_state_machine.run_to_completion("enough_");
    }

    public void repairing() throws Statechart_exception {
        _Card_dispenser_state_machine.run_to_completion("repairing");
    }

    public void reset(int cash) throws Statechart_exception {
        _Card_dispenser_state_machine.fires("reset", _Idle, _Idle, true, this, "set_cash_on_hand", new Object[]{Integer.valueOf(cash)});
        _Card_dispenser_state_machine.run_to_completion("reset");
    }

    public void to_be_dispensed(Integer amount) throws Statechart_exception {
        /**
         * post-condition(s)
         */
        _dispensed += amount.intValue();
        _Card_dispenser_state_machine.run_to_completion("to_be_dispensed");
    }

    public void withdrawal_realized() throws Statechart_exception {
        _Card_dispenser_state_machine.run_to_completion("withdrawal_realized");
    }

    public void set_cash_on_hand(int cash) {
        _cash_on_hand = cash;
    }

    public void set_dispensed() {
        _dispensed = 0;
    }

    public void activate_device() {
        _atm._user_interface.activate_cash_dispenser();
    }
}
