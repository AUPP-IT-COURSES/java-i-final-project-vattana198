@Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();
            if (amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Amount you want to withdraw");
            } else {
                Con c = new Con();
                // Change Resultset to ResultSet
                ResultSet resultSet = c.statement.executeQuery("select * from  bank where pin = '" + pin + "'");

                int balance = 0;
                while (resultSet.next()) {
                    if (resultSet.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }

                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                c.statement.executeUpdate("insert into bank values ('" + pin + "' , '" + date + "' , 'Withdrawl' , '" + amount + "'      )");
                JOptionPane.showMessageDialog(null, amount + " Debited Successfully");
                setVisible(false);
                new main_Class(pin);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }