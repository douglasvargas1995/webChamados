<%-- 
    Document   : index
    Created on : Mar 8, 2021, 9:46:02 PM
    Author     : pretto
--%>

<%@page import="apoio.Formatacao"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="ISO8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <% response.sendRedirect("login.jsp"); %>
        
        <h1>Sistema - WebChamados</h1>

        <ul>
            <li><a href='categoria.jsp'>Categorias</a></li>
            <li>Pessoas</li>
            <li>Item</li>
        </ul>
    </body>
</html>
