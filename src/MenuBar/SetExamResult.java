/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuBar;

import Connection.CourseDAO;
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
public class SetExamResult extends JFrame {
   
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
    JLabel label = new JLabel("Add Result of Exams");
    JTextField gradeField = new JTextField("0");
    
    JLabel id = new JLabel("ID", JLabel.CENTER);
    JLabel name = new JLabel("Name", JLabel.CENTER);
    JLabel course = new JLabel("Course Name", JLabel.CENTER);
    JLabel grade = new JLabel("Total Grade", JLabel.CENTER);
    JLabel idTable = new JLabel();
    
    public SetExamResult(){
        
        Font font = new Font("Serif",Font.BOLD,22);
        
        c.setLayout(null);
        c.setBackground(Color.decode("#54567a"));
        
        panel.setLayout(new GridLayout(4,1));
        panel.setBounds(20,20,300,250);
        panel.setBorder(BorderFactory.createMatteBorder(15, 3, 3, 3, Color.decode("#212348")));
        c.add(panel);
        panel.add(id);
        panel.add(name);
        panel.add(course);
        panel.add(grade);
        
        label.setBounds(350,80,400,50);
        label.setFont(font);
        label.setForeground(Color.white);
        c.add(label);
        
        gradeField.setBounds(350,135,100,50);
        c.add(gradeField);
        
        update.setBounds(600,140,150,50);
        c.add(update);
        
        scroll.setBounds(17,290,750,250);
        c.add(scroll); 
        
         Object[] col = {"ID Table","ID","First Name","Surname","Course Name","Exam Result(%)"};
         DefaultTableModel model = new DefaultTableModel();
         model.setColumnIdentifiers(col);
         table.setModel(model);
        
            CourseDAO cdao = new CourseDAO();
        
               for(Course c : cdao.courseRead()){ 
                   model.addRow(new Object[]{
                   c.getIdTable(),
                   c.getId(),
                   c.getStudentFName(),
                   c.getStudentSurname(),
                   c.getCourseName(),
                   c.getExamResult()
                   });

               }
         update.addActionListener(new updateButton());
        table.addMouseListener(new MouseAdapter(){
                  
            @Override
            public void mouseClicked(MouseEvent e){
                int i = table.getSelectedRow();
               idTable.setText(model.getValueAt(i,0).toString());
               id.setText(model.getValueAt(i, 1).toString());
               name.setText(model.getValueAt(i, 2).toString() +" " + model.getValueAt(i,3).toString());
               course.setText(model.getValueAt(i, 4).toString());
               grade.setText(model.getValueAt(i, 5).toString());
                
   
            }
        });
               
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public class updateButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          boolean grades = gradeField.getText().matches("\\d{1,3}");
            
           try{
               
               if(grades){
               String gradeTxt = gradeField.getText();
            double gradeConv = Double.parseDouble(gradeTxt);
                
            String dbGrade = grade.getText();
            double convDBGrade = Double.parseDouble(dbGrade);

            double updateExam = gradeConv + convDBGrade;
            String idText = idTable.getText();
            int idConv = Integer.parseInt(idText);
          
            CourseDAO cdao = new CourseDAO();
            cdao.setExam(updateExam, idConv);
            
            JOptionPane.showMessageDialog(null,"Successful Updated!");
               } else {
               JOptionPane.showMessageDialog(null,"Please Insert the grade correctly!");
               }
           
           }catch(NumberFormatException err){
           JOptionPane.showMessageDialog(null,"Please, select a student!","Wrong Message",JOptionPane.ERROR_MESSAGE);
           }
           
           
           
        }
    
    
    }
}
