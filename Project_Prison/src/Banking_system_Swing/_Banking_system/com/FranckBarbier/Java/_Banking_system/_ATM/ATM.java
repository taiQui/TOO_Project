package com.FranckBarbier.Java._Banking_system._ATM;

import com.pauware.pauware_engine._Core.*;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import com.pauware.pauware_engine._Java_EE.*;

import com.FranckBarbier.Java._Banking_system._Consortium.*;

public class ATM extends Timer_monitor implements Unique {

    protected User_interface _user_interface;

    public void add_user_interface(User_interface user_interface) {
        _user_interface = user_interface;
    }
    protected String _station_code;

    public boolean equals(Object atm) {
        if (this == atm) {
            return true;
        }
        if (atm instanceof ATM) {
            return _station_code == null ? false : unique().equals(((ATM) atm).unique());
        }
        return false;
    }

    public int hashCode() {
        return _station_code == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _station_code;
    }
    protected Card_reader _card_reader;
    protected Cash_dispenser _cash_dispenser;
    protected Transaction_manager _transaction_manager = new Transaction_manager();
    protected Consortium _consortium;
    protected Deposit_drawer _deposit_drawer;
    protected Receipt_printer _receipt_printer;
//    protected java.util.LinkedList _transaction /*= new java.util.LinkedList()*/;
    protected AbstractStatechart _Out_of_order;
    protected AbstractStatechart _Working;
    protected AbstractStatechart _First_delaying;
    protected AbstractStatechart _Brief_end;
    protected AbstractStatechart _Start;
    protected AbstractStatechart _Printing;
    protected AbstractStatechart _Password_request;
    protected AbstractStatechart _Transaction_verification;
    protected AbstractStatechart _Suspicious_end;
    protected AbstractStatechart _Second_delaying;
    protected AbstractStatechart _End;
    protected AbstractStatechart _Transaction_in_progress;
    protected AbstractStatechart _Amount_choice;
    protected AbstractStatechart _Operation_kind_choice;
    protected AbstractStatechart _Account_choice;
    protected AbstractStatechart _Amount_verification;
    protected AbstractStatechart _Transaction_end_choice;
    protected AbstractStatechart _Operation_verification;
    protected AbstractStatechart _Withdrawal_in_progress;
    protected AbstractStatechart _Deposit_in_progress;
    protected AbstractStatechart _Third_delaying;
    protected AbstractStatechart_monitor _ATM;
    protected Account _account;
    protected boolean _again;
    protected int _amount;
    protected byte _amount_choice_try;
    protected byte _attempt_;
    protected Authorisation _authorisation;
    protected boolean _breakdown;
    protected Operation _operation;
    protected byte _operation_kind;
    protected Portfolio _portfolio;
    protected Account _transfer_account;
    protected boolean _transfer_end;

    private void init_structure(final String station_code) throws Statechart_exception, Exception {
        _card_reader = new Card_reader(this);
        _cash_dispenser = new Cash_dispenser(this);
        _consortium = new Consortium(this);
        _deposit_drawer = new Deposit_drawer(this);
        _receipt_printer = new Receipt_printer(this);
        _station_code = station_code;
    }

    private void init_behavior() throws Statechart_exception {
        _Out_of_order = (new Statechart("Out of order")).set_entryAction(this, "display_out_of_order");
        _First_delaying = new Statechart("First delaying");
        _First_delaying.set_entryAction(this, "to_be_set", new Object[]{_First_delaying, Long.valueOf(5000)});
        _First_delaying.set_exitAction(this, "to_be_killed", new Object[]{_First_delaying});
        _Brief_end = (new Statechart("Brief_end")).set_entryAction(_card_reader, "card_to_be_ejected");
        _Brief_end.set_entryAction(this, "display_take_card", null);
        _Start = (new Statechart("Start")).set_entryAction(this, "display_insert_card");
        _Start.inputState();
        _Printing = new Statechart("Printing");
        _Password_request = new Statechart("Password request");
        _Password_request.set_entryAction(this, "to_be_set", new Object[]{_Password_request, Long.valueOf(15000)});
        _Password_request.set_entryAction(this, "display_enter_password");
        _Password_request.set_exitAction(this, "to_be_killed", new Object[]{_Password_request});
        _Transaction_verification = new Statechart("Transaction verification");
        _Transaction_verification.set_entryAction(this, "to_be_set", new Object[]{_Transaction_verification, Long.valueOf(5000)});
        _Transaction_verification.set_entryAction(this, "display_transaction_starts_");
        _Transaction_verification.set_exitAction(this, "to_be_killed", new Object[]{_Transaction_verification});
        _Suspicious_end = (new Statechart("Suspicious end")).set_entryAction(_card_reader, "card_to_be_put_down");
        _Suspicious_end.set_exitAction(_transaction_manager, "to_be_recorded");
        _Second_delaying = (new Statechart("Second delaying")).set_exitAction(this, "to_be_killed", new Object[]{_Transaction_verification});
        _Second_delaying.set_exitAction(this, "to_be_killed", new Object[]{_Password_request});
        _End = (new Statechart("End")).set_entryAction(_card_reader, "card_to_be_ejected");
        _End.set_entryAction(this, "display_take_card", null);
        _End.set_exitAction(_transaction_manager, "to_be_recorded");
        _Amount_choice = new Statechart("Amount choice");
        _Amount_choice.set_entryAction(this, "to_be_set", new Object[]{_Amount_choice, Long.valueOf(15000)});
        _Amount_choice.set_entryAction(this, "display_choose_amount");
        _Amount_choice.set_exitAction(this, "to_be_killed", new Object[]{_Amount_choice});
        _Operation_kind_choice = (new Statechart("Operation kind choice")).set_entryAction(this, "display_choose_operation_kind");
        _Account_choice = (new Statechart("Account choice")).set_entryAction(this, "display_choose_account");
        _Amount_verification = new Statechart("Amount verification");
        _Transaction_end_choice = (new Statechart("Transaction end choice")).set_entryAction(this, "display_choose_continuation_or_termination");
        _Operation_verification = new Statechart("Operation verification");
        _Operation_verification.set_entryAction(this, "to_be_set", new Object[]{_Operation_verification, Long.valueOf(5000)});
        _Operation_verification.set_entryAction(this, "display_operation_starts_");
        _Operation_verification.set_exitAction(this, "to_be_killed", new Object[]{_Operation_verification});
        _Withdrawal_in_progress = new Statechart("Withdrawal in progress");
        _Deposit_in_progress = new Statechart("Deposit in progress");
        _Third_delaying = new Statechart("Third delaying");
        _Third_delaying.set_entryAction(this, "to_be_set", new Object[]{_Third_delaying, Long.valueOf(5000)});
        _Third_delaying.set_exitAction(this, "to_be_killed", new Object[]{_Third_delaying});
        _Transaction_in_progress = (_Amount_choice.xor(_Operation_kind_choice).xor(_Amount_verification).xor(_Account_choice).xor(_Transaction_end_choice).xor(_Operation_verification).xor(_Withdrawal_in_progress).xor(_Deposit_in_progress).xor(_Third_delaying)).name("Transaction in progress");
        _Working = (_First_delaying.xor(_Brief_end).xor(_Start).xor(_Printing).xor(_Password_request).xor(_Transaction_verification).xor(_Suspicious_end).xor(_Second_delaying).xor(_End).xor(_Transaction_in_progress)).name("Working");
        _Working.inputState();

        AbstractStatechart_monitor_listener pv = null;
        // Toggle comment to activate PauWare view (and link 'PauWare_view' NetBeans project as library):
//        pv = new com.pauware.pauware_view.PauWare_view();
        _ATM = new Statechart_monitor((_Out_of_order.xor(_Working)), "ATM", AbstractStatechart_monitor.Show_on_system_out, pv);
    }

    protected void start(final int cash) throws Statechart_exception {
        /**
         * <initialization> post-condition(s)
         */
        set_breakdown(Boolean.FALSE);
        _ATM.fires("abort", _Transaction_in_progress, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("amount chosen", _Amount_choice, _Account_choice, this, "operation_is_Transfer", this, "set_transfer_end", new Object[]{Boolean.FALSE});
        _ATM.fires("amount chosen", _Amount_choice, _Amount_verification, this, "operation_is_Withdrawal", null, _cash_dispenser, "enough_", new Object[]{Integer.valueOf(_amount)}, Statechart_monitor.Reentrance);
        _ATM.fires("amount chosen", _Amount_choice, _Account_choice, this, "operation_is_Deposit");
        _ATM.fires("card put down", _Brief_end, _Start, true, this, "display_card_put_down");
        _ATM.fires("card put down", _Suspicious_end, _Start, true, this, "display_card_put_down");
        _ATM.fires("card put down", _End, _Start, this, "breakdown_equal_to_false", this, "display_card_put_down");
        _ATM.fires("card put down", _End, _Out_of_order, this, "breakdown_equal_to_true");
        _ATM.fires("card read", _Start, _Password_request, true, this, "set_attempt_", new Object[]{Byte.valueOf((byte) 1)});
        _ATM.fires("card reader breakdown", _Working, _Out_of_order);
        _ATM.fires("card removed", _Brief_end, _Start);
        _ATM.fires("card removed", _End, _Printing, true, _transaction_manager, "empty_", null, Statechart_monitor.Reentrance);
        _ATM.fires("card unreadable", _Start, _First_delaying, true, this, "display_card_unreadable");
        _ATM.fires("cash dispenser breakdown", _Withdrawal_in_progress, _End, true, this, "set_breakdown", new Object[]{Boolean.TRUE});
        _ATM.fires("cash dispenser breakdown", _Withdrawal_in_progress, _End, true, _consortium, "to_be_canceled", new Object[]{_station_code, _authorisation, _operation});
        _ATM.fires("continuation", _Transaction_end_choice, _Operation_kind_choice);
        _ATM.fires("deposit done", _Deposit_in_progress, _Third_delaying, true, this, "display_account");
        _ATM.fires("deposit drawer breakdown", _Deposit_in_progress, _End, true, this, "set_breakdown", new Object[]{Boolean.TRUE});
        _ATM.fires("deposit drawer breakdown", _Deposit_in_progress, _End, true, _consortium, "to_be_canceled", new Object[]{_station_code, _authorisation, _operation});
        _ATM.fires("enough cash", _Amount_verification, _Account_choice, this, "amount_less_or_equal_to_authorisation_withdrawal_weekly_limit");
        _ATM.fires("enough cash", _Amount_verification, _Third_delaying, this, "amount_greater_than_authorisation_withdrawal_weekly_limit", this, "display_operation_error", new Object[]{"\namount exceeds withdrawal weekly limit"});
        _ATM.fires("enough cash", _Amount_verification, _Third_delaying, this, "amount_greater_than_authorisation_withdrawal_weekly_limit", this, "set_amount_choice_try_to", new Object[]{Byte.valueOf((byte) (_amount_choice_try + 1))});
        _ATM.fires("enough cash", _Amount_verification, _Third_delaying, this, "amount_greater_than_authorisation_withdrawal_weekly_limit", this, "set_again_to", new Object[]{Boolean.valueOf(!_again)});
        _ATM.fires("not enough cash", _Amount_verification, _Third_delaying, true, this, "display_operation_error", new Object[]{"\namount exceeds cash on hand"});
        _ATM.fires("not enough cash", _Amount_verification, _Third_delaying, true, this, "set_amount_choice_try_to", new Object[]{Byte.valueOf((byte) (_amount_choice_try + 1))});
        _ATM.fires("not enough cash", _Amount_verification, _Third_delaying, true, this, "set_again_to", new Object[]{Boolean.valueOf(!_again)});
        _ATM.fires("operation kind chosen", _Operation_kind_choice, _Account_choice, this, "operation_is_Query");
        _ATM.fires("operation kind chosen", _Operation_kind_choice, _Amount_choice, this, "operation_is_not_Query", this, "set_amount_choice_try_to", new Object[]{Byte.valueOf((byte) 1)});
        _ATM.fires("operation kind chosen", _Operation_kind_choice, _Amount_choice, this, "operation_is_not_Query", this, "set_again_to", new Object[]{Boolean.FALSE});
        _ATM.fires("password entered", _Password_request, _Transaction_verification, this, "password_equal_to_authorisation_password", new Object[]{""}, _consortium, "start_of", new Object[]{_station_code, _authorisation, new java.sql.Timestamp(java.util.GregorianCalendar.getInstance().getTimeInMillis())}, Statechart_monitor.Reentrance);
        _ATM.fires("password entered", _Password_request, _Second_delaying, this, "password_not_equal_to_authorisation_password", new Object[]{""}, this, "display_invalid_password");
        _ATM.fires("password entered", _Password_request, _Second_delaying, this, "password_not_equal_to_authorisation_password", new Object[]{""}, this, "to_be_set", new Object[]{_Password_request, Long.valueOf(5000)});
        _ATM.fires("receipt printer breakdown", _Working, _Out_of_order);
        _ATM.fires("repairing", _Out_of_order, _Start, true, this, "set_breakdown", new Object[]{Boolean.FALSE});
        _ATM.fires("repairing", _Out_of_order, _Start, true, _card_reader, "repairing");
        _ATM.fires("repairing", _Out_of_order, _Start, true, _cash_dispenser, "repairing");
        _ATM.fires("repairing", _Out_of_order, _Start, true, _deposit_drawer, "repairing");
        _ATM.fires("repairing", _Out_of_order, _Start, true, _receipt_printer, "repairing");
        _ATM.fires("response to START OF ok", _Transaction_verification, _Operation_kind_choice);
        _ATM.fires("response to START OF not ok", _Transaction_verification, _Second_delaying, true, this, "display_transaction_error", new Object[]{""});
        _ATM.fires("response to START OF not ok", _Transaction_verification, _Second_delaying, true, _transaction_manager, "error", new Object[]{""});
        _ATM.fires("response to START OF not ok", _Transaction_verification, _Second_delaying, true, this, "to_be_set", new Object[]{_Transaction_verification, Long.valueOf(5000)});
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Deposit_in_progress, this, "operation_is_Deposit", null, _deposit_drawer, "to_be_deposited", null, Statechart_monitor.Reentrance);
        /**
         * Fake arguments, i.e., 'new Operation()', for PauWare view:
         */
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Deposit_in_progress, this, "operation_is_Deposit", _transaction_manager, "to_be_added", new Object[]{new Operation()});
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Third_delaying, this, "operation_is_Query_or_Transfer", _transaction_manager, "to_be_added", new Object[]{new Operation()});
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Withdrawal_in_progress, this, "operation_is_Withdrawal", _transaction_manager, "to_be_added", new Object[]{new Operation()});
        /**
         * End of fake arguments
         */
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Third_delaying, this, "operation_is_Query_or_Transfer", this, "display_account");
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Withdrawal_in_progress, this, "operation_is_Withdrawal", null, _cash_dispenser, "to_be_dispensed", new Object[]{Integer.valueOf(_amount)}, Statechart_monitor.Reentrance);
        _ATM.fires("response to TO BE PROCESSED not ok", _Operation_verification, _Third_delaying, true, this, "display_operation_error", new Object[]{""});
        _ATM.fires("termination", _Transaction_end_choice, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("time-out", _First_delaying, _Brief_end);
        _ATM.fires("time-out", _Second_delaying, _Password_request, this, "context_equal_to_Password_request_and_attempt_equal_to_1", new Object[]{_Password_request}, this, "set_attempt_", new Object[]{Byte.valueOf((byte) 2)});
        _ATM.fires("time-out", _Second_delaying, _Suspicious_end, this, "context_equal_to_Password_request_and_attempt_equal_to_2", new Object[]{_Password_request});
        _ATM.fires("time-out", _Second_delaying, _End, this, "context_equal_to_Transaction_verification", new Object[]{_Transaction_verification});
        _ATM.fires("time-out", _Third_delaying, _Amount_choice, this, "amount_choice_try_equal_to_2");
        _ATM.fires("time-out", _Third_delaying, _Transaction_end_choice, this, "amount_choice_try_not_equal_to_2");
        _ATM.fires("time-out", _Password_request, _End);
        _ATM.fires("time-out", _Transaction_verification, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("time-out", _Operation_verification, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("time-out", _Operation_verification, _End, true, _consortium, "to_be_canceled", new Object[]{_station_code, _authorisation, _operation});
        _ATM.fires("time-out", _Amount_choice, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("transaction empty", _Printing, _Out_of_order, this, "breakdown_equal_to_true");
        _ATM.fires("transaction empty", _Printing, _Start, this, "breakdown_equal_to_false");
        _ATM.fires("transaction not empty", _Printing, _Out_of_order, this, "breakdown_equal_to_true", _receipt_printer, "to_be_printed", new Object[]{_transaction_manager});
        _ATM.fires("transaction not empty", _Printing, _Start, this, "breakdown_equal_to_false", _receipt_printer, "to_be_printed", new Object[]{_transaction_manager});
        _ATM.fires("withdrawal done", _Withdrawal_in_progress, _Third_delaying, true, this, "display_account");
        _card_reader.start();
        _cash_dispenser.start(cash);
        _deposit_drawer.start();
        _receipt_printer.start();
        _ATM.start();
    }

    public ATM(final String station_code) throws Statechart_exception, Exception {
        init_structure(station_code);
        init_behavior();
    }

    protected void stop() throws Exception {
        abort();
        _consortium.stop();
        _card_reader.stop();
        _cash_dispenser.stop();
        _deposit_drawer.stop();
        _receipt_printer.stop();
        _ATM.stop();
    }

    synchronized public void abort() throws Statechart_exception {
        _ATM.fires("abort", _Transaction_in_progress, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.run_to_completion("abort");
    }

    synchronized public void account_chosen(Account account) throws Statechart_exception {
        _account = _transfer_end == false ? account : _account;
        _transfer_account = account;
        _ATM.fires("account chosen", _Account_choice, _Account_choice, this, "operation_is_Transfer_and_not_end_transfer", null, this, "set_transfer_end", new Object[]{Boolean.TRUE});
        _ATM.fires("account chosen", _Account_choice, _Third_delaying, this, "operation_is_Transfer_and_end_transfer_and_same_account", null, this, "display_operation_error", new Object[]{"\nsame account in transfer"});
        _operation = new Operation(_amount, _account.unique(), _transfer_account.unique());
        _ATM.fires("account chosen", _Account_choice, _Operation_verification, this, "operation_is_Transfer_and_end_transfer_and_not_same_account", null, _consortium, "to_be_processed", new Object[]{_station_code, _authorisation, _operation, new java.sql.Timestamp(java.util.GregorianCalendar.getInstance().getTimeInMillis())}, Statechart_monitor.Reentrance);
        _operation = new Operation(_operation_kind, _amount, _account.unique());
        _ATM.fires("account chosen", _Account_choice, _Operation_verification, this, "operation_is_not_Transfer", null, _consortium, "to_be_processed", new Object[]{_station_code, _authorisation, _operation, new java.sql.Timestamp(java.util.GregorianCalendar.getInstance().getTimeInMillis())}, Statechart_monitor.Reentrance);
        _ATM.run_to_completion("account chosen");
    }

    synchronized public void amount_chosen(int amount) throws Statechart_exception {
        _amount = amount;
        _ATM.fires("amount chosen", _Amount_choice, _Amount_verification, this, "operation_is_Withdrawal", null, _cash_dispenser, "enough_", new Object[]{Integer.valueOf(_amount)}, Statechart_monitor.Reentrance);
        _ATM.run_to_completion("amount chosen");
    }

    public void card_put_down() throws Statechart_exception {
        _ATM.run_to_completion("card put down");
    }

    public void card_read(Authorisation authorisation) throws Statechart_exception {
        _authorisation = authorisation;
        _transaction_manager.start(this);
        _ATM.run_to_completion("card read");
    }

    public void card_reader_breakdown() throws Statechart_exception {
        _ATM.run_to_completion("card reader breakdown");
    }

    public void card_removed() throws Statechart_exception {
        _ATM.run_to_completion("card removed");
    }

    public void card_unreadable() throws Statechart_exception {
        _ATM.run_to_completion("card unreadable");
    }

    synchronized public void cash_dispenser_breakdown() throws Statechart_exception {
        _ATM.fires("cash dispenser breakdown", _Withdrawal_in_progress, _End, true, _consortium, "to_be_canceled", new Object[]{_station_code, _authorisation, _operation});
        _ATM.run_to_completion("cash dispenser breakdown");
    }

    public void continuation() throws Statechart_exception {
        _ATM.run_to_completion("continuation");
    }

    public void deposit_done() throws Statechart_exception {
        _ATM.run_to_completion("deposit done");
    }

    synchronized public void deposit_drawer_breakdown() throws Statechart_exception {
        _ATM.fires("deposit drawer breakdown", _Deposit_in_progress, _End, true, _consortium, "to_be_canceled", new Object[]{_station_code, _authorisation, _operation});
        _ATM.run_to_completion("deposit drawer breakdown");
    }

    synchronized public void enough_cash() throws Statechart_exception {
        _ATM.fires("enough cash", _Amount_verification, _Third_delaying, this, "amount_greater_than_authorisation_withdrawal_weekly_limit", this, "set_amount_choice_try_to", new Object[]{Byte.valueOf((byte) (_amount_choice_try + 1))});
        _ATM.fires("enough cash", _Amount_verification, _Third_delaying, this, "amount_greater_than_authorisation_withdrawal_weekly_limit", this, "set_again_to", new Object[]{Boolean.valueOf(!_again)});
        _ATM.run_to_completion("enough cash");
    }

    synchronized public void not_enough_cash() throws Statechart_exception {
        _ATM.fires("not enough cash", _Amount_verification, _Third_delaying, true, this, "set_amount_choice_try_to", new Object[]{Byte.valueOf((byte) (_amount_choice_try + 1))});
        _ATM.fires("not enough cash", _Amount_verification, _Third_delaying, true, this, "set_again_to", new Object[]{Boolean.valueOf(!_again)});
        _ATM.run_to_completion("not enough cash");
    }

    public void operation_kind_chosen(byte operation_kind) throws Statechart_exception {
        _operation_kind = operation_kind;
        _ATM.run_to_completion("operation kind chosen");
    }

    synchronized public void password_entered(String password) throws Statechart_exception {
        _ATM.fires("password entered", _Password_request, _Transaction_verification, this, "password_equal_to_authorisation_password", new Object[]{password}, _consortium, "start_of", new Object[]{_station_code, _authorisation, new java.sql.Timestamp(java.util.GregorianCalendar.getInstance().getTimeInMillis())}, Statechart_monitor.Reentrance);
        _ATM.fires("password entered", _Password_request, _Second_delaying, this, "password_not_equal_to_authorisation_password", new Object[]{password}, this, "display_invalid_password");
        _ATM.fires("password entered", _Password_request, _Second_delaying, this, "password_not_equal_to_authorisation_password", new Object[]{password}, this, "to_be_set", new Object[]{_Password_request, Long.valueOf(5000)});
        _ATM.run_to_completion("password entered");
    }

    public void receipt_printer_breakdown() throws Statechart_exception {
        _ATM.run_to_completion("receipt printer breakdown");
    }

    public void repairing() throws Statechart_exception {
        _ATM.run_to_completion("repairing");
    }

    public void response_to_start_of_ok(Portfolio portfolio) throws Statechart_exception {
        _portfolio = portfolio;
        _ATM.run_to_completion("response to START OF ok");
    }

    synchronized public void response_to_start_of_not_ok(String text) throws Statechart_exception {
        _ATM.fires("response to START OF not ok", _Transaction_verification, _Second_delaying, true, this, "display_transaction_error", new Object[]{text});
        _ATM.fires("response to START OF not ok", _Transaction_verification, _Second_delaying, true, _transaction_manager, "error", new Object[]{text});
        _ATM.run_to_completion("response to START OF not ok");
    }

    synchronized public void response_to_to_be_processed_ok() throws Statechart_exception {
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Deposit_in_progress, this, "operation_is_Deposit", _transaction_manager, "to_be_added", new Object[]{_operation});
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Third_delaying, this, "operation_is_Query_or_Transfer", _transaction_manager, "to_be_added", new Object[]{_operation});
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Withdrawal_in_progress, this, "operation_is_Withdrawal", null, _cash_dispenser, "to_be_dispensed", new Object[]{Integer.valueOf(_amount)}, Statechart_monitor.Reentrance);
        _ATM.fires("response to TO BE PROCESSED ok", _Operation_verification, _Withdrawal_in_progress, this, "operation_is_Withdrawal", _transaction_manager, "to_be_added", new Object[]{_operation});
        _ATM.run_to_completion("response to TO BE PROCESSED ok");
    }

    synchronized public void response_to_to_be_processed_not_ok(String text) throws Statechart_exception {
        _ATM.fires("response to TO BE PROCESSED not ok", _Operation_verification, _Third_delaying, true, this, "display_operation_error", new Object[]{text});
        _ATM.run_to_completion("response to TO BE PROCESSED not ok");
    }

    synchronized public void termination() throws Statechart_exception {
        _ATM.fires("termination", _Transaction_end_choice, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.run_to_completion("termination");
    }

    synchronized public void time_out(long delay, AbstractStatechart context) throws Statechart_exception {
        _ATM.fires("time-out", _Second_delaying, _Password_request, this, "context_equal_to_Password_request_and_attempt_equal_to_1", new Object[]{context}, this, "set_attempt_", new Object[]{Byte.valueOf((byte) 2)});
        _ATM.fires("time-out", _Second_delaying, _Suspicious_end, this, "context_equal_to_Password_request_and_attempt_equal_to_2", new Object[]{context});
        _ATM.fires("time-out", _Second_delaying, _End, this, "context_equal_to_Transaction_verification", new Object[]{context});
        _ATM.fires("time-out", _Transaction_verification, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("time-out", _Operation_verification, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.fires("time-out", _Operation_verification, _End, true, _consortium, "to_be_canceled", new Object[]{_station_code, _authorisation, _operation});
        _ATM.fires("time-out", _Amount_choice, _End, true, _consortium, "end_of", new Object[]{_station_code, _authorisation});
        _ATM.run_to_completion("time-out");
    }

    public void time_out_error(Statechart_exception se) throws Statechart_exception {
        se.printStackTrace();
        // possible fault recovery process here...
    }

    public void transaction_empty() throws Statechart_exception {
        _ATM.run_to_completion("transaction empty");
    }

    public void transaction_not_empty() throws Statechart_exception {
        _ATM.run_to_completion("transaction not empty");
    }

    public void withdrawal_done() throws Statechart_exception {
        _ATM.run_to_completion("withdrawal done");
    }

    /**
     * Actions
     */
    public void display_account() {
        _user_interface.display_account();
    }

    public void display_card_put_down() {
        _user_interface.display_card_put_down();
    }

    public void display_card_unreadable() {
        _user_interface.display_card_unreadable();
    }

    public void display_choose_account() {
        _user_interface.display_choose_account();
    }

    public void display_choose_amount() {
        _user_interface.display_choose_amount();
    }

    public void display_choose_continuation_or_termination() {
        _user_interface.display_choose_continuation_or_termination();
    }

    public void display_choose_operation_kind() {
        _user_interface.display_choose_operation_kind();
    }

    public void display_enter_password() {
        _user_interface.display_enter_password();
    }

    public void display_insert_card() {
        _user_interface.display_insert_card();
    }

    public void display_invalid_password() {
        _user_interface.display_invalid_password();
    }

    public void display_operation_error(String text) {
        _user_interface.display_operation_error(text);
    }

    public void display_operation_starts_() {
        _user_interface.display_operation_starts_();
    }

    public void display_out_of_order() {
        _user_interface.display_out_of_order();
    }

    public void display_take_card() {
        _user_interface.display_take_card();
    }

    public void display_transaction_error(String text) {
        _user_interface.display_transaction_error(text);
    }

    public void display_transaction_starts_() {
        _user_interface.display_transaction_starts_();
    }

    public void set_again_to(Boolean breakdown) {
        _again = breakdown.booleanValue();
    }

    public void set_amount_choice_try_to(Byte value) {
        _amount_choice_try = value.byteValue();
    }

    public void set_attempt_(Byte attempt_) {
        _attempt_ = attempt_.byteValue();
    }

    public void set_breakdown(Boolean breakdown) {
        _breakdown = breakdown.booleanValue();
    }

    public void set_transfer_end(Boolean transfer_end) {
        _transfer_end = transfer_end.booleanValue();
    }

    /**
     * Guards
     */
    public boolean amount_less_or_equal_to_authorisation_withdrawal_weekly_limit() {
        return _amount <= _authorisation.withdrawal_weekly_limit();
    }

    public boolean amount_greater_than_authorisation_withdrawal_weekly_limit() {
        return _amount > _authorisation.withdrawal_weekly_limit();
    }

    public boolean amount_choice_try_equal_to_2() {
        return _amount_choice_try == 2;
    }

    public boolean amount_choice_try_not_equal_to_2() {
        return _amount_choice_try != 2;
    }

    public boolean breakdown_equal_to_false() {
        return _breakdown == false;
    }

    public boolean breakdown_equal_to_true() {
        return _breakdown == true;
    }

    public boolean context_equal_to_Password_request_and_attempt_equal_to_1(Statechart context) {
        return context == _Password_request && _attempt_ == 1;
    }

    public boolean context_equal_to_Password_request_and_attempt_equal_to_2(Statechart context) {
        return context == _Password_request && _attempt_ == 2;
    }

    public boolean context_equal_to_Transaction_verification(Statechart context) {
        return context == _Transaction_verification;
    }

    public boolean operation_is_Deposit() {
        return _operation_kind == Operation.Deposit;
    }

    public boolean operation_is_Query() {
        return _operation_kind == Operation.Query;
    }

    public boolean operation_is_Query_or_Transfer() {
        return _operation_kind == Operation.Query || _operation_kind == Operation.Transfer;
    }

    public boolean operation_is_not_Query() {
        return _operation_kind != Operation.Query;
    }

    public boolean operation_is_Transfer() {
        return _operation_kind == Operation.Transfer;
    }

    public boolean operation_is_not_Transfer() {
        return _operation_kind != Operation.Transfer;
    }

    public boolean operation_is_Transfer_and_not_end_transfer() {
        return _operation_kind == Operation.Transfer && _transfer_end == false;
    }

    public boolean operation_is_Transfer_and_end_transfer_and_same_account() {
        return _operation_kind == Operation.Transfer && _transfer_end == true && _account.equals(_transfer_account);
    }

    public boolean operation_is_Transfer_and_end_transfer_and_not_same_account() {
        return _operation_kind == Operation.Transfer && _transfer_end == true && !_account.equals(_transfer_account);
    }

    public boolean operation_is_Withdrawal() {
        return _operation_kind == Operation.Withdrawal;
    }

    public boolean password_equal_to_authorisation_password(String password) {
        return password.equals(_authorisation.password());
    }

    public boolean password_not_equal_to_authorisation_password(String password) {
        return !password.equals(_authorisation.password());
    }

    /**
     * User interface
     */
    public Card_reader card_reader() {
        return _card_reader;
    }

    public boolean account_choice() {
        return _ATM.in_state("Account choice");
    }

    public boolean amount_choice() {
        return _ATM.in_state("Amount choice");
    }

    public boolean operation_kind_choice() {
        return _ATM.in_state("Operation kind choice");
    }

    public boolean password_request() {
        return _ATM.in_state("Password request");
    }
}
