<%@ page import="model.Contact" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: nimeshkhandelwal
  Date: 1/10/18
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Contact List</title>
  </head>
  <body>
  <% Contact c = new Contact();
//creating arraylist object of type category class
    ArrayList<Contact> list;
//storing passed value from jsp
    list = (ArrayList<Contact>) request.getAttribute("contactList");

    for(int i = 0; i < list.size(); i++) {

      c = list.get(i);


      out.println(c.getName());

      out.println(c.getContact() );
    }
  %>
  </body>
</html>
