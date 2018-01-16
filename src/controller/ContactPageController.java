package controller;

import model.Contact;
import repository.ContactDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.List;

@WebServlet(name = "ContactPageController",urlPatterns = "/newcontact")
public class ContactPageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Using GET Method to Read Form Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        out.println(docType +                 "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "+
                name + "\n" +
                "  <li><b>Last Name</b>: "
                + contact + "\n" +
                "</ul>\n" +
                "</body> </html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         String JDBC_DRIVER = "com.mysql.jdbc.Driver";
         String DB_URL="jdbc:mysql://localhost/Contact";

        //  Database credentials
         String USER = "root";
         String PASS = "password";

        // Set response content type
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String title = "Database Result";
//
//        String docType =
//                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//        out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor = \"#f0f0f0\">\n" +
//                "<h1 align = \"center\">" + title + "</h1>\n");
        try {
            ContactDao dao = new ContactDao();
            List<Contact> contacts = dao.findAll();
            request.setAttribute("contactList",contacts);
            request.getRequestDispatcher("views/index.jsp").forward(request,response);
//            for(Contact c:contacts){
//                out.println("ID: " + c.getId() + "<br>");
//                out.println(" Name: " + c.getName() + "<br>");
//                out.println(" Contact: " + c.getContact()
//                        + "<br>");
//            }
//            out.println("</body></html>");

            // Register JDBC driver
//            Class.forName(JDBC_DRIVER);
//
//            // Open a connection
//            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println(conn);
//
//            // Execute SQL query
//            Statement stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT id, name, contact FROM Contact";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String contact = rs.getString("contact");
//
//                //Display values
//                out.println("ID: " + id + "<br>");
//                out.println(" Name: " + name + "<br>");
//                out.println(" Contact: " + contact
//                        + "<br>");
//            }
//            out.println("</body></html>");
//
//            // Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
