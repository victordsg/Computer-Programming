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
public class FacultyDAO {
    
    String url = "jdbc:mysql://localhost:3306/vgcdatabase";
    
    public List<Faculty> facultyRead(){
        List<Faculty> list = new ArrayList();
        
        
        String sql = "SELECT * FROM faculty";
        
        try{
           Connection con = DriverManager.getConnection(url,"root","admin"); 
           PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Faculty fc = new Faculty();
               fc.setId(rs.getString("id"));
               fc.setfName(rs.getString("firstName"));
               fc.setSurname(rs.getString("surname"));
               fc.setPhone(rs.getInt("phone"));
               fc.setAddress(rs.getString("address"));
               fc.setEmail(rs.getString("email"));
               fc.setPassword(rs.getString("password"));
               list.add(fc);
               
           }
        }catch(SQLException err){
        
        }
        
        return list;
    }
    
    public void facultyUpdate(Faculty f){
        String sql = "UPDATE faculty SET firstName = ? ,surname = ? , phone = ? , address = ? , email = ?, password = ? WHERE id = ? ";
        
        try{
            Connection con = DriverManager.getConnection(url,"root","admin");
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,f.getfName());
            stmt.setString(2,f.getSurname());
            stmt.setLong(3, f.getPhone());
            stmt.setString(4,f.getAddress());
            stmt.setString(5,f.getEmail());
            stmt.setString(6,f.getPassword());
            stmt.setString(7,f.getId());
            
            stmt.executeUpdate();
            
        }catch(SQLException err){
            System.out.println(err.getMessage());   
        }
    }
    
     public void facultyDelete(Faculty st){
        String sql = "DELETE FROM faculty WHERE id = ?";
        
        try{
        Connection con = DriverManager.getConnection(url,"root","admin");
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1,st.getId());
        
        stmt.executeUpdate();
        
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
            
      
    
}
