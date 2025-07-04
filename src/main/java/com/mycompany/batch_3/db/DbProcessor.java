package com.mycompany.batch_3.db;

import com.mycompany.batch_3.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Awng Awng
 */

public class DbProcessor {
    private String dbUrl;
    private String user;
    private String pwd;
    
    /*
    *Configruation my Database
    */
    public DbProcessor() {
        dbUrl = "jdbc:mysql://localhost:3306/batch3";
        user = "root";
        pwd = "awngawng12543@@";
    }
    
    /*
    *DB connection with MySQL.
    */
    private Connection getDbConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, user, pwd);
        }catch (ClassNotFoundException ex) {
//          System.Logger.getLogger(DbProcessor.class.getName()).
//          log(System.Logger.Level.SEVERE, null, ex);
            ex.printStackTrace();
        }catch (SQLException ex) {
//          System.Logger.getLogger(DbProcessor.class.getName());
//          log(Level.SEVERE, null, org.);
            ex.printStackTrace();
        }
        
        return con;
    }
    
    /*
    *INSERT student data. =>(CREATE)
    */
    public void save(Student std) {
        Connection dbConnection = null;
        
        try {
            dbConnection = getDbConnection();
            String editSQL = "INSERT INTO student (name, age, gender, address) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(editSQL);
            
            preparedStatement.setString(1, std.getName());
            preparedStatement.setInt(2, std.getAge());
            preparedStatement.setString(3, Character.toString(std.getGender()));
            preparedStatement.setString(4, std.getAddress());
            preparedStatement.executeUpdate();
            
        }catch (SQLException ex) {
            //Logger.getLogger(DbProcessor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            }catch (SQLException ex) {
                //Logger.getLogger(DbProcessor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }
    
    
    /*
    *UPDATE student datas. =>(UPDATE)
    */
    public void update(Student std) {
        Connection dbConnection = null;
        try{
            dbConnection = getDbConnection();
            String insertSQL = "UPDATE student set name=?, age=?, gender=?, address=? where id=?";
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL);
            
            preparedStatement.setString(1, std.getName());
            preparedStatement.setInt(2, std.getAge());
            preparedStatement.setString(3, Character.toString(std.getGender()));
            preparedStatement.setString(4, std.getAddress());
            preparedStatement.setInt(5, std.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /*
    *DELETE student datas. =>(DELETE)
    */
    public void delete(int id) {
        Connection dbConnection = null;
        try{
            dbConnection = getDbConnection();
            String deleteSQL = "delete from student where id=?";
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * 
     * VIEW All student. (READ)
     * This is extract all students by 
    */
    public Student[] getAllStudents(int rows) {
        Student[] stdArr = new Student[rows];
        
        Connection dbConnection = null;
        try {
            dbConnection = getDbConnection();
            String query = "select * from student limit "+ rows;
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int i = 0;
            while(rs.next()) {
                Student std = new Student();
                std.setId(rs.getInt("id"));
                std.setName(rs.getString("name"));
                std.setAge(rs.getInt("age"));
                std.setGender(rs.getString("gender").charAt(0));
                std.setAddress(rs.getString("address"));
                stdArr[i++] = std;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }       
        return stdArr;     
    }
}
