package com.FranckBarbier.Java._Banking_system._Consortium;

public class Account implements com.pauware.pauware_engine._Core.Unique {

    protected final String _bank_code;
    protected final String _account_number;
    protected double _balance;

    public boolean equals(Object account) {
        if (this == account) {
            return true;
        }
        if (account instanceof Account) {
            return _bank_code == null || _account_number == null ? false : unique().equals(((Account) account).unique());
        }
        return false;
    }

    public int hashCode() {
        return _bank_code == null || _account_number == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _bank_code + _account_number;
    }

    public Account(final String bank_code, final String account_number) {
        _bank_code = bank_code;
        _account_number = account_number;
        _balance = 0.;
    }

    public double balance() {
        return _balance;
    }
}
