<%-- 
    Document   : relchamados
    Created on : 20/05/2021, 14:17:51
    Author     : Douglas
--%>

<%@page import="dao.ChamadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório Chamados</title>
    </head>
    <body>
        <%
            byte[] bytes = new ChamadoDAO().gerarRelatorio();

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
