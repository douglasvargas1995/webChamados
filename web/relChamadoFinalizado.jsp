<%-- 
    Document   : relChamadoFinalizado
    Created on : 16/06/2021, 11:37:10
    Author     : Douglas
--%>

<%@page import="entidade.Chamado"%>
<%@page import="dao.ChamadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  Chamado chamado = (Chamado) request.getAttribute("objChamado");
            
            byte[] bytes = new ChamadoDAO().gerarRelatorioFinalizado(chamado.getId());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
