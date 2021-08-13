
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        CarpoolDatabase.DbCredentialsSingleton.setDbUrl(getServletContext().getInitParameter("dbUrl"));
        CarpoolDatabase.DbCredentialsSingleton.setDbUsername(getServletContext().getInitParameter("dbUsername"));
        CarpoolDatabase.DbCredentialsSingleton.setDbPassword(getServletContext().getInitParameter("dbPassword"));
        System.out.println("Singleton URL: " + getServletContext().getInitParameter("dbUrl"));
        
        %>
        
    </body>
</html>
