/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Victor Amorim
 */
public class AboutWindow extends JFrame{
    Container c = getContentPane();
    JTextArea about = new JTextArea();
    
    public AboutWindow(){
    
        
        c.setLayout(new BorderLayout());
        c.setBackground(Color.decode("#54567a"));
         Font font = new Font("Serif",Font.BOLD,20);
         Font font2 = new Font("Serif",Font.BOLD,15);
        JLabel label = new JLabel("<html>This project aims to learn in computer science in <br>its module in the programming course.</html>",JLabel.CENTER);
        JLabel label2 = new JLabel("Victor Amorim - 17633 || DORSET COLLEGE",JLabel.CENTER);
        label.setFont(font);
        label2.setFont(font2);
        label2.setForeground(Color.white);
        label.setForeground(Color.white);
        c.add(label2,BorderLayout.SOUTH);
        c.add(label,BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
