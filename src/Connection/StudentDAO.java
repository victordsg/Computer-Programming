/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Details.Faculty;
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
public class StudentDAO {
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    public List<Student> studentRead(){
    
        List<Student> list = new ArrayList<>();
        
        
        String sql = "SELECT * FROM student";
        
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Student st = new Student();
               st.setId(rs.getString("id"));
               st.setfName(rs.getString("firstName"));
               st.setSurname(rs.getString("surname"));
               st.setPhone(rs.getInt("phone"));
               st.setAddress(rs.getString("address"));
               st.setEmail(rs.getString("email"));
               st.setPassword(rs.getString("password"));
               st.setTotalAttendance(rs.getDouble("totalAttendance"));
               list.add(st);
               
           }
        }catch(SQLException err){
        
        }
        
        return list;
    }
    
    public void studentUpdate(Student st){
        String sql = "UPDATE student SET firstName = ? ,surname = ? , phone = ? , address = ? , email = ?, password = ? WHERE id = ? ";
        
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,st.getfName());
            stmt.setString(2,st.getSurname());
            stmt.setLong(3, st.getPhone());
            stmt.setString(4,st.getAddress());
            stmt.setString(5,st.getEmail());
            stmt.setString(6,st.getPassword());
            stmt.setString(7,st.getId());
            
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
    }
    
    public void studentDelete(Student st){
        String sql = "DELETE FROM student WHERE id = ?";
        
        try{
        Connection con = DriverManager.getConnection(url,"root","admin");
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1,st.getId());
        
        stmt.executeUpdate();
        
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public void studentPayment(String pay, String info, String id){
        
      
      String sql = "INSERT INTO payment VALUES (default,?,?,?)";
        
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, pay);
            stmt.setString(2, info);
            stmt.setString(3, id);
            
            System.out.println(pay + " " + info + " " + id);
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
    }
    
    public void studentAttend(double att, String id){
    
         String sql = "UPDATE student SET totalAttendance = ? WHERE id = ?";
        
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setDouble(1, att);
            stmt.setString(2, id);
            

            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
        
    }
    
    public void attend(String id){
    
    String sql = "SELECT totalAttendance FROM student WHERE = ?";
        
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            
            stmt.setString(1, id);
            

            stmt.executeQuery();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
        
    }
    
     public List<Student> studentPayDet(){
    
        List<Student> list = new ArrayList<>();
        
        
        String sql = "select s.id, s.firstName, s.surname, p.payment, p.info from student as s\n" +
"left join payment as p on s.id = p.stuDet\n" +
"group by s.id";
        
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Student st = new Student();
               st.setId(rs.getString("s.id"));
               st.setfName(rs.getString("s.firstName"));
               st.setSurname(rs.getString("s.surname"));
               st.setPayment(rs.getString("p.payment"));
               st.setInfo(rs.getString("info"));
               list.add(st);
               
           }
        }catch(SQLException err){
        
        }
        
        return list;
    }
    
   
    
}
