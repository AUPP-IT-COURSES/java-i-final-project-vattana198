package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main_Class extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4,b5,b6,b7 ;
    String pin ;


    main_Class(String pin){
        this.pin = pin ;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.png"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,900,850);
        add(l3);


        JLabel label = new JLabel("Please Select Your Transaction") ;
        label.setFont(new Font("Raleway" , Font.BOLD , 20));
        label.setForeground(Color.WHITE);
        label.setBounds(170,300,500,30);
        l3.add(label);



        b1  = new JButton("DEPOSIT");
        b1.setFont(new Font("Raleway", Font.BOLD , 16));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(160, 390, 150, 28);
        b1.setOpaque(true);
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        b1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b1.setBackground(Color.WHITE);
            }
            public void mouseReleased(MouseEvent e) {
                b1.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b1);


        b2  = new JButton(" WITHDRAWAL");
        b2.setFont(new Font("Raleway", Font.BOLD , 12));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(350,390,160,28);
        b2.setOpaque(true);
        b2.setBorderPainted(false);
        b2.addActionListener(this);
        b2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b2.setBackground(Color.WHITE);
                new Withdrawl(pin) ;
            }
            public void mouseReleased(MouseEvent e) {

                b2.setBackground(new Color(65, 125, 128));
            }
        });
        l3.add(b2);




        JButton b3 = new JButton("FAST CASH ");
        b3.setFont(new Font("Raleway" , Font.BOLD , 12));
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(160,425,150,28);
        b3.setOpaque(true);
        b3.setBorderPainted(false);
        b3.addActionListener(this);
        b3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b3.setBackground(Color.WHITE);
                new FastCash(pin);
            }
            public void mouseReleased(MouseEvent e) {
                b3.setBackground(new Color(65, 125, 128));

            }
        });
        l3.add(b3) ;

        JButton b4 = new JButton(" MINI STATEMENT ");
        b4.setFont(new Font("Raleway" , Font.BOLD , 12));
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(350,425,160,28);
        b4.setOpaque(true);
        b4.setBorderPainted(false);
        b4.addActionListener(this);
        b4.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
//                    b4.setBackground(Color.WHITE);
                new mini(pin);
            }
            public void mouseReleased(MouseEvent e) {
                b5.setBackground(new Color(65, 125, 128));

            }
        });
        l3.add(b4) ;

        JButton b5 = new JButton("PIN CHANGE");
        b5.setFont(new Font("Raleway" , Font.BOLD , 12)) ;
        b5.setBackground(new Color(65,125,128));
        b5.setForeground(Color.WHITE);
        b5.setBounds(160 , 460  ,150, 28);
        b5.setOpaque(true);
        b5.setBorderPainted(false) ;
        b5.addActionListener(this);
        b5.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b5.setBackground(Color.WHITE);
                new Pin(pin);
            }
            public void mouseReleased(MouseEvent e) {
                b5.setBackground(new Color(65, 125, 128));

            }
        });
        l3.add(b5) ;


        JButton b6 = new JButton("BALANCE ENQUIRY");
        b6.setFont(new Font("Raleway", Font.BOLD, 12));
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65, 125, 128));
        b6.setBounds(350,460,161,28);
        b6.addActionListener(this);
        b6.setOpaque(true);
        b6.setBorderPainted(false);
        b6.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b6.setBackground(Color.WHITE);
                new BalanceEnquriy(pin);
            }
            public void mouseReleased(MouseEvent e) {
                b6.setBackground(new Color(65, 125, 128));

            }
        });
        l3.add(b6);


        JButton b7 = new JButton("Exit");
        b7.setFont(new Font("Raleway" , Font.BOLD , 12));
        b7.setBackground(new Color(65,125,128));
        b7.setForeground(Color.WHITE);
        b7.setBounds(350,495,160,25);
        b7.setOpaque(true);
        b7.setBorderPainted(false);
        b7.addActionListener(this);
        b7.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                b7.setBackground(Color.WHITE);
                System.exit(0);
            }
            public void mouseReleased(MouseEvent e) {
                b7.setBackground(new Color(65, 125, 128));

            }
        });
        l3.add(b7);;






        setLayout(null);
        setSize( 900, 850  );
        setLocation(300,0);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b1){
            new Deposit(pin);
            setVisible(false);
        } else if (e.getSource() == b7){
            System.exit(0);
        }else if (e.getSource() == b2){
            new Withdrawl(pin);
            setVisible(false) ;
//           new main_Class(pin);

        }  else if (e.getSource() == b6){
            new BalanceEnquriy(pin);
            setVisible(false);
        } else if (e.getSource() == b3){
            new FastCash(pin);
            setVisible(false);
        }else if (e.getSource() == b5){
            new Pin(pin);
            setVisible(false);
        }else if(e.getSource() == b4){
            new mini(pin);
            setVisible(false);
        }



    }

    public static void main(String[] args) {
        new main_Class("");
    }



}
