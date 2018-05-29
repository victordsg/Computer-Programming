/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuBar;

import Connection.CourseDAO;
import Connection.StudentDAO;
import Details.Course;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor Amorim
 */
public class SetAttendance extends JFrame {
    Container c = getContentPane();
    
     JTable table = new JTable(){
     
     
        @Override
        public boolean isCellEditable(int row, int col){
        
            return false;
        }
    };
    JScrollPane scroll = new JScrollPane(table);
    JButton update = new JButton("Update");
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Add Attendance According to the Course");
    JTextField attend = new JTextField("0");
    
    //box labels
    JLabel id = new JLabel("Id", JLabel.CENTER);
    JLabel name = new JLabel("Name",JLabel.CENTER);
    JLabel course = new JLabel("Course",JLabel.CENTER);
    JLabel attendance = new JLabel("Attendance",JLabel.CENTER);
    JLabel idTable = new JLabel();
    JLabel totalClasses = new JLabel();
    JLabel totalAttend = new JLabel();
   
    
    public SetAttendance(){
        
        Font font = new Font("Serif",Font.BOLD,22);
        
        c.setLayout(null);
        c.setBackground(Color.decode("#54567a"));
        
        panel.setBounds(20,20,300,250);
        panel.setBorder(BorderFactory.createMatteBorder(15, 3, 3, 3, Color.decode("#212348")));
        c.add(panel);
        
        label.setBounds(350,80,400,50);
        label.setFont(font);
        label.setForeground(Color.white);
        c.add(label);
        
        attend.setBounds(350,135,100,50);
        c.add(attend);
        
        update.setBounds(600,140,150,50);
        c.add(update);
        
        scroll.setBounds(17,290,750,250);
        c.add(scroll); 
        
        panel.setLayout(new GridLayout(4,1));
        panel.add(id);
        panel.add(name);
        panel.add(course);
        panel.add(attendance);
        
        
         Object[] col = {"ID Student","First Name","Surname","ID Course","Course Name","Total Attendance","Total Classes", "Total Attendance"};
         DefaultTableModel model = new DefaultTableModel();
         model.setColumnIdentifiers(col);
         table.setModel(model);
        
            CourseDAO cdao = new CourseDAO();
        
               for(Course c : cdao.courseRead()){ 
                   model.addRow(new Object[]{
                   c.getId() ,
                   c.getStudentFName(),
                   c.getStudentSurname(),
                   c.getIdTable(),
                   c.getCourseName(),
                   c.getAttend(),
                   c.getTotalClasses(),
                   c.getTotalAttendance() + " %"
                   });

               }
                 
               
               update.addActionListener(new updateButton());
               
               
               table.addMouseListener(new MouseAdapter(){
                  
            @Override
            public void mouseClicked(MouseEvent e){
                int i = table.getSelectedRow();
                id.setText(model.getValueAt(i, 0).toString());
                name.setText(model.getValueAt(i, 1).toString() + " " + model.getValueAt(i, 2).toString());
                idTable.setText(model.getValueAt(i, 3).toString());
                course.setText(model.getValueAt(i, 4).toString());
                attendance.setText(model.getValueAt(i, 5).toString());
                totalClasses.setText(model.getValueAt(i,6).toString());
                totalAttend.setText(model.getValueAt(i,7).toString());
   
            }
        });
               
              
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public class updateButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean attendan = attendance.getText().matches("\\d{1,3}");
            
           
            try{
                
                 if(attendan){
             String att = attendance.getText();
            int convAtt = Integer.parseInt(att); // GETTING ATTENDANCE FROM DB
            
            
            String attField = attend.getText();
            int convField = Integer.parseInt(attField); // GETTING THE VALUE FROM THE FIELD
            
            
            String getId = idTable.getText(); //GETTING ID FROM THE COURSE TO UPDATE
            int convId = Integer.parseInt(getId);
            
            
            int updatedAttend = convAtt + convField;
            
                String classes = totalClasses.getText();
                double convCla = Double.parseDouble(classes);
                
                double totalAttendance = (convAtt * 100) / convCla;
                
                
           CourseDAO cdao = new CourseDAO();
           cdao.setAttend(updatedAttend,totalAttendance,convId);

        double resultAttend = 0;
        int courses = 0;
        for(Course c : cdao.studentCourse()){
        resultAttend = c.getTotalAttendance() + resultAttend;
        courses ++;
        }
        
        double attend = resultAttend / courses;
        
        String studentId = id.getText();
        
        StudentDAO sdao = new StudentDAO();
        sdao.studentAttend(attend, studentId);
           
  
            JOptionPane.showMessageDialog(null,"Successful Updated!");
            
            } else {
                 JOptionPane.showMessageDialog(null,"Please Insert the attendance correctly!");
                 }
           
            }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"Please, select a student!","Wrong Message",JOptionPane.ERROR_MESSAGE);
            }
        }
    
    }
}
