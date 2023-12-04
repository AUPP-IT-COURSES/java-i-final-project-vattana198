package bank.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pin extends JFrame implements ActionListener {
    JButton b1,b2 ;
    JPasswordField p1 , p2 ;



    String pin ;

    Pin(String pin  ){
        this.pin = pin ;


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/atm.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 850);
        add(l3);


        JLabel label1 = new JLabel("CHANGE YOUR PIN ") ;
        label1.setFont(new Font("Raleway", Font.BOLD, 14)) ;
        label1.setForeground(Color.WHITE);
        label1.setBounds(180,300,700,35);
        l3.add(label1) ;


        JLabel label2 = new JLabel("New PIN:") ;
        label2.setFont(new Font("Raleway", Font.BOLD, 14)) ;
        label2.setForeground(Color.WHITE);
        label2.setBounds(180,330,700,35);
        l3.add(label2) ;

        p1 = new JPasswordField();
        p1.setFont(new Font("Raleway", Font.BOLD, 14)) ;
        p1.setForeground(Color.WHITE);
        p1.setBackground(new Color(65, 125, 128));
        p1.setBounds(340,330,160,25);
        p1.setOpaque(true);
        l3.add(p1) ;


        JLabel label3 = new JLabel("RE-ENTER NEW PIN:");
        label3.setFont(new Font("Raleway", Font.BOLD, 14)) ;
        label3.setForeground(Color.WHITE);
        label3.setBounds(180,365,600,30);
        l3.add(label3) ;

        p2 = new JPasswordField() ;
        p2.setFont(new Font("Raleway", Font.BOLD, 14));
        p2.setForeground(Color.WHITE);
        p2.setBackground(new Color(65, 125, 128));
        p2.setBounds(340,365,160,25);
        p2.setOpaque(true);
        l3.add(p2) ;


        b1 = new JButton("CHANGE") ;
        b1.setFont(new Font("Raleway", Font.BOLD , 16));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(400, 440, 120, 25);
        b1.setOpaque(true);
        b1.setBorderPainted(false);
        b1.addActionListener(this);
        l3.add(b1) ;

//        b2.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                b2.setBackground(Color.WHITE);
//            }
//            public void mouseReleased(MouseEvent e) {
//                b2.setBackground(new Color(65, 125, 128));
//            }
//        });
//        l3.add(b2);

        b2 = new JButton("BACK");
        b2.setFont(new Font("Raleway", Font.BOLD , 16));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(400, 475, 120, 25);
        b2.setOpaque(true);
        b2.setBorderPainted(false);
        b2.addActionListener(this);
        l3.add(b2);



        setLayout(null);
        setSize(900, 850);
        setLocation(300,0);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String pin1= p1.getText();
            String pin2 = p2.getText();

            if (!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null , "Entered Pin does not match ");
                return ;
            }

            if(e.getSource() == b1){
                if (p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null , "Enter New Pin");
                    return ;
                }else if (p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null , "Rr-Enter New Pin ");
                    return ;
                }
                Con c = new Con() ;
                String q1 = "update bank set pin = '"+pin1+"' where pin = '"+pin+"'    ";
                String q2 = "update login set pin = '"+pin1+"' where pin = '"+pin+"'  ";
                String q3 = "update signupthree set pin = '"+pin1+"' where pin = '"+pin+"'    ";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null ,"PIN changed successfully ");
                setVisible(false);
                new main_Class(pin) ;



            }else if(e.getSource() == b2 ){
                new main_Class(pin);
                setVisible(false);
            }










        }catch (Exception E){
            E.printStackTrace();
        }




    }


    public static void main(String[] args) {
        new Pin("");

    }



}
