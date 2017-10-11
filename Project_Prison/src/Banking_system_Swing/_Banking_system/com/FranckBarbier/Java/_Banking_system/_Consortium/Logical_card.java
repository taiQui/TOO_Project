package com.FranckBarbier.Java._Banking_system._Consortium;

public class Logical_card implements com.pauware.pauware_engine._Core.Unique {

    protected final String _card_number;
    public final String password;
    public final int withdrawal_weekly_limit;
    protected final Client _client;

    public boolean equals(Object logical_card) {
        if (this == logical_card) {
            return true;
        }
        if (logical_card instanceof Logical_card) {
            return _card_number == null ? false : unique().equals(((Logical_card) logical_card).unique());
        }
        return false;
    }

    public int hashCode() {
        return _card_number == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _card_number;
    }

    public Logical_card(final String card_number, final Client client, final String _password, final int _withdrawal_weekly_limit) {
        _card_number = card_number;
        _client = client;
        password = _password;
        withdrawal_weekly_limit = _withdrawal_weekly_limit;
    }

    public String bank_code() {
        return _client.bank_code();
    }
}
