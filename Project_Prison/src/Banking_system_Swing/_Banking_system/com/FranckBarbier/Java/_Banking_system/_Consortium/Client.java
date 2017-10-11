package com.FranckBarbier.Java._Banking_system._Consortium;

public class Client implements com.pauware.pauware_engine._Core.Unique {

    private final String _bank_code;
    private final String _client_PIN;

    public boolean equals(Object client) {
        if (this == client) {
            return true;
        }
        if (client instanceof Client) {
            return _bank_code == null || _client_PIN == null ? false : unique().equals(((Client) client).unique());
        }
        return false;
    }

    public int hashCode() {
        return _bank_code == null || _client_PIN == null ? 0 : unique().hashCode();
    }

    public String unique() {
        return _bank_code + _client_PIN;
    }

    public Client(String bank_code, String client_PIN) {
        _bank_code = bank_code;
        _client_PIN = client_PIN;
    }

    public String bank_code() {
        return _bank_code;
    }
}
