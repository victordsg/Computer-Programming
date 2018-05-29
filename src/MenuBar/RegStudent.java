/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuBar;


import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;
import Details.Student;

/**
 *
 * @author Victor Amorim
 */
public class RegStudent extends JFrame{
    
    Container c = getContentPane();
    Container regDetails = new JPanel();
    Container buttons = new JPanel();
    Container studPanel = new JPanel();
    
    JPanel test = new JPanel();
    
    //Register JLabel
    JLabel fName = new JLabel("First Name");
    JLabel lName = new JLabel("Last Name");
    JLabel phoneNumber = new JLabel("Phone Number");
    JLabel address = new JLabel("Address");
    JLabel email = new JLabel("E-mail");
    JLabel pass = new JLabel("Password");
    
    //Register Field
    JTextField fieldName = new JTextField(20);
    JTextField fieldSur = new JTextField();
    JTextField fieldPhone = new JTextField();
    JTextField fieldAdd = new JTextField();
    JTextField fieldEm = new JTextField();
    JTextField passField = new JTextField();
    
   //Buttons 
    JButton regButton = new JButton("Register");
    JButton detButton = new JButton("Details");
    
    //Others
    JSplitPane split = new JSplitPane();
    JList<Student> list = new JList();
    DefaultListModel<Student> model = new DefaultListModel();
    JSeparator sep = new JSeparator(JSeparator.VERTICAL); 
    JScrollPane scroll = new JScrollPane(list);
    
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    
    public RegStudent(){
    super("Register of new Students");

        setPanelDetails();
        setLocation();
        setContainer();
        selectDatabase();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void setPanelDetails(){
    
        fName.setForeground(Color.white);
        lName.setForeground(Color.white);
        phoneNumber.setForeground(Color.white);
        address.setForeground(Color.white);
        email.setForeground(Color.white);
        pass.setForeground(Color.white);
        
        list.setModel(model);
        c.setLayout(null);
        c.setBackground(Color.decode("#54567a"));
        
        regDetails.setLayout(new GridLayout(8,2,15,15));
        regDetails.setBackground(Color.decode("#54567a"));
        
        buttons.setLayout(new GridLayout(1,1));
        buttons.setBackground(Color.decode("#DEDEDE"));
        
        studPanel.setBackground(Color.decode("#DEDEDE"));
        
    }
    
    public void setLocation(){
    
        regDetails.add(fName);
        regDetails.add(fieldName);
        regDetails.add(lName);
        regDetails.add(fieldSur);
        regDetails.add(phoneNumber);
        regDetails.add(fieldPhone);
        regDetails.add(address);
        regDetails.add(fieldAdd);
        regDetails.add(email);
        regDetails.add(fieldEm);
        regDetails.add(pass);
        regDetails.add(passField);
        
        buttons.add(regButton);
        
        regButton.addActionListener(new regButton());

    regDetails.setBounds(40,50,450,450);
    buttons.setBounds(340,425,150,40);
    sep.setBounds(525,15,300,490);
    scroll.setBounds(540,15,240,490);

    }
    
    public void setContainer(){
        c.add(buttons);
        c.add(regDetails);
        c.add(sep);
        c.add(scroll);

    }
    
    public void selectDatabase(){
    
    String select = "SELECT * FROM student";
        
    try(Connection con = DriverManager.getConnection(url,"root","admin");
     PreparedStatement stmt = con.prepareStatement(select);
     ResultSet rs = stmt.executeQuery()){

         while(rs.next()){
             String id = rs.getString("id");
             String firstName = rs.getString("firstName");
             String surname = rs.getString("surname");
             
   
             model.addElement(new Student(id,firstName,surname));
             
         }
     
     }catch(SQLException err){
         System.err.println("ERROR " + err);
     
     }
    
    }
    
    public String generatingId(){
        
       Random rand = new Random();
        
       String firstName = fieldName.getText().toLowerCase();
       String surname = fieldSur.getText().toLowerCase();
     
       int numb1 = rand.nextInt(9);
       int numb2 = rand.nextInt(9);
       int numb3 = rand.nextInt(9);
       
       String id = firstName.charAt(0) + "" + surname.charAt(0) + "" + numb1 + "" + numb2 + "" + numb3;
       return id;
    }
    
    public class regButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
     String insert = "INSERT INTO student VALUES (?,?,?,?,?,?,?)";
            
     try(Connection con = DriverManager.getConnection(url,"root","admin");
         PreparedStatement stmt = con.prepareStatement(insert)){
         
            String id = generatingId();
            String name = fieldName.getText();
            String surname = fieldSur.getText();
            String phone = fieldPhone.getText();
            int phonec = Integer.parseInt(phone);
            String address = fieldAdd.getText();
            String email = fieldEm.getText();
            String password = passField.getText();
         
         stmt.setString(1, id);
         stmt.setString(2, name);
         stmt.setString(3, surname);
         stmt.setInt(4, phonec);
         stmt.setString(5, address);
         stmt.setString(6, email);
         stmt.setString(7, password);
         stmt.addBatch();
         
         stmt.executeBatch();

             model.addElement(new Student(id,name,surname,phonec,address,email,password));
            JOptionPane.showMessageDialog(null,"ID: " + id + "\nName: "+ name + " "+ surname, "Register Complete",JOptionPane.INFORMATION_MESSAGE);
     
     }catch(SQLException err){
         System.err.println("ERROR " + err);
     
     }catch(StringIndexOutOfBoundsException err){
         JOptionPane.showMessageDialog(null, "Error: " + err.getMessage() + "\nPlease Fill the field!", "Error Message",JOptionPane.ERROR_MESSAGE);
     }catch(NumberFormatException err){
         JOptionPane.showMessageDialog(null, "Error: " + err.getMessage() + "\nWrong Phone Number!", "Error Message",JOptionPane.ERROR_MESSAGE);
     }
            
         
        }
    
    }
    
    
}
