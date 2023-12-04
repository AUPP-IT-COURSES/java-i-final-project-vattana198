package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    String pin ;

    JButton b1,b2,b3,b4,b5,b6,b7 ;
    FastCash(String pin ){
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 850);
        add(l3);

        JLabel label = new JLabel("SELECT WITHDRAW AMOUNT") ;
        label.setFont(new Font("Raleway", Font.BOLD, 16)) ;
        label.setForeground(Color.WHITE);
        label.setBounds(220,300,700,35);
        l3.add(label) ;

        b1 = new JButton("5$");
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Raleway" , Font.BOLD , 12));
        b1.setBounds(160,390,150,30);
        b1.setOpaque(true);
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("10$");
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Raleway" , Font.BOLD , 12));
        b2.setBounds(360,390,150,30);
        b2.setOpaque(true);
        b2.setBorderPainted(false);
        b2.addActionListener(this);
        l3.add(b2);


        JButton b3 = new JButton("20$ ");
        b3.setFont(new Font("Raleway" , Font.BOLD , 12));
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(160,425,150,30);
        b3.setOpaque(true);
        b3.setBorderPainted(false);
        b3.addActionListener(this);
        l3.add(b3) ;

        b4 = new JButton("30$");
        b4.setBackground(new Color(65,125,128));
        b4.setForeground(Color.WHITE);
        b4.setFont(new Font("Raleway" , Font.BOLD , 12));
        b4.setBounds(360,425,150,30);
        b4.setOpaque(true);
        b4.setBorderPainted(false);
        b4.addActionListener(this);
        b4.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b4.setBackground(Color.WHITE);
//                setVisible(false);
                new main_Class(pin);

            }
            public void mouseReleased(MouseEvent e) {
                b4.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b4);

        b5 = new JButton("50$");
        b5.setBackground(new Color(65,125,128));
        b5.setForeground(Color.WHITE);
        b5.setFont(new Font("Raleway" , Font.BOLD , 12));
        b5.setBounds(160,460,150,30);
        b5.setOpaque(true);
        b5.setBorderPainted(false);
        b5.addActionListener(this);
        l3.add(b5);


        b6 = new JButton("100$");
        b6.setBackground(new Color(65,125,128));
        b6.setForeground(Color.WHITE);
        b6.setFont(new Font("Raleway" , Font.BOLD , 12));
        b6.setBounds(360,460,150,30);
        b6.setOpaque(true);
        b6.setBorderPainted(false);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("BACK");
        b7.setFont(new Font("Raleway", Font.BOLD, 14));
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65, 125, 128));
        b7.setBounds(360, 495, 150, 25);
        b7.setOpaque(true);
        b7.setBorderPainted(false);
        b7.addActionListener(this);
        b7.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b7.setBackground(Color.WHITE);
//                System.exit(0);

                new main_Class(pin) ;
                setVisible(false);
            }

            public void mouseReleased(MouseEvent e) {
                b7.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b7);


        setLayout(null);
        setSize(900, 850);
        setLocation(300,0);
//        setUndecorated(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b7) {
            setVisible(false);
            new main_Class(pin);
        } else {
            String buttonText = ((JButton) e.getSource()).getText();
            System.out.println("Button Text: " + buttonText);

            // Extract the numerical part of the button text
            String amountText = buttonText.replaceAll("[^\\d]", "");
            System.out.println("Withdrawal Amount Text: " + amountText);

            if (amountText.isEmpty()) {
                // Handle the case where the amount is not valid
                JOptionPane.showMessageDialog(null, "Invalid Withdrawal Amount");
                return;
            }

            // Parse the amount as an integer
            int amount = Integer.parseInt(amountText);
            System.out.println("Withdrawal Amount: " + amount);

            Con c = new Con();
            Date date = new Date();

            try {
                // Retrieve all transactions for the given PIN
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

                // Initialize balance to 0
                int balance = 0;

                // Process each transaction
                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    String amountString = resultSet.getString("amount");

                    // Check for null values and update balance accordingly
                    if (amountString != null) {
                        int transactionAmount = Integer.parseInt(amountString);
                        if ("Deposit".equals(type)) {
                            balance += transactionAmount;
                        } else if ("Withdrawal".equals(type)) {
                            balance -= transactionAmount;
                        }
                    }
                }

                System.out.println("Current Balance: " + balance);

                // Check for insufficient balance before processing the withdrawal
//                if (e.getSource() != b7 && balance < amount) {
//                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
//                    return;
//                }

                // Update the balance based on the withdrawal
                balance -= amount;

                // Insert the new withdrawal transaction into the 'bank' table
                c.statement.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'withdrawal', '" + amount + "')");

                // Display success message
                JOptionPane.showMessageDialog(null, amount + " Debited Successfully");

                // Display the updated balance
                System.out.println("Updated Balance: " + balance);

                // Close the current frame and navigate to the main screen
                setVisible(false);
                new main_Class(pin);
            } catch (NumberFormatException ex) {
                // Handle the case where the amount cannot be parsed as an integer
                JOptionPane.showMessageDialog(null, "Invalid Withdrawal Amount");
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            setVisible(false);
            new main_Class(pin);
        }
    }




    public static void main(String[] args) {
        new FastCash("") ;

    }


}
