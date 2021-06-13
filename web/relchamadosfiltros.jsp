<%-- 
    Document   : relchamadosfiltros
    Created on : 21/05/2021, 13:44:42
    Author     : Douglas
--%>

<%@page import="entidade.Chamado"%>
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
            byte[] bytes = new ChamadoDAO().gerarRelatorioFiltros((Chamado) request.getAttribute("objChamado"));

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
