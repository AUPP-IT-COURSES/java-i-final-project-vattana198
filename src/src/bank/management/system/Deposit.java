package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    String pin ;
    TextField textField ;
    JButton b1 , b2 ;
    Deposit(String pin){



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.png")) ;
        Image i2 = i1.getImage().getScaledInstance(900, 850 , Image.SCALE_DEFAULT) ;
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3) ;
        l3.setBounds(0, 0 ,900, 850) ;
        add(l3);


        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT") ;
        label1.setFont(new Font("Raleway" , Font.BOLD , 14));
        label1.setForeground(Color.WHITE);

        label1.setBounds(190,300,400,35);
        l3.add(label1) ;

        textField = new TextField() ;
        textField.setFont(new Font("Raleway" , Font.BOLD , 14));
        textField.setBounds(200,330,260,30);
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        l3.add(textField) ;



        String amount = textField.getText();
        b1  = new JButton("DEPOSIT");
        b1.setFont(new Font("Raleway", Font.BOLD , 16));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(400, 440, 120, 25);
        b1.setOpaque(true);
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        b1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b1.setBackground(Color.WHITE);

//                    setVisible(false);
//                    new main_Class(pin) ;

            }
            public void mouseReleased(MouseEvent e) {
                b1.setBackground(new Color(65, 125, 128));
            }
            public void mouseClicked(MouseEvent e) {
                // Additional action when the button is clicked (optional)
                // For example, you can add code to show a popup here
                b1.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b1);



        b2  = new JButton("BACK");
        b2.setFont(new Font("Raleway", Font.BOLD , 16));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(400, 475, 120, 25);
        b2.setOpaque(true);
        b2.setBorderPainted(false);
        b2.addActionListener(this);
        b2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b2.setBackground(Color.WHITE);
            }
            public void mouseReleased(MouseEvent e) {
                b2.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b2);

        this.pin = pin ;
        setLayout(null);
        setSize(900, 850);
        setLocation(300,0);
        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();
            if (e.getSource()==b1){
                if (textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter the Amount you want to Deposit");
                }else {
                    Con c = new Con();
                    c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"','Deposit', '"+amount+"')");
                    JOptionPane.showMessageDialog(null,amount+" Deposited Successfully");
                    setVisible(false);
                    new main_Class(pin) ;
                }
            }else if (e.getSource()==b2){
                setVisible(false);
                new main_Class(pin).setVisible(true);
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }



    public static void main(String[] args) {
        new Deposit("");




    }


}
