
package MenuBar;

import Connection.CourseDAO;
import Connection.StudentDAO;
import Details.Course;
import Details.Student;
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

/**
 *
 * @author Victor Amorim
 */
public class InfoStudentAttend extends JFrame{
    
    Container c= getContentPane();
    JScrollPane scroll;
    JTable table = new JTable(){
       @Override
       public boolean isCellEditable(int row,int column){
       
           return false;
       }
       };
    DefaultTableModel model;
    JLabel label;
  
    
    
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    
    public InfoStudentAttend(){
    
    super("Student Information");

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
    
    
    label = new JLabel("Student Information");
    Font font = new Font("Serif",Font.BOLD,35);
    label.setForeground(Color.white);
    label.setFont(font);

        Object[] col = {"ID","First Name", "Surname","Total Attendance"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(col);
        table.setModel(model);
        
        StudentDAO sdao = new StudentDAO();
        for(Student s : sdao.studentRead()){
        model.addRow(new Object[]{
        s.getId(),
        s.getfName(),
        s.getSurname(),
        s.getTotalAttendance()  + "%"
        });
        
        }

        scroll = new JScrollPane(table);
        scroll.setBounds(40,90,720,320);  
    
   
    
       c.add(label);
       label.setBounds(245,25,720,50);
       c.add(scroll);
    }
    
 
    
}
