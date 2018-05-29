/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuBar;

import Connection.CourseDAO;
import Connection.StudentDAO;
import Details.Course;
import Details.Student;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor Amorim
 */
public class RegStudCourse extends JFrame{
    
    Container c= getContentPane();
    
    JPanel panel = new JPanel();
    JLabel label3 = new JLabel("Select a Course:");
    JLabel label2 = new JLabel("Select a Student:");
    JLabel label1 = new JLabel("Course:");
    JComboBox courseComb = new JComboBox();
    JButton update = new JButton("Register");
    JButton delete = new JButton("Delete");
    
    DefaultTableModel model =  new DefaultTableModel();
    JTable tableCourse = new JTable(){
    
        @Override
        public boolean isCellEditable(int row, int col){
        
            return false;
        }
    };
    
    JTable tableStudent = new JTable(){
    
        @Override
        public boolean isCellEditable(int row, int col){
        
            return false;
        }
    };
    
    
    JScrollPane scrollCourse = new JScrollPane(tableCourse);
    JScrollPane scrollStudent = new JScrollPane(tableStudent);
    
    public RegStudCourse(){
    
        super("REGISTERING STUDENT TO COURSE");
        
     Font font = new Font("Serif",Font.BOLD,22);
        
     c.setLayout(null);
     c.setBackground(Color.decode("#54567a"));

     scrollCourse.setBounds(17,290,750,250);
     c.add(scrollCourse);
      
     scrollStudent.setBounds(17,70,250,185);
     c.add(scrollStudent);
     
     label1.setBounds(365,80,100,50);
     label1.setFont(font);
     label1.setForeground(Color.white);
     c.add(label1);
     
     label2.setBounds(17, 20, 175,50);
     label2.setForeground(Color.white);
     label2.setFont(font);
     c.add(label2);
     
     label3.setBounds(390, 20, 175,50);
     label3.setForeground(Color.white);
     label3.setFont(font);
     c.add(label3);

     courseComb.setBounds(450,90,250,30);
     c.add(courseComb);
     
     update.setBounds(550,200,150,50);
     c.add(update);
     
     delete.setBounds(385,200,150,50);
     c.add(delete);
     
        
                // STUDENTS TABLE REGISTERS TO A COURSE
                Object[] col = {"ID","First Name","Surname","Year","Course Name"};
                
                model.setColumnIdentifiers(col);
                tableCourse.setModel(model);
                model.fireTableDataChanged();
                

         
               CourseDAO cdao = new CourseDAO();

               for(Course c : cdao.courseRead()){ 
                   model.addRow(new Object[]{
                   c.getId(),
                   c.getStudentFName(),
                   c.getStudentSurname(),
                   c.getYearCollege(),
                   c.getCourseName() 
                   
                   });

               }
               
               //adding course to the Course JComboBox
               for(Course c: cdao.nameCourse()){
                   courseComb.addItem(c.getCourseName() + " | Year: " + c.getYearCollege());
                  
               }
               
               // STUDENT TABLE FOR REGISTERING
               Object[] col2 = {"ID","First Name", "Surname"};
               DefaultTableModel model2 = new DefaultTableModel();
               model2.setColumnIdentifiers(col2);
               tableStudent.setModel(model2);
               model2.fireTableDataChanged();
               
               
               StudentDAO stdao = new StudentDAO();
               
               for(Student s : stdao.studentRead()){
                   model2.addRow(new Object[]{
                   s.getId(),
                   s.getfName(),
                   s.getSurname()
                   });
               
               }

               update.addActionListener(new updateButton());
               delete.addActionListener(new deleteButton());
               
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
                
 }  
    
    public class updateButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(tableStudent.getSelectedColumn() != -1){
                int i = tableStudent.getSelectedRow(); //getting index of the select student
               String id = tableStudent.getValueAt(i, 0).toString();
               
               int courseIndex = courseComb.getSelectedIndex() + 1;
               
               CourseDAO cdao = new CourseDAO();
               cdao.regStudentCourse(id, courseIndex);
               
           
               
               JOptionPane.showMessageDialog(null, "Name: " + tableStudent.getValueAt(i, 1) +  " " + tableStudent.getValueAt(i, 2)+ "\n" + "Course: " + courseComb.getSelectedItem());
            }else{
                JOptionPane.showMessageDialog(null, "Please select someone from the table \non the left side to keep registering!","DELETE OPTION",JOptionPane.ERROR_MESSAGE);
            }
          
                   
        }
    
    }
    
    public class deleteButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           int i = tableCourse.getSelectedRow();
            
           if(tableCourse.getSelectedColumn() != -1){
            CourseDAO cdao = new CourseDAO();
            cdao.deleteStudentCourse(i);
            JOptionPane.showMessageDialog(null, "Name: " + tableStudent.getValueAt(i, 1) +  " " + tableStudent.getValueAt(i, 2)+ "\n" + "Course: " + courseComb.getSelectedItem());
           }else{
               JOptionPane.showMessageDialog(null, "Please select someone from the table \non the botton to keep deleting!","DELETE OPTION",JOptionPane.ERROR_MESSAGE);
           }
         
        }
    
    }
}
