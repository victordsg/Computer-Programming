
package MenuBar;

import Connection.FacultyDAO;
import Details.Faculty;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class InfoFaculty extends JFrame {
    Container c= getContentPane();
    JScrollPane scroll;
    JTable table;
    DefaultTableModel model;
    JLabel label;
  
    
    
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    
    public InfoFaculty(){
    
    super("Faculty Information");

    c.setLayout(null);
    c.setBackground(Color.decode("#54567a"));
    databaseTable();
   
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    setSize(800,500);
    setLocationRelativeTo(null);
    setResizable(false);
    }
    
   
    public void databaseTable(){
    String sql = "SELECT id,firstName,surname,phone,address,email,password FROM student";
    
    label = new JLabel("Student Information");
    Font font = new Font("Serif",Font.BOLD,35);
    label.setForeground(Color.white);
    label.setFont(font);
    
    
        //IT MAKES A NON-EDITABLE TABLE
         table = new JTable(){
       @Override
       public boolean isCellEditable(int row,int column){
       
           return false;
       }
       };
       
        
         
        Object[] col = {"ID","First Name", "Surname", "Phone" , "Address", "E-mail","Password"};
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(col);
        
        FacultyDAO fdao = new FacultyDAO();
        
        for(Faculty f : fdao.facultyRead()){
        model.addRow(new Object[]{
        f.getId(),
            f.getfName(),
            f.getSurname(),
            f.getPhone(),
            f.getAddress(),
            f.getEmail(),
            f.getPassword()
        
        });
            
        }
        
        

        scroll = new JScrollPane(table);
        scroll.setBounds(40,90,720,320);  
        c.add(label);
       label.setBounds(245,25,720,50);
        c.add(scroll);
    }
    
 
   
   
}
