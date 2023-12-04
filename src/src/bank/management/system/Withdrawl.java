package bank.management.system;



import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.sql.ResultSet;






public class Withdrawl extends JFrame implements ActionListener {

    String pin ;
    TextField textField ;
    JButton b1 , b2 ;

    Withdrawl(String pin  ) {
        this.pin = pin ;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 850);
        add(l3);


        JLabel label1 = new JLabel("PLEASE ENTER YOUR AMOUNT ");
        label1.setFont(new Font("Raleway", Font.BOLD, 14));
        label1.setForeground(Color.WHITE);
        label1.setBounds(220, 290, 300, 35);
        l3.add(label1);

        textField = new TextField();
        textField.setFont(new Font("Raleway", Font.BOLD, 14));
        textField.setBounds(200, 330, 260, 30);
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        l3.add(textField);


        String amount = textField.getText();
        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("Raleway", Font.BOLD, 16));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(370, 440, 140, 25);
        b1.setOpaque(true);
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        b1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b1.setBackground(Color.WHITE);

                new main_Class(pin) ;
//                setVisible(false);
                JOptionPane.showMessageDialog(null,   amount + " Withdraw Successfully");
            }

            public void mouseReleased(MouseEvent e) {

                b1.setBackground(new Color(65, 125, 128));
            }



        });
        l3.add(b1);


        b2 = new JButton("BACK");
        b2.setFont(new Font("Raleway", Font.BOLD, 16));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(370, 475, 140, 25);
        b2.setOpaque(true);
        b2.setBorderPainted(false);
        b2.addActionListener(this);
//        b2.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                b2.setBackground(Color.WHITE);
//                new main_Class(pin) ;
//            }
//
//            public void mouseReleased(MouseEvent e) {
//                b2.setBackground(new Color(65, 125, 128));
//            }
//        });
        l3.add(b2);

        this.pin = pin;
        setLayout(null);
        setSize(900, 850);
        setLocation(300, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();
            if (e.getSource() == b1) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Con c = new Con();
                    ResultSet resultSet = c.statement.executeQuery("select * from bank where pin =  '"+pin+"'     ") ;
                    int balance = 0;
                    while(resultSet.next()){
                        if(resultSet.getString("type" ).equals("Deposit")){
                            balance +=   Integer.parseInt(resultSet.getString("amount"));
                        } else{
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }

                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null , "Insufficient Balance");
                        return ;
                    }
                    int updatedBalance = balance - Integer.parseInt(amount);
//                    c.statement.executeUpdate("insert into bank values ('" + pin + "' , '" + date + "','Deposit' ,'" + amount + "'  )");


                    c.statement.executeUpdate("insert into bank values ('" + pin + "' , '" + date + "','Withdrawal' ,'" + amount + "'  )");

                    // Update the balance for the corresponding account
                    c.statement.executeUpdate("update bank set balance = " + updatedBalance + " where pin = '" + pin + "'");

                    new main_Class(pin) ;
                    JOptionPane.showMessageDialog(null,   amount + "Deposited Successfully ");

//                    setVisible(false);
                }



            } else if (e.getSource() == b2) {

                new main_Class(pin) ;
                setVisible(false);
            }
        } catch (Exception E) {
            E.printStackTrace();
            System.out.println("error" + E);
        }
    }


    public static void main(String[] args) {
        new Withdrawl("");
    }



}
