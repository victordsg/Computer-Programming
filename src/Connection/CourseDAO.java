
package Connection;

import Details.Course;
import Details.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Amorim
 */
public class CourseDAO {
    
     String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    public List<Course> courseRead(){
    
        List<Course> list = new ArrayList<>();
        
         String sql = "SELECT st.id, st.firstName, st.surname, c.year, c.name,c.totalClasses, "
                + "sy.totalAttendance, sy.attend, sy.examResult, sy.id FROM student AS st "
                + "JOIN studentYear AS sy ON st.id = sy.studentName "
                + "JOIN course AS c ON sy.courseName = c.id"   ;
                
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Course c = new Course();
               c.setId(rs.getString("st.id"));
               c.setStudentFName(rs.getString("st.firstName"));
               c.setStudentSurname(rs.getString("st.surname"));
               c.setYearCollege(rs.getInt("c.year"));
               c.setCourseName(rs.getString("c.name"));
               c.setTotalClasses(rs.getInt("c.totalClasses"));
               c.setTotalAttendance(rs.getDouble("sy.totalAttendance"));
               c.setAttend(rs.getInt("sy.attend"));
               c.setExamResult(rs.getDouble("sy.examResult"));
               c.setIdTable(rs.getInt("sy.id"));
               list.add(c);

           }
        }catch(SQLException err){
            System.out.println();
        }
        
        return list;
    }
    
    public List<Course> nameCourse(){
        
        List<Course> list = new ArrayList<>();
        
         String sql = "SELECT * FROM course";
                
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Course c = new Course();
               c.setId(rs.getString("id"));
               c.setCourseName(rs.getString("name"));
               c.setTotalClasses(rs.getInt("totalClasses"));
               c.setYearCollege(rs.getInt("year"));
               list.add(c);

           }
        }catch(SQLException err){
            System.out.println();
        }
        
        return list;
    
    }
    
    public void regStudentCourse(String student, int course){
    
       String sql = "INSERT INTO studentYear VALUES (default,0,0,0,?,?)";
        
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, student);
            stmt.setInt(2, course);
            
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
    
    }
    
    public void deleteStudentCourse(int id){
    
      String sql = "DELETE FROM studentYear WHERE id = ?";
      Course c = new Course();  
      
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, c.getAttend());
            
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
        
    }
    
    public void setAttend(int attend,double total, int id){
       String sql = "UPDATE studentYear SET attend = ?, totalAttendance = ? WHERE id = ?";
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,attend );
            stmt.setDouble(2, total);
            stmt.setInt(3,id);
            
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
        
    
    }
   
      public void setExam(double exam, int id){
       String sql = "UPDATE studentYear SET examResult = ? WHERE id = ?";
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setDouble(1,exam);
            stmt.setDouble(2,id);
         
            
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
        
    
    }   
      
      public List<Course> studentCourse (){
    
      List<Course> list = new ArrayList<>();
        
      
      
         String sql = "SELECT s.studentName, c.name, s.attend ,s.totalAttendance,s.examResult\n" +
                            "FROM studentyear as s\n" +
                            "join course as c on c.id = s.courseName\n" +
                            "where s.studentName = 'va047'";
                
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Course c = new Course();
               
               c.setId(rs.getString("s.studentName"));
               c.setCourseName(rs.getString("c.name"));
               c.setAttend(rs.getInt("s.attend"));
               c.setTotalAttendance(rs.getDouble("s.totalAttendance"));
               c.setExamResult(rs.getDouble("examResult"));
               
              
               list.add(c);
               

           }
        }catch(SQLException err){
            System.out.println();
        }
        
        return list;
      }
    
    public List<Course> studentDetails (String id){
    
      List<Course> list = new ArrayList<>();
        
      
      
         String sql = "SELECT s.studentName, c.name, s.attend ,s.totalAttendance,s.examResult\n" +
                            "FROM studentyear as s\n" +
                            "join course as c on c.id = s.courseName\n " +
                            "where s.studentName = ? ";
        
                
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           
           stmt.setString(1, id);
           ResultSet rs = stmt.executeQuery();
           
         
        
           
           while(rs.next()){
               Course c = new Course();
               
               c.setId(rs.getString("s.studentName"));
               c.setCourseName(rs.getString("c.name"));
               c.setAttend(rs.getInt("s.attend"));
               c.setTotalAttendance(rs.getDouble("s.totalAttendance"));
               c.setExamResult(rs.getDouble("examResult"));
               list.add(c);
           }
           
            
           
           
        }catch(SQLException err){
            System.out.println();
        }
        
        return list;
      }
      
    
}
