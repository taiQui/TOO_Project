package com.FranckBarbier.Java._Banking_system._Consortium;

public class Authorisation implements java.io.Serializable, com.pauware.pauware_engine._Core.Unique {

    public static Authorisation authorisation(String serial_number, String card_number, String password, int withdrawal_weekly_limit, String bank_code, String client_PIN) {
        return new Authorisation(new Plastic_card(serial_number, new Logical_card(card_number, new Client(bank_code, client_PIN), password, withdrawal_weekly_limit)));
    }
    protected final Plastic_card _plastic_card;

    public boolean equals(Object authorisation) {
        if (this == authorisation) {
            return true;
        }
        if (authorisation instanceof Authorisation) {
            return _plastic_card == null ? false : unique().equals(((Authorisation) authorisation).unique());
        }
        return false;
    }

    public int hashCode() {
        return _plastic_card == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _plastic_card.unique();
    }

    public Authorisation(Plastic_card plastic_card) {
        _plastic_card = plastic_card;
    }

    public String bank_code() {
        return _plastic_card.bank_code();
    }

    public String password() {
        return _plastic_card.password();
    }

    public int withdrawal_weekly_limit() {
        return _plastic_card.withdrawal_weekly_limit();
    }
}
