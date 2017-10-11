package com.FranckBarbier.Java._Banking_system._ATM;

public final class Operation {

    public final static byte Deposit = 0;
    public final static byte Query = 1;
    public final static byte Transfer = 2;
    public final static byte Withdrawal = 3;
    static byte[] _Kinds = new byte[4];
    static String[] _Literals = new String[4];

    static {
        _Kinds[Deposit] = Deposit;
        _Kinds[Query] = Query;
        _Kinds[Transfer] = Transfer;
        _Kinds[Withdrawal] = Withdrawal;
        _Literals[Deposit] = new String("Deposit");
        _Literals[Query] = new String("Query");
        _Literals[Transfer] = new String("Transfer");
        _Literals[Withdrawal] = new String("Withdrawal");
    }
    String _account;
    int _amount;
    String _secondary;
    byte _type;

    Operation() {
    }

    Operation(final byte type, final int amount, final String account) {
        _account = account;
        _amount = amount;
        _secondary = null;
        _type = type;
    }

    Operation(final int amount, final String account, final String secondary) {
        _account = account;
        _amount = amount;
        _secondary = secondary;
        _type = Transfer;
    }

    public String toString() {
        return _Literals[_type] + "/" + _amount + "/" + super.toString();
    }
}
