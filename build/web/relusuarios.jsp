<%-- 
    Document   : relsuarios
    Created on : 04/05/2021, 20:43:11
    Author     : Douglas
--%>

<%@page import="dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório de Usuários</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            byte[] bytes = new LoginDAO().gerarRelatorio();

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
