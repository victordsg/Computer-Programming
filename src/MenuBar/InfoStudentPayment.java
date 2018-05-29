
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Victor Amorim
 */
public class InfoStudentPayment extends JFrame{
    Container c= getContentPane();
    JScrollPane scroll;
    JTable table;
    DefaultTableModel model;
    JLabel label1 = new JLabel("Payment");
    JLabel label2 = new JLabel("Information");
    JButton update = new JButton("UPDATE");
    JPanel info = new JPanel();
    String[] payment = {"PAID", "INCOMPLETE","NOT PAID"};
    JComboBox combo = new JComboBox(payment);
    JTextArea area = new JTextArea("Nothing to Declare");
    JScrollPane areaScroll = new JScrollPane(area);
  
    JLabel idtxt = new JLabel("ID",JLabel.CENTER);
    JLabel nametxt = new JLabel("Name",JLabel.CENTER);
    JLabel pay = new JLabel("Payment",JLabel.CENTER);
    JLabel information = new JLabel("Information",JLabel.CENTER);
    
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    
    public InfoStudentPayment(){
    
    super("Payment");

    c.setLayout(null);
    c.setBackground(Color.decode("#54567a"));
    databaseTable();
   
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    setSize(800,650);
    setLocationRelativeTo(null);
    setResizable(false);
    }
    
   
    public void databaseTable(){
    String sql = "SELECT id,firstName,surname FROM student";
    
    Font font = new Font("Serif",Font.BOLD,22);
    
    info.setLayout(new GridLayout(4,1));
    info.add(idtxt);
    info.add(nametxt);
    info.add(pay);
    info.add(information);
    
    info.setBounds(40,20,300,250);
    info.setBorder(BorderFactory.createMatteBorder(15, 3, 3, 3, Color.decode("#212348")));
    c.add(info);

    label2.setBounds(350,20,150,50);
    label2.setFont(font);
    label2.setForeground(Color.white);
    c.add(label2);
    
    areaScroll.setBounds(350,65,410,100);
    c.add(areaScroll);
    
    label1.setBounds(350,170,150,50);
    label1.setFont(font);
    label1.setForeground(Color.white);
    c.add(label1);
    
    combo.setBounds(439,185,150,30);
    c.add(combo);
    
    update.setBounds(610,185,150,30);
    c.add(update);
    
    try{
        Connection con = DriverManager.getConnection(url, "root","admin");
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();
        
        //IT MAKES A NON-EDITABLE TABLE
         table = new JTable(){
       @Override
       public boolean isCellEditable(int row,int column){
       
           return false;
       }
       };
       
         StudentDAO sdao = new StudentDAO();
        Object[] col = {"ID","First Name", "Surname","Payment Details","Information"};
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        int c = meta.getColumnCount();
        model.setColumnIdentifiers(col);
        

         for(Student s : sdao.studentPayDet()){
            model.addRow(new Object[]{
            s.getId(),
            s.getfName(),
            s.getSurname(),
            s.getPayment(),
            s.getInfo()
            
            });
        }
        
         table.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
            
                try{
           int i = table.getSelectedRow();
                
           String id = model.getValueAt(i, 0).toString();
           String name = model.getValueAt(i, 1).toString() + " " + model.getValueAt(i, 2).toString();
           String payment = model.getValueAt(i, 3).toString();
           String det = model.getValueAt(i, 4).toString();
           idtxt.setText(id);
           nametxt.setText(name);
           pay.setText(payment);
           information.setText(det);
                }catch(NullPointerException np){ }
            }
            });
         
         update.addActionListener(new updateButton());

        scroll = new JScrollPane(table);
        scroll.setBounds(40,300,720,300);  
    
    }catch(SQLException err){
       JOptionPane.showMessageDialog(null, err.getMessage());
    }
    
       
        c.add(scroll);
        
    }
    
    public class updateButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
            String payment = null;
            
            if(combo.getSelectedItem() == "PAID"){
            payment = "FULL";
            } else if (combo.getSelectedItem() == "INCOMPLETE"){
            payment = "INCOMPLETE";
            } else if (combo.getSelectedItem() == "NOT PAID"){
            payment = "NOT";
            }

            String inputDet = area.getText();
            String id = idtxt.getText();
            
            StudentDAO sdao = new StudentDAO();
            sdao.studentPayment(payment, inputDet, id);
            JOptionPane.showMessageDialog(null, "Updated");
            }catch(Exception sql){
            JOptionPane.showMessageDialog(null, "Error");
            }
           
            
            
            
        }
    
    }
    
    
}
