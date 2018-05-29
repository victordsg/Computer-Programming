package VGCWindow;

import Connection.CourseDAO;
import Connection.StudentDAO;
import Details.Course;
import Details.Faculty;
import MenuBar.RegStudent;
import com.toedter.calendar.JCalendar;
import java.awt.*;
import java.awt.event.*;
import java.util.PriorityQueue;
import javax.swing.*;
import Details.Student;
import MenuBar.AboutWindow;
import MenuBar.InfoStudent;
import MenuBar.InfoStudentAttend;
import MenuBar.InfoStudentPayment;
import MenuBar.RegFaculty;
import Details.Manager;
import MenuBar.InfoFaculty;
import MenuBar.RegStudCourse;
import MenuBar.SetAttendance;
import MenuBar.SetExamResult;
import MenuBar.upFaculty;
import MenuBar.upStudent;
import java.util.*;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Victor Amorim
 */
public class ViewWindow extends JFrame {
    
    
    //MENU
  JMenuBar bar = new JMenuBar();   
  JMenu menu = new JMenu("Menu");
  JMenu info = new JMenu("Information");
  JMenuItem about = new JMenuItem("About");
  JMenu register = new JMenu("Register"); 
  JMenu update = new JMenu("Update");
  JMenu course = new JMenu("Course");
  JMenu infoStudent = new JMenu("Student");
  JMenu infoFaculty = new JMenu("Faculty");
  
  JMenuBar visitBar;
  JMenu menuVisit;
  JLabel menuLabel = new JLabel("Welcome, ");

  //MenuItem
  JMenuItem regStudent = new JMenuItem("Student");
  JMenuItem regFaculty = new JMenuItem("Faculty");
  JMenuItem upStudent = new JMenuItem("Student");
  JMenuItem upFaculty = new JMenuItem("Faculty");
  JMenuItem infoDetStudent = new JMenuItem("Details");
  JMenuItem infoAttStudent = new JMenuItem("Attendance");
  JMenuItem infoPayStudent = new JMenuItem("Payment");
  JMenuItem detFaculty = new JMenuItem("Details");
  JMenuItem setStudentCourse = new JMenuItem("Set Course to Student");
  JMenuItem setAttendance = new JMenuItem("Set Attendance");
  JMenuItem setExams = new JMenuItem("Set Exams Result");
  
  


  //Window View
  JPanel coursePanel = new JPanel();
  Container panelRight = new JPanel();
  Container panelLeft = new JPanel();
  JScrollPane scrollRight = new JScrollPane(panelRight);
  JScrollPane scrollLeft = new JScrollPane(panelLeft);
  JSplitPane split = new JSplitPane();
  JCalendar calendar = new JCalendar();
  JPanel dataCourse = new JPanel();
  

    public ViewWindow(){

        super("VGC - VIRTUAL GLOBAL COLLEGE");
        
        ImageIcon jframeIcon = new ImageIcon(getClass().getResource("/Images/book_addresses.png"));
        setIconImage(jframeIcon.getImage());
        
        
        windowView();
        
        //JFrame Default Details
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        
    }
    
    //WHEN A STUDENT LOGIN
   public void infoStudent(Student st){
       CourseDAO cdao = new CourseDAO();
       StudentDAO sdao = new StudentDAO();
       String id = st.getId();
       sdao.attend(id);
       
       
       
        visitBar = new JMenuBar();
        menuVisit = new JMenu(st.getfName() + " " + st.getSurname());
        visitBar.add(menuLabel);
        visitBar.add(menuVisit);
        coursePanel.setLayout(new BorderLayout());
        coursePanel.add(visitBar,BorderLayout.NORTH);
        coursePanel.setBounds(0,0,760,21);
        studentDetails(st);
        
        
         int numberCourse = 0; //COUNTS THE NUMBER OF COURSE OF THE STUDENT!
        for(Course c : cdao.studentCourse()){
       numberCourse++;
    }    
       
   
    JPanel arrayCourse[] = new JPanel[numberCourse];
    
    JPanel courses;
    
    JLabel labelCourse;
    PriorityQueue pq01 = new PriorityQueue();
      
    int i = 0;
    int position = 0;
      for (Course c : cdao.studentDetails(id)) {
          position = position + 120;
          String courseConv = c.getCourseName();
                pq01.offer(courseConv);
                labelCourse = new JLabel((String) pq01.peek(),JLabel.CENTER);
                courses = new JPanel();
                courses.setLayout(new GridLayout(3,1));
                courses.setBounds(55, position, 600, 100);
                courses.add(labelCourse);
                courses.setBackground(Color.decode("#e9e9ee"));
                courses.setBorder(BorderFactory.createMatteBorder(3, 15, 3, 3, Color.decode("#212348")));
                pq01.peek();
                
                
                
                while(!pq01.isEmpty()){
                    String rmv = (String) pq01.poll();
                    pq01.poll();
                }
                
                double examResult = c.getExamResult();
                String convExam = "Exam Result: " + String.valueOf(examResult);
                courses.add(new JLabel(convExam,JLabel.CENTER));
                double attend = c.getTotalAttendance();
                String convAttend = "Attendance: " + String.valueOf(attend) + "%";
                courses.add(new JLabel(convAttend,JLabel.CENTER));
                arrayCourse[i] = courses;
                panelLeft.add(arrayCourse[i]);
          i++;
          numberCourse++;
         
      }
      
        int hight = arrayCourse.length * 150;
        panelLeft.setPreferredSize(new Dimension(680, hight));

       
       // panelLeft.add(dataCourse);
        dataCourse.setBorder(BorderFactory.createLineBorder(Color.black));
        dataCourse.setBounds(75,220,600,300);
        
   }
   
   public void infoFaculty(Faculty f){
   
       JLabel label = new JLabel("Notification");
       JTextArea area = new JTextArea();
       JButton button = new JButton("Add");
       
       visitBar = new JMenuBar();
        menuVisit = new JMenu(f.getfName() + " " + f.getSurname());
        visitBar.add(menuLabel);
        visitBar.add(menuVisit);
        coursePanel.setLayout(new BorderLayout());
        coursePanel.add(visitBar,BorderLayout.NORTH);
        coursePanel.setBounds(0,0,760,21);
       
       JTable table = new JTable();
       Object[] col = {"ID","First Name", "Surname", "Course", "Attendance", "Exam Result"};
       DefaultTableModel model = new DefaultTableModel();
       
       table.setModel(model);
       model.setColumnIdentifiers(col);
       JScrollPane scroll = new JScrollPane(table);
       
       CourseDAO cdao = new CourseDAO();
       
       for(Course c : cdao.courseRead() ){
       model.addRow(new Object[]{
       c.getId(),
       c.getStudentFName(),
       c.getCourseName(),
       c.getStudentSurname(),
       c.getTotalAttendance(),
       c.getExamResult()
       
       });
       }
        Font font = new Font("Serif",Font.BOLD,20);
        label.setFont(font);
        label.setForeground(Color.white);
        button.setBounds(535,590,150,50);
       area.setBounds(33,475,650,100);
       label.setBounds(33,425,150,50);
       scroll.setBounds(33,100,650,300);
       panelLeft.add(scroll);
       panelLeft.add(label);
       panelLeft.add(area);
       panelLeft.add(button);
       
       
   }

    /*ADMIN LOGIN AND
     ADDING MENU + MENU ITENS*/
    public void infoManger(Manager man){
      
        menuIcon();
       setJMenuBar(bar);
      
   

       bar.add(menu);
       bar.add(info); 
       bar.add(course);
       bar.add(about);
       
       menu.add(register); 
       menu.addSeparator();
       menu.add(update);
       
       
       register.add(regStudent);
       register.add(regFaculty);//sub Menu Items
       
       update.add(upStudent);
       update.add(upFaculty);    

       info.add(infoStudent);
       info.addSeparator();
       info.add(infoFaculty);
       
       infoStudent.add(infoDetStudent);
       infoStudent.add(infoAttStudent);
       infoStudent.add(infoPayStudent);
       
       infoFaculty.add(detFaculty);
       

       //VISIT MENU BAR 
        visitBar = new JMenuBar();
        menuVisit = new JMenu(man.getfName() + " " + man.getSurname() + " - ADMIN");
        visitBar.add(menuLabel);
        visitBar.add(menuVisit);
        coursePanel.setLayout(new BorderLayout());
        coursePanel.add(visitBar,BorderLayout.NORTH);
        coursePanel.setBounds(0,0,760,21);
       
        course.add(setStudentCourse);
        menu.addSeparator();
        course.add(setAttendance);
        menu.addSeparator();
        course.add(setExams);
        
 
       //Menu ActionListener
       regStudent.addActionListener(new regStudent());
       regFaculty.addActionListener(new regFaculty());
       infoDetStudent.addActionListener(new detStudent());
       infoAttStudent.addActionListener(new attStudent());
       infoPayStudent.addActionListener(new payStudent());
       upStudent.addActionListener(new updateStudent());
       upFaculty.addActionListener(new updateFaculty());
       setStudentCourse.addActionListener(new studentCourse());
       setAttendance.addActionListener(new updateAttendace());
       setExams.addActionListener( new ExamResult());
       about.addActionListener(new aboutWindow());
       detFaculty.addActionListener(new detFaculty());
       
    }
    
    public void studentDetails(Student st){
           JTextArea infoDet = new JTextArea();
           infoDet.setText(st.detailsStudent());
           dataCourse.add(infoDet);
    }
    //WINDOW VIEW DETAILS
    public void windowView(){
   
      
        
    //SEPARATOR
        split.setDividerLocation(715);
        split.setEnabled(false);
        split.setLeftComponent(scrollLeft);
        split.setRightComponent(scrollRight);
        add(split); 
        panelLeft.add(coursePanel); //MENU BAR WITH STUDENT NAME
        
    //PANEL LEFT FOR COURSES AND ATTENDANCE! 
    panelLeft.setLayout(null);
    panelLeft.setBackground(Color.decode("#54567a"));
    
        
   //PANEL RIGHT FOR NOTIFICATIONS AND CALENDAR !
   Stack<String> pq02 = new Stack<>();
   JPanel arrayPanel[] = new JPanel[5];
   GridLayout layout = new GridLayout((arrayPanel.length + 1),2,7,7);
   panelRight.setLayout(layout);
   panelRight.setBackground(Color.decode("#54567a")); 
       panelRight.add(calendar);
        JLabel label;
        JPanel notif;
        JButton button;
        int count = 0;
            for(int i =0; i<arrayPanel.length;i++){
                count = count + 1;
                String conv = "Notification n" + count;
                pq02.push(conv);
                
                
                label = new JLabel((String) pq02.peek());
                notif = new JPanel();
                notif.add(label);
                notif.setBackground(Color.decode("#e9e9ee"));
                notif.setBorder(BorderFactory.createMatteBorder(15, 3, 3, 3, Color.decode("#212348")));
                pq02.peek();
                    while(!pq02.isEmpty()){
                        String remove = (String) pq02.pop();                    
                    }
                
                arrayPanel[i] = notif;
                panelRight.add(arrayPanel[i]);
            }
  
    }
    
    public void menuIcon (){
    
       menu.setIcon(new ImageIcon(getClass().getResource("/Images/application_form.png")));
       info.setIcon(new ImageIcon(getClass().getResource("/Images/folder.png")));
       course.setIcon(new ImageIcon(getClass().getResource("/Images/book_addresses.png")));
       about.setIcon(new ImageIcon(getClass().getResource("/Images/flag_red.png")));
       register.setIcon(new ImageIcon(getClass().getResource("/Images/group_add.png")));
       update.setIcon(new ImageIcon(getClass().getResource("/Images/group_edit.png")));
       
       Icon studentIcon = new ImageIcon(getClass().getResource("/Images/user_add.png"));
       Icon facultyIcon = new ImageIcon(getClass().getResource("/Images/user_green.png"));
       ImageIcon st = new ImageIcon(getClass().getResource("/Images/user_green.png"));
       infoStudent.setIcon(studentIcon);
       regStudent.setIcon(studentIcon);
       regFaculty.setIcon(facultyIcon);
       infoFaculty.setIcon(facultyIcon);
       
       infoDetStudent.setIcon(new ImageIcon(getClass().getResource("/Images/database_table.png")));
       detFaculty.setIcon(new ImageIcon(getClass().getResource("/Images/database_table.png")));
       infoAttStudent.setIcon(new ImageIcon(getClass().getResource("/Images/page_paste.png")));
       infoPayStudent.setIcon(new ImageIcon(getClass().getResource("/Images/coins.png")));
       setStudentCourse.setIcon(new ImageIcon(getClass().getResource("/Images/page_paste.png")));
       setAttendance.setIcon(new ImageIcon(getClass().getResource("/Images/page_paste.png")));
       setExams.setIcon(new ImageIcon(getClass().getResource("/Images/page_paste.png")));
       menuLabel.setIcon(studentIcon);
       
       upStudent.setIcon(studentIcon);
       upFaculty.setIcon(facultyIcon);
      
  
       
    }
    
    public class regStudent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            RegStudent rs = new RegStudent();
            }
            
        }
    public class regFaculty implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            RegFaculty rs = new RegFaculty();
           
            }
            
        }
    public class detStudent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            InfoStudent infoStud = new InfoStudent();
           
            }
            
        }
    public class attStudent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            InfoStudentAttend info = new InfoStudentAttend();
        }

}
    public class payStudent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            InfoStudentPayment pay = new InfoStudentPayment();
        }

}
    public class aboutWindow implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AboutWindow about = new AboutWindow();
        }

}
    public class updateStudent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            upStudent upst = new upStudent();
        }

}
    public class updateFaculty implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            upFaculty upfc = new upFaculty();
        }

}
    
    public class studentCourse implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            RegStudCourse rsc = new RegStudCourse();
        }
    
    }
    public class updateAttendace implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SetAttendance setAttend = new SetAttendance();
        }
    
    }
    public class ExamResult implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SetExamResult exam = new SetExamResult();
        }
    
    }
    public class detFaculty implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            InfoFaculty faculty = new InfoFaculty();
        }
    
    }
    
    
    
    }
    
  
    
    

