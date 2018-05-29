
package VGCWindow;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import Details.Faculty;
import Details.Manager;
import Details.Student;

 

public class LoginWindow extends JFrame{
    
    Container c = getContentPane();
    JLabel login = new JLabel("Login");
    JLabel password = new JLabel("Password");
    JTextField loginField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    
    String[] optionLogin = {"Student","Faculty","Manager"};
    JComboBox option = new JComboBox(optionLogin);
    
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    
    
    
    public LoginWindow(){
        
         super("VGC Login");

        Container();
        Location();
        addContainer();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(450,300);
        setLocationRelativeTo(null);

    }
    
   public void loginCheck(String login,String pass){
       
      Object tableOption = option.getSelectedItem();
      String conv = tableOption.toString().toLowerCase();
      
      String sql = "SELECT * FROM " + conv + " WHERE id = ? AND password = ?";
      
      
       try{
         Connection con = DriverManager.getConnection(url,"root","admin");
         PreparedStatement stmt = con.prepareStatement(sql);

         stmt.setString(1,login);
         stmt.setString(2,pass);
         
         ResultSet rs = stmt.executeQuery();
         
         
         if("student".equals(conv)){
         
             if(rs.next()){
             Student st = new Student(rs.getString("id"),rs.getString("firstName"),rs.getString("surname"),
                     rs.getInt("phone"),rs.getString("address"),rs.getString("email"),rs.getString("password"));
             ViewWindow vw = new ViewWindow();
             vw.infoStudent(st); 
             dispose(); 

            }else{
              JOptionPane.showMessageDialog(null, "Invalid Login/Password, please try again!","Login Problem",JOptionPane.ERROR_MESSAGE);
            }
         }else if("faculty".equals(conv)){
             
             if(rs.next()){
             Faculty fc = new Faculty(rs.getString("id"),rs.getString("firstName"),rs.getString("surname"),
                     rs.getInt("phone"),rs.getString("address"),rs.getString("email"),rs.getString("password"));
             ViewWindow vw = new ViewWindow();
             vw.infoFaculty(fc);
             dispose();

            }else{
              JOptionPane.showMessageDialog(null, "Invalid Login/Password, please try again!","Login Problem",JOptionPane.ERROR_MESSAGE);
          }  
         }else if("manager".equals(conv)){
             
             if(rs.next()){
             Manager man = new Manager(rs.getString("firstName"),rs.getString("surname"));
             //List<Student> student= insertInformation();
             ViewWindow vw = new ViewWindow();
             vw.infoManger(man);
             dispose(); 

            }else{
              JOptionPane.showMessageDialog(null, "Invalid Login/Password, please try again!","Login Problem",JOptionPane.ERROR_MESSAGE);
            }
         }
         
         else{
             System.out.println("error");
         }
         
         
         
       }catch(SQLException err){
           
        }
   }
 
   public void Container (){
   c.setLayout(null);
   }
   
   public void Location(){
       login.setBounds(130,100,100,20);
       password.setBounds(100,130,100,20);
       loginField.setBounds(180,100,150,25);
       passField.setBounds(180,130,150,25);
       loginButton.setBounds(210,200,140,30);
       option.setBounds(248,160,80,30);

   }
   
   public void addContainer(){
   
       c.add(login);
       c.add(password);
       c.add(loginField);
       c.add(passField);
       c.add(loginButton);
       c.add(option);

        loginButton.addActionListener(new buttonAction());
        
        passField.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            loginButton.doClick(); }}});
   }

   public class buttonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
           loginCheck(loginField.getText(),new String(passField.getPassword()));
        }
}
   
}
