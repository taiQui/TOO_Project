package com.FranckBarbier.Java._Banking_system._ATM;

import com.FranckBarbier.Java._Banking_system._Consortium.*;

public class User_interface extends javax.swing.JFrame {

    private javax.swing.JTextArea _display_area;
    private javax.swing.JPanel _key_pad;
    private javax.swing.JPanel _devices;
    private javax.swing.JTextArea _explaination_area;
    private javax.swing.JButton _abort;
    private javax.swing.JButton _continuation;
    private javax.swing.JButton _termination;
    private javax.swing.JButton _key_0;
    private javax.swing.JButton _key_1;
    private javax.swing.JButton _key_2;
    private javax.swing.JButton _key_3;
    private javax.swing.JButton _key_4;
    private javax.swing.JButton _key_5;
    private javax.swing.JButton _key_6;
    private javax.swing.JButton _key_7;
    private javax.swing.JButton _key_8;
    private javax.swing.JButton _key_9;
    private javax.swing.JButton _dot;
    private javax.swing.JButton _backspace;
    private javax.swing.JButton _enter;
    private javax.swing.JLabel _card_reader_text;
    private javax.swing.JButton _card_inserted;
    private javax.swing.JButton _card_taken;
    private javax.swing.JLabel _cash_dispenser_text;
    private javax.swing.JTextField _cash_dispenser;
    private javax.swing.JLabel _deposit_drawer_text;
    private javax.swing.JTextField _deposit_drawer;
    private javax.swing.JLabel _receipt_printer_text;
    private javax.swing.JTextField _receipt_printer;
    protected static final byte _Amount_length = 4;
    protected static final byte _Password_length = 4;
    public final static byte Franck_Barbier = 0;
    public final static byte Oscar_Barbier__Darnal = 1;
    public final static byte Lena_Barbier__Darnal = 2;
    public final static byte Joseph_Barbier__Darnal = 3;
    protected static final Plastic_card[] _Plastic_cards = new Plastic_card[4];

    static {
        _Plastic_cards[Franck_Barbier] = new Plastic_card("PC1 FB", new Logical_card("LC FB", new Client("B1", "11-01-1963"), "1101", 3000));
        _Plastic_cards[Oscar_Barbier__Darnal] = new Plastic_card("PC1 OBD", new Logical_card("LC OBD", new Client("B1", "06-12-1993"), "0612", 1000));
        _Plastic_cards[Lena_Barbier__Darnal] = new Plastic_card("PC1 LBD", new Logical_card("LC LBD", new Client("B2", "15-04-1996"), "1504", 500));
        _Plastic_cards[Joseph_Barbier__Darnal] = new Plastic_card("PC1 JDB", new Logical_card("LC JBD", new Client("B1", "22-02-2001"), "2202", 100));
    }
    protected StringBuffer _amount = new StringBuffer(_Amount_length);
    protected StringBuffer _password = new StringBuffer(_Password_length);
    protected ATM _atm;

    public void add_atm(ATM atm) {
        _atm = atm;
    }

    public User_interface() {
        initComponents();
    }

    private void initComponents() {
        _display_area = new javax.swing.JTextArea();
        _key_pad = new javax.swing.JPanel();
        _devices = new javax.swing.JPanel();
        _explaination_area = new javax.swing.JTextArea();

        _abort = new javax.swing.JButton();
        _continuation = new javax.swing.JButton();
        _termination = new javax.swing.JButton();

        _key_0 = new javax.swing.JButton();
        _key_1 = new javax.swing.JButton();
        _key_2 = new javax.swing.JButton();
        _key_3 = new javax.swing.JButton();
        _key_4 = new javax.swing.JButton();
        _key_5 = new javax.swing.JButton();
        _key_6 = new javax.swing.JButton();
        _key_7 = new javax.swing.JButton();
        _key_8 = new javax.swing.JButton();
        _key_9 = new javax.swing.JButton();
        _dot = new javax.swing.JButton();
        _backspace = new javax.swing.JButton();
        _enter = new javax.swing.JButton();

        _card_reader_text = new javax.swing.JLabel();
        _card_inserted = new javax.swing.JButton();
        _card_taken = new javax.swing.JButton();
        _cash_dispenser_text = new javax.swing.JLabel();
        _cash_dispenser = new javax.swing.JTextField();
        _deposit_drawer_text = new javax.swing.JLabel();
        _deposit_drawer = new javax.swing.JTextField();
        _receipt_printer_text = new javax.swing.JLabel();
        _receipt_printer = new javax.swing.JTextField();

        getContentPane().setLayout(new java.awt.GridLayout(2, 2));

        setTitle("Banking system (PauWare engine Java library, (c) Franck.Barbier@FranckBarbier.com)");
        setBackground(new java.awt.Color(255, 255, 153));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        _display_area.setEditable(false);
        _display_area.setFont(new java.awt.Font("Arial Black", 3, 12));
        _display_area.setForeground(new java.awt.Color(255, 51, 51));
        _display_area.setBorder(new javax.swing.border.MatteBorder(null));

        _devices.setLayout(new java.awt.GridLayout(3, 3));
        _devices.setBackground(new java.awt.Color(255, 255, 204));
        _devices.setBorder(new javax.swing.border.EtchedBorder());
        _devices.setEnabled(false);

        _card_reader_text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        _card_reader_text.setText("Card reader");
        _devices.add(_card_reader_text);

        _cash_dispenser_text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        _cash_dispenser_text.setText("Cash dispenser");
        _devices.add(_cash_dispenser_text);
        _cash_dispenser.setEditable(false);
        _cash_dispenser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _devices.add(_cash_dispenser);

        _card_inserted.setText("card inserted");
        _card_inserted.setEnabled(false);
        _card_inserted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                card_inserted(evt);
            }
        });
        _devices.add(_card_inserted);

        _deposit_drawer_text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        _deposit_drawer_text.setText("Deposit drawer");
        _devices.add(_deposit_drawer_text);
        _deposit_drawer.setEditable(false);
        _deposit_drawer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _devices.add(_deposit_drawer);

        _card_taken.setText("card taken");
        _card_taken.setEnabled(false);
        _card_taken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                card_taken(evt);
            }
        });
        _devices.add(_card_taken);

        _receipt_printer_text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        _receipt_printer_text.setText("Receipt printer");
        _devices.add(_receipt_printer_text);
        _receipt_printer.setEditable(false);
        _receipt_printer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _devices.add(_receipt_printer);

        _key_pad.setLayout(new java.awt.GridLayout(4, 4));
        _key_pad.setBackground(new java.awt.Color(255, 255, 204));
        _key_pad.setBorder(new javax.swing.border.EtchedBorder());

        _key_0.setBackground(new java.awt.Color(204, 255, 204));
        _key_0.setFont(new java.awt.Font("Courier", 1, 10));
        _key_0.setText("0");
        _key_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_0(evt);
            }
        });
        _key_pad.add(_key_0);

        _key_1.setBackground(new java.awt.Color(204, 255, 204));
        _key_1.setFont(new java.awt.Font("Courier", 1, 10));
        _key_1.setText("1");
        _key_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_1(evt);
            }
        });
        _key_pad.add(_key_1);

        _key_2.setBackground(new java.awt.Color(204, 255, 204));
        _key_2.setFont(new java.awt.Font("Courier", 1, 10));
        _key_2.setText("2");
        _key_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_2(evt);
            }
        });
        _key_pad.add(_key_2);

        _abort.setBackground(new java.awt.Color(204, 255, 204));
        _abort.setText("Abort");
        _abort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abort(evt);
            }
        });
        _key_pad.add(_abort);

        _key_3.setBackground(new java.awt.Color(204, 255, 204));
        _key_3.setFont(new java.awt.Font("Courier", 1, 10));
        _key_3.setText("3");
        _key_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_3(evt);
            }
        });
        _key_pad.add(_key_3);

        _key_4.setBackground(new java.awt.Color(204, 255, 204));
        _key_4.setFont(new java.awt.Font("Courier", 1, 10));
        _key_4.setText("4");
        _key_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_4(evt);
            }
        });
        _key_pad.add(_key_4);

        _key_5.setBackground(new java.awt.Color(204, 255, 204));
        _key_5.setFont(new java.awt.Font("Courier", 1, 10));
        _key_5.setText("5");
        _key_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_5(evt);
            }
        });
        _key_pad.add(_key_5);

        _continuation.setBackground(new java.awt.Color(204, 255, 204));
        _continuation.setText("Continuation");
        _continuation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuation(evt);
            }
        });
        _key_pad.add(_continuation);

        _key_6.setBackground(new java.awt.Color(204, 255, 204));
        _key_6.setFont(new java.awt.Font("Courier", 1, 10));
        _key_6.setText("6");
        _key_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_6(evt);
            }
        });
        _key_pad.add(_key_6);

        _key_7.setBackground(new java.awt.Color(204, 255, 204));
        _key_7.setFont(new java.awt.Font("Courier", 1, 10));
        _key_7.setText("7");
        _key_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_7(evt);
            }
        });
        _key_pad.add(_key_7);

        _key_8.setBackground(new java.awt.Color(204, 255, 204));
        _key_8.setFont(new java.awt.Font("Courier", 1, 10));
        _key_8.setText("8");
        _key_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_8(evt);
            }
        });
        _key_pad.add(_key_8);

        _termination.setBackground(new java.awt.Color(204, 255, 204));
        _termination.setText("Termination");
        _termination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termination(evt);
            }
        });
        _key_pad.add(_termination);

        _key_9.setBackground(new java.awt.Color(204, 255, 204));
        _key_9.setFont(new java.awt.Font("Courier", 1, 10));
        _key_9.setText("9");
        _key_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_key_9(evt);
            }
        });
        _key_pad.add(_key_9);

        _dot.setBackground(new java.awt.Color(204, 255, 204));
        _dot.setFont(new java.awt.Font("Courier", 1, 10));
        _dot.setText(".");
        _key_pad.add(_dot);

        _backspace.setBackground(new java.awt.Color(204, 255, 204));
        _backspace.setFont(new java.awt.Font("Courier", 1, 10));
        _backspace.setText("<-");
        _backspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_backspace(evt);
            }
        });
        _key_pad.add(_backspace);

        _enter.setBackground(new java.awt.Color(204, 255, 204));
        _enter.setText("Enter");
        _enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter(evt);
            }
        });
        _key_pad.add(_enter);

        _explaination_area.setEditable(false);
        _explaination_area.setFont(new java.awt.Font("Times", 1, 9));
        _explaination_area.setForeground(java.awt.Color.BLUE);
        _explaination_area.setBorder(new javax.swing.border.MatteBorder(null));
        _explaination_area.setText(" press the 'card inserted' button to start...\n\n"
                + " currently active plastic card is:\n"
                + " _Plastic_cards[Franck_Barbier] = new Plastic_card(\"PC1 FB\",new Logical_card(\"LC FB\",\n"
                + " new Client(\"B1\",\"11-01-1963\"),\"1101\",3000));\n\n"
                + " change the code in the 'private void card_inserted(java.awt.event.ActionEvent evt)' method\n"
                + " of the User_interface Java class to get other plastic cards (see: '_Plastic_cards' array)\n");

        getContentPane().add(_display_area);
        getContentPane().add(_key_pad);
        getContentPane().add(_devices);
        getContentPane().add(_explaination_area);

        pack();
    }

    private void card_inserted(java.awt.event.ActionEvent evt) {
        _card_inserted.setEnabled(false);
        try {
            _atm.card_reader().card_inserted(_Plastic_cards[Franck_Barbier]);
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage() + "\n\n");
            // _atm.card_reader().breakdown();
        }
    }

    private void card_taken(java.awt.event.ActionEvent evt) {
        _card_taken.setEnabled(false);
        try {
            _atm.card_reader().card_taken();
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
            // _atm.card_reader().breakdown();
        }
    }

    private void abort(java.awt.event.ActionEvent evt) {
        try {
            _atm.abort();
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
    }

    private void continuation(java.awt.event.ActionEvent evt) {
        try {
            _atm.continuation();
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
    }

    private void enter(java.awt.event.ActionEvent evt) {
        try {
            try {
                if (_atm.amount_choice()) {
                    _atm.amount_chosen((new Integer(_amount.toString())).intValue());
                }
            } catch (NumberFormatException nfe) {
            }
            if (_atm.password_request()) {
                _atm.password_entered(_password.toString());
            }
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
    }

    private void termination(java.awt.event.ActionEvent evt) {
        try {
            _atm.termination();
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
    }

    private void on_backspace(java.awt.event.ActionEvent evt) {
        if (_atm.amount_choice()) {
            if (_amount.length() > 0) {
                _amount.deleteCharAt(_amount.length() - 1);
                StringBuffer screen = new StringBuffer(_display_area.getText());
                if (screen.length() > 0) {
                    screen.deleteCharAt(screen.length() - 1);
                }
                _display_area.setText(screen.toString());
            }
        }
        if (_atm.password_request()) {
            if (_password.length() > 0) {
                _password.deleteCharAt(_password.length() - 1);
                StringBuffer screen = new StringBuffer(_display_area.getText());
                if (screen.length() > 0) {
                    screen.deleteCharAt(screen.length() - 1);
                }
                _display_area.setText(screen.toString());
            }
        }
    }

    private void on_key_0(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[0]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('0');
                _display_area.setText(_display_area.getText() + '0');
            }
        }
        try {
            if (_atm.operation_kind_choice()) {
                _atm.operation_kind_chosen(Operation._Kinds[0]);
            }
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('0');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_9(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[9]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('9');
                _display_area.setText(_display_area.getText() + '9');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('9');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_8(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[8]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('8');
                _display_area.setText(_display_area.getText() + '8');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('8');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_7(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[7]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('7');
                _display_area.setText(_display_area.getText() + '7');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('7');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_6(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[6]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('6');
                _display_area.setText(_display_area.getText() + '6');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('6');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_5(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[5]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('5');
                _display_area.setText(_display_area.getText() + '5');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('5');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_4(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[4]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('4');
                _display_area.setText(_display_area.getText() + '4');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('4');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void on_key_3(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[3]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('3');
                _display_area.setText(_display_area.getText() + '3');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('3');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
        try {
            if (_atm.operation_kind_choice()) {
                _atm.operation_kind_chosen(Operation._Kinds[3]);
            }
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
    }

    private void on_key_2(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[2]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('2');
                _display_area.setText(_display_area.getText() + '2');
            }
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('2');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
        try {
            if (_atm.operation_kind_choice()) {
                _atm.operation_kind_chosen(Operation._Kinds[2]);
            }
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
    }

    private void on_key_1(java.awt.event.ActionEvent evt) {
        try {
            if (_atm.account_choice()) {
                _atm.account_chosen((Account) _atm._portfolio.account().toArray()[1]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.amount_choice()) {
            if (_amount.length() < _Amount_length) {
                _amount.append('1');
                _display_area.setText(_display_area.getText() + '1');
            }
        }
        try {
            if (_atm.operation_kind_choice()) {
                _atm.operation_kind_chosen(Operation._Kinds[1]);
            }
        } catch (com.pauware.pauware_engine._Exception.Statechart_exception se) {
            _display_area.setText(se.getMessage());
        }
        if (_atm.password_request()) {
            if (_password.length() < _Password_length) {
                _password.append('1');
                _display_area.setText(_display_area.getText() + "*");
            }
        }
    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        try {
            _atm.stop();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(0);
    }

    public void activate_cash_dispenser() {
        _cash_dispenser.setText("du fric !");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ie) {
            _display_area.setText(ie.getMessage());
        }
        _cash_dispenser.setText("no more money...");
    }

    public void activate_deposit_drawer() {
        _deposit_drawer.setBackground(java.awt.Color.RED);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ie) {
            _display_area.setText(ie.getMessage());
        }
        _deposit_drawer.setBackground(java.awt.Color.BLACK);
    }

    public void activate_receipt_printer() {
        _receipt_printer.setBackground(java.awt.Color.RED);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ie) {
            _display_area.setText(ie.getMessage());
        }
        _receipt_printer.setBackground(java.awt.Color.BLACK);
    }

    public void display_account() {
        switch (_atm._operation_kind) {
            case Operation.Deposit:
                _display_area.setText("Deposit(" + _atm._account.unique() + "):\n" + _atm._amount);
                break;
            case Operation.Query:
                _display_area.setText("Query(" + _atm._account.unique() + "):\n" + _atm._account.balance());
                break;
            case Operation.Transfer:
                _display_area.setText("Transfer(" + _atm._account.unique() + "\n->" + _atm._transfer_account.unique() + "): " + _atm._amount);
                break;
            case Operation.Withdrawal:
                _display_area.setText("Withdrawal(" + _atm._account.unique() + "):\n" + _atm._amount);
                break;
        }
    }

    public void display_card_put_down() {
        _display_area.setText("card put down\n");
        _card_taken.setEnabled(false);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ie) {
            _display_area.setText(ie.getMessage());
        }
    }

    public void display_card_unreadable() {
        _display_area.setText("card unreadable\n");
    }

    public void display_choose_account() {
        _display_area.setText("choose account (" + Operation._Literals[_atm._operation_kind] + ")\n");
        for (int i = 0; i < _atm._portfolio.account().toArray().length; i++) {
            _display_area.setText(_display_area.getText() + i + ".: " + ((Account) _atm._portfolio.account().toArray()[i]).unique() + "\n");
        }
    }

    public void display_choose_amount() {
        _display_area.setText("choose amount\n");
        _amount.delete(0, _Amount_length);
    }

    public void display_choose_continuation_or_termination() {
        _display_area.setText("choose continuation or termination\n");
    }

    public void display_choose_operation_kind() {
        _display_area.setText("choose operation kind\n");
        for (int i = 0; i < Operation._Literals.length; i++) {
            _display_area.setText(_display_area.getText() + i + ".: " + Operation._Literals[i] + "\n");
        }
    }

    public void display_enter_password() {
        _display_area.setText("enter password\n");
        _password.delete(0, _Password_length);
    }

    public void display_insert_card() {
        _display_area.setText("insert card\n");
        _card_inserted.setEnabled(true);
    }

    public void display_invalid_password() {
        _display_area.setText("invalid password\n");
    }

    public void display_operation_error(String text) {
        _display_area.setText("operation error: " + text + "\n");
    }

    public void display_operation_starts_() {
        _display_area.setText("operation starts...\n");
    }

    public void display_out_of_order() {
        _display_area.setText("out of order\n");
    }

    public void display_take_card() {
        _display_area.setText("take card\n");
        _card_taken.setEnabled(true);
    }

    public void display_transaction_error(String text) {
        _display_area.setText("transaction error: " + text + "\n");
    }

    public void display_transaction_starts_() {
        _display_area.setText("transaction starts...\n");
    }

    public static void main(String[] args) {
        try {
            User_interface user_interface = new User_interface();
            ATM atm = new ATM("0000000001");
            user_interface.add_atm(atm);
            atm.add_user_interface(user_interface);
            user_interface.setVisible(true);
            atm.start(20000);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
