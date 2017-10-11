package com.FranckBarbier.Java._Banking_system._Consortium;

public class Plastic_card implements java.io.Serializable, com.pauware.pauware_engine._Core.Unique {

    protected final String _serial_number;
    protected final Logical_card _logical_card;

    public boolean equals(Object plastic_card) {
        if (this == plastic_card) {
            return true;
        }
        if (plastic_card instanceof Plastic_card) {
            return _serial_number == null ? false : unique().equals(((Plastic_card) plastic_card).unique());
        }
        return false;
    }

    public int hashCode() {
        return _serial_number == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _serial_number;
    }

    public Plastic_card(String serial_number, Logical_card logical_card) {
        _serial_number = serial_number;
        _logical_card = logical_card;
    }

    public String bank_code() {
        return _logical_card.bank_code();
    }

    public String password() {
        return _logical_card.password;
    }

    public int withdrawal_weekly_limit() {
        return _logical_card.withdrawal_weekly_limit;
    }

    protected void writeObject(java.io.ObjectInputStream ois) throws java.io.IOException {
    }

    protected void readObject(java.io.ObjectOutputStream oos) throws java.io.IOException {
    }
}
