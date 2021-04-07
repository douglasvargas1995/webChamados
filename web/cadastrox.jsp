<%-- 
    Document   : cadastrox
    Created on : Mar 8, 2021, 10:24:57 PM
    Author     : pretto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro X</h1>

        <%
            String nome = request.getParameter("nomePessoa");
        %>
        
        <p>Nome que veio no parâmetro: <%= nome %></p>
    </body>
</html>
