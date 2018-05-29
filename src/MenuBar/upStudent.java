
package MenuBar;

import Connection.StudentDAO;
import Details.Student;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class upStudent extends JFrame {
    Container c =  getContentPane();
    JPanel info = new JPanel();
    JButton delete = new JButton("DELETE");
    JButton update = new JButton("UPDATE");
    
    JLabel label1 = new JLabel("First Name");
    JLabel label2 = new JLabel("Surname");
    JLabel label3 = new JLabel("Phone");
    JLabel label4 = new JLabel("Address");
    JLabel label5 = new JLabel("Email");
    JLabel label6 = new JLabel("Password");
    
    JTextField nameField = new JTextField();
    JTextField surField = new JTextField();
    JTextField addrField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField passField = new JTextField();
    
    
    
    JTable table = new JTable(){
        @Override
        public boolean isCellEditable(int row, int col){
        return false;
        }
        };
    DefaultTableModel model;
    JScrollPane scroll = new JScrollPane(table);
    
    public upStudent(){
    
        super("STUDENT UPDATE");
        
       c.setLayout(null);
       c.setBackground(Color.decode("#54567a"));
       
       Font font = new Font("Serif", Font.BOLD,22);
       label1.setFont(font);
       label2.setFont(font);
       label3.setFont(font);
       label4.setFont(font);
       label5.setFont(font);
       label6.setFont(font);
       
       label1.setForeground(Color.white);
       label2.setForeground(Color.white);
       label3.setForeground(Color.white);
       label4.setForeground(Color.white);
       label5.setForeground(Color.white);
       label6.setForeground(Color.white);
       
       info.setBackground(Color.decode("#54567a"));
       
       info.setLayout(new GridLayout(6,2));
       info.add(label1);
       info.add(nameField);
       info.add(label2);
       info.add(surField);
       info.add(label3);
       info.add(phoneField);
       info.add(label4);
       info.add(addrField);
       info.add(label5);
       info.add(emailField);
       info.add(label6);
       info.add(passField);
       
       
     
       
       info.setBounds(17,20,750, 250);
       c.add(info);
       
       update.setBounds(470,290,130,50);
       c.add(update);
       update.addActionListener(new updateButton());
       
       delete.setBounds(620, 290, 130,50);
       c.add(delete);
       delete.addActionListener(new deleteButton());
 
                Object[] col = {"ID", "First Name", "Surname","Phone","Address","E-mail","Password"};
                model = new DefaultTableModel();
                model.setColumnIdentifiers(col);
                table.setModel(model);
                
                StudentDAO sdao = new StudentDAO();
                
                for(Student s : sdao.studentRead()){
                    model.addRow(new Object[]{
                    s.getId(),
                    s.getfName(),
                    s.getSurname(),
                    s.getPhone(),
                    s.getAddress(),
                    s.getEmail(),
                    s.getPassword()
                    });
                    
                }

           table.addMouseListener(new MouseAdapter(){
                  
            @Override
            public void mouseClicked(MouseEvent e){
                int i = table.getSelectedRow();
                nameField.setText(model.getValueAt(i, 1).toString());
                surField.setText(model.getValueAt(i, 2).toString());
                phoneField.setText(model.getValueAt(i, 3).toString());
                addrField.setText(model.getValueAt(i, 4).toString());
                emailField.setText(model.getValueAt(i, 5).toString());
                passField.setText(model.getValueAt(i, 6).toString());
                
                
            }
        });
               scroll.setBounds(17,360,750,280);

        c.add(scroll);
    
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
}
    
    public class updateButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
       
            if(table.getSelectedColumn() != -1){
             Student st = new Student();
            StudentDAO stdao = new StudentDAO();
            
            st.setfName(nameField.getText());
            st.setSurname(surField.getText()); 
            String phone = phoneField.getText();
            int pconv = Integer.parseInt(phone);
            st.setPhone(pconv);
            st.setAddress(addrField.getText());
            st.setEmail(emailField.getText());
            st.setPassword(passField.getText());
            
            int index = table.getSelectedRow();
            st.setId(table.getValueAt(index, 0).toString());
 
            stdao.studentUpdate(st);
            JOptionPane.showMessageDialog(null,"Successfully Updated!","Update Student",JOptionPane.PLAIN_MESSAGE);
            }else{
            JOptionPane.showMessageDialog(null,"Please select a student!","Update Student",JOptionPane.ERROR_MESSAGE);
        }
           
       }
    }
    
    public class deleteButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(table.getSelectedColumn() != -1){
                 
            Student st = new Student();
            StudentDAO stdao = new StudentDAO();

            int index = table.getSelectedRow();
            st.setId(table.getValueAt(index, 0).toString());
            
            
 
            stdao.studentDelete(st);
            JOptionPane.showMessageDialog(null,"Successfully Deleted!","Delete Student",JOptionPane.PLAIN_MESSAGE);
            }else{
            JOptionPane.showMessageDialog(null,"Please select a student!","Delete Student",JOptionPane.ERROR_MESSAGE);
        }
            
        }
    
    
    }
}
