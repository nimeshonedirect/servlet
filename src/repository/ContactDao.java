package repository;
import model.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    Connection conn;

    public ContactDao() throws SQLException, IOException {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL="jdbc:mysql://localhost/Contact";

        //  Database credentials
        String USER = "root";
        String PASS = "password";

        // Set response content type
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Clean-up environment
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public List<Contact> findAll(){
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, contact FROM Contact";
            ResultSet rs = stmt.executeQuery(sql);
            List<Contact> contacts = new ArrayList<Contact>();

            while(rs.next()) {
                Contact contact1 = new Contact();
                //Retrieve by column name
                contact1.setId(rs.getInt("id"));
                contact1.setName(rs.getString("name"));
                contact1.setContact(rs.getString("contact"));

                contacts.add(contact1);
            }


            rs.close();
            stmt.close();
            return contacts;
        }catch(Exception e){
            e.printStackTrace();
        }

    return null;
    }
    public int insert(String name2,String contact){
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql="INSERT INTO contact(name,contact) VALUES ("+"\""+name2+"\",\""+contact+"\")";
            int rs = stmt.executeUpdate(sql);
            return rs;
        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;

    }
}
