package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame implements ActionListener {

    String pin ;
    JLabel label2;

    JButton b1 ;

    BalanceEnquriy(String pin){
        this.pin = pin ;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 850);
        add(l3);


        JLabel label1 = new JLabel("Your Current Balance is  ");
        label1.setFont(new Font("Raleway", Font.BOLD, 14));
        label1.setForeground(Color.WHITE);
        label1.setBounds(180, 290, 300, 35);
        l3.add(label1);

        label2 = new JLabel() ;
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(360,290,400,35);
        l3.add(label2) ;



        b1 = new JButton("BACK");
        b1.setFont(new Font("Raleway", Font.BOLD, 16));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(370, 475, 140, 25);
        b1.setOpaque(true);
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        b1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b1.setBackground(Color.WHITE);
//                System.exit(0);
                setVisible(false);
                new main_Class(pin) ;
            }

            public void mouseReleased(MouseEvent e) {
                b1.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b1);

        int balance = 0  ;
        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '" + pin + "'");

            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));

                }
            }

        } catch(Exception e ){
            e.printStackTrace();
        }

        label2.setText(" "+balance);



        setLayout(null);
        setSize(900, 850);
        setLocation(300,0);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);

    }



    public static void main(String[] args) {
        new BalanceEnquriy("");
    }


}
